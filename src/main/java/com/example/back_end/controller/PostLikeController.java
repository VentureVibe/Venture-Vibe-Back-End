package com.example.back_end.controller;

import com.example.back_end.dto.PostLikeDTO;
import com.example.back_end.exception.postlike.PostLikeAlreadyExistsException;
import com.example.back_end.exception.postlike.PostLikeException;
import com.example.back_end.exception.postlike.PostLikeSaveException;
import com.example.back_end.model.PostLike;
import com.example.back_end.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/likes")
public class PostLikeController {

    @Autowired
    PostLikeService postLikeService;

    @PostMapping
    public ResponseEntity<PostLikeDTO> createPostLike(@RequestBody PostLike postLike) {
        PostLikeDTO createdPostLike = postLikeService.addPostLike(postLike);
        return new ResponseEntity<>(createdPostLike, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{postId}/{travelerId}")
    public ResponseEntity<PostLikeDTO> deletePostLike(@PathVariable Integer postId, @PathVariable String travelerId) {
        PostLikeDTO deletedPostLike = postLikeService.deletePostLike(postId, travelerId);
        return new ResponseEntity<>(deletedPostLike, HttpStatus.OK);
    }

    @GetMapping(value = "/{postId}/{travelerId}")
    public ResponseEntity<Boolean> isPostLiked(@PathVariable Integer postId, @PathVariable String travelerId) {
        boolean isLiked = postLikeService.isPostLiked(postId, travelerId);
        return new ResponseEntity<>(isLiked, HttpStatus.OK);
    }

}
