package com.graphql.graphqlsql.service;


import com.graphql.graphqlsql.dto.CommentDto;
import com.graphql.graphqlsql.dto.PostDto;
import com.graphql.graphqlsql.model.Comment;

import java.awt.print.Pageable;
import java.util.List;

public interface CommentService {

 List<CommentDto> getFirstCommentsByAuthor_Id(Long authorId, Integer first);

 List<CommentDto> getFirstCommentsByPostId(Long id, Integer first);

 List<CommentDto> getComments(Integer count, Integer offset);

 PostDto getPost(Long id);

    Long createComment(CommentDto commentDto);
}
