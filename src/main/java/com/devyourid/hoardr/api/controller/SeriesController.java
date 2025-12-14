package com.devyourid.hoardr.api.controller;

import com.devyourid.hoardr.api.model.dto.CardDto;
import com.devyourid.hoardr.api.model.dto.ExpansionSetDto;
import com.devyourid.hoardr.api.model.dto.SeriesDto;
import com.devyourid.hoardr.api.model.entity.Card;
import com.devyourid.hoardr.api.model.entity.ExpansionSet;
import com.devyourid.hoardr.api.model.entity.Series;
import com.devyourid.hoardr.api.service.CardService;
import com.devyourid.hoardr.api.service.ExpansionSetService;
import com.devyourid.hoardr.api.service.SeriesService;
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
public class SeriesController {

    private final SeriesService seriesService;
    private final ExpansionSetService expansionSetService;
    private final CardService cardService;

    // ---------- SERIES ---------- //

    // CREATE

    @MutationMapping
    public Series createSeries(@Argument("input") SeriesDto input) {
        return seriesService.createSeries(input.getName());
    }

    // READ

    @QueryMapping
    public Series findSeriesById(@Argument String id) {
        return seriesService.findSeriesById(id);
    }

    @QueryMapping
    public List<Series> findAllSeries() {
        return seriesService.findAllSeries();
    }

    // UPDATE

    @MutationMapping
    public Series updateSeries(@Argument String id, @Argument String name) {
        return seriesService.updateSeries(id, name);
    }

    // DELETE

    @MutationMapping
    public String deleteSeries(@Argument String id) {
        return seriesService.deleteSeries(id);
    }

    // ---------- EXPANSION SETS ---------- //

    // CREATE

    @MutationMapping
    public ExpansionSet addExpansionSetToSeries(@Argument String seriesId, @Argument("input") ExpansionSetDto input) {
        return expansionSetService.addExpansionSet(seriesId, input.getName());
    }

    // UPDATE

    //TODO: Update ExpansionsSet
    @MutationMapping
    public ExpansionSet updateExpansionSet(@Argument String seriesId, @Argument String expansionSetId, @Argument String name) {
        return expansionSetService.updateExpansionSet(seriesId, expansionSetId, name);
    }

    // DELETE

    @MutationMapping
    public String deleteExpansionSet(@Argument String seriesId, @Argument String expansionSetId) {
        return expansionSetService.deleteExpansionSet(seriesId, expansionSetId);
    }

    // ---------- CARDS ---------- //

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
