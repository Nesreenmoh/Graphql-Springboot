package com.graphql.graphqlsql.resolver.comment;


import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.service.CommentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class CommentMutationResolver implements GraphQLMutationResolver {

    private final CommentService commentService;
    private final CommentPublisher commentPublisher;

    public CommentMutationResolver(CommentService commentService, CommentPublisher commentPublisher) {
        this.commentService = commentService;
        this.commentPublisher = commentPublisher;
    }

    public Long createComment(CommentDto commentDto){
        Long commentId = commentService.createComment(commentDto);
        commentPublisher.publish(commentDto);
        commentDto.setId(commentId);
        return commentId;
    }
}
