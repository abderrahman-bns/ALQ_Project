package com.voting.backapp.ui.controllers;

import java.util.List;

import com.voting.backapp.exceptions.VotingAppException;
import com.voting.backapp.ui.model.request.PostModel;
import com.voting.backapp.ui.model.response.PostRest;
import com.voting.backapp.ui.model.response.VotingAppResponse;

public interface PostController {

    /**
     * Create a new post
     * 
     * @param postModel PostModel object
     * @return PostRest object
     * @throws VotingAppException
     */
    VotingAppResponse<PostRest> createPost(PostModel postModel) throws VotingAppException;

    /**
     * Get a post by id
     * 
     * @param idPost id of the post to get
     * @return PostRest object
     * @throws VotingAppException
     */
    VotingAppResponse<PostRest> getPost(String idPost) throws VotingAppException;

    /**
     * Update a post
     * 
     * @param idPost    id of the post to update
     * @param postModel PostModel object
     * @return
     * @throws VotingAppException
     */
    VotingAppResponse<PostRest> updatePost(String idPost, PostModel postModel) throws VotingAppException;

    /**
     * Delete a post
     * 
     * @param idPost id of the post to delete
     * @return PostRest object
     * @throws VotingAppException
     */

    VotingAppResponse<PostRest> deletePost(String idPost) throws VotingAppException;

    /**
     * Get all posts
     * 
     * @return List of PostRest objects
     * @throws VotingAppException
     */

    VotingAppResponse<List<PostRest>> getPosts(int page, int limite) throws VotingAppException;
}
