package com.example.back_end.service;

import com.example.back_end.model.CommunityPost;
import com.example.back_end.repository.CommunityPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityPostService {

    @Autowired
    CommunityPostRepo communityPostRepo;

    public CommunityPost addCommunityPost(CommunityPost communityPost) {
        return communityPostRepo.save(communityPost);
    }

    @Transactional
    public CommunityPost updateCommunityPost(Integer id, CommunityPost updatedPost) {
        // Retrieve the CommunityPost by ID
        Optional<CommunityPost> optionalPost = communityPostRepo.findById(id);

        if (optionalPost.isPresent()) {
            CommunityPost existingPost = optionalPost.get();

            // Update the existing post with values from updatedPost
            existingPost.setContent(updatedPost.getContent());
            existingPost.setImgUrl(updatedPost.getImgUrl());
            existingPost.setTotalLikes(updatedPost.getTotalLikes());
            existingPost.setTotalComments(updatedPost.getTotalComments());

            // Save the updated post back to the repository
            return communityPostRepo.save(existingPost);
        } else {
            throw new IllegalArgumentException("CommunityPost with ID " + id + " not found");
        }
    }

    public List<CommunityPost> getAllCommunityPosts() {
        return communityPostRepo.findAll();
    }

//    public Optional<CommunityPost> getCommunityPost(Integer id) {
//        return communityPostRepo.findById(id);
//    }

    public CommunityPost getCommunityPost(Integer id) {
        return communityPostRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CommunityPost with ID " + id + " not found"));
    }

    public void deleteCommunityPost(Integer id) {
        communityPostRepo.deleteById(id);
    }
}
