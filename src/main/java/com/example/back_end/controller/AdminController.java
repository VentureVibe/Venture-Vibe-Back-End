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

    public AdminController(UserService userService) {
        this.userService = userService;

    }


    @GetMapping("/users")
    public ResponseEntity<List<TravelerDto>> getAllUsers(){
        List<TravelerDto> users = travelerService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO){
        UserDTO updatedUser = userService.updateUser(userId,userDTO);
        if(updatedUser != null){
            return ResponseEntity.ok(updatedUser);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
