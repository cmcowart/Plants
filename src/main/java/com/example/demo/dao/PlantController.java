package com.example.demo.dao;

import com.example.demo.DemoApplication;
import com.example.demo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlantController {
    Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    PlantDao plantDao;
    @Autowired
    PlantActionDao plantActionDao;

    public List<Plant> getAllPlants() {
        return plantDao.getAllPlants();
    }

    public List<HydratedPlant> getAllActiveHydratedPlants() {
        return plantDao.getCurrentPlantReport();
    }

    public int createPlant(ImmutablePlantEgg egg) {
        LOG.info("Creating new plant (name: {}, type: {}, location: {}, action: {})",
                egg.getName(),
                egg.getPlantTypeId(),
                egg.getPlantLocationId(),
                egg.getCreationActionType());

        plantDao.createPlant(egg);

        //TODO: FIgure out the correct JDBC way to return ID on create
        int newPlantId = plantDao.getMaxPlantId();
        LOG.info("LOOKS LIKE plant {} was just created -- adding a creation action of type {} for it as well",
                newPlantId,
                egg.getCreationActionType());

        plantActionDao.createPlantAction(
                ImmutablePlantActionEgg.builder()
                        .plantId(newPlantId)
                        .actionTypeId(egg.getCreationActionType())
                        .build());

        return newPlantId;
    }
}
