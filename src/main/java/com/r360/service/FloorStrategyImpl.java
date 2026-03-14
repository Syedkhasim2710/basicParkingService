package com.r360.service;

import com.r360.model.ParkingFloor;
import com.r360.model.ParkingSpot;
import com.r360.model.VehicleType;
import com.r360.strategy.ParkingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FloorStrategyImpl implements ParkingStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, VehicleType type) {
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = floor.getSpots().stream()
                    .filter(s -> s.isFree() && s.getSupportedType() == type)
                    .findFirst();
            if (spot.isPresent()) return spot.get();
        }
        return null;
    }

    @Override
    public ParkingSpot findSpotId(List<ParkingFloor> floors, String spotId) {
        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpots()) {
                if (spot.getSpotId().equals(spotId)) {
                    if (!spot.isFree()) {
                        spot.unpark();
                        return spot;
                    } else {
                        throw new RuntimeException("Spot is already empty");
                    }
                }
            }
        }
        return null;
    }
}
