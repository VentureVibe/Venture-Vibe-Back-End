package com.example.back_end.service;

import com.example.back_end.dto.ServiceProviderDTO;
import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.Experience;
import com.example.back_end.model.ServiceProvider;
import com.example.back_end.model.TravelGuide;
import com.example.back_end.model.TravelPlan;
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
        ServiceProvider serviceProvider = modelMapper.map(dto, ServiceProvider.class);
        serviceProviderRepository.save(serviceProvider);
    }

    public void updateTravelGuide(ServiceProviderDTO dto) {
        try {
            // Map DTO to TravelGuide entity
            TravelGuide travelGuide = modelMapper.map(dto, TravelGuide.class);

            // Save the TravelGuide entity
            travelGuide = travelGuideRepository.save(travelGuide);

            // If experiences are not null, associate them with the TravelGuide
            if (travelGuide.getExperiences() != null) {
                for (Experience experience : travelGuide.getExperiences()) {
                    experience.setTravelGuide(travelGuide);
                }
                // Save experiences in a separate transaction or update within the same transaction
                // Assuming the repository handles cascading
                travelGuideRepository.save(travelGuide);
            }

        } catch (Exception e) {

            throw new RuntimeException("Error updating travel guide", e); // Rethrow or handle the exception
        }
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






}
