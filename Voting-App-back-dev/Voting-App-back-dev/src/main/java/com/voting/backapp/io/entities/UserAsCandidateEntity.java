package com.voting.backapp.io.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users_as_candidates", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "user_as_candidate_id" })
})
@Getter
@Setter
public class UserAsCandidateEntity implements Serializable {
    private static final long serialVersionUID = -2868261645569139587L;

    @Id
    private Long id;

    @Column(name = "user_as_candidate_id", nullable = false, length = 30)
    private String userAsCandidateId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JsonIgnore
    private UserEntity candidate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PostEntity post;

    @Column(name = "is_valid_candidate", nullable = false)
    private boolean isValidCandidate = false;

    @OneToMany(mappedBy = "userAsCandidate", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<VoteEntity> votes = new ArrayList<>();

    @Column(name = "number_of_votes", nullable = false)
    private int numberOfVotes = 0;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        numberOfVotes = getTotalNumberOfUpVotes();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
        numberOfVotes = getTotalNumberOfUpVotes();
    }

    private int getTotalNumberOfUpVotes() {
        int total = 0;
        if (votes == null)
            return total;
        for (VoteEntity vote : votes) {
            if (vote.isValidVote()) {
                total++;
            }
        }
        return total;
    }

}
