package com.r360.strategy;

import com.r360.model.ParkingFloor;
import com.r360.model.ParkingSpot;
import com.r360.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
@Service
public class ParkingService {
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final ParkingStrategy strategy;
    private final ReentrantLock lock = new ReentrantLock();

//    public ParkingService(ParkingStrategy strategy) {
//        this.strategy = strategy;
//    }

    public void createLot(int numFloors, int spotsPerFloor) {
        for (int i = 1; i <= numFloors; i++) {
            int i1 = floors.size() + 1;
            floors.add(new ParkingFloor(i1, spotsPerFloor));
        }
    }

    public List<ParkingFloor> getLot() {
        return floors;
    }

    public String park(Vehicle vehicle) {
        lock.lock(); // Critical Section: Ensure no two vehicles get the same spot
        try {
            ParkingSpot spot = strategy.findSpot(floors, vehicle.getType());
            if (spot == null) throw new RuntimeException("No available spot for " + vehicle.getType());
            spot.park(vehicle);
            return "Ticket_Generated_Spot_" + spot.getSpotId();
        } finally {
            lock.unlock();
        }
    }

    public String unpark(String ticket) {
        lock.lock(); // Critical Section: Ensure thread safety
        try {
            // Extract spotId from ticket (format: Ticket_Generated_Spot_{spotId})
            String prefix = "Ticket_Generated_Spot_";
            if (!ticket.startsWith(prefix)) {
                throw new IllegalArgumentException("Invalid ticket format");
            }
            String spotId = ticket.substring(prefix.length());
            ParkingSpot spot = strategy.findSpotId(floors, spotId);
            if (spot==null) {
                throw new RuntimeException("Spot not found for ticket: " + ticket);
            }
            spot.unpark();
            return "Vehicle unparked from Spot_" + spotId;
        } finally {
            lock.unlock();
        }
    }
}