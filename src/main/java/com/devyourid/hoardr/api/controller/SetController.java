package com.devyourid.hoardr.api.controller;

import com.devyourid.hoardr.api.model.dto.CardDto;
import com.devyourid.hoardr.api.model.dto.CollectionDto;
import com.devyourid.hoardr.api.model.dto.SetDto;
import com.devyourid.hoardr.api.model.entity.Card;
import com.devyourid.hoardr.api.model.entity.Collection;
import com.devyourid.hoardr.api.model.entity.Set;
import com.devyourid.hoardr.api.service.SetService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Getter @Setter
@AllArgsConstructor
public class SetController {

    private final SetService setService;

    /* ---------- Queries ---------- */

    @QueryMapping
    public Set findSetById(@Argument String id) {
        return setService.findById(id);
    }

    @QueryMapping
    public List<Set> findAllSets() {
        return setService.findAll();
    }

    // ---------- Sets ---------- */

    @MutationMapping
    public Set createSet(@Argument("setInput") SetDto input) {
        return setService.createSet(input.getName());
    }

    @MutationMapping
    public Set updateSet(@Argument String id, @Argument String name) {
        return setService.updateSet(id, name);
    }

    @MutationMapping
    public Boolean deleteSet(@Argument String id) {
        return setService.deleteSet(id);
    }

    /* ---------- Collections ---------- */

    @MutationMapping
    public Collection addCollectionToSet(@Argument String setId, @Argument("collectionInput") CollectionDto input) {
        return setService.addCollection(setId, input.getName());
    }

    @MutationMapping
    public Boolean deleteCollection(@Argument String setId, @Argument String collectionId) {
        return setService.deleteCollection(setId, collectionId);
    }

    /* ---------- Cards ---------- */

    @MutationMapping
    public Card addCardToCollection(@Argument String setId, @Argument String collectionId,@Argument("cardInput") CardDto input) {
        return setService.addCard(setId, collectionId, input);
    }

    @MutationMapping
    public Card updateCard(
            @Argument String setId,
            @Argument String collectionId,
            @Argument String cardId,
            @Argument String name,
            @Argument Integer number,
            @Argument Boolean collected,
            @Argument Float price
    ) {
        return setService.updateCard(setId, collectionId, cardId, name, number, collected, price);
    }

    @MutationMapping
    public Boolean deleteCard(@Argument String setId, @Argument String collectionId, @Argument String cardId) {
        return setService.deleteCard(setId, collectionId, cardId);
    }
}
