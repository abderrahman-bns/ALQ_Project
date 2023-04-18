package com.voting.backapp.shared.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PostDto extends SharedDto {
    private static final long serialVersionUID = 4367191394184904380L;
    private String idPost;
    private String title;
    private String description;
    private List<CandidateDto> candidates;
}
