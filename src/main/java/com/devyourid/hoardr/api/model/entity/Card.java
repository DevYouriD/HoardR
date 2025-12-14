package com.devyourid.hoardr.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@AllArgsConstructor
@Document(collection = "cards")
public class Card {

    @Id
    private String id;
//    private Blob face;
    private String name;
    private int number;
    private boolean collected;
    private float price;

}
