package com.example.demo.model;

import org.immutables.value.Value;

@Value.Immutable
public interface PlantType {
    int getId();
    String getName();
    long getAddedAt();
}
