package com.crossasyst.resttemplate1.controller;

import com.crossasyst.resttemplate1.model.PostRequest;
import com.crossasyst.resttemplate1.model.PostResponse;
import com.crossasyst.resttemplate1.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRequest[]> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PostResponse getById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @PostMapping(value = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> createPosts(@RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.createPosts(postRequest);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> updatePosts(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.updatePosts(id, postRequest);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{userId}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long userId) {
        postService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

