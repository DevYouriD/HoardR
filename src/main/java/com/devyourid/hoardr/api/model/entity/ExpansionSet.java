package com.devyourid.hoardr.api.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class ExpansionSet {

    private String id;
    private String name;
    private String icon;
    private List<Card> cards;

}
