package com.graphql.graphqlsql.resolver.author;

import com.graphql.graphqlsql.dto.AuthorDto;
import com.graphql.graphqlsql.service.AuthorService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {

    private final AuthorService authorService;

    public AuthorMutationResolver(AuthorService authorService) {
        this.authorService = authorService;
    }


    public Long createAuthor(AuthorDto authorDto){
        return authorService.createAuthor(authorDto);
    }
}
