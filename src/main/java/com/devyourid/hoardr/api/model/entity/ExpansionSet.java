package com.devyourid.hoardr.api.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Builder
public class ExpansionSet {

    private String id;
    private String name;
    private String icon;
    private LocalDate releaseDate;
    private List<Card> cards;

}
