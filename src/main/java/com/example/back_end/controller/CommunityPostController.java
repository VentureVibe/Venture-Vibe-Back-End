package com.example.back_end.controller;

import com.example.back_end.model.CommunityPost;
import com.example.back_end.service.CommunityPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/v1/communityPost")
public class CommunityPostController {

    @Autowired
    CommunityPostService communityPostService;

    @PostMapping
    public CommunityPost addCommunityPost(@RequestBody CommunityPost communityPost) {
        return communityPostService.addCommunityPost(communityPost);
    }

    @GetMapping
    public List<CommunityPost> getAllCommunityPosts() {
        return communityPostService.getAllCommunityPosts();
    }

    @GetMapping("/{id}")
    public Optional<CommunityPost> getCommunityPost(@PathVariable Integer id) {
        return communityPostService.getCommunityPost(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCommunityPost(@PathVariable Integer id) {
        communityPostService.deleteCommunityPost(id);
    }
}
