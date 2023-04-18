package com.voting.backapp.io.repositories;

import com.voting.backapp.io.entities.UserAsCandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAsCandidateRepository extends JpaRepository<UserAsCandidateEntity, Long> {
    Optional<UserAsCandidateEntity> findByUserAsCandidateId(String userAsCandidateId);

    Optional<UserAsCandidateEntity> findByPostIdAndUserAsCandidateId(String postId, String userId);
}