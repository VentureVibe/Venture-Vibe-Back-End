package com.example.back_end.service;

import com.example.back_end.model.CommunityPost;
import com.example.back_end.repository.CommunityPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityPostService {

    @Autowired
    CommunityPostRepo communityPostRepo;

    public CommunityPost addCommunityPost(CommunityPost communityPost) {
        return communityPostRepo.save(communityPost);
    }

    public List<CommunityPost> getAllCommunityPosts() {
        return communityPostRepo.findAll();
    }

    public Optional<CommunityPost> getCommunityPost(Integer id) {
        return communityPostRepo.findById(id);
    }

    public void deleteCommunityPost(Integer id) {
        communityPostRepo.deleteById(id);
    }
}
