package com.example.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutablePlantActionEgg.class)
@JsonDeserialize(as = ImmutablePlantActionEgg.class)
public interface PlantActionEgg extends PlantActionFields{
}
