package com.example.demo.dao.impl;

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
    public List<PlantAction> getAllPlantActions() {
        final String sql = "SELECT id, plant_id, action_type_id, action_at FROM PLANT_ACTIONS ORDER BY id";
        return getJdbcTemplate().query(sql, (rs, rowNum) ->
                ImmutablePlantAction.builder()
                        .id(rs.getInt("id"))
                        .plantId(rs.getInt("plant_id"))
                        .actionTypeId(rs.getInt("action_type_id"))
                        .actionAt(rs.getLong("action_at"))
                        .build());
    }

    @Override
    public int createPlantAction(ImmutablePlantActionEgg egg) {
        final String sql = "INSERT INTO PLANT_ACTIONS(plant_id, action_type_id, action_at) VALUES (?,?,?)";
        return getJdbcTemplate().update(sql, egg.getPlantId(), egg.getActionTypeId(), System.currentTimeMillis());
    }
}
