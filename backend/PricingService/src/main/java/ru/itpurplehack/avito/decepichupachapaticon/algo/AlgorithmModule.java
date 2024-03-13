package ru.itpurplehack.avito.decepichupachapaticon.algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.algo.Entities.AuxiliaryPair;
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


    public void fillAuxiliaryTable() {
        int[] heights = new int[matrixDAO.getDiscounts().size()];

        int categoryId = treeTableService.getRootId(IdType.CATEGORY);
        Arrays.fill(heights, -1);
        dfs(heights, categoryId, IdType.CATEGORY);

        int locationId = treeTableService.getRootId(IdType.LOCATION);
        Arrays.fill(heights, -1);
        dfs(heights, locationId, IdType.LOCATION);
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

        microcategoryService.changeJumps(categoryId, matrixId, 0);
//        matrixService.changeHeight(0, categoryId, matrixId, IdType.CATEGORY);
        changeHeightsWithAdding(categoryId, 0, matrixId, IdType.CATEGORY);

        locationService.changeJumps(locationId, matrixId,0);
       // matrixService.changeHeight(0, locationId, matrixId, IdType.LOCATION);
        changeHeightsWithAdding(categoryId, 0, matrixId, IdType.LOCATION);
    }

    private void changeHeightsWithAdding(int nodeId, int height, int matrixId, IdType treeType) {
        switch(treeType) {
            case CATEGORY -> {
                height++;
                List<Microcategory> children = microcategoryRepository.findById(nodeId).get().getChildMicrocategories();
                if (children != null) {
                    for (Microcategory childNode : children) {
                        Optional<AuxiliaryPair> auxiliaryPair = matrixService.getHeight(childNode, matrixId);
                        if (auxiliaryPair.isPresent()) {
                            if (auxiliaryPair.get().getHeight() == 0) {
                                continue;
                            } else {
                                matrixService.changeHeight(height, childNode, matrixId, treeType);
                            }
                        } else {
                            matrixService.changeHeight(height, childNode, matrixId, treeType);
                        }
                        changeHeightsWithAdding(childNode, height, matrixId, treeType);
                    }
                }
            }
            case LOCATION -> {

            }
        }
    }

    ////todo Если у данной  пары несколько нод, то не делать пересчет прыжков
    public void deletePair(int categoryId, int locationId, int matrixId) {
        matrixService.deletePair(categoryId, locationId, matrixId);
        int height = 0;

        height = findNewHeight(categoryId, matrixId, IdType.CATEGORY);
        changeHeightsWithRemoving(categoryId, height, matrixId, IdType.CATEGORY);

        height = findNewHeight(locationId, matrixId, IdType.LOCATION);
        changeHeightsWithRemoving(locationId, height, matrixId, IdType.LOCATION);
    }

    private int findNewHeight(int nodeId, int matrixId, IdType treeType) {
        Optional<Integer> parentNode = treeTableService.getParent(nodeId, treeType);
        if (parentNode.isPresent()) {
            Optional<AuxiliaryPair> auxiliaryPair = matrixService.getHeight(parentNode.get(), matrixId);
            return auxiliaryPair.map(pair -> pair.getHeight() + 1).orElse(-1);
        } else {
            return -1;
        }
    }

    private void changeHeightsWithRemoving(int nodeId, int height, int matrixId, IdType treeType) {
        if (height == -1) {
            matrixService.deleteHeight(nodeId, matrixId, treeType);
        } else {
            matrixService.changeHeight(height, nodeId, matrixId, treeType);
            height++;
        }
        List<Integer> children = treeTableService.getNodeChildren(nodeId, treeType);
        if (children != null) {
            for (int childNode : children) {
                Optional<AuxiliaryPair> auxiliaryPair = matrixService.getHeight(childNode, matrixId);
                if (auxiliaryPair.isPresent()) {
                    if (auxiliaryPair.get().getHeight() == 0) {
                        continue;
                    }
                }
                changeHeightsWithRemoving(childNode, height, matrixId, treeType);
            }
        }
    }

    public void roadUpSearch(int categoryId, int locationId) {
        List<Integer> discountMatrices = matrixService.getDiscountMatrices();

    }
}
