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
    public PostComment createPostComment(@RequestBody PostComment postComment) {
        return postCommentService.addPostComment(postComment);
        //return new ResponseEntity<>(createdPostComment, HttpStatus.CREATED);
    }

    @GetMapping("/{postid}")
    public List<PostComment> getPostCommentsByPostId(@PathVariable Integer postid) {
        return postCommentService.getPostCommentsByPostId(postid);
    }

    @DeleteMapping("/{id}")
    public void deletePostComment(@PathVariable Integer id) {
        postCommentService.deletePostComment(id);
    }
}
