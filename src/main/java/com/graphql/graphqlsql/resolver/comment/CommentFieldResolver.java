package com.graphql.graphqlsql.resolver.comment;

import com.graphql.graphqlsql.dto.AuthorDto;
import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.dto.PostDto;
import com.graphql.graphqlsql.service.AuthorService;
import com.graphql.graphqlsql.service.CommentService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class CommentFieldResolver implements GraphQLResolver<CommentDto> {

    private final CommentService commentService;
    private final AuthorService authorService;

    public CommentFieldResolver(CommentService commentService, AuthorService authorService) {
        this.commentService = commentService;
        this.authorService = authorService;
    }


    public PostDto getPost(CommentDto commentDto){
        return commentService.getPost(commentDto.getId());
    }

    public AuthorDto getAuthor(CommentDto commentDto){
        AuthorDto authorById = authorService.getAuthorById(commentDto.getAuthorId());
        if(authorById.equals("")) throw  new RuntimeException("No Author found!");
        return  authorById;

    }
}
