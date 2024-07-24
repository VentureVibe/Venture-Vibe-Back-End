package com.example.back_end.service;

import com.example.back_end.model.*;
import com.example.back_end.repository.FollowerRepository;
import com.example.back_end.repository.FollowingListRepository;
import com.example.back_end.repository.TravelerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FollowerService {

    private static final Logger logger = LoggerFactory.getLogger(FollowerService.class);

    @Autowired
    private FollowingListRepository followingListRepository;

    @Autowired
    private FollowingListService followingListService;

    @Autowired
    private TravelerRepo userRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @Transactional
    public Follower followTraveler(Follower follower) {
        Traveler traveler = userRepository.findById(follower.getTraveler().getId()).orElse(null);
        if (traveler == null) {
            throw new IllegalArgumentException("Traveler not found");
        }

        Traveler followedTraveler = userRepository.findById(follower.getFollowedTraveler().getId()).orElse(null);
        if (followedTraveler == null) {
            throw new IllegalArgumentException("FollowedTraveler not found");
        }

        FollowingList followingList = null;
        if (follower.getFollowingList() != null) {
            followingList = followingListRepository.findById(follower.getFollowingList().getId()).orElse(null);
        }

        if (followingList == null) {
            // Create a new following list
            FollowingList newFollowingList = new FollowingList();
            newFollowingList.setTraveler(traveler);
            followingList = followingListService.startFollowingList(newFollowingList);
        }

        follower.setTraveler(traveler);
        follower.setFollowedTraveler(followedTraveler);
        follower.setFollowingList(followingList);

        logger.info("Traveler {} is now following Traveler {}", traveler.getId(), followedTraveler.getId());

        return followerRepository.save(follower);
    }
}
