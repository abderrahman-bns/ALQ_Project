package com.voting.backapp.io.repositories;

import com.voting.backapp.io.entities.PostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {
    Optional<PostEntity> findByTitle(String title);

    Optional<PostEntity> findByPostId(String postId);

    Boolean existsByPostId(String postId);

    Boolean existsByTitle(String title);
}