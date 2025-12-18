package com.devyourid.hoardr.api.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class Card {

    private String id;
//    private Blob face;
    private String face;
    private String name;
    private String number;
    private boolean collected;
    private float price;

}
