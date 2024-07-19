package com.example.back_end.service;

import com.example.back_end.dto.TravelerDto;
import com.example.back_end.exception.allreadyexists.AllReadyExists;
import com.example.back_end.exception.deletefailed.DeleteFailed;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.Traveler;
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
public class TravelerService {
    @Autowired
    private TravelerRepo travelerRepo;

    @Autowired
    private ModelMapper modelMapper;


    public  TravelerDto getTravelerByID(String travelerId){
        Traveler traveler= travelerRepo.findById(travelerId)
                .orElseThrow(() -> new NotFound());
        return modelMapper.map(traveler, TravelerDto.class);
    }

    public TravelerDto addTraveler(TravelerDto travelerDto) {

        if (travelerRepo.findById(travelerDto.getId()).isPresent()) {
            throw new AllReadyExists(travelerDto.getId());
        }

        Traveler savedTraveler;
        try {
            savedTraveler = travelerRepo.save(modelMapper.map(travelerDto, Traveler.class));
        }
        catch (Exception e) {
            throw new SavedFailed();
        }
        return modelMapper.map(savedTraveler, TravelerDto.class);
    }

    @Transactional
    public TravelerDto deleteTraveler(String travelerId) {
        try{
            Traveler traveler = travelerRepo.findById(travelerId)
                    .orElseThrow(() -> new NotFound());


            travelerRepo.delete(traveler);

            return modelMapper.map(traveler, TravelerDto.class);
        }
        catch(Exception e){
            System.out.println(e);
            throw new DeleteFailed();
        }

    }
}
