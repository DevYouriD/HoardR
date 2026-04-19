package com.devyourid.hoardr.api.service;

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
import java.util.List;
import java.util.Optional;

@Service
@Getter @Setter
@AllArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public Series createSeries(String name, String icon, String releaseDate) {
        return seriesRepository.save(
                new Series(
                        null,
                        name,
                        icon,
                        releaseDate != null && !releaseDate.isBlank() ? LocalDate.parse(releaseDate) : LocalDate.now(),
                        new ArrayList<>()));
    }

    public List<Series> findAllSeries() {
        List<Series> seriesList = seriesRepository.findAll();

        // Sort by newest to oldest
        seriesList.sort(
                Comparator.comparing(
                        Series::getReleaseDate,
                        Comparator.nullsLast(Comparator.naturalOrder())
                ).reversed()
        );

        return seriesList;
    }

    public Series findSeriesById(String id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(id));
    }

    public Series updateSeries(String id, String name, String icon, String releaseDate) {
        Series series = findSeriesById(id);
        series.setName(name);
        series.setIcon(icon);
        series.setReleaseDate(releaseDate != null && !releaseDate.isBlank() ? LocalDate.parse(releaseDate) : LocalDate.now());
        return seriesRepository.save(series);
    }

    public String deleteSeries(String id) {
        Optional<Series> seriesOptional = seriesRepository.findById(id);

        if (seriesOptional.isPresent()) {
            seriesRepository.deleteById(id);
            return "Series removed Successfully!";
        } else {
            throw new GraphQLExceptionHandler.SeriesNotFoundException(id);
        }
    }
}
