package com.servicer.app.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void savePost(Post post){
        postRepository.save(post);

    }

}
