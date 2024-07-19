package com.example.back_end.service;

import com.example.back_end.dto.PostCommentDTO;
import com.example.back_end.exception.postcomment.PostCommentException;
import com.example.back_end.exception.postcomment.PostCommentSaveException;
import com.example.back_end.model.CommunityPost;
import com.example.back_end.model.PostComment;
import com.example.back_end.repository.PostCommentRepo;
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
    PostCommentRepo postCommentRepo;

    @Transactional
    public PostCommentDTO addPostComment(PostComment postComment) {
        if (postComment == null) {
            throw new IllegalArgumentException("PostComment cannot be null");
        }

        try {

            CommunityPost post = postComment.getPost();
            post.addPostComment(postComment);

            postCommentRepo.save(postComment);

            return new PostCommentDTO(postComment.getId(), postComment.getComment(), postComment.getPost().getId(), postComment.getTraveler().getId());
        } catch (DataAccessException dae) {
            throw new PostCommentSaveException("Failed to save PostComment due to database error");
        } catch (Exception e) {
            throw new PostCommentException("An unexpected error occurred while adding PostComment");
        }
    }

    public List<PostCommentDTO> getPostCommentsByPostId(Integer postId) {
        try {
            List<PostComment> postComments = postCommentRepo.findByPostId(postId);
            return postComments.stream()
                    .map(comment -> new PostCommentDTO(comment.getId(), comment.getComment(), comment.getPost().getId(), comment.getTraveler().getId()))
                    .collect(Collectors.toList());
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
