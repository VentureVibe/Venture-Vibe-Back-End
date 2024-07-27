package com.example.back_end.service;

import com.example.back_end.dto.PostLikeDTO;
import com.example.back_end.exception.postlike.PostLikeAlreadyExistsException;
import com.example.back_end.exception.postlike.PostLikeException;
import com.example.back_end.exception.postlike.PostLikeSaveException;
import com.example.back_end.model.CommunityPost;
import com.example.back_end.model.PostLike;
import com.example.back_end.repository.CommunityPostRepo;
import com.example.back_end.repository.PostLikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostLikeService {

    @Autowired
    private PostLikeRepo postLikeRepo;

    @Autowired
    private CommunityPostService communityPostService;
    @Autowired
    private CommunityPostRepo communityPostRepo;

    @Transactional
    public PostLikeDTO addPostLike(PostLike postLike) {
        if (postLike == null) {
            throw new IllegalArgumentException("PostLike cannot be null");
        }

        try {
            boolean isLiked = postLikeRepo.existsByPostIdAndTravelerId(postLike.getPost().getId(), postLike.getTraveler().getId());
            if (isLiked) {
                throw new PostLikeAlreadyExistsException("PostLike already exists for the given post and traveler");
            }

            // Get the post to update its like count
            CommunityPost post = communityPostService.getCommunityPost(postLike.getPost().getId());
            post.setTotalLikes(post.getTotalLikes() + 1);
            communityPostService.updateCommunityPost(post.getId(), post);

            // Save the PostLike
            postLikeRepo.save(postLike);

            return new PostLikeDTO( postLike.getPost().getId(), postLike.getTraveler().getId());
        } catch (DataAccessException dae) {
            throw new PostLikeSaveException("Failed to save PostLike due to database error");
        } catch (Exception e) {
            throw new PostLikeException("An unexpected error occurred while adding PostLike");
        }
    }

    @Transactional
    public PostLikeDTO deletePostLike(Integer postId, String travelerId) {
        try {
            CommunityPost compost = communityPostRepo.findById(postId).orElseThrow(() -> new PostLikeException("Post not found"));

            // Update the post's like count
            compost.setTotalLikes(compost.getTotalLikes() - 1);
            communityPostRepo.save(compost);

            // Delete the post like
            postLikeRepo.deleteByPostIdAndTravelerId(postId, travelerId);

            return new PostLikeDTO(postId, travelerId);
        } catch (DataAccessException dae) {
            throw new PostLikeSaveException("Failed to delete PostLike due to database error");
        } catch (Exception e) {
            throw new PostLikeException("An unexpected error occurred while deleting PostLike");
        }
    }

    public boolean isPostLiked(Integer postId, String travelerId) {
        return postLikeRepo.existsByPostIdAndTravelerId(postId, travelerId);
    }
}
