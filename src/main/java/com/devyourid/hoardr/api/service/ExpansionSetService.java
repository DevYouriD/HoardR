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

    public ExpansionSet addExpansionSet(String seriesId, String name) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(seriesId));

        ExpansionSet expansionSet = ExpansionSet.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .cards(new ArrayList<>())
                .build();

        series.getExpansionSets().add(expansionSet);
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
