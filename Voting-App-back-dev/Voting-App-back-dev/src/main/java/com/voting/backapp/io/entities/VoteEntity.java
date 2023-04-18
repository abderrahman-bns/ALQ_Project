package com.voting.backapp.io.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "vote_id")
})
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Getter
@Setter
public class VoteEntity extends AbstractEntity {
    private static final long serialVersionUID = 2187894036030776689L;
    @Column(name = "vote_id", nullable = false, length = 30)
    private String voteId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;

    @NotNull(message = "valid Vote cannot be null")
    @Column(name = "valid_vote", nullable = false)
    private boolean validVote;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_as_candidate_id", nullable = false)
    private UserAsCandidateEntity userAsCandidate;

}
