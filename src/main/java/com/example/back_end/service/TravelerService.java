package com.example.back_end.service;

import com.example.back_end.dto.TravelerDto;
import com.example.back_end.dto.UserDTO;
import com.example.back_end.exception.allreadyexists.AllReadyExists;
import com.example.back_end.exception.deletefailed.DeleteFailed;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.TravelPlan;
import com.example.back_end.model.Traveler;
import com.example.back_end.model.User;
import com.example.back_end.repository.TravelerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                        .orElseThrow(() -> new DeleteFailed());
        return modelMapper.map(traveler, TravelerDto.class);
    }


    public TravelerDto addTraveler(TravelerDto travelerDto) {

        /*if (travelerRepo.findById(travelerDto.getId()).isPresent()) {
            throw new AllReadyExists(travelerDto.getId());
        }*/
        if (travelerRepo.findByEmail(travelerDto.getEmail()) != null) {
            //throw new AllReadyExists(travelerDto.getEmail());
            return null;
        }

        Traveler savedTraveler;
        try {
            if(travelerDto.getProfileImg() == null) {
                travelerDto.setProfileImg("https://venturevibeimages.s3.eu-north-1.amazonaws.com/OIP-removebg-preview.png");
            }
            if(travelerDto.getCoverImg() == null) {
                travelerDto.setCoverImg("https://venturevibeimages.s3.eu-north-1.amazonaws.com/OIP-removebg-preview.png");
            }
            if(travelerDto.getRole() == null) {
                travelerDto.setRole("Traveler");
            }
            if(travelerDto.getPassword() == null) {
                travelerDto.setPassword("12345");
            }
            savedTraveler = travelerRepo.save(modelMapper.map(travelerDto, Traveler.class));
        }
        catch (Exception e) {
            throw new SavedFailed();
        }
        return modelMapper.map(savedTraveler, TravelerDto.class);
    }


    public Traveler findById(String id) {
        return travelerRepo.findById(id).orElse(null);
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
            throw new DeleteFailed();
        }
    }


    public List<TravelerDto> getTravelerByEmailPartially(String email) {
        try {
            List<Traveler> travelers = travelerRepo.findByEmailContainingIgnoreCase(email);
            return modelMapper.map(travelers , new TypeToken<List<TravelerDto>>(){}.getType());

        } catch (Exception e) {
            throw new NotFound();
        }
    }

    public List<TravelerDto> getAllUsers() {
        List<Traveler> users = travelerRepo.findAll();
        return users.stream().map(user->modelMapper.map(user,TravelerDto.class))
                .collect(Collectors.toList());
    }

    public TravelerDto updateUser(String userId, TravelerDto travelerDto) {
        Optional<Traveler> optionalUser = travelerRepo.findById(userId);
        if (optionalUser.isPresent()) {
            Traveler traveler = optionalUser.get();
            traveler.setName(traveler.getName());
            traveler.setEmail(traveler.getEmail());
            traveler.setRole(traveler.getRole());



            Traveler updatedUser = travelerRepo.save(traveler);
            return modelMapper.map(updatedUser, TravelerDto.class);
        } else {
            return null;
        }
    }

    public void deleteUser(String userId) {
        Optional<Traveler> optionalUser = travelerRepo.findById(userId);
        if (optionalUser.isPresent()) {
            travelerRepo.deleteById(userId);
        } else {
            throw new NotFound();
        }
    }
}
