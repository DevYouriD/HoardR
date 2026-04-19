package com.devyourid.hoardr.api.controller.web;

import com.devyourid.hoardr.api.model.dto.CardDto;
import com.devyourid.hoardr.api.model.entity.ExpansionSet;
import com.devyourid.hoardr.api.model.entity.Series;
import com.devyourid.hoardr.api.service.CardService;
import com.devyourid.hoardr.api.service.ExpansionSetService;
import com.devyourid.hoardr.api.service.SeriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.devyourid.hoardr.api.utility.Paths.ADD_CARD_PATH;
import static com.devyourid.hoardr.api.utility.Paths.ADD_EXPANSION_PATH;
import static com.devyourid.hoardr.api.utility.Paths.ADD_SERIES_PATH;
import static com.devyourid.hoardr.api.utility.Paths.ADMIN_BASE_PATH;
import static com.devyourid.hoardr.api.utility.Paths.ADMIN_PANEL_REDIRECT_URL;
import static com.devyourid.hoardr.api.utility.Paths.DELETE_CARD_PATH;
import static com.devyourid.hoardr.api.utility.Paths.DELETE_EXPANSION_PATH;
import static com.devyourid.hoardr.api.utility.Paths.DELETE_SERIES_PATH;
import static com.devyourid.hoardr.api.utility.Paths.UPDATE_CARD_PATH;
import static com.devyourid.hoardr.api.utility.Paths.UPDATE_EXPANSION_PATH;
import static com.devyourid.hoardr.api.utility.Paths.UPDATE_SERIES_PATH;

@Controller
@AllArgsConstructor
@RequestMapping(ADMIN_BASE_PATH)
public class AdminWebController {

    private final SeriesService seriesService;
    private final ExpansionSetService expansionSetService;
    private final CardService cardService;

    @GetMapping()
    public String adminPage(Model model) {
        List<Series> seriesList = seriesService.findAllSeries();
        model.addAttribute("seriesList", seriesList);
        return "admin";
    }

    // ---------- SERIES ---------- //

    @PostMapping(ADD_SERIES_PATH)
    public String addSeries(@RequestParam String name,
                            @RequestParam(required = false) String icon,
                            @RequestParam String releaseDate) {
        seriesService.createSeries(name, icon, releaseDate);
        return ADMIN_PANEL_REDIRECT_URL;
    }

    @PostMapping(UPDATE_SERIES_PATH)
    public String updateSeries(@RequestParam String id,
                               @RequestParam String name,
                               @RequestParam(required = false) String icon,
                               @RequestParam String releaseDate) {
        seriesService.updateSeries(id, name, icon, releaseDate);
        return ADMIN_PANEL_REDIRECT_URL;
    }

    @PostMapping(DELETE_SERIES_PATH)
    public String deleteSeries(@RequestParam String id) {
        seriesService.deleteSeries(id);
        return ADMIN_PANEL_REDIRECT_URL;
    }

    // ---------- EXPANSION SETS ---------- //

    @PostMapping(ADD_EXPANSION_PATH)
    public String addExpansionSet(@RequestParam String seriesId,
                                  @RequestParam String name,
                                  @RequestParam(required = false) String icon,
                                  @RequestParam String releaseDate) {
        expansionSetService.addExpansionSet(seriesId, name, icon, releaseDate);
        return ADMIN_PANEL_REDIRECT_URL;
    }

    @PostMapping(UPDATE_EXPANSION_PATH)
    public String updateExpansionSet(@RequestParam String seriesId,
                                     @RequestParam String expansionSetId,
                                     @RequestParam String name,
                                     @RequestParam(required = false) String icon,
                                     @RequestParam String releaseDate) {
        expansionSetService.updateExpansionSet(seriesId, expansionSetId, name, icon, releaseDate);
        return ADMIN_PANEL_REDIRECT_URL;
    }

    @PostMapping(DELETE_EXPANSION_PATH)
    public String deleteExpansionSet(@RequestParam String seriesId,
                                     @RequestParam String expansionSetId) {
        expansionSetService.deleteExpansionSet(seriesId, expansionSetId);
        return ADMIN_PANEL_REDIRECT_URL;
    }

    // ---------- CARDS ---------- //

    @PostMapping(ADD_CARD_PATH)
    public String addCard(@RequestParam String seriesId,
                          @RequestParam String expansionSetId,
                          @RequestParam String name,
                          @RequestParam String number,
                          @RequestParam(required = false) String face,
                          @RequestParam(required = false) Float price,
                          @RequestParam(required = false) Boolean collected,
                          Model model) {

        CardDto input = new CardDto();
        input.setName(name);
        input.setNumber(number);
        input.setFace(face);
        input.setPrice(price != null ? price : 0f);
        input.setCollected(collected != null && collected);

        cardService.addCard(seriesId, expansionSetId, input);

        ExpansionSet set = expansionSetService.findExpansionSetById(seriesId, expansionSetId);

        model.addAttribute("seriesId", seriesId);
        model.addAttribute("set", set);

        return "fragments/cards :: card-list";
    }

    @PostMapping(UPDATE_CARD_PATH)
    public String updateCard(@RequestParam String seriesId,
                             @RequestParam String expansionSetId,
                             @RequestParam String cardId,
                             @RequestParam String name,
                             @RequestParam String face,
                             @RequestParam(required = false) String number,
                             @RequestParam(required = false) Float price,
                             @RequestParam(required = false) Boolean collected,
                             Model model) {

        cardService.updateCard(
                seriesId,
                expansionSetId,
                cardId,
                face,
                name,
                number,
                collected != null && collected,
                price != null ? price : 0f
        );

        ExpansionSet set = expansionSetService.findExpansionSetById(seriesId, expansionSetId);

        model.addAttribute("seriesId", seriesId);
        model.addAttribute("set", set);

        return "fragments/cards :: card-list";
    }

    @PostMapping("/admin/card/toggle-collected")
    public String toggleCollected(@RequestParam String seriesId,
                                  @RequestParam String expansionSetId,
                                  @RequestParam String cardId,
                                  @RequestParam Boolean collected,
                                  Model model) {

        cardService.toggleCollected(seriesId, expansionSetId, cardId, collected);

        ExpansionSet set = expansionSetService.findExpansionSetById(seriesId, expansionSetId);

        model.addAttribute("seriesId", seriesId);
        model.addAttribute("set", set);

        return "fragments/cards :: card-list";
    }

    @PostMapping(DELETE_CARD_PATH)
    public String deleteCard(@RequestParam String seriesId,
                             @RequestParam String expansionSetId,
                             @RequestParam String cardId,
                             Model model) {

        cardService.deleteCard(seriesId, expansionSetId, cardId);

        ExpansionSet set = expansionSetService.findExpansionSetById(seriesId, expansionSetId);

        model.addAttribute("seriesId", seriesId);
        model.addAttribute("set", set);

        return "fragments/cards :: card-list";
    }
}
