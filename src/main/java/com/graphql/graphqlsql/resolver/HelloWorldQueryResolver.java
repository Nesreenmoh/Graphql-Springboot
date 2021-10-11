package com.graphql.graphqlsql.resolver;

import com.graphql.graphqlsql.dto.MessageDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {

    public String getHelloWorld(){
        return "GraphQl is Awesome!";
    }

    public String greetingMessage(String name ){
        return "Hello " + name + " In your Graphql";
    }

    public MessageDto getMessage(){
        return  MessageDto.builder()
                .id(UUID.randomUUID())
                .msg("My Graphql is awesome!")
                .build();
    }

    public List<Integer> getRoleDice(){
        return Arrays.asList(1,2,3,4,5,6,7);
    }
}
