package com.example.back_end.controller;


import com.example.back_end.dto.TravelerDto;
import com.example.back_end.dto.UserDTO;
import com.example.back_end.service.TravelerService;
import com.example.back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private final UserService userService;
    @Autowired
    private TravelerService travelerService;

//    public AdminController(UserService userService) {
//        this.userService = userService;
//
//    }

    public AdminController(UserService userService, TravelerService travelerService){
        this.userService = userService;
        this.travelerService = travelerService;
    }

//    public AdminController(TravelerService travelerService){
//        this.travelerService = travelerService;
//    }



//    @GetMapping("/users")
//    public ResponseEntity<List<TravelerDto>> getAllUsers(){
//        List<TravelerDto> users = travelerService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }


//    @GetMapping("/users")
//    public ResponseEntity<List<TravelerDto>> getAllTravelers() {
//        List<TravelerDto> users = travelerService.getAllUsers();
//        return ResponseEntity.ok(users);
//
//    }

    @GetMapping("/users")
    public ResponseEntity<List<TravelerDto>> getAllTravelers() {
        List<TravelerDto> travelers = travelerService.getAllUsers(); // Renamed method in service
        return ResponseEntity.ok(travelers);
    }





    @PutMapping("/users/{userId}")
    public ResponseEntity<TravelerDto> updateUser(@PathVariable String userId, @RequestBody TravelerDto travelerDto) {
        TravelerDto updatedUser = travelerService.updateUser(userId, travelerDto);
        return ResponseEntity.ok(updatedUser);
    }

    //    @DeleteMapping("/users/{userId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
//        travelerService.deleteUser(userId);
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<TravelerDto> deleteUser(@PathVariable String userId){
        travelerService.deleteTraveler(userId);
        return ResponseEntity.ok().build();
    }



}
