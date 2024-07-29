package com.example.back_end.controller;

import com.example.back_end.model.CommunityPost;
import com.example.back_end.service.CommunityPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/v1/communityPost")
public class CommunityPostController {

    @Autowired
    CommunityPostService communityPostService;

    /*@PostMapping
    public CommunityPost addCommunityPost(@RequestBody CommunityPost communityPost) {
        return communityPostService.addCommunityPost(communityPost);
    }*/
    @PostMapping
    public CommunityPost addCommunityPost(
            @RequestPart("communityPost") String communityPostJson,
            @RequestPart("image") MultipartFile image) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommunityPost communityPost = objectMapper.readValue(communityPostJson, CommunityPost.class);
        return communityPostService.addCommunityPost(communityPost, image);
    }

    @GetMapping
    public List<CommunityPost> getAllCommunityPosts() {
        return communityPostService.getAllCommunityPosts();
    }

    @GetMapping("/{id}")
    public CommunityPost getCommunityPost(@PathVariable Integer id) {
        return communityPostService.getCommunityPost(id);
    }

    @GetMapping("/user/{userId}")
    public List<CommunityPost> getCommunityPostsByUserId(@PathVariable String userId) {
        return communityPostService.getCommunityPostsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteCommunityPost(@PathVariable Integer id) {
        communityPostService.deleteCommunityPost(id);
    }
}
