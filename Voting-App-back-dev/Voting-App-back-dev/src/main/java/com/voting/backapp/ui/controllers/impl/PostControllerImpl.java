package com.voting.backapp.ui.controllers.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.voting.backapp.exceptions.VotingAppException;
import com.voting.backapp.service.PostService;
import com.voting.backapp.shared.constants.ApiConstants;
import com.voting.backapp.shared.constants.CommonConstants;
import com.voting.backapp.shared.dto.PostDto;
import com.voting.backapp.ui.controllers.PostController;
import com.voting.backapp.ui.model.request.PostModel;
import com.voting.backapp.ui.model.response.PostRest;
import com.voting.backapp.ui.model.response.VotingAppResponse;

@RestController
@RequestMapping(ApiConstants.API_V1 + ApiConstants.POSTS)
public class PostControllerImpl implements PostController {

    private final PostService postService;

    public PostControllerImpl(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public VotingAppResponse<PostRest> createPost(@RequestBody @Valid PostModel postModel) throws VotingAppException {
        ModelMapper modelMapper = new ModelMapper();
        PostDto postCreatedDto = postService.createPost(modelMapper.map(postModel, PostDto.class));
        return new VotingAppResponse<>(CommonConstants.SUCCESS,
                CommonConstants.POST_CREATED,
                HttpStatus.CREATED.toString(),
                modelMapper.map(postCreatedDto, PostRest.class));
    }

    @DeleteMapping(path = "/{idPost}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public VotingAppResponse<PostRest> deletePost(@PathVariable String idPost) throws VotingAppException {
        postService.deletePost(idPost);
        return new VotingAppResponse<>(CommonConstants.SUCCESS,
                CommonConstants.POST_DELETED,
                HttpStatus.OK.toString(),
                null);
    }

    @GetMapping(path = "/{idPost}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public VotingAppResponse<PostRest> getPost(@PathVariable String idPost) throws VotingAppException {
        PostDto post = postService.getPost(idPost);
        return new VotingAppResponse<>(CommonConstants.SUCCESS,
                CommonConstants.POST_FOUND,
                HttpStatus.OK.toString(),
                new ModelMapper().map(post, PostRest.class));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public VotingAppResponse<List<PostRest>> getPosts(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limite", defaultValue = "25") int limite) throws VotingAppException {
        List<PostRest> returnedPosts = new ArrayList<>();
        List<PostDto> posts = postService.getPosts(page, limite);
        posts.forEach(post -> returnedPosts.add(new ModelMapper().map(post, PostRest.class)));

        return new VotingAppResponse<>(CommonConstants.SUCCESS,
                CommonConstants.POSTS_FOUND,
                HttpStatus.OK.toString(),
                returnedPosts);
    }

    @PutMapping(path = "/{idPost}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public VotingAppResponse<PostRest> updatePost(@PathVariable String idPost, @RequestBody @Valid PostModel postModel)
            throws VotingAppException {
        ModelMapper modelMapper = new ModelMapper();
        PostDto postUpdatedDto = postService.updatePost(idPost, modelMapper.map(postModel, PostDto.class));
        return new VotingAppResponse<>(CommonConstants.SUCCESS,
                CommonConstants.POST_UPDATED,
                HttpStatus.OK.toString(),
                modelMapper.map(postUpdatedDto, PostRest.class));
    }

}
