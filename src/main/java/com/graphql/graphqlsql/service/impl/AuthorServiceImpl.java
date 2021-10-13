package com.graphql.graphqlsql.service.impl;

import com.graphql.graphqlsql.dto.AuthorDto;
import com.graphql.graphqlsql.exception.ResourceNotFound;
import com.graphql.graphqlsql.mapper.CommentMapper;
import com.graphql.graphqlsql.model.Author;
import com.graphql.graphqlsql.repository.AuthorRepository;
import com.graphql.graphqlsql.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CommentMapper commentMapper;


    public AuthorServiceImpl(AuthorRepository authorRepository, CommentMapper commentMapper) {
        this.authorRepository = authorRepository;
        this.commentMapper = commentMapper;
    }


    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> allAuthors = authorRepository.findAll();
        if (allAuthors.isEmpty()) {
            throw new ResourceNotFound("No Authors Available!");
        }
        return allAuthors
                .stream()
                .map(commentMapper::convertAuthorToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(Long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        Author author = authorOptional.orElseThrow(() ->
                new ResourceNotFound("Author with this id "+ authorId + " is not exist!")
        );
        return commentMapper.convertAuthorToDto(author);
    }

    @Override
    public Long createAuthor(AuthorDto authorDto) {
        Author newAuthor = commentMapper.convertDtoToAuthor(authorDto);
        Author createdAuthor = authorRepository.saveAndFlush(newAuthor);
        return createdAuthor.getId();
    }

}
