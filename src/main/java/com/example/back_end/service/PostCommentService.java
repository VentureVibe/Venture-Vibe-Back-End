package com.example.back_end.service;

import com.example.back_end.dto.PostCommentDTO;
import com.example.back_end.exception.postcomment.PostCommentException;
import com.example.back_end.exception.postcomment.PostCommentSaveException;
import com.example.back_end.model.CommunityPost;
import com.example.back_end.model.PostComment;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.PostCommentRepo;
import com.example.back_end.repository.TravelerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostCommentService {

    @Autowired
    private PostCommentRepo postCommentRepo;

    @Autowired
    private TravelerRepo travelerRepo;

    @Autowired
    private CommunityPostService communityPostService;

    @Transactional
    public PostComment addPostComment(PostComment postComment) {
        if (postComment == null) {
            throw new IllegalArgumentException("PostComment cannot be null");
        }

        try {

            CommunityPost post = communityPostService.getCommunityPost(postComment.getPost().getId());
            post.setTotalComments(post.getTotalComments() + 1);
            communityPostService.updateCommunityPost(post.getId(), post);
            
            Optional<Traveler> user = travelerRepo.findById(postComment.getUsercommented().getId());
            postComment.setUsercommented(user.orElseThrow(() -> new IllegalArgumentException("User not found")));


            return postCommentRepo.save(postComment);

        } catch (DataAccessException dae) {
            throw new PostCommentSaveException("Failed to save PostComment due to database error");
        } catch (Exception e) {
            throw new PostCommentException("An unexpected error occurred while adding PostComment");
        }
    }

    public List<PostComment> getPostCommentsByPostId(Integer postId) {
        try {
            return postCommentRepo.findByPostId(postId);
        }  catch (Exception e) {
            throw new PostCommentException("Failed to retrieve PostComment due to database error");
        }
    }

    public void deletePostComment(Integer id) {
        try {
            postCommentRepo.deleteById(id);
        } catch (DataAccessException dae) {
            throw new PostCommentException("Failed to delete PostComment due to database error");
        } catch (Exception e) {
            throw new PostCommentException("An unexpected error occurred while deleting PostComment");
        }
    }
}
