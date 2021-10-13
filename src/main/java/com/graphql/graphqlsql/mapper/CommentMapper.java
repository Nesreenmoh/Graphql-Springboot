package com.graphql.graphqlsql.mapper;


import com.graphql.graphqlsql.dto.AuthorDto;
import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.model.Author;
import com.graphql.graphqlsql.model.Comment;
import com.graphql.graphqlsql.model.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDto convertCommentToDto(Comment comment){
      return   CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .postId(comment.getPost().getId())
                .authorId(comment.getAuthor().getId())
                .build();
    }

    public Comment convertDtoToComment(CommentDto commentDto, Author author, Post post){
        return Comment.builder()
                .id(commentDto.getId())
                .text(commentDto.getText())
                .post(post)
                .author(author)
                .build();
    }

    public Author convertDtoToAuthor(AuthorDto authorDto){
      return  Author.builder()
              .name(authorDto.getName())
              .email(authorDto.getEmail())
              .build();
    }

    public AuthorDto convertAuthorToDto(Author author){
     return AuthorDto
             .builder()
             .name(author.getName())
             .id(author.getId())
             .email(author.getEmail())
             .build();
    }
}
