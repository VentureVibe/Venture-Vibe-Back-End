/*package com.example.back_end.service;

import com.example.back_end.dto.ServiceProviderDTO;
import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.Experience;
import com.example.back_end.model.ServiceProvider;
import com.example.back_end.model.TravelGuide;
import com.example.back_end.repository.ServiceProviderRepository;
import com.example.back_end.repository.TravelGuideRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceProviderService {
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private TravelGuideRepository travelGuideRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ServiceProviderDTO addEventPlanner(ServiceProviderDTO dto) {
        ServiceProvider serviceProvider = modelMapper.map(dto, ServiceProvider.class);
        serviceProviderRepository.save(serviceProvider);
        return modelMapper.map(serviceProvider, ServiceProviderDTO.class);
    }

    public ServiceProviderDTO addTravelGuide(ServiceProviderDTO dto) {
    // Map DTO to entity
    TravelGuide travelGuide = modelMapper.map(dto, TravelGuide.class);

    // Save the TravelGuide first to get its ID
    travelGuide = travelGuideRepository.save(travelGuide);

    // Now that we have the ID, set it for each Experience
    if (travelGuide.getExperiences() != null) {
        for (Experience experience : travelGuide.getExperiences()) {
            experience.setTravelGuide(travelGuide);
        }
        // Save again to update the experiences
        travelGuide = travelGuideRepository.save(travelGuide);
    }

    // Map back to DTO and return
    return modelMapper.map(travelGuide, ServiceProviderDTO.class);
}

    public List<ServiceProviderDTO> getAllTravelGuides() {
        return travelGuideRepository.findAll().stream()
                .map(travelGuide -> modelMapper.map(travelGuide, ServiceProviderDTO.class))
                .collect(Collectors.toList());
    }

    public void updateEventPlanner(ServiceProviderDTO dto) {
    ServiceProvider existingServiceProvider = serviceProviderRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("ServiceProvider not found with id: " + dto.getId()));

    // Update only the provided fields
    if (dto.getRole() != null) existingServiceProvider.setRole(dto.getRole());
    if (dto.getPurchaseDate() != null) existingServiceProvider.setPurchaseDate(dto.getPurchaseDate());
    if (dto.getExpirationDate() != null) existingServiceProvider.setExpirationDate(dto.getExpirationDate());
    if (dto.getPlanType() != null) existingServiceProvider.setPlanType(dto.getPlanType());
    if (dto.getContactNumber() != null) existingServiceProvider.setContactNumber(dto.getContactNumber());
    if (dto.getEmail() != null) existingServiceProvider.setEmail(dto.getEmail());
    if (dto.getSp_lat() != null) existingServiceProvider.setSp_lat(dto.getSp_lat());
    if (dto.getSp_lng() != null) existingServiceProvider.setSp_lng(dto.getSp_lng());

    serviceProviderRepository.save(existingServiceProvider);
}

public void updateTravelGuide(ServiceProviderDTO dto) {
    TravelGuide existingTravelGuide = travelGuideRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("TravelGuide not found with id: " + dto.getId()));

    // Update only the provided fields
    if (dto.getRole() != null) existingTravelGuide.setRole(dto.getRole());
    if (dto.getPurchaseDate() != null) existingTravelGuide.setPurchaseDate(dto.getPurchaseDate());
    if (dto.getExpirationDate() != null) existingTravelGuide.setExpirationDate(dto.getExpirationDate());
    if (dto.getPlanType() != null) existingTravelGuide.setPlanType(dto.getPlanType());
    if (dto.getContactNumber() != null) existingTravelGuide.setContactNumber(dto.getContactNumber());
    if (dto.getEmail() != null) existingTravelGuide.setEmail(dto.getEmail());
    if (dto.getSp_lat() != null) existingTravelGuide.setSp_lat(dto.getSp_lat());
    if (dto.getSp_lng() != null) existingTravelGuide.setSp_lng(dto.getSp_lng());
    if (dto.getRadius() != null) existingTravelGuide.setRadius(dto.getRadius());

    // If experiences are provided, update them
    if (dto.getExperiences() != null) {
        existingTravelGuide.getExperiences().clear();
        existingTravelGuide.getExperiences().addAll(dto.getExperiences().stream()
                .map(expDto -> modelMapper.map(expDto, Experience.class))
                .collect(Collectors.toList()));
        for (Experience experience : existingTravelGuide.getExperiences()) {
            experience.setTravelGuide(existingTravelGuide);
        }
    }

    travelGuideRepository.save(existingTravelGuide);
}


    public void deleteServiceProvider(String id) {
        serviceProviderRepository.deleteById(id);
    }

    public void deleteTravelGuide(String id) {

        travelGuideRepository.deleteById(id);
    }

    public ServiceProviderDTO getTravelGuide(String id) {
        TravelGuide travelGuide = travelGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TravelGuide not found with id: " + id));
        return modelMapper.map(travelGuide, ServiceProviderDTO.class);
    }

    public ServiceProviderDTO getServiceProvider(String id) {
        ServiceProvider serviceProvider = serviceProviderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceProvider not found with id: " + id));
        return modelMapper.map(serviceProvider, ServiceProviderDTO.class);
    }

    public ServiceProviderDTO addServiceProvider(ServiceProviderDTO dto) {
    // Get the old service provider using the ID from the DTO
    ServiceProvider oldServiceProvider = serviceProviderRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("ServiceProvider not found with id: " + dto.getId()));

    // Delete the old service provider
    serviceProviderRepository.delete(oldServiceProvider);

    // Call addTravelGuide function to add the new travel guide
    return addTravelGuide(dto);
}

}*/

// src/main/java/com/example/back_end/service/ServiceProviderService.java
package com.example.back_end.service;

import com.example.back_end.dto.ServiceProviderDTO;
import com.example.back_end.model.Experience;
import com.example.back_end.model.ServiceProvider;
import com.example.back_end.model.TravelGuide;
import com.example.back_end.repository.ServiceProviderRepository;
import com.example.back_end.repository.TravelGuideRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceProviderService {
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private TravelGuideRepository travelGuideRepository;

    @Autowired
    private ModelMapper modelMapper;
  
  public List<ServiceProviderDTO> getAllEventPlanners() {
        // Logic to fetch all event planners from the database
        return serviceProviderRepository.findAll()
                .stream()
                .map(eventPlanner -> modelMapper.map(eventPlanner, ServiceProviderDTO.class))
                .collect(Collectors.toList());
    }

    public ServiceProviderDTO addEventPlanner(ServiceProviderDTO dto) {
        ServiceProvider serviceProvider = modelMapper.map(dto, ServiceProvider.class);
        serviceProviderRepository.save(serviceProvider);
        return modelMapper.map(serviceProvider, ServiceProviderDTO.class);
    }

    public ServiceProviderDTO addTravelGuide(ServiceProviderDTO dto) {
        TravelGuide travelGuide = modelMapper.map(dto, TravelGuide.class);
        travelGuide = travelGuideRepository.save(travelGuide);

        if (travelGuide.getExperiences() != null) {
            for (Experience experience : travelGuide.getExperiences()) {
                experience.setTravelGuide(travelGuide);
            }
            travelGuide = travelGuideRepository.save(travelGuide);
        }

        return modelMapper.map(travelGuide, ServiceProviderDTO.class);
    }

    public List<ServiceProviderDTO> getAllTravelGuides() {
        return travelGuideRepository.findAll().stream()
                .map(travelGuide -> modelMapper.map(travelGuide, ServiceProviderDTO.class))
                .collect(Collectors.toList());
    }

    public void updateEventPlanner(ServiceProviderDTO dto) {
        ServiceProvider existingServiceProvider = serviceProviderRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("ServiceProvider not found with id: " + dto.getId()));

        if (dto.getRole() != null) existingServiceProvider.setRole(dto.getRole());
        if (dto.getPurchaseDate() != null) existingServiceProvider.setPurchaseDate(dto.getPurchaseDate());
        if (dto.getExpirationDate() != null) existingServiceProvider.setExpirationDate(dto.getExpirationDate());
        if (dto.getPlanType() != null) existingServiceProvider.setPlanType(dto.getPlanType());
        if (dto.getContactNumber() != null) existingServiceProvider.setContactNumber(dto.getContactNumber());
        if (dto.getEmail() != null) existingServiceProvider.setEmail(dto.getEmail());
        if (dto.getSp_lat() != null) existingServiceProvider.setSp_lat(dto.getSp_lat());
        if (dto.getSp_lng() != null) existingServiceProvider.setSp_lng(dto.getSp_lng());

        serviceProviderRepository.save(existingServiceProvider);
    }

    public void updateTravelGuide(ServiceProviderDTO dto) {
        TravelGuide existingTravelGuide = travelGuideRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("TravelGuide not found with id: " + dto.getId()));

        if (dto.getRole() != null) existingTravelGuide.setRole(dto.getRole());
        if (dto.getPurchaseDate() != null) existingTravelGuide.setPurchaseDate(dto.getPurchaseDate());
        if (dto.getExpirationDate() != null) existingTravelGuide.setExpirationDate(dto.getExpirationDate());
        if (dto.getPlanType() != null) existingTravelGuide.setPlanType(dto.getPlanType());
        if (dto.getContactNumber() != null) existingTravelGuide.setContactNumber(dto.getContactNumber());
        if (dto.getEmail() != null) existingTravelGuide.setEmail(dto.getEmail());
        if (dto.getSp_lat() != null) existingTravelGuide.setSp_lat(dto.getSp_lat());
        if (dto.getSp_lng() != null) existingTravelGuide.setSp_lng(dto.getSp_lng());
        if (dto.getRadius() != null) existingTravelGuide.setRadius(dto.getRadius());
        if (dto.getPrice() != null) existingTravelGuide.setPrice(dto.getPrice());
        if (dto.getSpecialties() != null) existingTravelGuide.setSpecialties(dto.getSpecialties());
        if (dto.getLanguages() != null) existingTravelGuide.setLanguages(dto.getLanguages());

        if (dto.getExperiences() != null) {
            existingTravelGuide.getExperiences().clear();
            existingTravelGuide.getExperiences().addAll(dto.getExperiences().stream()
                    .map(expDto -> modelMapper.map(expDto, Experience.class))
                    .collect(Collectors.toList()));
            for (Experience experience : existingTravelGuide.getExperiences()) {
                experience.setTravelGuide(existingTravelGuide);
            }
        }

        travelGuideRepository.save(existingTravelGuide);
    }

    public void deleteServiceProvider(String id) {
        serviceProviderRepository.deleteById(id);
    }

    public void deleteTravelGuide(String id) {
        travelGuideRepository.deleteById(id);
    }

    public ServiceProviderDTO getTravelGuide(String id) {
        TravelGuide travelGuide = travelGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TravelGuide not found with id: " + id));
        return modelMapper.map(travelGuide, ServiceProviderDTO.class);
    }

    public ServiceProviderDTO getServiceProvider(String id) {
        ServiceProvider serviceProvider = serviceProviderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceProvider not found with id: " + id));
        return modelMapper.map(serviceProvider, ServiceProviderDTO.class);
    }

    public ServiceProviderDTO addServiceProvider(ServiceProviderDTO dto) {
        ServiceProvider oldServiceProvider = serviceProviderRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("ServiceProvider not found with id: " + dto.getId()));

        serviceProviderRepository.delete(oldServiceProvider);

        return addTravelGuide(dto);
    }
}
