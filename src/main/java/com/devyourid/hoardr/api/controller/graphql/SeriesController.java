package com.devyourid.hoardr.api.controller.graphql;

import com.devyourid.hoardr.api.model.dto.SeriesDto;
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

    // CREATE

    @MutationMapping
    public Series createSeries(@Argument("input") SeriesDto input) {
        return seriesService.createSeries(input.getName(), input.getIcon(), input.getReleaseDate());
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
    public Series updateSeries(@Argument String id, @Argument String name, @Argument String icon, @Argument String releaseDate) {
        return seriesService.updateSeries(id, name, icon, releaseDate);
    }

    // DELETE

    @MutationMapping
    public String deleteSeries(@Argument String id) {
        return seriesService.deleteSeries(id);
    }
}
