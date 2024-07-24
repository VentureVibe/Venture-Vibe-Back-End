package com.example.back_end.controller;

import com.example.back_end.model.Conversation;
import com.example.back_end.model.Follower;
import com.example.back_end.model.FollowingList;
import com.example.back_end.model.Message;
import com.example.back_end.service.FollowerService;
import com.example.back_end.service.FollowingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/following")
public class FollowingController {

    @Autowired
    private FollowingListService followingListService;

    @Autowired
    private FollowerService followerService;

    @PostMapping
    public FollowingList startFollowingList(@RequestBody FollowingList followingList) {
        return followingListService.startFollowingList(followingList);
    }

    @PostMapping("/follow")
    public Follower addTravelerToFollowingList(@RequestBody Follower follower) {
        return followerService.followTraveler(follower);
    }

}
