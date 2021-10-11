package com.graphql.graphqlsql.resolver.post;

import com.graphql.graphqlsql.dto.PostDto;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PostSubscription implements GraphQLSubscriptionResolver {

   private final PostPublisher postPublisher;

    public PostSubscription(PostPublisher postPublisher) {
        this.postPublisher = postPublisher;
    }

    public Publisher<PostDto> recentPost(){
    return postPublisher.getRecentPost();
    }

    public Publisher<PostDto> recentPostByAuthorId(Long authorId){
        return postPublisher.getPostByAuthorId(authorId);
    }
}
