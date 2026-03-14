package com.r360.controller;

import com.r360.model.ParkingFloor;
import com.r360.model.Vehicle;
import com.r360.strategy.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService service;


    @PostMapping("/init")
    public String init(@RequestParam int floors, @RequestParam int spots) {
        service.createLot(floors, spots);
        return "Parking Lot Created with " + floors + " floors.";
    }

    @PostMapping("/getSpots")
    public List<ParkingFloor> getLot() {
       return service.getLot();
    }

    @PostMapping("/park")
    public String park(@RequestBody Vehicle vehicle) {
        return service.park(vehicle);
    }

    @PostMapping("/unpark")
    public String unpark(@RequestParam String ticket) {
        return service.unpark(ticket);
    }
}
