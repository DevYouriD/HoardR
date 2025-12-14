package com.devyourid.hoardr.api.controller;

import com.devyourid.hoardr.api.model.entity.Card;
import com.devyourid.hoardr.api.model.dto.CardDto;
import com.devyourid.hoardr.api.service.CardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

//@Controller
//@Getter @Setter
//@AllArgsConstructor
//public class CardController {
//
//    private final CardService cardService;
//
//    @QueryMapping
//    public Card findCardById(@Argument String id) {
//        return cardService.findOne(id);
//    }
//
//    @QueryMapping
//    public List<Card> findAllCards() {
//        return cardService.findAll();
//    }
//
//    @MutationMapping
//    public Card createCard(@Argument("input") CardDto newCard) {
//        Card card = new Card(null, newCard.getName(), newCard.getNumber(), newCard.isCollected(), newCard.getPrice());
//        return cardService.create(card);
//    }
//
//    @MutationMapping
//    public Card updateCard(@Argument String id, @Argument String name, @Argument Integer number, @Argument Boolean collected, Float price) {
//        return cardService.update(id, name, number, collected, price);
//    }
//
//    @MutationMapping
//    public Card deleteCard(@Argument String id) {
//        return cardService.delete(id);
//    }
//
//}
