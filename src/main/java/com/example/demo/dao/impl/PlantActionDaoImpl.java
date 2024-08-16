package com.example.demo.dao.impl;

import com.example.demo.constants.SqlQueries;
import com.example.demo.dao.PlantActionDao;
import com.example.demo.model.ImmutablePlantAction;
import com.example.demo.model.ImmutablePlantActionEgg;
import com.example.demo.model.PlantAction;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PlantActionDaoImpl extends JdbcDaoSupport implements PlantActionDao {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<PlantAction> getActionsForPlant(int plantId) {
        return getJdbcTemplate().query(
                SqlQueries.GET_ACTIONS_FOR_PLANT,
                (rs, rowNum) ->
                    ImmutablePlantAction.builder()
                            .id(rs.getInt("id"))
                            .plantId(rs.getInt("plant_id"))
                            .actionTypeId(rs.getInt("action_type_id"))
                            .actionAt(rs.getLong("action_at"))
                            .build(),
                plantId);
    }
    @Override
    public List<PlantAction> getAllPlantActions() {
        return getJdbcTemplate().query(SqlQueries.GET_ALL_PLANT_ACTIONS, (rs, rowNum) ->
                ImmutablePlantAction.builder()
                        .id(rs.getInt("id"))
                        .plantId(rs.getInt("plant_id"))
                        .actionTypeId(rs.getInt("action_type_id"))
                        .actionAt(rs.getLong("action_at"))
                        .build());
    }

    @Override
    public int createPlantAction(ImmutablePlantActionEgg egg) {
        return getJdbcTemplate().update(
                SqlQueries.CREATE_PLANT_ACTION,
                egg.getPlantId(),
                egg.getActionTypeId(),
                System.currentTimeMillis());
    }
}
