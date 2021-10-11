package com.graphql.graphqlsql.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String category;
    private Long authorId;
}
