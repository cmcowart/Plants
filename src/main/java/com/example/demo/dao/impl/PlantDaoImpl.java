package com.example.demo.dao.impl;

import com.example.demo.constants.SqlQueries;
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
    public int getMaxPlantId() {
        return getJdbcTemplate().queryForObject(
                SqlQueries.GET_MAX_PLANT_ID,
                Integer.class);
    }

    @Override
    public List<Plant> getAllPlants() {
        return getJdbcTemplate().query(SqlQueries.GET_ALL_PLANTS, (rs, rowNum) ->
                ImmutablePlant.builder()
                        .id(rs.getInt("id"))
                        .plantTypeId(rs.getInt("plant_type_id"))
                        .plantLocationId(rs.getInt("plant_location_id"))
                        .name(rs.getString("name"))
                        .isActive(rs.getInt("is_active") == 1)
                        .build());
    }

    public List<HydratedPlant> getCurrentPlantReport() {
        return getJdbcTemplate().query(SqlQueries.GET_PLANT_REPORT, (rs, rowNum) ->
                ImmutableHydratedPlant.builder()
                        .id(rs.getInt("id"))
                        .plantTypeId(rs.getInt("plant_type_id"))
                        .plantLocationId(rs.getInt("plant_location_id"))
                        .name(rs.getString("name"))
                        .isActive(rs.getInt("is_active") == 1)
                        .lastWateredAt(rs.getLong("watered_at"))
                        .lastFertilizedAt(rs.getLong("fed_at"))
                        .build());
    }

    public int createPlant(ImmutablePlantEgg egg) {
        return getJdbcTemplate().update(
                SqlQueries.CREATE_PLANT,
                egg.getPlantTypeId(),
                egg.getPlantLocationId(),
                egg.getName());
    }

    @Override
    public List<PlantActionType> getAllPlantActionTypes() {
        return getJdbcTemplate().query(SqlQueries.GET_PLANT_ACTION_TYPES, (rs, rowNum) ->
                ImmutablePlantActionType.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("description"))
                        .build());
    }

    @Override
    public List<PlantLocation> getAllPlantLocations() {
        return getJdbcTemplate().query(SqlQueries.GET_ALL_PLANT_LOCATIONS, (rs, rowNum) ->
                ImmutablePlantLocation.builder()
                        .id(rs.getInt("id"))
                        .description(rs.getString("description"))
                        .build());
    }
}
