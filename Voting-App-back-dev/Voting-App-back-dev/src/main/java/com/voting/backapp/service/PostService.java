package com.voting.backapp.service;

import com.voting.backapp.shared.dto.PostDto;

import java.util.List;

public interface PostService {
    /**
     * Create a new post
     * 
     * @param post post to create
     * @return created post
     */
    PostDto createPost(PostDto post);

    /**
     * Get a post by id
     * 
     * @param postId id of the post
     * @return post
     */
    PostDto getPost(String postId);

    /**
     * Update a post
     * 
     * @param postId id of the post
     * @param post   post to update
     * @return updated post
     */
    PostDto updatePost(String postId, PostDto post);

    /**
     * Delete a post
     * 
     * @param postId id of the post
     */
    void deletePost(String postId);

    /**
     * Get all posts
     * 
     * @param page  page number
     * @param limit number of posts per page
     * @return list of posts
     */
    List<PostDto> getPosts(int page, int limit);
}
