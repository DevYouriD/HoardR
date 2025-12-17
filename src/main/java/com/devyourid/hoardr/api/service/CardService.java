package com.devyourid.hoardr.api.service;

import com.devyourid.hoardr.api.model.dto.CardDto;
import com.devyourid.hoardr.api.model.entity.Card;
import com.devyourid.hoardr.api.model.entity.ExpansionSet;
import com.devyourid.hoardr.api.model.entity.Series;
import com.devyourid.hoardr.api.repository.SeriesRepository;
import com.devyourid.hoardr.api.utility.GraphQLExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter @Setter
@AllArgsConstructor
public class CardService {

    private final SeriesRepository seriesRepository;

    public Card addCard(String seriesId, String expansionSetId, CardDto input) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(seriesId));

        ExpansionSet expansionSet = series.getExpansionSets().stream()
                .filter(es -> es.getId().equals(expansionSetId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.ExpansionSetNotFoundException(expansionSetId));

        Card card = Card.builder()
                .id(UUID.randomUUID().toString())
                .face(input.getFace())
                .name(input.getName())
                .number(input.getNumber())
                .collected(false)
                .price(input.getPrice())
                .build();

        expansionSet.getCards().add(card);
        seriesRepository.save(series);

        return card;
    }

    public Card updateCard(
            String seriesId,
            String expansionSetId,
            String cardId,
            String name,
            Integer number,
            Boolean collected,
            Float price
    ) {
        // Fetch Series
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(seriesId));

        // Find ExpansionSet
        ExpansionSet expansionSet = series.getExpansionSets().stream()
                .filter(es -> es.getId().equals(expansionSetId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.ExpansionSetNotFoundException(expansionSetId));

        // Find Card
        Card card = expansionSet.getCards().stream()
                .filter(c -> c.getId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.CardNotFoundException(cardId));

        if (name != null) card.setName(name);
        if (number != null) card.setNumber(number);
        if (collected != null) card.setCollected(collected);
        if (price != null) card.setPrice(price);

        seriesRepository.save(series);

        return card;
    }

    public String deleteCard(String seriesId, String expansionSetId, String cardId) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(seriesId));

        ExpansionSet expansionSet = series.getExpansionSets().stream()
                .filter(es -> es.getId().equals(expansionSetId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.ExpansionSetNotFoundException(expansionSetId));

        boolean removed = expansionSet.getCards()
                .removeIf(c -> c.getId().equals(cardId));

        if (!removed) {
            throw new GraphQLExceptionHandler.CardNotFoundException(cardId);
        }

        seriesRepository.save(series);

        return "Card removed Successfully!";
    }
}
