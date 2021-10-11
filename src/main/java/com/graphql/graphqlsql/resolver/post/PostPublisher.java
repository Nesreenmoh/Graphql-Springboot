package com.graphql.graphqlsql.resolver.post;

import com.graphql.graphqlsql.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Slf4j
@Component
public class PostPublisher {

    private final Sinks.Many<PostDto> processor;

    public PostPublisher() {
        /*
        register all subscriptions
         */
        this.processor = Sinks.many().replay().all();
    }

    public Publisher<PostDto> getRecentPost() {
        /*
        return that post
         */
      return  processor.asFlux();
    }

    public void publish(PostDto postDto) {
        processor.emitNext(postDto, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Publisher<PostDto> getPostByAuthorId(Long authorId) {
        return processor.asFlux().filter(postDto ->
                authorId.equals(postDto.getAuthorId()));
    }
}
