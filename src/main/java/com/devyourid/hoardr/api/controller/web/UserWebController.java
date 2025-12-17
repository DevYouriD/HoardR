package com.devyourid.hoardr.api.controller.web;

import com.devyourid.hoardr.api.model.entity.ExpansionSet;
import com.devyourid.hoardr.api.model.entity.Series;
import com.devyourid.hoardr.api.service.CardService;
import com.devyourid.hoardr.api.service.SeriesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.devyourid.hoardr.api.utility.Paths.BASE_PATH;
import static com.devyourid.hoardr.api.utility.Paths.EXPANSION_SETS_DETAILS_PATH;
import static com.devyourid.hoardr.api.utility.Paths.SERIES_DETAILS_PATH;

@Controller
@Getter @Setter
@AllArgsConstructor
@RequestMapping(BASE_PATH)
public class UserWebController {

    SeriesService seriesService;
    CardService cardService;

    @GetMapping()
    public String home(Model model) {
        List<Series> seriesList = seriesService.findAllSeries();
        model.addAttribute("seriesList", seriesList);
        return "home.html";
    }

    @GetMapping(SERIES_DETAILS_PATH)
    public String seriesDetail(@PathVariable String id, Model model) {
        Series series = seriesService.findSeriesById(id);
        model.addAttribute("series", series);
        model.addAttribute("expansionSets", series.getExpansionSets());
        return "series-detailed";
    }

    @GetMapping(EXPANSION_SETS_DETAILS_PATH)
    public String expansionSetDetail(
            @PathVariable String seriesId,
            @PathVariable String setId,
            Model model
    ) {
        Series series = seriesService.findSeriesById(seriesId);

        ExpansionSet set = series.getExpansionSets()
                .stream()
                .filter(es -> es.getId().equals(setId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expansion set not found"));

        model.addAttribute("series", series);
        model.addAttribute("set", set);
        model.addAttribute("cards", set.getCards());

        return "expansion-set-detailed";
    }
}
