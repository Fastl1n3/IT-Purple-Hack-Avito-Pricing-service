package ru.itpurplehack.avito.decepichupachapaticon.Leontev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.Leontev.Entities.AuxiliaryPair;
import ru.itpurplehack.avito.decepichupachapaticon.Leontev.services.AuxiliaryTableService;
import ru.itpurplehack.avito.decepichupachapaticon.Leontev.services.MatrixService;
import ru.itpurplehack.avito.decepichupachapaticon.Leontev.services.TreeTableService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class AlgorithmModule {
    private final AuxiliaryTableService auxiliaryTableService;
    private final MatrixService matrixService;
    private final TreeTableService treeTableService;


    @Autowired
    public AlgorithmModule(AuxiliaryTableService auxiliaryTableService, MatrixService matrixService, TreeTableService treeTableService) {
        this.auxiliaryTableService = auxiliaryTableService;
        this.matrixService = matrixService;
        this.treeTableService = treeTableService;
    }

    public void fillAuxiliaryTable() {
        int[] heights = new int[matrixService.getDiscountMatrixCount()];

        int categoryId = treeTableService.getRootId(IdType.CATEGORY);
        Arrays.fill(heights, -1);
        dfs(heights, categoryId, IdType.CATEGORY);

        int locationId = treeTableService.getRootId(IdType.LOCATION);
        Arrays.fill(heights, -1);
        dfs(heights, locationId, IdType.LOCATION);
    }

    private void dfs(int[] heights, int nodeId, IdType treeType) {
        for (int i = 0; i < heights.length; i++) {
            if (matrixService.findInDiscountMatrix(nodeId, i, treeType)) {
                heights[i] = 0;
                matrixService.saveHeight(0, nodeId, i, treeType);
            } else {
                if (heights[i] != -1) {
                    heights[i]++;
                    matrixService.saveHeight(heights[i], nodeId, i, treeType);
                }
            }
        }
        List<Integer> children = treeTableService.getNodeChildren(nodeId, treeType);
        if (children != null) {
            for (int childNode : children) {
                int[] copyHeights = new int[heights.length];
                System.arraycopy(heights, 0, copyHeights, 0, heights.length);
                dfs(copyHeights, childNode, treeType);
            }
        }
    }

    public void addPair(int categoryId, int locationId, int matrixId) {
        matrixService.addPair(categoryId, locationId, matrixId);

        matrixService.changeHeight(0, categoryId, matrixId, IdType.CATEGORY);
        changeHeightsWithAdding(categoryId, 0, matrixId, IdType.CATEGORY);

        matrixService.changeHeight(0, locationId, matrixId, IdType.LOCATION);
        changeHeightsWithAdding(categoryId, 0, matrixId, IdType.LOCATION);
    }

    private void changeHeightsWithAdding(int nodeId, int height, int matrixId, IdType treeType) {
        height++;
        List<Integer> children = treeTableService.getNodeChildren(nodeId, treeType);
        if (children != null) {
            for (int childNode : children) {
                Optional<AuxiliaryPair> auxiliaryPair = matrixService.getHeight(childNode, matrixId, treeType);
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
            Optional<AuxiliaryPair> auxiliaryPair = matrixService.getHeight(parentNode.get(), matrixId, treeType);
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
                Optional<AuxiliaryPair> auxiliaryPair = matrixService.getHeight(childNode, matrixId, treeType);
                if (auxiliaryPair.isPresent()) {
                    if (auxiliaryPair.get().getHeight() == 0) {
                        continue;
                    }
                }
                changeHeightsWithAdding(childNode, height, matrixId, treeType);
            }
        }
    }
}
