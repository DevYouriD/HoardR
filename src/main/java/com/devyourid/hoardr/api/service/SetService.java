package com.devyourid.hoardr.api.service;

import com.devyourid.hoardr.api.model.dto.CardDto;
import com.devyourid.hoardr.api.model.entity.Card;
import com.devyourid.hoardr.api.model.entity.Collection;
import com.devyourid.hoardr.api.model.entity.Set;
import com.devyourid.hoardr.api.repository.SetRepository;
import com.devyourid.hoardr.api.utility.GraphQLExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter @Setter
@AllArgsConstructor
public class SetService {

    private final SetRepository setRepository;

    // ---------- Sets ----------

    public Set createSet(String name) {
        return setRepository.save(new Set(null, name, new ArrayList<>()));
    }

    public List<Set> findAll() {
        return setRepository.findAll();
    }

    public Set findById(String id) {
        return setRepository.findById(id)
                .orElseThrow(() -> new GraphQLExceptionHandler.SetNotFoundException(id));
    }

    public Set updateSet(String id, String name) {
        Set set = findById(id);
        set.setName(name);
        return setRepository.save(set);
    }

    public boolean deleteSet(String id) {
        setRepository.deleteById(id);
        return true;
    }

    // ---------- Collections ----------

    public Collection addCollection(String setId, String name) {
        Set set = findById(setId);

        Collection collection = new Collection(null, name, new ArrayList<>());

        set.getCollections().add(collection);
        setRepository.save(set);

        return collection;
    }

    public boolean deleteCollection(String setId, String collectionId) {
        Set set = findById(setId);

        boolean removed = set.getCollections()
                .removeIf(c -> c.getId().equals(collectionId));

        if (!removed) {
            throw new GraphQLExceptionHandler.CollectionNotFoundException(collectionId);
        }

        setRepository.save(set);
        return true;
    }

    // ---------- Cards ----------

    public Card addCard(String setId, String collectionId, CardDto input) {
        Set set = findById(setId);
        Collection collection = findCollection(set, collectionId);
        Card card = new Card(null, input.getName(), input.getNumber(), input.isCollected(), input.getPrice());

        collection.getCards().add(card);
        setRepository.save(set);

        return card;
    }

    public Card updateCard(
            String setId,
            String collectionId,
            String cardId,
            String name,
            Integer number,
            Boolean collected,
            Float price
    ) {
        Card card = findCard(setId, collectionId, cardId);

        if (name != null) card.setName(name);
        if (number != null) card.setNumber(number);
        if (collected != null) card.setCollected(collected);
        if (price != null) card.setPrice(price);

        setRepository.save(findById(setId));
        return card;
    }

    public boolean deleteCard(String setId, String collectionId, String cardId) {
        Set set = findById(setId);

        Collection collection = findCollection(set, collectionId);

        boolean removed = collection.getCards()
                .removeIf(c -> c.getId().equals(cardId));

        if (!removed) {
            throw new GraphQLExceptionHandler.CardNotFoundException(cardId);
        }

        setRepository.save(set);
        return true;
    }

    // ---------- Helpers ----------

    private Collection findCollection(Set set, String collectionId) {
        return set.getCollections().stream()
                .filter(c -> c.getId().equals(collectionId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.CollectionNotFoundException(collectionId));
    }

    private Card findCard(String setId, String collectionId, String cardId) {
        Collection collection = findCollection(findById(setId), collectionId);

        return collection.getCards().stream()
                .filter(c -> c.getId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.CardNotFoundException(cardId));
    }
}
