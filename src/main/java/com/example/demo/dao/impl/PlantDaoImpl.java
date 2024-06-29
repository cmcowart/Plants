package com.example.demo.dao.impl;

import com.example.demo.dao.PlantDao;
import com.example.demo.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PlantDaoImpl extends JdbcDaoSupport implements PlantDao {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<Plant> getAllPlants() {
        final String sql = "SELECT id, plant_type_id, plant_location_id, name FROM PLANTS";
        return getJdbcTemplate().query(sql, (rs, rowNum) ->
                ImmutablePlant.builder()
                        .id(rs.getInt("id"))
                        .plantTypeId(rs.getInt("plant_type_id"))
                        .plantLocationId(rs.getInt("plant_location_id"))
                        .name(rs.getString("name"))
                        .build());
    }

    public int createPlant(ImmutablePlantEgg egg) {
        final String sql = "INSERT INTO PLANTS(plant_type_id, plant_location_id, name) VALUES (?,?,?)";
        return getJdbcTemplate().update(sql, egg.getPlantTypeId(), egg.getPlantLocationId(), egg.getName());
    }

    @Override
    public List<PlantActionType> getAllPlantActionTypes() {
        final String sql = "SELECT id, description FROM PLANT_ACTION_TYPES";
        return getJdbcTemplate().query(sql, (rs, rowNum) ->
                ImmutablePlantActionType.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("description"))
                        .build());
    }

    @Override
    public List<PlantLocation> getAllPlantLocations() {
        final String sql = "SELECT id, description FROM PLANT_LOCATIONS";
        return getJdbcTemplate().query(sql, (rs, rowNum) ->
                ImmutablePlantLocation.builder()
                        .id(rs.getInt("id"))
                        .description(rs.getString("description"))
                        .build());
    }
}
