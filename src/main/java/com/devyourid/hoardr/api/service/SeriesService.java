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

import java.util.ArrayList;
import java.util.List;

@Service
@Getter @Setter
@AllArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    // ---------- Series ----------

    public Series createSeries(String name) {
        return seriesRepository.save(new Series(null, name, new ArrayList<>()));
    }

    public List<Series> findAllSeries() {
        return seriesRepository.findAll();
    }

    public Series findExpansionSetById(String id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(id));
    }

    public Series updateSeries(String id, String name) {
        Series series = findExpansionSetById(id);
        series.setName(name);
        return seriesRepository.save(series);
    }

    public boolean deleteSeries(String id) {
        seriesRepository.deleteById(id);
        return true;
    }

    // ---------- Expansion Sets ----------

    public ExpansionSet addExpansionSet(String seriesId, String name) {
        Series series = findExpansionSetById(seriesId);

        ExpansionSet expansionSet = ExpansionSet.builder()
                .name(name)
                .cards(new ArrayList<>()
)               .build();

        series.getExpansionSets().add(expansionSet);
        seriesRepository.save(series);

        return expansionSet;
    }

    public boolean deleteExpansionSet(String seriesId, String collectionId) {
        Series series = findExpansionSetById(seriesId);

        boolean removed = series.getExpansionSets()
                .removeIf(c -> c.getId().equals(collectionId));

        if (!removed) {
            throw new GraphQLExceptionHandler.ExpansionSetNotFoundException(collectionId);
        }

        seriesRepository.save(series);
        return true;
    }

    // ---------- Cards ----------

    public Card addCard(String seriesId, String collectionId, CardDto input) {
        Series series = findExpansionSetById(seriesId);
        ExpansionSet expansionSet = findExpansionSet(series, collectionId);
        Card card = Card.builder()
                .name(input.getName())
                .number(input.getNumber())
                .collected(input.isCollected())
                .price(input.getPrice())
                .build();

        expansionSet.getCards().add(card);
        seriesRepository.save(series);

        return card;
    }

    public Card updateCard(
            String seriesId,
            String collectionId,
            String cardId,
            String name,
            Integer number,
            Boolean collected,
            Float price
    ) {
        Card card = findCard(seriesId, collectionId, cardId);

        if (name != null) card.setName(name);
        if (number != null) card.setNumber(number);
        if (collected != null) card.setCollected(collected);
        if (price != null) card.setPrice(price);

        seriesRepository.save(findExpansionSetById(seriesId));
        return card;
    }

    public boolean deleteCard(String seriesId, String collectionId, String cardId) {
        Series series = findExpansionSetById(seriesId);

        ExpansionSet expansionSet = findExpansionSet(series, collectionId);

        boolean removed = expansionSet.getCards()
                .removeIf(c -> c.getId().equals(cardId));

        if (!removed) {
            throw new GraphQLExceptionHandler.CardNotFoundException(cardId);
        }

        seriesRepository.save(series);
        return true;
    }

    // ---------- Helpers ----------

    private ExpansionSet findExpansionSet(Series series, String collectionId) {
        return series.getExpansionSets().stream()
                .filter(c -> c.getId().equals(collectionId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.ExpansionSetNotFoundException(collectionId));
    }

    private Card findCard(String seriesId, String collectionId, String cardId) {
        ExpansionSet expansionSet = findExpansionSet(findExpansionSetById(seriesId), collectionId);

        return expansionSet.getCards().stream()
                .filter(c -> c.getId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.CardNotFoundException(cardId));
    }
}
