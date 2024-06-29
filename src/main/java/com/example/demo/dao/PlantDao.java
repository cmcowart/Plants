package com.example.demo.dao;

import com.example.demo.model.*;

import java.util.List;

public interface PlantDao {
    List<Plant> getAllPlants();
    int createPlant(ImmutablePlantEgg egg);
    List<PlantActionType> getAllPlantActionTypes();
    List<PlantLocation> getAllPlantLocations();
}
