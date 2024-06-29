package com.example.demo.model;

import org.immutables.value.Value;

@Value.Immutable
public interface PlantAction extends PlantActionFields {
    int getId();
    long getActionAt();
}
