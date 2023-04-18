package com.voting.backapp.service.impl;

import com.voting.backapp.exceptions.UserAsCandidateAndPostServiceException;
import com.voting.backapp.io.entities.PostEntity;
import com.voting.backapp.io.entities.UserAsCandidateEntity;
import com.voting.backapp.io.entities.UserEntity;
import com.voting.backapp.io.repositories.PostRepository;
import com.voting.backapp.io.repositories.UserAsCandidateRepository;
import com.voting.backapp.io.repositories.UserRepository;
import com.voting.backapp.service.UserAsCandidateAndPostService;
import com.voting.backapp.shared.constants.ExceptionConstant;
import com.voting.backapp.shared.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class UserAsCandidateAndPostServiceImpl implements UserAsCandidateAndPostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserAsCandidateRepository userAsCandidateRepository;

    public UserAsCandidateAndPostServiceImpl(UserRepository userRepository, PostRepository postRepository, Utils utils, UserAsCandidateRepository userAsCandidateRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.userAsCandidateRepository = userAsCandidateRepository;
    }

    @Override
    public void addCandidateToPost(String postId, String userId) {
        checkIfValid(postId, userId);
        UserEntity userToFound = userRepository.findByUserId(userId).orElseThrow(
                () -> new UserAsCandidateAndPostServiceException(ExceptionConstant.USER_NOT_FOUND)
        );
        PostEntity postToFound = postRepository.findByPostId(postId).orElseThrow(
                () -> new UserAsCandidateAndPostServiceException(ExceptionConstant.POST_NOT_FOUND)
        );
        UserAsCandidateEntity userAsCandidateEntity = new UserAsCandidateEntity();
        userAsCandidateEntity.setUserAsCandidateId(userToFound.getUserId());
        userAsCandidateEntity.setPost(postToFound);
        userAsCandidateEntity.setCandidate(userToFound);
        userAsCandidateEntity.setValidCandidate(Boolean.FALSE);

        try {
            userAsCandidateRepository.save(userAsCandidateEntity);
        } catch (Exception e) {
            throw new UserAsCandidateAndPostServiceException(e.getMessage());
        }
    }

    @Override
    public void removeCandidateFromPost(String postId, String userId) {
        checkIfValid(postId, userId);
        UserAsCandidateEntity userAsCandidateEntity = userAsCandidateRepository.findByPostIdAndUserAsCandidateId(postId, userId).orElseThrow(
                () -> new UserAsCandidateAndPostServiceException(ExceptionConstant.CANDIDATE_NOT_FOUND_FOR_POST)
        );
        try {
            userAsCandidateRepository.delete(userAsCandidateEntity);
        } catch (Exception e) {
            throw new UserAsCandidateAndPostServiceException(e.getMessage());
        }
    }

    private void checkIfValid(String postId, String userId) {
        if (userAsCandidateRepository.findByPostIdAndUserAsCandidateId(postId, userId).isPresent()) {
            throw new UserAsCandidateAndPostServiceException(ExceptionConstant.CANDIDATE_ALREADY_EXISTS_FOR_POST);
        }
        if (Boolean.FALSE.equals(userRepository.existsByUserId(userId))) {
            throw new UserAsCandidateAndPostServiceException(ExceptionConstant.USER_NOT_FOUND);
        }
        if (Boolean.FALSE.equals(postRepository.existsByPostId(postId))) {
            throw new UserAsCandidateAndPostServiceException(ExceptionConstant.POST_NOT_FOUND);
        }
    }
}
