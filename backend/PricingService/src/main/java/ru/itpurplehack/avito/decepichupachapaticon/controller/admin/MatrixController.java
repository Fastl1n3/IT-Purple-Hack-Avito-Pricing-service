package ru.itpurplehack.avito.decepichupachapaticon.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itpurplehack.avito.decepichupachapaticon.algo.AlgorithmModule;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixDAO;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixInfoDAO;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.MatrixInfo;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.PricePair;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.AbstractAdminResponse;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.FailedCreateRecordResponse;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.Matrices;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.SuccessCreateRecordResponse;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class MatrixController {

    private final MatrixInfoDAO matrixInfoDAO;

    private final MatrixDAO matrixDAO;
    private final AlgorithmModule algorithmModule;

    @Autowired
    public MatrixController(MatrixInfoDAO matrixInfoDAO, MatrixDAO matrixDAO, AlgorithmModule algorithmModule) {
        this.matrixInfoDAO = matrixInfoDAO;
        this.matrixDAO = matrixDAO;
        this.algorithmModule = algorithmModule;
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
    public ResponseEntity<AbstractAdminResponse> createRecordInDiscount(@PathVariable("m_id") int id, @RequestBody PricePair pricePair) {
        Optional<MatrixInfo> matrixInfo = matrixInfoDAO.findDiscountMatrixById(id);
        if (matrixInfo.isEmpty()) {
            return ResponseEntity.status(402).body(new FailedCreateRecordResponse(402, "Not found this matrix: " + id));
        }
        matrixDAO.save(matrixInfo.get().getMatrixName(), pricePair);
        algorithmModule.addPair(pricePair.getMicrocategoryId(), pricePair.getLocationId(), id);
        return ResponseEntity.ok(new SuccessCreateRecordResponse(id));
    }

    @PostMapping("/baseline_matrices/{m_id}")
    public ResponseEntity<AbstractAdminResponse> createRecordInBaseline(@PathVariable("m_id") int id, @RequestBody PricePair pricePair) {
        Optional<MatrixInfo> matrixInfo = matrixInfoDAO.findBaselineMatrixById(id);
        if (matrixInfo.isEmpty()) {
            return ResponseEntity.status(402).body(new FailedCreateRecordResponse(402, "Not found this matrix: " + id));
        }
        matrixDAO.save(matrixInfo.get().getMatrixName(), pricePair);

        return ResponseEntity.ok(new SuccessCreateRecordResponse(id));
    }

    @PatchMapping("/discount_matrices/{m_id}")
    public ResponseEntity<AbstractAdminResponse> updateRecordInDiscount(@PathVariable("m_id") int id, @RequestBody PricePair pricePair) {
        Optional<MatrixInfo> matrixInfo = matrixInfoDAO.findDiscountMatrixById(id);
        if (matrixInfo.isEmpty()) {
            return ResponseEntity.status(402).body(new FailedCreateRecordResponse(402, "Not found this matrix: " + id));
        }
        matrixDAO.update(matrixInfo.get().getMatrixName(), pricePair);
        return ResponseEntity.ok(new SuccessCreateRecordResponse(id));
    }

    @PatchMapping("/baseline_matrices/{m_id}")
    public ResponseEntity<AbstractAdminResponse> updateRecordInBaseline(@PathVariable("m_id") int id, @RequestBody PricePair pricePair) {
        Optional<MatrixInfo> matrixInfo = matrixInfoDAO.findBaselineMatrixById(id);
        if (matrixInfo.isEmpty()) {
            return ResponseEntity.status(402).body(new FailedCreateRecordResponse(402, "Not found this matrix: " + id));
        }
        matrixDAO.update(matrixInfo.get().getMatrixName(), pricePair);
        return ResponseEntity.ok(new SuccessCreateRecordResponse(id));
    }

    @DeleteMapping("/discount_matrices/{m_id}")
    public ResponseEntity<AbstractAdminResponse> deleteRecordInDiscount(@PathVariable("m_id") int id, @RequestBody PricePair pricePair) {
        Optional<MatrixInfo> matrixInfo = matrixInfoDAO.findDiscountMatrixById(id);
        if (matrixInfo.isEmpty()) {
            return ResponseEntity.status(402).body(new FailedCreateRecordResponse(402, "Not found this matrix: " + id));
        }
        matrixDAO.delete(matrixInfo.get().getMatrixName(), pricePair);
        algorithmModule.deletePair(pricePair.getMicrocategoryId(), pricePair.getLocationId(), id);
        return ResponseEntity.ok(new SuccessCreateRecordResponse(id));
    }

    @DeleteMapping("/baseline_matrices/{m_id}")
    public ResponseEntity<AbstractAdminResponse> deleteRecordInBaseline(@PathVariable("m_id") int id, @RequestBody PricePair pricePair) {
        Optional<MatrixInfo> matrixInfo = matrixInfoDAO.findBaselineMatrixById(id);
        if (matrixInfo.isEmpty()) {
            return ResponseEntity.status(402).body(new FailedCreateRecordResponse(402, "Not found this matrix: " + id));
        }
        matrixDAO.delete(matrixInfo.get().getMatrixName(), pricePair);
        return ResponseEntity.ok(new SuccessCreateRecordResponse(id));
    }
}
