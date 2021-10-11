package com.graphql.graphqlsql.resolver.comment;


import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.service.CommentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class CommentMutationResolver implements GraphQLMutationResolver {

    private final CommentService commentService;

    public CommentMutationResolver(CommentService commentService) {
        this.commentService = commentService;
    }

    public Long createComment(CommentDto commentDto){
        return commentService.createComment(commentDto);
    }
}
