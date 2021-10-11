package com.graphql.graphqlsql.repository;

import com.graphql.graphqlsql.model.Comment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByAuthor_Id(Long authorId, Pageable pageable);

    List<Comment> findAllByPostId(Long postId, Pageable of);
}
