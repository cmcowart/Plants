package com.example.demo;

import com.example.demo.dao.PlantActionDao;
import com.example.demo.dao.PlantDao;
import com.example.demo.dao.PlantTypeDao;
import com.example.demo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DemoApplication {
	Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	PlantDao plantDao;
	@Autowired
	PlantActionDao plantActionDao;
	@Autowired
	PlantTypeDao plantTypeDao;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/plants")
	public List<Plant> getAllPlants() {
		return plantDao.getAllPlants();
	}

	//curl -X POST http://localhost:8080/plants/create  -H "Content-Type: application/json" -d '{"name":"test","plantTypeId":1,"plantLocationId":1}'
	@PostMapping("/plants/create")
	public int createPlant(@RequestBody ImmutablePlantEgg egg) {
		LOG.info(
				String.format("Creating new plant (name: %s, type: %d, location: %d)",
						egg.getName(),
						egg.getPlantTypeId(),
						egg.getPlantLocationId()));
		return plantDao.createPlant(egg);
	}

	@GetMapping("/plant-action-types")
	public List<PlantActionType> getAllActionTypes() {
		return plantDao.getAllPlantActionTypes();
	}

	@GetMapping("/plant-locations")
	public List<PlantLocation> getAllLocations() {
		return plantDao.getAllPlantLocations();
	}

	@GetMapping("/plant-types")
	public List<PlantType> getAllPlantTypes() {
		return plantTypeDao.getAllPlantTypes();
	}

	//curl -X POST http://localhost:8080/plant-types/create  -H "Content-Type: application/json" -d 'Fern'
	@PostMapping("/plant-types/create")
	public int createPlantType(@RequestBody String name) {
		LOG.info(String.format("Creating a plant type for name %s", name));
		//todo check for existing type with name
		return plantTypeDao.createPlantType(name);
	}

	@GetMapping("/plant-actions")
	public List<PlantAction> getAllPlantActions() {
		return plantActionDao.getAllPlantActions();
	}

	//curl -X POST http://localhost:8080/plant-actions/create  -H "Content-Type: application/json" -d '{"plantId":1,"actionTypeId":1}'
	@PostMapping(value = "/plant-actions/create", consumes = {"application/json"} )
	public int createPlantAction(@RequestBody ImmutablePlantActionEgg egg) {
		LOG.info(
				String.format("Creating plant action with values: %d - %d",
						egg.getPlantId(),
						egg.getActionTypeId()));
		return plantActionDao.createPlantAction(egg);
	}
}
