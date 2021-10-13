package com.graphql.graphqlsql.service.impl;

import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.dto.PostDto;
import com.graphql.graphqlsql.exception.ResourceNotFound;
import com.graphql.graphqlsql.mapper.CommentMapper;
import com.graphql.graphqlsql.model.Author;
import com.graphql.graphqlsql.model.Comment;
import com.graphql.graphqlsql.model.Post;
import com.graphql.graphqlsql.repository.AuthorRepository;
import com.graphql.graphqlsql.repository.CommentRepository;
import com.graphql.graphqlsql.repository.PostRepository;
import com.graphql.graphqlsql.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, AuthorRepository authorRepository, PostRepository postRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDto> getFirstCommentsByAuthor_Id(Long authorId, Integer first) {
        List<Comment> allByAuthor_id = commentRepository.findAllByAuthor_Id
                (authorId, PageRequest.of(0, first));
        if (allByAuthor_id.isEmpty()) {
            throw new ResourceNotFound("No Posts for this Author Id "+ authorId);
        }
        return allByAuthor_id.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getFirstCommentsByPostId(Long postId, Integer first) {
        List<Comment> firstCommentsByPostId =
                commentRepository.findAllByPostId(postId, PageRequest.of(0, first));
        if (firstCommentsByPostId.isEmpty()) {
            throw  new ResourceNotFound("No Comments for this Post Id "+postId);
        }

        return firstCommentsByPostId.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getComments(Integer count, Integer offset) {
        Page<Comment> someComments = commentRepository.findAll(PageRequest.of(offset, count));
        if(someComments.isEmpty()){
            throw new ResourceNotFound("No Comments Available!");
        }
        return someComments.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(Long id) {
        Optional<Comment> commentById = commentRepository.findById(id);
        Comment comment = commentById.orElseThrow(() -> new ResourceNotFound("Comment with this" +
                "id " + id + " is not exist!"));

        return PostDto.builder()
                .id(comment.getPost().getId())
                .title(comment.getPost().getTitle())
                .description(comment.getPost().getDescription())
                .authorId(comment.getAuthor().getId())
                .category(comment.getPost().getCategory())
                       .build();
    }

    @Override
    public Long createComment(CommentDto commentDto) {
        Optional<Author> authorOptional = authorRepository.findById(commentDto.getAuthorId());
        Optional<Post> postOptional = postRepository.findById(commentDto.getPostId());

        Author author = authorOptional.orElseThrow(() ->
                new ResourceNotFound("Author is not exist!"));

        Post post = postOptional.orElseThrow(() ->
                new ResourceNotFound("Post is not exist!"));

       Comment newComment = commentMapper.convertDtoToComment(commentDto,author,post);

        Comment createdComment = commentRepository.saveAndFlush(newComment);

        return createdComment.getId();
    }
}
