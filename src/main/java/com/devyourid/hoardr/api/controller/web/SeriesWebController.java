package com.devyourid.hoardr.api.controller.web;

import com.devyourid.hoardr.api.model.entity.Series;
import com.devyourid.hoardr.api.service.SeriesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Getter @Setter
@AllArgsConstructor
public class SeriesWebController {

    SeriesService seriesService;

    @GetMapping("/")
    public String home(Model model) {
        List<Series> seriesList = seriesService.findAllSeries();
        model.addAttribute("seriesList", seriesList);
        return "home.html";
    }

}
