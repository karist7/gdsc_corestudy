package com.example.gdsc.Service;

import com.example.gdsc.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postsRepository;
}
