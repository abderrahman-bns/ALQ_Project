package com.voting.backapp.io.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "posts", uniqueConstraints = {
        @UniqueConstraint(columnNames = "post_id"),
        @UniqueConstraint(columnNames = "title")
})
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Getter
@Setter
public class PostEntity extends AbstractEntity {
    private static final long serialVersionUID = 2694687487788915020L;

    @Column(name = "post_id", nullable = false, length = 30)
    private String postId;

    @NotEmpty(message = "Title is required")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @NotEmpty(message = "Description is required")
    @Size(min = 2, max = 150, message = "Description must be between 2 and 150 characters")
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserAsCandidateEntity> candidates;

}
