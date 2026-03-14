package com.r360.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingFloor {
    private final int floorNum;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNum, int numSpots) {
        this.floorNum = floorNum;
        this.spots = new ArrayList<>();
        for (int i = 1; i <= numSpots; i++) {
            // Sample logic: 1st spot is TRUCK, next 3 are BIKE, rest are CAR
            VehicleType type = (i == 1) ? VehicleType.TRUCK : (i <= 4) ? VehicleType.BIKE : VehicleType.CAR;
            spots.add(new ParkingSpot("F" + floorNum + "-S" + i, floorNum, type));
        }
    }
}
