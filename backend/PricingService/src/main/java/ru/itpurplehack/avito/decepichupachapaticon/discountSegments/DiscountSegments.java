package ru.itpurplehack.avito.decepichupachapaticon.discountSegments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixDAO;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixInfoDAO;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.MatrixInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DiscountSegments {
    private final MatrixInfoDAO matrixInfoDAO;
    Map<Integer, int[]> segments;

    @Autowired
    public DiscountSegments(MatrixInfoDAO matrixInfoDAO) {
        this.matrixInfoDAO = matrixInfoDAO;
    }

    public List<MatrixInfo> findDiscountMatricesForUser(int userId) {
        List<MatrixInfo> discountMatrices = matrixInfoDAO.findAllDiscountMatrices();
        List<MatrixInfo> matricesForUser = new ArrayList<>();
        for (MatrixInfo matrix : discountMatrices) {
            int[] users = segments.get(matrix.getSegment());
            for (int i = 0; i < users.length; i++) {
                if (users[i] == userId) {
                    matricesForUser.add(matrix);
                    break;
                }
            }
        }
        return matricesForUser;
    }
}
