package com.graphql.graphqlsql.resolver.author;

import com.graphql.graphqlsql.dto.AuthorDto;
import com.graphql.graphqlsql.service.AuthorService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorQueryResolver implements GraphQLQueryResolver {

    private final AuthorService authorService;

    public AuthorQueryResolver(AuthorService authorService) {
        this.authorService = authorService;
    }


    public List<AuthorDto> getAuthors(){
     return  authorService.getAuthors();
    }
}
