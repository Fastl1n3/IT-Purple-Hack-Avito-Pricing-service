package ru.itpurplehack.avito.decepichupachapaticon.algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixDAO;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.PricePair;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Location;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.LocationJump;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Microcategory;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.MicrocategoryJump;
import ru.itpurplehack.avito.decepichupachapaticon.repository.LocationRepository;
import ru.itpurplehack.avito.decepichupachapaticon.repository.MicrocategoryRepository;
import ru.itpurplehack.avito.decepichupachapaticon.service.LocationService;
import ru.itpurplehack.avito.decepichupachapaticon.service.MicrocategoryService;

import java.util.*;

@Component
public class AlgorithmModule {
    private final MatrixDAO matrixDAO;
    private final LocationService locationService;
    private final MicrocategoryService microcategoryService;
    private final LocationRepository locationRepository;
    private final MicrocategoryRepository microcategoryRepository;

    @Autowired
    public AlgorithmModule(MatrixDAO matrixDAO, LocationService locationService, MicrocategoryService microcategoryService, LocationRepository locationRepository, MicrocategoryRepository microcategoryRepository) {
        this.matrixDAO = matrixDAO;
        this.locationService = locationService;
        this.microcategoryService = microcategoryService;
        this.locationRepository = locationRepository;
        this.microcategoryRepository = microcategoryRepository;
    }


    public void fillJumpTable() {
        int[] heights = new int[matrixDAO.getDiscounts().size()];

        Microcategory microcategory = microcategoryRepository.findRootNode();
        Arrays.fill(heights, -1);
        dfs(heights, microcategory.getId(), IdType.CATEGORY);

        Location location = locationRepository.findLocationByParentLocationIsNull();
        Arrays.fill(heights, -1);
        dfs(heights, location.getId(), IdType.LOCATION);
    }

    private void dfs(int[] heights, int nodeId, IdType treeType) {
        switch (treeType) {
            case CATEGORY -> {
                List<MicrocategoryJump> microcategoryJumps = new ArrayList<>();
                int i = 0;
                for (Map.Entry<Integer, String> ent : matrixDAO.getDiscounts().entrySet()) {
                    if (matrixDAO.containsMicrocategory(ent.getValue(), nodeId)) {
                        heights[i] = 0;
                        microcategoryJumps.add(new MicrocategoryJump(ent.getKey(), 0));
                    } else {
                        if (heights[i] != -1) {
                            heights[i]++;
                            microcategoryJumps.add(new MicrocategoryJump(ent.getKey(), heights[i]));
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
                        dfs(copyHeights, childNode.getId(), treeType);
                    }
                }
            }

            case LOCATION -> {
                List<LocationJump> locationJumps = new ArrayList<>();
                int i = 0;
                for (Map.Entry<Integer, String> ent : matrixDAO.getDiscounts().entrySet()) {
                    if (matrixDAO.containsLocation(ent.getValue(), nodeId)) {
                        heights[i] = 0;
                        locationJumps.add(new LocationJump(ent.getKey(), 0));
                    } else {
                        if (heights[i] != -1) {
                            heights[i]++;
                            locationJumps.add(new LocationJump(ent.getKey(), heights[i]));
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
                        dfs(copyHeights, childNode.getId(), treeType);
                    }
                }
            }
        }
    }

    public void addPair(int categoryId, int locationId, int price, int matrixId) {
        PricePair pricePair = new PricePair(categoryId, locationId, price);
        matrixDAO.save(matrixDAO.getDiscounts().get(matrixId), pricePair);

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

    ////todo Если у данной  пары несколько нод, то не делать пересчет прыжков
    public void deletePair(int categoryId, int locationId, int price, int matrixId) {
        matrixDAO.delete(matrixDAO.getDiscounts().get(matrixId), new PricePair(categoryId, locationId, price));
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

            }
        }
    }

    public void roadUpSearch(int categoryId, int locationId) {
        //List<Integer> discountMatrices = matrixService.getDiscountMatrices();

    }
}
