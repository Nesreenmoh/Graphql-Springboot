package com.graphql.graphqlsql.service;

import com.graphql.graphqlsql.dto.PostDto;
import com.graphql.graphqlsql.model.Post;

import java.util.List;


public interface PostService {
    List<PostDto> getAllPostsByAuthorId(Long authorId);

    List<PostDto> getRecentPosts(Integer count, Integer offset);

    List<PostDto> getAllPosts();

    Long createPost(PostDto postDto);
}
