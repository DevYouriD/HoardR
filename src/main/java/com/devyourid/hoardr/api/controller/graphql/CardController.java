package com.devyourid.hoardr.api.controller.graphql;

import com.devyourid.hoardr.api.model.dto.CardDto;
import com.devyourid.hoardr.api.model.entity.Card;
import com.devyourid.hoardr.api.service.CardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@Getter @Setter
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    // CREATE

    @MutationMapping
    public Card addCardToExpansionSet(@Argument String seriesId, @Argument String expansionSetId, @Argument("input") CardDto input) {
        return cardService.addCard(seriesId, expansionSetId, input);
    }

    // UPDATE

    @MutationMapping
    public Card updateCard(
            @Argument String seriesId,
            @Argument String expansionSetId,
            @Argument String cardId,
            @Argument String name,
            @Argument Integer number,
            @Argument Boolean collected,
            @Argument Float price
    ) {
        return cardService.updateCard(seriesId, expansionSetId, cardId, name, number, collected, price);
    }

    // DELETE

    @MutationMapping
    public String deleteCard(@Argument String seriesId, @Argument String expansionSetId, @Argument String cardId) {
        return cardService.deleteCard(seriesId, expansionSetId, cardId);
    }
}
