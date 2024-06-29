package com.example.demo.dao.impl;

import com.example.demo.dao.PlantTypeDao;
import com.example.demo.model.ImmutablePlantType;
import com.example.demo.model.PlantType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class PlantTypeDaoImpl extends JdbcDaoSupport implements PlantTypeDao {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<PlantType> getAllPlantTypes() {
        final String sql = "SELECT id, name, added_at FROM PLANT_TYPES";
        return getJdbcTemplate().query(sql, (rs, rowNum) ->
                ImmutablePlantType.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .addedAt(rs.getLong("added_at"))
                        .build());
    }

    @Override
    public int createPlantType(String name) {
        final String sql = "INSERT INTO PLANT_TYPES(name, added_at) VALUES (?,?)";
        return getJdbcTemplate().update(sql, name, System.currentTimeMillis());
    }
}
