package com.graphql.graphqlsql.resolver.author;

import com.graphql.graphqlsql.dto.AuthorDto;
import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.dto.PostDto;
import com.graphql.graphqlsql.service.CommentService;
import com.graphql.graphqlsql.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AuthorFieldResolver implements GraphQLResolver<AuthorDto> {

    private final PostService postService;
    private final CommentService commentService;

    public AuthorFieldResolver(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


    /*
    field resolver
     */

    public List<PostDto> getPosts( AuthorDto authorDto) {
        return postService.getAllPostsByAuthorId(authorDto.getId());

    }

    public Integer getPostsCounts(AuthorDto authorDto){
        return postService.getAllPostsByAuthorId(authorDto.getId()).size();
    }

    public List<CommentDto> getComments(AuthorDto authorDto, Integer first ){

        return commentService.getFirstCommentsByAuthor_Id(authorDto.getId(), first);
    }
}
