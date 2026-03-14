package com.r360.model;

import lombok.Data;

@Data
public class ParkingSpot {
    private final String spotId;
    private final int floorNumber;
    private final VehicleType supportedType;
    private boolean isFree = true;
    private Vehicle parkedVehicle;

    public synchronized void park(Vehicle v) {
        this.parkedVehicle = v;
        this.isFree = false;
    }

    public synchronized void unpark() {
        this.parkedVehicle = null;
        this.isFree = true;
    }
}