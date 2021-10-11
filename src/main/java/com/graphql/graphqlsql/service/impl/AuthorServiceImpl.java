package com.graphql.graphqlsql.service.impl;

import com.graphql.graphqlsql.dto.AuthorDto;
import com.graphql.graphqlsql.model.Author;
import com.graphql.graphqlsql.repository.AuthorRepository;
import com.graphql.graphqlsql.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> allAuthors = authorRepository.findAll();

        return allAuthors
                .stream()
                .map(author -> {
                  return AuthorDto.builder()
                           .id(author.getId())
                           .email(author.getEmail())
                           .name(author.getName())
                           .build();
                }).collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(Long authorId) {
        Author authorById = authorRepository.findById(authorId).get();

        return AuthorDto
                .builder()
                .name(authorById.getName())
                .id(authorId)
                .email(authorById.getEmail())
                .build();
    }

    @Override
    public Long createAuthor(AuthorDto authorDto) {
        Author newAuthor = Author.builder()
                .name(authorDto.getName())
                .email(authorDto.getEmail())
                .build();
        Author createdAuthor = authorRepository.saveAndFlush(newAuthor);
        return createdAuthor.getId();
    }

}
