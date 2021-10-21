package com.servicer.app.post;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public void post(@RequestBody Post post){
        postService.savePost(post);


    }
}
