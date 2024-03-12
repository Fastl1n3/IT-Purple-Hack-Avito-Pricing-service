package ru.itpurplehack.avito.decepichupachapaticon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixDAO;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixInfoDAO;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.MatrixInfo;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.PricePair;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.CreateRecordResponse;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.FailedCreateRecordResponse;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.Matrices;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.SuccessCreateRecordResponse;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class MatrixController {

    private final MatrixInfoDAO matrixInfoDAO;

    private final MatrixDAO matrixDAO;

    @Autowired
    public MatrixController(MatrixInfoDAO matrixInfoDAO, MatrixDAO matrixDAO) {
        this.matrixInfoDAO = matrixInfoDAO;
        this.matrixDAO = matrixDAO;
    }

    @GetMapping("/discount_matrices")
    public Matrices getDiscountMatrices() {
        return new Matrices(matrixInfoDAO.findAllDiscountMatrices(), LocalDateTime.now());
    }

    @GetMapping("/baseline_matrices")
    public Matrices getBaselineMatrices() {
        return new Matrices(matrixInfoDAO.findAllBaselineMatrices(), LocalDateTime.now());
    }

    @PostMapping("/discount_matrices/{m_id}")
    public ResponseEntity<CreateRecordResponse> createRecordInDiscount(@PathVariable("m_id") int id, @RequestBody PricePair pricePair) {
        Optional<MatrixInfo> matrixInfo = matrixInfoDAO.findDiscountMatrixById(id);
        if (matrixInfo.isEmpty()) {
            return ResponseEntity.status(402).body(new FailedCreateRecordResponse(402, "Not found this matrix: " + id));
        }
        matrixDAO.save(matrixInfo.get().getMatrixName(), pricePair);
        return ResponseEntity.ok(new SuccessCreateRecordResponse(id));
    }

    @PostMapping("/baseline_matrices/{m_id}")
    public ResponseEntity<CreateRecordResponse> createRecordInBaseline(@PathVariable("m_id") int id, @RequestBody PricePair pricePair) {
        Optional<MatrixInfo> matrixInfo = matrixInfoDAO.findBaselineMatrixById(id);
        if (matrixInfo.isEmpty()) {
            return ResponseEntity.status(402).body(new FailedCreateRecordResponse(402, "Not found this matrix: " + id));
        }
        matrixDAO.save(matrixInfo.get().getMatrixName(), pricePair);
        return ResponseEntity.ok(new SuccessCreateRecordResponse(id));
    }
}
