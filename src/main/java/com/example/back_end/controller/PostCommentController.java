package com.example.back_end.controller;

import com.example.back_end.dto.PostCommentDTO;
import com.example.back_end.model.CommunityPost;
import com.example.back_end.model.PostComment;
import com.example.back_end.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/v1/comments")
public class PostCommentController {

    @Autowired
    PostCommentService postCommentService;

    @PostMapping
    public ResponseEntity<PostCommentDTO> createPostComment(@RequestBody PostComment postComment) {
        PostCommentDTO createdPostComment = postCommentService.addPostComment(postComment);
        return new ResponseEntity<>(createdPostComment, HttpStatus.CREATED);
    }

    @GetMapping("/{postid}")
    public ResponseEntity<List<PostCommentDTO>> getPostCommentsByPostId(@PathVariable Integer postid) {
        List<PostCommentDTO> postComments = postCommentService.getPostCommentsByPostId(postid);
        if (postComments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postComments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePostComment(@PathVariable Integer id) {
        postCommentService.deletePostComment(id);
    }
}
