package ru.itpurplehack.avito.decepichupachapaticon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.entity.priceMatrix.PricePair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MatrixDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MatrixDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(String tableName, PricePair pricePair) {
        jdbcTemplate.update("INSERT INTO " + tableName + "(microcategory_id, location_id, price) VALUES (?, ?, ?)",
                pricePair.getMicrocategoryId(), pricePair.getLocationId(), pricePair.getPrice());
    }

    public void delete(String tableName, PricePair pricePair) {
        jdbcTemplate.update("DELETE FROM " + tableName + " WHERE microcategory_id=? AND location_id=?",
                pricePair.getMicrocategoryId(), pricePair.getLocationId());
    }

    public void update(String tableName, PricePair pricePair) {
        jdbcTemplate.update("UPDATE " + tableName + " SET price=? WHERE microcategory_id=? AND location_id=?",
                pricePair.getPrice(), pricePair.getMicrocategoryId(), pricePair.getLocationId());
    }

    public boolean containsLocation(String tableName, int locationId) {
        return !jdbcTemplate.query("SELECT DISTINCT location_id FROM " + tableName + " WHERE location_id=?",
                new BeanPropertyRowMapper<>(Integer.class), locationId).isEmpty();
    }

    public boolean containsMicrocategory(String tableName, int categoryId) {
        return !jdbcTemplate.query("SELECT DISTINCT microcategory_id FROM " + tableName + " WHERE microcategory_id=?",
                new BeanPropertyRowMapper<>(Integer.class), categoryId).isEmpty();
    }

    public Optional<PricePair> findByLocationAndMicrocategory(String tableName, int locationId, int microcategoryId) {
        return jdbcTemplate.query("SELECT * FROM " + tableName + " WHERE location_id=? AND microcategory_id=?",
                new BeanPropertyRowMapper<>(PricePair.class), locationId, microcategoryId).stream().findAny();
    }

}
