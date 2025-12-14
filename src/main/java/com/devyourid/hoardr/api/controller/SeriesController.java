package com.devyourid.hoardr.api.controller;

import com.devyourid.hoardr.api.model.dto.CardDto;
import com.devyourid.hoardr.api.model.dto.ExpansionSetDto;
import com.devyourid.hoardr.api.model.dto.SeriesDto;
import com.devyourid.hoardr.api.model.entity.Card;
import com.devyourid.hoardr.api.model.entity.ExpansionSet;
import com.devyourid.hoardr.api.model.entity.Series;
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

    /* ---------- Queries ---------- */

    @QueryMapping
    public Series findSeriesById(@Argument String id) {
        return seriesService.findExpansionSetById(id);
    }

    @QueryMapping
    public List<Series> findAllSeries() {
        return seriesService.findAllSeries();
    }

    // ---------- Series ---------- */

    @MutationMapping
    public Series createSeries(@Argument("seriesInput") SeriesDto input) {
        return seriesService.createSeries(input.getName());
    }

    @MutationMapping
    public Series updateSeries(@Argument String id, @Argument String name) {
        return seriesService.updateSeries(id, name);
    }

    @MutationMapping
    public Boolean deleteSeries(@Argument String id) {
        return seriesService.deleteSeries(id);
    }

    /* ---------- Expansion Sets ---------- */

    @MutationMapping
    public ExpansionSet addExpansionSetToSeries(@Argument String seriesId, @Argument("expansionSetInput") ExpansionSetDto input) {
        return seriesService.addExpansionSet(seriesId, input.getName());
    }

    @MutationMapping
    public Boolean deleteExpansionSet(@Argument String seriesId, @Argument String expansionSetId) {
        return seriesService.deleteExpansionSet(seriesId, expansionSetId);
    }

    /* ---------- Cards ---------- */

    @MutationMapping
    public Card addCardToExpansionSet(@Argument String seriesId, @Argument String expansionSetId, @Argument("cardInput") CardDto input) {
        return seriesService.addCard(seriesId, expansionSetId, input);
    }

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
        return seriesService.updateCard(seriesId, expansionSetId, cardId, name, number, collected, price);
    }

    @MutationMapping
    public Boolean deleteCard(@Argument String seriesId, @Argument String expansionSetId, @Argument String cardId) {
        return seriesService.deleteCard(seriesId, expansionSetId, cardId);
    }
}
