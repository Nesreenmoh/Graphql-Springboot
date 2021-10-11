package com.graphql.graphqlsql.repository;

import com.graphql.graphqlsql.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
