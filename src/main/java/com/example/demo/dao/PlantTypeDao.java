package com.example.demo.dao;

import com.example.demo.model.PlantType;

import java.util.List;

public interface PlantTypeDao {
    List<PlantType> getAllPlantTypes();
    int createPlantType(String name);
}
