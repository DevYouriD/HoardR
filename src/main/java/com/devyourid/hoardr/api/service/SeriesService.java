package com.devyourid.hoardr.api.service;

import com.devyourid.hoardr.api.model.entity.Series;
import com.devyourid.hoardr.api.repository.SeriesRepository;
import com.devyourid.hoardr.api.utility.GraphQLExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter @Setter
@AllArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public Series createSeries(String name) {
        return seriesRepository.save(new Series(null, name, new ArrayList<>()));
    }

    public List<Series> findAllSeries() {
        return seriesRepository.findAll();
    }

    public Series findSeriesById(String id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new GraphQLExceptionHandler.SeriesNotFoundException(id));
    }

    public Series updateSeries(String id, String name) {
        Series series = findSeriesById(id);
        series.setName(name);
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
