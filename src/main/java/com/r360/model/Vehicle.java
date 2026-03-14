package com.r360.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private String regNumber;
    private String color;
    private VehicleType type;
}