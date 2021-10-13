package com.graphql.graphqlsql.service.impl;


import com.graphql.graphqlsql.dto.PostDto;
import com.graphql.graphqlsql.exception.ResourceNotFound;
import com.graphql.graphqlsql.model.Author;
import com.graphql.graphqlsql.model.Post;
import com.graphql.graphqlsql.repository.AuthorRepository;
import com.graphql.graphqlsql.repository.PostRepository;
import com.graphql.graphqlsql.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<PostDto> getAllPostsByAuthorId(Long authorId) {
        List<Post> allByAuthor_id = postRepository.findAllByAuthor_Id(authorId);
        if (allByAuthor_id.isEmpty()) {
            throw new ResourceNotFound("No posts for this Author Id "+authorId);
        }
        return allByAuthor_id
                .stream()
                .map(post -> {
                    return PostDto.builder()
                            .id(post.getId())
                            .authorId(authorId)
                            .description(post.getDescription())
                            .category(post.getCategory())
                            .title(post.getTitle())
                            .title(post.getTitle())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getRecentPosts(Integer count, Integer offset) {
        PageRequest of = PageRequest.of(offset, count);
        Page<Post> all = postRepository.findAll(of);
        if (all.isEmpty()) {
            throw new ResourceNotFound("No recent posts!");
        }
            return all.stream()
                    .map(post -> {
                        return PostDto.builder()
                                .id(post.getId())
                                .title(post.getTitle())
                                .description(post.getDescription())
                                .category(post.getCategory())
                                .authorId(post.getAuthor().getId())
                                .build();
                    }).collect(Collectors.toList());
        }



    @Override
    public List<PostDto> getAllPosts() {

        List<Post> allPosts = postRepository.findAll();
        if (allPosts.isEmpty()) {
            throw new ResourceNotFound("No posts available!");
        }
        return allPosts.stream()
                .map(post -> {
                    return PostDto.builder()
                            .authorId(post.getAuthor().getId())
                            .id(post.getId())
                            .title(post.getTitle())
                            .description(post.getDescription())
                            .category(post.getCategory())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public Long createPost(PostDto postDto) {
        Optional<Author> authorOptional = authorRepository.findById(postDto.getAuthorId());
        Author author = authorOptional.orElseThrow(() ->
                new ResourceNotFound("Author is not exist!"));
        Post newPost = Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .category(postDto.getCategory())
                .author(author)
                .build();
        Post createdPost = postRepository.saveAndFlush(newPost);

        return createdPost.getId();
    }
}
