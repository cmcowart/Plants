package com.example.demo.model;

import org.immutables.value.Value;

@Value.Immutable
public interface HydratedPlant extends PlantFields, PlantHydrationFields {
    int getId();
    boolean getIsActive();
}
