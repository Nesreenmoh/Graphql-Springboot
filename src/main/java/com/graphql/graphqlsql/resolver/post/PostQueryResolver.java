package com.graphql.graphqlsql.resolver.post;

import com.graphql.graphqlsql.dto.PostDto;
import com.graphql.graphqlsql.service.PostService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;

    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }


    public List<PostDto> recentPosts(Integer count, Integer offset){
      return postService.getRecentPosts(count, offset);
    }

    public List<PostDto>  getPosts() {
        return postService.getAllPosts();
    }
}
