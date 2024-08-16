package com.example.demo.dao;

import com.example.demo.model.*;

import java.util.List;

public interface PlantDao {
    int getMaxPlantId();
    List<Plant> getAllPlants();
    List<HydratedPlant> getCurrentPlantReport();
    int createPlant(ImmutablePlantEgg egg);
    List<PlantActionType> getAllPlantActionTypes();
    List<PlantLocation> getAllPlantLocations();
}
