package ru.itpurplehack.avito.decepichupachapaticon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.MatrixInfo;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.PricePair;

import java.util.List;
import java.util.Optional;

@Component
public class MatrixInfoDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MatrixInfoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MatrixInfo> findAllDiscountMatrices() {
        return jdbcTemplate.query("SELECT * FROM DMatrices_info",
                new BeanPropertyRowMapper<>(MatrixInfo.class));
    }

    public List<MatrixInfo> findAllBaselineMatrices() {
        return jdbcTemplate.query("SELECT * FROM BMatrices_info",
                new BeanPropertyRowMapper<>(MatrixInfo.class));
    }

    public Optional<MatrixInfo> findDiscountMatrixById(int id) {
        return jdbcTemplate.query("SELECT * FROM DMatrices_info WHERE matrix_id=?",
                new BeanPropertyRowMapper<>(MatrixInfo.class), id).stream().findAny();
    }

    public Optional<MatrixInfo> findBaselineMatrixById(int id) {
        return jdbcTemplate.query("SELECT * FROM DMatrices_info WHERE matrix_id=?",
                new BeanPropertyRowMapper<>(MatrixInfo.class), id).stream().findAny();
    }

}
