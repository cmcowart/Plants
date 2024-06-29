package com.example.demo.model;

import org.immutables.value.Value;

@Value.Immutable
public interface PlantActionType {
    int getId();
    String getName();
}
