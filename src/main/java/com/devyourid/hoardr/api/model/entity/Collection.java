package com.devyourid.hoardr.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@Document(collection = "collections")
public class Collection {

    @Id
    private String id;
    private String name;
    private List<Card> cards;

}
