package com.example.back_end.service;

import com.example.back_end.dto.UserDTO;
import com.example.back_end.model.User;
import com.example.back_end.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO addUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()) == null) {
            User user = modelMapper.map(userDTO, User.class);
            if (userDTO.getProfileImageUrl() == null) {
                user.setProfileImageUrl("https://venturevibeimages.s3.eu-north-1.amazonaws.com/Default-Profile-Picture-Download-PNG-Image.png");
            }
            if (userDTO.getRole() == null) {
                user.setRole("USER");
            }
            user = userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        }
        else {
            return null;
        }
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return modelMapper.map(user, UserDTO.class);
        }
        else {
            return null;
        }
    }


}
