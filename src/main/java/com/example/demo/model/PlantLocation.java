package com.example.demo.model;

import org.immutables.value.Value;

@Value.Immutable
public interface PlantLocation {
    int getId();
    String getDescription();
}
