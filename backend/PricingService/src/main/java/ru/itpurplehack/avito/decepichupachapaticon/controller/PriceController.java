package ru.itpurplehack.avito.decepichupachapaticon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixDAO;
import ru.itpurplehack.avito.decepichupachapaticon.dao.MatrixInfoDAO;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.MatrixInfo;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.PricePair;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.*;

import java.util.Optional;

@RestController
public class PriceController {

    private final MatrixDAO matrixDAO;

    private final MatrixInfoDAO matrixInfoDAO;

    @Autowired
    public PriceController(MatrixDAO matrixDAO, MatrixInfoDAO matrixInfoDAO) {
        this.matrixDAO = matrixDAO;
        this.matrixInfoDAO = matrixInfoDAO;
    }

    @GetMapping("/price/{bm_id}")
    public ResponseEntity<AbstractAdminResponse> getPriceFromBaseline(@PathVariable("bm_id") int id, @RequestParam("lc_id") int locationId, @RequestParam("mc_id") int microcategoryId) {
        Optional<MatrixInfo> matrixInfo = matrixInfoDAO.findBaselineMatrixById(id);
        if (matrixInfo.isEmpty()) {
            return ResponseEntity.status(403).body(new FailedCreateRecordResponse(403, "Not found this matrix: " + id));
        }
        Optional<PricePair> pricePair = matrixDAO.findByLocationAndMicrocategory(matrixInfo.get().getMatrixName(), locationId, microcategoryId);
        int price = 0;
        if (pricePair.isPresent()) {
            price = pricePair.get().getPrice();
        }
        return ResponseEntity.ok(new PriceResponse(price, locationId, microcategoryId, id));
    }

}
