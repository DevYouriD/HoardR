package com.devyourid.hoardr.api.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@Builder
public class ExpansionSet {

    private String id;
    private String name;
    private List<Card> cards;

}
