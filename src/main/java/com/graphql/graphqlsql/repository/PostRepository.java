package com.graphql.graphqlsql.repository;

import com.graphql.graphqlsql.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAuthor_Id(Long authorId);
}
