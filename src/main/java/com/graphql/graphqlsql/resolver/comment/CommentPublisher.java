package com.graphql.graphqlsql.resolver.comment;


import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.dto.PostDto;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class CommentPublisher {

    private final Sinks.Many<CommentDto> processor;

    public CommentPublisher() {
        /*
        register all subscriptions
         */
        this.processor = Sinks.many().replay().all();
    }

    public void publish(CommentDto commentDto) {
        processor.emitNext(commentDto, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Publisher<CommentDto> getNewComment(Long postId) {
        return  processor.asFlux()
                .filter(commentDto -> postId.equals(commentDto.getPostId()));
    }
}
