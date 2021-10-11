package com.graphql.graphqlsql.resolver.post;

import com.graphql.graphqlsql.dto.AuthorDto;
import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.dto.PostDto;
import com.graphql.graphqlsql.service.AuthorService;
import com.graphql.graphqlsql.service.CommentService;
import com.graphql.graphqlsql.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PostFieldResolver implements GraphQLResolver<PostDto> {

    private final PostService postService;
    private final AuthorService authorService;
    private final CommentService commentService;

    public PostFieldResolver(PostService postService, AuthorService authorService, CommentService commentService) {
        this.postService = postService;
        this.authorService = authorService;
        this.commentService = commentService;
    }

    public AuthorDto getAuthor(PostDto postDto){

        return authorService.getAuthorById(postDto.getAuthorId());
    }

    public List<CommentDto> getComments(PostDto postDto, Integer first){
        return commentService.getFirstCommentsByPostId(postDto.getId(), first);

    }

}
