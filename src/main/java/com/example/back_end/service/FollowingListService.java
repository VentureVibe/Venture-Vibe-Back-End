package com.example.back_end.service;

import com.example.back_end.model.Conversation;
import com.example.back_end.model.FollowingList;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.FollowingListRepository;
import com.example.back_end.repository.TravelerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FollowingListService {

    @Autowired
    private FollowingListRepository followingListRepository;

    @Autowired
    private TravelerRepo userRepository;

    public FollowingList startFollowingList(FollowingList followingList) {
        Traveler user = userRepository.findById(followingList.getTraveler().getId()).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        followingList.setTraveler(user);

        return followingListRepository.save(followingList);
    }
}

