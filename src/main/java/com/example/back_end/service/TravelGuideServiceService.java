package com.example.back_end.service;

import com.example.back_end.dto.TravelGuideServiceDTO;
import com.example.back_end.dto.TravelerDto;
import com.example.back_end.exception.deletefailed.DeleteFailed;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.TravelGuide;
import com.example.back_end.model.TravelGuideServices;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.TravelGuideServiceRepo;
import com.example.back_end.repository.TravelerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TravelGuideServiceService {

    @Autowired
    TravelGuideServiceRepo travelGuideServiceRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    TravelerRepo travelerRepo;

    public TravelGuideServiceDTO addTravelGuideService(TravelGuideServiceDTO travelGuideServiceDTO) {




        TravelGuideServices savedTraveler;

        try {

            savedTraveler = travelGuideServiceRepo.save(modelMapper.map(travelGuideServiceDTO, TravelGuideServices.class));
        }
        catch (Exception e) {
            throw new SavedFailed();
        }
        return modelMapper.map(savedTraveler, TravelGuideServiceDTO.class);
    }

    @Transactional
    public TravelGuideServiceDTO deleteTravelGuideService(Long travelerId) {
        try{
            TravelGuideServices traveler = travelGuideServiceRepo.findById(travelerId)
                    .orElseThrow(() -> new NotFound());


            travelGuideServiceRepo.delete(traveler);

            return modelMapper.map(traveler, TravelGuideServiceDTO.class);
        }
        catch(Exception e){
            throw new DeleteFailed();
        }
    }
}
