package ru.itpurplehack.avito.decepichupachapaticon.algo.services;

import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.algo.Entities.AuxiliaryPair;
import ru.itpurplehack.avito.decepichupachapaticon.algo.Entities.Price;
import ru.itpurplehack.avito.decepichupachapaticon.algo.IdType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//dao для всех матриц
@Component
public class MatrixService {
    //discounts
    private final List<Integer> discountMatrices = new ArrayList<>();

    public int getDiscountMatrixCount() {
        return discountMatrices.size();
    }
    public List<Integer> getDiscountMatrices(){
        return discountMatrices;
    }

    public boolean findInDiscountMatrix(int nodeId, int i, IdType treeType) {
        int matrixId = discountMatrices.get(i);
        return true;
    }
    public Optional<Price> getPrice(int categoryId, int locationId, int matrixId){
        return null;
    }
    public void addPair(int categoryId, int locationId, int matrixId) {
    }
    public void deletePair(int categoryId, int locationId, int matrixId) {
    }



    //auxiliaries
    public void saveHeight(int height, int nodeId, int i, IdType treeType) {
        int matrixId = discountMatrices.get(i);
    }

    public Optional<AuxiliaryPair> getHeight(int nodeId, int matrixId) {
        return null;
    }

    public void changeHeight(int height, int nodeId, int matrixId, IdType treeType) {
        //если уже есть в таблице, то обновляет
    }

    public void deleteHeight(int nodeId, int matrixId, IdType treeType) {

    }
}
