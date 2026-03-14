package com.r360.strategy;

import com.r360.model.ParkingFloor;
import com.r360.model.ParkingSpot;
import com.r360.model.VehicleType;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot findSpot(List<ParkingFloor> floors, VehicleType type);


    ParkingSpot findSpotId(List<ParkingFloor> floors, String spotId);
}
