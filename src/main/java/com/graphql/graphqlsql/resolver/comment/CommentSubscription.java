package com.graphql.graphqlsql.resolver.comment;

import com.graphql.graphqlsql.dto.CommentDto;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class CommentSubscription implements GraphQLSubscriptionResolver {

    private final CommentPublisher commentPublisher;

    public CommentSubscription(CommentPublisher commentPublisher) {
        this.commentPublisher = commentPublisher;
    }


    public Publisher<CommentDto> recentCommentByPost(Long postId){
        return commentPublisher.getNewComment(postId);
    }
}
