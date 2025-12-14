package com.devyourid.hoardr.api.service;

import com.devyourid.hoardr.api.model.entity.Card;
import com.devyourid.hoardr.api.repository.CardRepository;
import com.devyourid.hoardr.api.utility.GraphQLExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//@Getter @Setter
//@AllArgsConstructor
//public class CardService {
//
//    private final CardRepository cardRepository;
//
//    // CREATE
//    public Card create(Card card) {
//        return cardRepository.save(card);
//    }
//
//    // READ
//    public List<Card> findAll() {
//        return cardRepository.findAll();
//    }
//
//    public Card findOne(String id) {
//        return cardRepository.findById(id)
//                .orElseThrow(() -> new GraphQLExceptionHandler.CardNotFoundException(id));
//    }
//
//    // UPDATE
//    public Card update(String id, String name, Integer number, Boolean collected, Float price) {
//        Card existing = cardRepository.findById(id)
//                .orElseThrow(() -> new GraphQLExceptionHandler.CardNotFoundException(id));
//
//        if (name != null) existing.setName(name);
//        if (number != null) existing.setNumber(number);
//        if (collected != null) existing.setCollected(collected);
//        if (price != null) existing.setPrice(price);
//
//        return cardRepository.save(existing);
//    }
//
//    // DELETE
//    public Card delete(String id) {
//        Card existing = cardRepository.findById(id)
//                .orElseThrow(() -> new GraphQLExceptionHandler.CardNotFoundException(id));
//
//        cardRepository.deleteById(id);
//        return existing;
//    }
//
//}
