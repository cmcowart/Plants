package com.example.demo.model;

import org.immutables.value.Value;

@Value.Immutable
public interface Plant extends PlantFields {
    @Value
    int getId();
}
