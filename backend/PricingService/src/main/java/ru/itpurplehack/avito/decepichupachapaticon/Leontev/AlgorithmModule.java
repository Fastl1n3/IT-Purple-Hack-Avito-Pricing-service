package ru.itpurplehack.avito.decepichupachapaticon.Leontev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.Leontev.services.AuxiliaryTableService;
import ru.itpurplehack.avito.decepichupachapaticon.Leontev.services.MatrixService;
import ru.itpurplehack.avito.decepichupachapaticon.Leontev.services.TreeTableService;

import java.util.Arrays;
import java.util.List;

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
        if (children!=null) {
            for (int childNode : children) {
                int[] copyHeights = new int[heights.length];
                System.arraycopy(heights, 0, copyHeights, 0, heights.length);
                dfs(copyHeights, childNode, treeType);
            }
        }
    }

    public void addPair(int categoryId, int locationId, int matrixId) {
        matrixService.changeHeight(0, categoryId,matrixId,IdType.CATEGORY);
        changeHeights(categoryId, 1,  IdType.CATEGORY);

        matrixService.changeHeight(0, locationId,matrixId,IdType.LOCATION);
        changeHeights(categoryId, 1,  IdType.LOCATION);
    }
    public void deletePair(int categoryId, int locationId, int matrixId) {
        matrixService.deleteHeight(categoryId, matrixId, IdType.CATEGORY);

        matrixService.deleteHeight(locationId, matrixId, IdType.LOCATION);
    }

    private void changeHeights(int nodeId, int height, IdType treeType){
        List<Integer> children = treeTableService.getNodeChildren(nodeId, treeType);
        if (children!=null) {
            for (int childNode : children) {
                int[] copyHeights = new int[heights.length];
                System.arraycopy(heights, 0, copyHeights, 0, heights.length);
                dfs(copyHeights, childNode, treeType);
            }
        }
    }
}
