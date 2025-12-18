package com.devyourid.hoardr.api.service;

import com.devyourid.hoardr.api.model.entity.ExpansionSet;
import com.devyourid.hoardr.api.model.entity.Series;
import com.devyourid.hoardr.api.repository.SeriesRepository;
import com.devyourid.hoardr.api.utility.GraphQLExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@Getter @Setter
@AllArgsConstructor
public class ExpansionSetService {

    private final SeriesRepository seriesRepository;

    public ExpansionSet addExpansionSet(String seriesId, String name, String icon) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(seriesId));

        ExpansionSet expansionSet = ExpansionSet.builder()
                .id(UUID.randomUUID().toString())
                .icon(icon)
                .name(name)
                .cards(new ArrayList<>())
                .build();

        series.getExpansionSets().add(expansionSet);
        seriesRepository.save(series);

        return expansionSet;
    }

    public ExpansionSet findExpansionSetById(String seriesId, String expansionSetId) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(seriesId));

        return series.getExpansionSets().stream()
                .filter(es -> es.getId().equals(expansionSetId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.ExpansionSetNotFoundException(expansionSetId));
    }

    public ExpansionSet updateExpansionSet(
            String seriesId,
            String expansionSetId,
            String name,
            String icon
    ) {
        // Fetch Series
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(seriesId));

        // Find ExpansionSet
        ExpansionSet expansionSet = series.getExpansionSets().stream()
                .filter(es -> es.getId().equals(expansionSetId))
                .findFirst()
                .orElseThrow(() -> new GraphQLExceptionHandler.ExpansionSetNotFoundException(expansionSetId));

        if (name != null) expansionSet.setName(name);
        if (icon != null) expansionSet.setIcon(icon);

        seriesRepository.save(series);

        return expansionSet;
    }

    public String deleteExpansionSet(String seriesId, String expansionSetId) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(seriesId));

        boolean removed = series.getExpansionSets()
                .removeIf(c -> c.getId().equals(expansionSetId));

        if (!removed) {
            throw new GraphQLExceptionHandler.ExpansionSetNotFoundException(expansionSetId);
        }

        seriesRepository.save(series);
        return "Expansion Set removed Successfully!";
    }
}
