package com.devyourid.hoardr.api.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CardDto {
    private String name;
    private int number;
    private boolean collected;
    private float price;
}
