package com.voting.backapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.voting.backapp.exceptions.PostServiceException;
import com.voting.backapp.io.entities.PostEntity;
import com.voting.backapp.io.repositories.PostRepository;
import com.voting.backapp.service.PostService;
import com.voting.backapp.shared.constants.ExceptionConstant;
import com.voting.backapp.shared.dto.PostDto;
import com.voting.backapp.shared.utils.Utils;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final Utils utils;

    public PostServiceImpl(PostRepository postRepository, Utils utils) {
        this.postRepository = postRepository;
        this.utils = utils;
    }

    @Override
    public PostDto createPost(PostDto post) {
        if (Boolean.TRUE.equals(existsByPostName(post.getTitle()))) {
            throw new PostServiceException(ExceptionConstant.POST_ALREADY_EXISTS);
        }
        ModelMapper modelMapper = new ModelMapper();
        PostEntity postEntity = modelMapper.map(post, PostEntity.class);
        try {
            postEntity.setPostId(utils.generatePostId(30));
            PostEntity savedPost = postRepository.save(postEntity);
            return modelMapper.map(savedPost, PostDto.class);
        } catch (Exception e) {
            throw new PostServiceException(e.getMessage());
        }
    }

    @Override
    public void deletePost(String postId) {
        if (Boolean.FALSE.equals(existsByPostId(postId))) {
            throw new PostServiceException(ExceptionConstant.POST_NOT_FOUND);
        }
        try {
            PostEntity postEntityToDelete = postRepository.findByPostId(postId).orElseThrow(
                    () -> new PostServiceException(ExceptionConstant.POST_NOT_FOUND));
            postRepository.delete(postEntityToDelete);
        } catch (Exception e) {
            throw new PostServiceException(e.getMessage());
        }

    }

    @Override
    public PostDto getPost(String postId) {
        if (Boolean.FALSE.equals(existsByPostId(postId))) {
            throw new PostServiceException(ExceptionConstant.POST_NOT_FOUND);
        }
        try {
            PostEntity postEntity = postRepository.findByPostId(postId).orElseThrow(
                    () -> new PostServiceException(ExceptionConstant.POST_NOT_FOUND));
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(postEntity, PostDto.class);
        } catch (Exception e) {
            throw new PostServiceException(e.getMessage());
        }
    }

    @Override
    public List<PostDto> getPosts(int page, int limit) {
        List<PostDto> returnValues = new ArrayList<>();
        if (page > 0)
            page -= 1;
        try {
            Pageable pageableRequest = PageRequest.of(page, limit);
            Page<PostEntity> postsPage = postRepository.findAll(pageableRequest);
            List<PostEntity> posts = postsPage.getContent();
            ModelMapper modelMapper = new ModelMapper();
            for (PostEntity postEntity : posts) {
                PostDto postDto = modelMapper.map(postEntity, PostDto.class);
                returnValues.add(postDto);
            }
        } catch (Exception e) {
            throw new PostServiceException(e.getMessage());
        }
        return returnValues;
    }

    @Override
    public PostDto updatePost(String postId, PostDto post) {
        if (Boolean.FALSE.equals(existsByPostId(postId))) {
            throw new PostServiceException(ExceptionConstant.POST_NOT_FOUND);
        }
        try {
            PostEntity postEntity = postRepository.findByPostId(postId).orElseThrow(
                    () -> new PostServiceException(ExceptionConstant.POST_NOT_FOUND));
            postEntity.setTitle(post.getTitle());
            postEntity.setDescription(post.getDescription());
            PostEntity updatedPost = postRepository.save(postEntity);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(updatedPost, PostDto.class);
        } catch (Exception e) {
            throw new PostServiceException(e.getMessage());
        }
    }

    /**
     * Check if post exists by post name
     * 
     * @param postName post name
     * @return true if exists, false otherwise
     */
    private Boolean existsByPostName(String postName) {
        return postRepository.existsByTitle(postName);
    }

    /**
     * Check if post exists by post id
     * 
     * @param postId post id
     * @return true if exists, false otherwise
     */
    private Boolean existsByPostId(String postId) {
        return postRepository.existsByPostId(postId);
    }

}
