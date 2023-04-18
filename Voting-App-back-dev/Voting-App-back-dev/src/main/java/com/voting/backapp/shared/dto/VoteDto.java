package com.voting.backapp.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteDto extends SharedDto {

    private static final long serialVersionUID = -1599580312076429836L;
    private String idVote;
    private String idPost;
    private String idUser;
    private String vote;
}
