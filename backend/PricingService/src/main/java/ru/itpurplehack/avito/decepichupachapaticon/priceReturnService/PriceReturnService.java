package ru.itpurplehack.avito.decepichupachapaticon.priceReturnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixDAO;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixInfoDAO;
import ru.itpurplehack.avito.decepichupachapaticon.discountSegments.DiscountSegments;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.MatrixInfo;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.PricePair;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Location;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.LocationJump;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Microcategory;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.MicrocategoryJump;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.UserPriceResponse;
import ru.itpurplehack.avito.decepichupachapaticon.repository.LocationRepository;
import ru.itpurplehack.avito.decepichupachapaticon.repository.MicrocategoryRepository;
import ru.itpurplehack.avito.decepichupachapaticon.service.LocationService;
import ru.itpurplehack.avito.decepichupachapaticon.service.MicrocategoryService;

import java.util.*;

@Component
public class PriceReturnService {
    private final MatrixDAO matrixDAO;
    private final MatrixInfoDAO matrixInfoDao;
    private final LocationService locationService;
    private final MicrocategoryService microcategoryService;
    private final LocationRepository locationRepository;
    private final MicrocategoryRepository microcategoryRepository;
    private final DiscountSegments discountSegments;

    @Autowired
    public PriceReturnService(MatrixDAO matrixDAO, MatrixInfoDAO matrixInfoDao, LocationService locationService, MicrocategoryService microcategoryService, LocationRepository locationRepository, MicrocategoryRepository microcategoryRepository, DiscountSegments discountSegments) {
        this.matrixDAO = matrixDAO;
        this.matrixInfoDao = matrixInfoDao;
        this.locationService = locationService;
        this.microcategoryService = microcategoryService;
        this.locationRepository = locationRepository;
        this.microcategoryRepository = microcategoryRepository;
        this.discountSegments = discountSegments;
    }


    public void fillTables() {
        List<MatrixInfo> matrices = matrixInfoDao.findAllDiscountMatrices();
        int[] heights = new int[matrices.size()];

        Microcategory microcategory = microcategoryRepository.findRootNode();
        Arrays.fill(heights, -1);
        dfs(heights, microcategory.getId(), matrices, IdType.CATEGORY);

        Location location = locationRepository.findLocationByParentLocationIsNull();
        Arrays.fill(heights, -1);
        dfs(heights, location.getId(), matrices, IdType.LOCATION);

        matrices = matrixInfoDao.findAllBaselineMatrices();

        Arrays.fill(heights, -1);
        dfs(heights, microcategory.getId(), matrices, IdType.CATEGORY);

        Arrays.fill(heights, -1);
        dfs(heights, location.getId(), matrices, IdType.LOCATION);
    }

    private void dfs(int[] heights, int nodeId, List<MatrixInfo> matrices, IdType treeType) {
        switch (treeType) {
            case CATEGORY -> {
                List<MicrocategoryJump> microcategoryJumps = new ArrayList<>();
                int i = 0;
                for (MatrixInfo matrix : matrices) {
                    if (matrixDAO.containsMicrocategory(matrix.getMatrixName(), nodeId)) {
                        heights[i] = 0;
                        microcategoryJumps.add(new MicrocategoryJump(matrix.getMatrixId(), 0));
                    } else {
                        if (heights[i] != -1) {
                            heights[i]++;
                            microcategoryJumps.add(new MicrocategoryJump(matrix.getMatrixId(), heights[i]));
                        }
                    }
                    i++;
                }
                Microcategory microcategory = microcategoryRepository.findById(nodeId).get();
                microcategory.setMicrocategoryJumps(microcategoryJumps);
                microcategoryRepository.save(microcategory);

                List<Microcategory> childNodes = microcategory.getChildMicrocategories();
                if (childNodes != null) {
                    for (Microcategory childNode : childNodes) {
                        int[] copyHeights = new int[heights.length];
                        System.arraycopy(heights, 0, copyHeights, 0, heights.length);
                        dfs(copyHeights, childNode.getId(), matrices, treeType);
                    }
                }
            }

            case LOCATION -> {
                List<LocationJump> locationJumps = new ArrayList<>();
                int i = 0;
                for (MatrixInfo matrix : matrices) {
                    if (matrixDAO.containsLocation(matrix.getMatrixName(), nodeId)) {
                        heights[i] = 0;
                        locationJumps.add(new LocationJump(matrix.getMatrixId(), 0));
                    } else {
                        if (heights[i] != -1) {
                            heights[i]++;
                            locationJumps.add(new LocationJump(matrix.getMatrixId(), heights[i]));
                        }
                    }
                    i++;
                }
                Location location = locationRepository.findById(nodeId).get();
                location.setLocationJumps(locationJumps);
                locationRepository.save(location);

                List<Location> childNodes = location.getChildLocations();
                if (childNodes != null) {
                    for (Location childNode : childNodes) {
                        int[] copyHeights = new int[heights.length];
                        System.arraycopy(heights, 0, copyHeights, 0, heights.length);
                        dfs(copyHeights, childNode.getId(), matrices, treeType);
                    }
                }
            }
        }
    }

    public void addPair(int categoryId, int locationId, int matrixId) {
        microcategoryService.changeJump(categoryId, matrixId, 0);
        changeHeightsWithAdding(categoryId, 0, matrixId, IdType.CATEGORY);

        locationService.changeJump(locationId, matrixId, 0);
        changeHeightsWithAdding(categoryId, 0, matrixId, IdType.LOCATION);
    }

    private void changeHeightsWithAdding(int nodeId, int height, int matrixId, IdType treeType) {
        switch (treeType) {
            case CATEGORY -> {
                height++;
                List<Microcategory> children = microcategoryRepository.findById(nodeId).get().getChildMicrocategories();
                if (children != null) {
                    for (Microcategory childNode : children) {
                        Optional<MicrocategoryJump> jump = microcategoryService.findJumpByMatrix(childNode, matrixId);
                        if (jump.isPresent()) {
                            if (jump.get().getDistanceToNearest() == 0) {
                                continue;
                            } else {
                                microcategoryService.changeJump(childNode.getId(), matrixId, height);
                            }
                        } else {
                            microcategoryService.changeJump(childNode.getId(), matrixId, height);
                        }
                        changeHeightsWithAdding(childNode.getId(), height, matrixId, treeType);
                    }
                }
            }
            case LOCATION -> {
                height++;
                List<Location> children = locationRepository.findById(nodeId).get().getChildLocations();
                if (children != null) {
                    for (Location childNode : children) {
                        Optional<LocationJump> jump = locationService.findJumpByMatrix(childNode, matrixId);
                        if (jump.isPresent()) {
                            if (jump.get().getDistanceToNearest() == 0) {
                                continue;
                            } else {
                                locationService.changeJump(childNode.getId(), matrixId, height);
                            }
                        } else {
                            locationService.changeJump(childNode.getId(), matrixId, height);
                        }
                        changeHeightsWithAdding(childNode.getId(), height, matrixId, treeType);
                    }
                }
            }
        }
    }

    public void deletePair(int categoryId, int locationId, int matrixId) {
        int height = 0;

        height = findNewHeight(categoryId, matrixId, IdType.CATEGORY);
        changeHeightsWithRemoving(categoryId, height, matrixId, IdType.CATEGORY);

        height = findNewHeight(locationId, matrixId, IdType.LOCATION);
        changeHeightsWithRemoving(locationId, height, matrixId, IdType.LOCATION);
    }

    private int findNewHeight(int nodeId, int matrixId, IdType treeType) {
        switch (treeType) {
            case CATEGORY -> {
                Microcategory microcategory = microcategoryRepository.findById(nodeId).get();
                Microcategory parentMicrocategory = microcategory.getParentMicrocategory();
                if (parentMicrocategory != null) {
                    Optional<MicrocategoryJump> jump = microcategoryService.findJumpByMatrix(parentMicrocategory, matrixId);
                    return jump.map(j -> j.getDistanceToNearest() + 1).orElse(-1);
                } else {
                    return -1;
                }
            }
            case LOCATION -> {
                Location location = locationRepository.findById(nodeId).get();
                Location parentLocation = location.getParentLocation();
                if (parentLocation != null) {
                    Optional<LocationJump> jump = locationService.findJumpByMatrix(parentLocation, matrixId);
                    return jump.map(j -> j.getDistanceToNearest() + 1).orElse(-1);
                } else {
                    return -1;
                }
            }
            default -> {
                return 0;
            }
        }
    }

    private void changeHeightsWithRemoving(int nodeId, int height, int matrixId, IdType treeType) {
        switch (treeType) {
            case CATEGORY -> {
                if (height == -1) {
                    microcategoryService.deleteJump(nodeId, matrixId);
                } else {
                    microcategoryService.changeJump(nodeId, matrixId, height);
                    height++;
                }
                Microcategory microcategory = microcategoryRepository.findById(nodeId).get();
                List<Microcategory> childNodes = microcategory.getChildMicrocategories();
                if (childNodes != null) {
                    for (Microcategory childNode : childNodes) {
                        Optional<MicrocategoryJump> jump = microcategoryService.findJumpByMatrix(microcategory, matrixId);
                        if (jump.isPresent()) {
                            if (jump.get().getDistanceToNearest() == 0) {
                                continue;
                            }
                        }
                        changeHeightsWithRemoving(childNode.getId(), height, matrixId, treeType);
                    }
                }
            }
            case LOCATION -> {
                if (height == -1) {
                    locationService.deleteJump(nodeId, matrixId);
                } else {
                    locationService.changeJump(nodeId, matrixId, height);
                    height++;
                }
                Location location = locationRepository.findById(nodeId).get();
                List<Location> childNodes = location.getChildLocations();
                if (childNodes != null) {
                    for (Location childNode : childNodes) {
                        Optional<LocationJump> jump = locationService.findJumpByMatrix(location, matrixId);
                        if (jump.isPresent()) {
                            if (jump.get().getDistanceToNearest() == 0) {
                                continue;
                            }
                        }
                        changeHeightsWithRemoving(childNode.getId(), height, matrixId, treeType);
                    }
                }
            }
        }
    }

    public UserPriceResponse searchInMatrix(int categoryId, int locationId, int userId) {
        List<MatrixInfo> discountMatrices = discountSegments.findDiscountMatricesForUser(userId);
        UserPriceResponse userPriceResponse = null;
        for (MatrixInfo matrix : discountMatrices) {
            userPriceResponse = searchLoop(categoryId, locationId, matrix);
            if (userPriceResponse != null) {
                break;
            }
        }
        if (userPriceResponse == null) {
            MatrixInfo baselineMatrix = matrixInfoDao.findBaselineMatrixById(matrixInfoDao.getActiveBaselineMatrixId()).get();
            userPriceResponse = searchLoop(categoryId, locationId, baselineMatrix);
        }
        return userPriceResponse;
    }

    private UserPriceResponse searchLoop(int categoryId, int locationId, MatrixInfo matrix) {
        Microcategory microcategory = microcategoryRepository.findById(categoryId).get();
        Location location = locationRepository.findById(locationId).get();
        boolean findFlag = true;
        while (true) {
            if (findFlag) {
                Optional<PricePair> pricePair = matrixDAO.findByLocationAndMicrocategory(matrix.getMatrixName(), location.getId(), microcategory.getId());
                if (pricePair.isPresent()) {
                    return new UserPriceResponse(pricePair.get().getPrice(), locationId, categoryId, matrix.getMatrixId(), matrix.getSegment());
                }
            }
            findFlag = true;
            Optional<MicrocategoryJump> categoryJump = microcategoryService.findJumpByMatrix(microcategory, matrix.getMatrixId());
            if (categoryJump.isPresent()) {
                if (categoryJump.get().getDistanceToNearest() == 0) {
                    microcategory = microcategory.getParentMicrocategory();
                    if (microcategory != null) {
                        categoryJump = microcategoryService.findJumpByMatrix(microcategory, matrix.getMatrixId());
                        if (categoryJump.isPresent()) {
                            for (int i = 0; i < categoryJump.get().getDistanceToNearest(); i++) {
                                microcategory = microcategory.getParentMicrocategory();
                            }
                        } else {
                            findFlag = false;
                        }
                    } else {
                        findFlag = false;
                    }
                } else {
                    for (int i = 0; i < categoryJump.get().getDistanceToNearest(); i++) {
                        microcategory = microcategory.getParentMicrocategory();
                    }
                }
            } else {
                microcategory = microcategoryRepository.findById(categoryId).get();
                Optional<LocationJump> locationJump = locationService.findJumpByMatrix(location, matrix.getMatrixId());
                if (locationJump.isPresent()) {
                    if (locationJump.get().getDistanceToNearest() == 0) {
                        location = location.getParentLocation();
                        if (location != null) {
                            locationJump = locationService.findJumpByMatrix(location, matrix.getMatrixId());
                            if (locationJump.isPresent()) {
                                for (int i = 0; i < locationJump.get().getDistanceToNearest(); i++) {
                                    location = location.getParentLocation();
                                }
                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    } else {
                        for (int i = 0; i < locationJump.get().getDistanceToNearest(); i++) {
                            location = location.getParentLocation();
                        }
                    }
                } else {
                    return null;
                }
            }
        }
    }
}

