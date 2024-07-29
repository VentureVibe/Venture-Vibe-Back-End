package com.example.back_end.controller;

import com.example.back_end.dto.TravelInviteDTO;
import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.exception.deletefailed.DeleteFailed;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.TravelInvitation;
import com.example.back_end.service.TravelInvitationService;
import com.example.back_end.service.TravelerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/travelInvite")

public class TravelInvitationController {



    @Autowired
    private TravelInvitationService travelInvitationService;

    @PostMapping(value = "/{travelPlanId}/{travelerId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TravelInvitation> createTravelInvitation(@PathVariable Long travelPlanId, @PathVariable String travelerId) {
        TravelInvitation createdTravelInvitation = travelInvitationService.addTravelInvitation(travelPlanId, travelerId);
        return new ResponseEntity<>(createdTravelInvitation, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{travelInvitationId}", produces = "application/json")
    public ResponseEntity<TravelInviteDTO> getTravelInvitationById(@PathVariable Long travelInvitationId) {
        try {
            TravelInviteDTO travelInvitation = travelInvitationService.getTravelInvitationById(travelInvitationId);
            return new ResponseEntity<>(travelInvitation, HttpStatus.OK);
        } catch (Exception e) {
            throw new SavedFailed();
        }
    }

    @GetMapping(value = "/traveler/{travelerId}", produces = "application/json")
    public ResponseEntity<Page<TravelInviteDTO>> getTravelInvitationsByTravelerId(@PathVariable String travelerId,
                                                                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                                                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Page<TravelInviteDTO> travelInvitationPage = travelInvitationService.getTravelInvitationsByTravelerId(travelerId, page, size);
            return new ResponseEntity<>(travelInvitationPage, HttpStatus.OK);
        } catch (Exception e) {
             throw new SavedFailed();
        }
    }

    @DeleteMapping(value = "/{travelInvitationId}")
    public ResponseEntity<Void> deleteTravelInvitationById(@PathVariable Long travelInvitationId) {
        try {
            travelInvitationService.deleteTravelInvitationById(travelInvitationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new DeleteFailed();
        }
    }

}
