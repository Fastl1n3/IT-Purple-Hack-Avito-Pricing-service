package ru.itpurplehack.avito.decepichupachapaticon.Leontev.services;

import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.Leontev.IdType;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatrixService {
    private final List<Integer> discountMatrices = new ArrayList<>();
    public int getDiscountMatrixCount(){
        return discountMatrices.size();
    }
    public boolean findInDiscountMatrix(int nodeId, int i, IdType treeType){
        int matrixId = discountMatrices.get(i);
        return true;
    }




    public void saveHeight(int height, int nodeId, int i, IdType treeType){
        int matrixId = discountMatrices.get(i);
    }
    public void changeHeight(int height, int nodeId, int matrixId, IdType treeType){
        //если уже есть в таблице, то обновляет
    }
    public void deleteHeight(int nodeId, int matrixId, IdType treeType){

    }
}
