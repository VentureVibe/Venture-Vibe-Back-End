/*package com.example.back_end.controller;

import com.example.back_end.dto.ServiceProviderDTO;
import com.example.back_end.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/serviceProvider")
@CrossOrigin("http://localhost:5173")
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;

    @PostMapping("/add-event-planner")
    public ResponseEntity<ServiceProviderDTO> addEventPlanner(@RequestBody ServiceProviderDTO dto) {
        ServiceProviderDTO savedDTO = serviceProviderService.addEventPlanner(dto);
        return ResponseEntity.ok(savedDTO);
    }

    @PostMapping("/add-travel-guide")
    public ResponseEntity<ServiceProviderDTO> addTravelGuide(@RequestBody ServiceProviderDTO dto) {
        ServiceProviderDTO savedDTO = serviceProviderService.addTravelGuide(dto);
        return ResponseEntity.ok(savedDTO);
    }

    @PostMapping("/add-service-provider")
    public ResponseEntity<ServiceProviderDTO> addServiceProvider(@RequestBody ServiceProviderDTO dto) {
        ServiceProviderDTO savedDTO = serviceProviderService.addServiceProvider(dto);
        return ResponseEntity.ok(savedDTO);
    }

    @GetMapping("/travel-guides")
    public ResponseEntity<List<ServiceProviderDTO>> getAllTravelGuides() {
        List<ServiceProviderDTO> travelGuides = serviceProviderService.getAllTravelGuides();
        return ResponseEntity.ok(travelGuides);
    }

    @PutMapping("/update-event-planner")
    public ResponseEntity<Void> updateEventPlanner(@RequestBody ServiceProviderDTO dto) {
        serviceProviderService.updateEventPlanner(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-travel-guide")
    public ResponseEntity<Void> updateTravelGuide(@RequestBody ServiceProviderDTO dto) {
        serviceProviderService.updateTravelGuide(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceProvider(@PathVariable String id) {
        serviceProviderService.deleteServiceProvider(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/event-planner/{id}")
    public ServiceProviderDTO getServiceProvider(@PathVariable String id) {
        return serviceProviderService.getServiceProvider(id);
    }

    // Get TravelGuide by ID
    @GetMapping("/travel-guide/{id}")
    public ServiceProviderDTO getTravelGuide(@PathVariable String id) {
        return serviceProviderService.getTravelGuide(id);
    }



    @DeleteMapping("/delete-travel-guide/{id}")
    public ResponseEntity<Void> deleteTravelGuide(@PathVariable String id) {

        serviceProviderService.deleteTravelGuide(id);
        return ResponseEntity.ok().build();
    }

}*/

// src/main/java/com/example/back_end/controller/ServiceProviderController.java
package com.example.back_end.controller;

import com.example.back_end.dto.ServiceProviderDTO;
import com.example.back_end.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/serviceProvider")
@CrossOrigin("http://localhost:5173")
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;

    @PostMapping("/add-event-planner")
    public ResponseEntity<ServiceProviderDTO> addEventPlanner(@RequestBody ServiceProviderDTO dto) {
        ServiceProviderDTO savedDTO = serviceProviderService.addEventPlanner(dto);
        return ResponseEntity.ok(savedDTO);
    }

    @PostMapping("/add-travel-guide")
    public ResponseEntity<ServiceProviderDTO> addTravelGuide(@RequestBody ServiceProviderDTO dto) {
        ServiceProviderDTO savedDTO = serviceProviderService.addTravelGuide(dto);
        return ResponseEntity.ok(savedDTO);
    }

    @PostMapping("/add-service-provider")
    public ResponseEntity<ServiceProviderDTO> addServiceProvider(@RequestBody ServiceProviderDTO dto) {
        ServiceProviderDTO savedDTO = serviceProviderService.addServiceProvider(dto);
        return ResponseEntity.ok(savedDTO);
    }

    @GetMapping("/travel-guides")
    public ResponseEntity<List<ServiceProviderDTO>> getAllTravelGuides() {
        List<ServiceProviderDTO> travelGuides = serviceProviderService.getAllTravelGuides();
        return ResponseEntity.ok(travelGuides);
    }

    @PutMapping("/update-event-planner")
    public ResponseEntity<Void> updateEventPlanner(@RequestBody ServiceProviderDTO dto) {
        serviceProviderService.updateEventPlanner(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-travel-guide")
    public ResponseEntity<Void> updateTravelGuide(@RequestBody ServiceProviderDTO dto) {
        serviceProviderService.updateTravelGuide(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceProvider(@PathVariable String id) {
        serviceProviderService.deleteServiceProvider(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/event-planner/{id}")
    public ServiceProviderDTO getServiceProvider(@PathVariable String id) {
        return serviceProviderService.getServiceProvider(id);
    }

    @GetMapping("/travel-guide/{id}")
    public ServiceProviderDTO getTravelGuide(@PathVariable String id) {
        return serviceProviderService.getTravelGuide(id);
    }

    @DeleteMapping("/delete-travel-guide/{id}")
    public ResponseEntity<Void> deleteTravelGuide(@PathVariable String id) {
        serviceProviderService.deleteTravelGuide(id);
        return ResponseEntity.ok().build();
    }
}
