package com.voting.backapp.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CandidateDto implements Serializable {

    private static final long serialVersionUID = 1833724146291476946L;

    private long id;
    private String userAsCandidateId;

    private UserDto candidate;

    private PostDto post;

    private boolean isValidCandidate = false;

    private List<VoteDto> votes;

    private int numberOfVotes;

    private Date createdAt;

    private Date updatedAt;

}