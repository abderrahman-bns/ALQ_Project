package com.voting.backapp.service;

public interface UserAsCandidateAndPostService {
    void addCandidateToPost(String postId, String userId);
    void removeCandidateFromPost(String postId, String userId);
}
