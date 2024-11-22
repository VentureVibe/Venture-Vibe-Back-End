package com.example.back_end.service;

import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.exception.deletefailed.DeleteFailed;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.TravelDate;
import com.example.back_end.model.TravelPlan;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.TravelDateRepo;
import com.example.back_end.repository.TravelPlanRepo;
import com.example.back_end.repository.TravelerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TravelPlanService {

    @Autowired
    private final TravelPlanRepo travelPlanRepo;

    @Autowired
    private TravelerRepo travelerRepo;

    @Autowired
    private TravelDateRepo travelerDateRepo;

    @Autowired
    private TravelInvitationService travelInvitationService;

    private final ModelMapper modelMapper;



    public TravelPlanDto getTravelerPlanByID(Long travelerPlanId, String travelerId) {
        // Retrieve the TravelPlan by its ID
        TravelPlan travelPlan = travelPlanRepo.findById(travelerPlanId)
                .orElseThrow(() -> new NotFound());

        // Check if the provided travelerId is in the list of travelers
        boolean isTravelerPresent = travelPlan.getTravelers().stream()
                .anyMatch(traveler -> traveler.getId().equals(travelerId));

        if (!isTravelerPresent) {
            throw new NotFound();
        }

        // Map the TravelPlan entity to TravelPlanDto
        return modelMapper.map(travelPlan, TravelPlanDto.class);
    }



    public List<TravelPlanDto> getTravelPlansByUserId(String userId) {

        Optional<Traveler> travelerOptional = travelerRepo.findById(userId);
        if (travelerOptional.isPresent()) {
            Traveler traveler = travelerOptional.get();
            return traveler.getTravelplans().stream()
                    .map(travelPlan -> modelMapper.map(travelPlan, TravelPlanDto.class))
                    .collect(Collectors.toList());
        } else {
            throw new NotFound();
        }
    }


    public Page<TravelPlanDto> getAcceptedTravelPlansByUserId(String userId, int page, int size) {
        Optional<Traveler> travelerOptional = travelerRepo.findById(userId);
        if (travelerOptional.isPresent()) {
            Traveler traveler = travelerOptional.get();
            List<TravelPlanDto> acceptedPlans = traveler.getTravelplans().stream()
                    .filter(travelPlan ->
                            travelPlan.getTravelers().stream()
                                    .anyMatch(t -> t.getId().equals(userId)) &&
                                    !travelPlan.getTravelPlanOwner().getId().equals(userId)
                    )
                    .map(travelPlan -> modelMapper.map(travelPlan, TravelPlanDto.class))
                    .collect(Collectors.toList());

            // Creating a Pageable object
            Pageable pageable = PageRequest.of(page, size);

            // Getting the sublist based on pagination parameters
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), acceptedPlans.size());
            List<TravelPlanDto> sublist = acceptedPlans.subList(start, end);

            // Returning the Page object
            return new PageImpl<>(sublist, pageable, acceptedPlans.size());
        } else {
            throw new NotFound();
        }
    }


    public Page<TravelPlanDto> getOwnedTravelPlansByUserId(String userId, int page, int size) {
        Optional<Traveler> travelerOptional = travelerRepo.findById(userId);
        if (travelerOptional.isPresent()) {
            Traveler traveler = travelerOptional.get();
            List<TravelPlanDto> ownedPlans = traveler.getTravelplans().stream()
                    .filter(travelPlan -> travelPlan.getTravelPlanOwner().getId().equals(userId)) // Filter to only owned plans
                    .map(travelPlan -> modelMapper.map(travelPlan, TravelPlanDto.class))
                    .collect(Collectors.toList());

            // Creating a Pageable object
            Pageable pageable = PageRequest.of(page, size);

            // Getting the sublist based on pagination parameters
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), ownedPlans.size());
            List<TravelPlanDto> sublist = ownedPlans.subList(start, end);

            // Returning the Page object
            return new PageImpl<>(sublist, pageable, ownedPlans.size());
        } else {
            throw new NotFound();
        }
    }


    @Transactional
    public TravelPlanDto addTravelPlan(String travelerId, TravelPlanDto travelPlanDto) {
        Traveler traveler;

        try {
            traveler = travelerRepo.findById(travelerId)
                    .orElseThrow(() -> new NotFound());


            TravelPlan travelPlanEntity = modelMapper.map(travelPlanDto, TravelPlan.class);

            if (travelPlanEntity.getTravelers() == null) {
                travelPlanEntity.setTravelers(new ArrayList<>());
            }

            travelPlanEntity.getTravelers().add(traveler);

            if (traveler.getTravelplans() == null) {
                traveler.setTravelplans(new ArrayList<>());
            }

            traveler.getTravelplans().add(travelPlanEntity);

            // Set the travel plan owner

            travelPlanEntity.setTravelPlanOwner(traveler);



            TravelPlan savedTravelPlan = travelPlanRepo.save(travelPlanEntity);

            List<String> travelInviteList = travelPlanDto.getTravelInviteList();

            if (travelInviteList != null && !travelInviteList.isEmpty()) {
                for (String travelerId2 : travelInviteList) {

                    Traveler traveler2 = travelerRepo.findById(travelerId2)
                            .orElseThrow(() -> new NotFound());

                    travelInvitationService.addTravelInvitation(savedTravelPlan.getId(),traveler2.getId());
                }
            }




            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fromDate = LocalDate.parse(travelPlanDto.getFromDate(), formatter);
            LocalDate toDate = LocalDate.parse(travelPlanDto.getToDate(), formatter);
            List<TravelDate> travelDates = new ArrayList<>();
            for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                TravelDate travelDate = new TravelDate();
                travelDate.setDate(date.toString());
                travelDate.setTravelPlan(savedTravelPlan);
                travelDates.add(travelDate);
            }
            travelerDateRepo.saveAll(travelDates);

            return modelMapper.map(savedTravelPlan, TravelPlanDto.class);
        } catch (NotFound e) {
            throw new NotFound();
        } catch (Exception ee) {
            throw new SavedFailed();
        }
    }



    @Transactional
    public TravelPlanDto addTravelerToTravelPlan(Long travelPlanId, String travelerId) {

        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound());

        Traveler traveler = travelerRepo.findById(travelerId)
                .orElseThrow(() -> new NotFound());

        if (travelPlan.getTravelers() == null) {
            travelPlan.setTravelers(new ArrayList<>());
        }

        if (!travelPlan.getTravelers().contains(traveler)) {
            travelPlan.getTravelers().add(traveler);
        }

        if (traveler.getTravelplans() == null) {
            traveler.setTravelplans(new ArrayList<>());
        }

        if (!traveler.getTravelplans().contains(travelPlan)) {
            traveler.getTravelplans().add(travelPlan);
        }

        TravelPlan updatedTravelPlan = travelPlanRepo.save(travelPlan);

        travelerRepo.save(traveler);

        return modelMapper.map(updatedTravelPlan, TravelPlanDto.class);
    }


    @Transactional
    public TravelPlanDto deleteTravelPlan(Long travelPlanId) {
        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound());

        try{
            for (Traveler traveler : new ArrayList<>(travelPlan.getTravelers())) {
                traveler.getTravelplans().remove(travelPlan);
                travelerRepo.save(traveler); // Ensure changes are persisted
            }

            travelPlanRepo.delete(travelPlan);
            return modelMapper.map(travelPlan, TravelPlanDto.class);
        }
        catch(Exception e){
            throw new DeleteFailed();
        }


    }

    @Transactional
    public TravelPlanDto deleteTravelerFromTravelPlan(Long travelPlanId,String travelerId) {
        System.out.println(travelerId +" "+travelPlanId);
        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound());

        try{
            for (Traveler traveler : new ArrayList<>(travelPlan.getTravelers())) {
                if(traveler.getId().equals(travelerId)){

                    traveler.getTravelplans().remove(travelPlan);
                    travelerRepo.save(traveler);
                }
            }

            return modelMapper.map(travelPlan, TravelPlanDto.class);

        }
        catch(Exception e){
            throw new DeleteFailed();
        }


    }


    public TravelPlanDto addNoteToTravelPlan(Long travelPlanId, String note) {

        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound());


        travelPlan.setNote(note);

        TravelPlan updatedTravelPlan = travelPlanRepo.save(travelPlan);

        return modelMapper.map(updatedTravelPlan, TravelPlanDto.class);
    }


    public TravelPlanDto addBudgetToTravelPlan(Long travelPlanId, Long price) {

        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound());


        travelPlan.setBudget(price);

        TravelPlan updatedTravelPlan = travelPlanRepo.save(travelPlan);

        return modelMapper.map(updatedTravelPlan, TravelPlanDto.class);
    }

}
