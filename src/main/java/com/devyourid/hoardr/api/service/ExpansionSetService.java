package com.devyourid.hoardr.api.service;

import com.devyourid.hoardr.api.model.entity.ExpansionSet;
import com.devyourid.hoardr.api.model.entity.Series;
import com.devyourid.hoardr.api.repository.SeriesRepository;
import com.devyourid.hoardr.api.utility.GraphQLExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

@Service
@Getter @Setter
@AllArgsConstructor
public class ExpansionSetService {

    private final SeriesRepository seriesRepository;

    public ExpansionSet addExpansionSet(String seriesId, String name, String icon, String releaseDate) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(seriesId));

        ExpansionSet expansionSet = ExpansionSet.builder()
                .id(UUID.randomUUID().toString())
                .icon(icon)
                .name(name)
                .releaseDate(releaseDate != null && !releaseDate.isBlank() ? LocalDate.parse(releaseDate) : LocalDate.now())
                .cards(new ArrayList<>())
                .build();

        series.getExpansionSets().add(expansionSet);

        // Sort by newest to oldest
        if (series.getExpansionSets() != null) {
            series.getExpansionSets().sort(
                    Comparator.comparing(
                            ExpansionSet::getReleaseDate,
                            Comparator.nullsLast(Comparator.naturalOrder())
                    ).reversed()
            );
        }

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
            String icon,
            String releaseDate
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
        if (releaseDate != null) {
            expansionSet.setReleaseDate(LocalDate.parse(releaseDate));
        }

        // Sort by newest to oldest
        if (series.getExpansionSets() != null) {
            series.getExpansionSets().sort(
                    Comparator.comparing(
                            ExpansionSet::getReleaseDate,
                            Comparator.nullsLast(Comparator.naturalOrder())
                    ).reversed()
            );
        }

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
