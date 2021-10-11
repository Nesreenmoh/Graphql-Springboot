package com.graphql.graphqlsql.service.impl;

import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.dto.PostDto;
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

    public CommentServiceImpl(CommentRepository commentRepository, AuthorRepository authorRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<CommentDto> getFirstCommentsByAuthor_Id(Long authorId, Integer first) {
        List<Comment> allByAuthor_id = commentRepository.findAllByAuthor_Id
                (authorId, PageRequest.of(0, first));
        return allByAuthor_id.stream()
                .map(comment -> CommentDto.builder()
                            .id(comment.getId())
                            .text(comment.getText())
                        .postId(comment.getPost().getId())
                        .authorId(comment.getAuthor().getId())
                            .build()
                ).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getFirstCommentsByPostId(Long postId, Integer first) {
        List<Comment> firstCommentsByPostId =
                commentRepository.findAllByPostId(postId, PageRequest.of(0, first));
        return firstCommentsByPostId.stream()
                .map(comment -> CommentDto.builder()
                        .id(comment.getId())
                        .text(comment.getText())
                        .authorId(comment.getAuthor().getId())
                        .postId(comment.getPost().getId())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getComments(Integer count, Integer offset) {
        Page<Comment> someComments = commentRepository.findAll(PageRequest.of(offset, count));
        if(someComments.isEmpty()){
            throw new RuntimeException("No Comments Available");
        }
        return someComments.stream()
                .map(comment -> CommentDto.builder()
                        .id(comment.getId())
                        .text(comment.getText())
                        .postId(comment.getPost().getId())
                        .authorId(comment.getAuthor().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(Long id) {
        Optional<Comment> commentbyId = commentRepository.findById(id);
        if(!commentbyId.isPresent()){
            throw new RuntimeException("No Comment Available!");
        }
        Comment retriveComment = commentbyId.get();
        return PostDto.builder()
                .id(retriveComment.getPost().getId())
                .title(retriveComment.getPost().getTitle())
                .description(retriveComment.getPost().getDescription())
                .authorId(retriveComment.getAuthor().getId())
                .category(retriveComment.getPost().getCategory())
                       .build();
    }

    @Override
    public Long createComment(CommentDto commentDto) {
        Author author = authorRepository.findById(commentDto.getAuthorId()).get();
        Post post = postRepository.findById(commentDto.getPostId()).get();
       Comment newComment = Comment.builder()
               .id(commentDto.getId())
               .text(commentDto.getText())
               .post(post)
               .author(author)
               .build();

        Comment createdComment = commentRepository.saveAndFlush(newComment);

        return createdComment.getId();
    }
}
