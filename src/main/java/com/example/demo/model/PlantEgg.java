package com.example.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutablePlantEgg.class)
@JsonDeserialize(as = ImmutablePlantEgg.class)
public interface PlantEgg extends PlantFields, PlantCreationFields {
}
