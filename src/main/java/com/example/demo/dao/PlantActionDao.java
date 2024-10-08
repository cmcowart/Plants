package com.example.demo.dao;

import com.example.demo.model.ImmutablePlantActionEgg;
import com.example.demo.model.PlantAction;

import java.util.List;

public interface PlantActionDao {
    List<PlantAction> getAllPlantActions();
    List<PlantAction> getActionsForPlant(int plantId);
    int createPlantAction(ImmutablePlantActionEgg egg);
}
