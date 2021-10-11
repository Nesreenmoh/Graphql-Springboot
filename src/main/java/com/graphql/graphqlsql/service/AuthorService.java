package com.graphql.graphqlsql.service;

import com.graphql.graphqlsql.dto.AuthorDto;


import java.util.List;


public interface AuthorService {
    List<AuthorDto> getAuthors();

    AuthorDto getAuthorById(Long authorId);

    Long createAuthor(AuthorDto authorDto);
}
