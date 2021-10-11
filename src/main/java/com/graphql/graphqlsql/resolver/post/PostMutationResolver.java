package com.graphql.graphqlsql.resolver.post;

import com.graphql.graphqlsql.dto.PostDto;
import com.graphql.graphqlsql.service.PostService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class PostMutationResolver implements GraphQLMutationResolver {

    private final PostService postService;
    private final PostPublisher postPublisher;

    public PostMutationResolver(PostService postService, PostPublisher postPublisher) {
        this.postService = postService;
        this.postPublisher = postPublisher;
    }

    public Long createPost(PostDto postDto){
        Long postId = postService.createPost(postDto);
        postDto.setId(postId);
        postPublisher.publish(postDto);
        return postId;
    }
}
