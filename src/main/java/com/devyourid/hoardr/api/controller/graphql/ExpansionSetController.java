package com.devyourid.hoardr.api.controller.graphql;

import com.devyourid.hoardr.api.model.dto.ExpansionSetDto;
import com.devyourid.hoardr.api.model.entity.ExpansionSet;
import com.devyourid.hoardr.api.service.ExpansionSetService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@Getter @Setter
@AllArgsConstructor
public class ExpansionSetController {

    private final ExpansionSetService expansionSetService;

    // CREATE

    @MutationMapping
    public ExpansionSet addExpansionSetToSeries(@Argument String seriesId, @Argument("input") ExpansionSetDto input) {
        return expansionSetService.addExpansionSet(seriesId, input.getName(), input.getIcon());
    }

    // UPDATE

    @MutationMapping
    public ExpansionSet updateExpansionSet(@Argument String seriesId, @Argument String expansionSetId, @Argument String name, @Argument String icon) {
        return expansionSetService.updateExpansionSet(seriesId, expansionSetId, name, icon);
    }

    // DELETE

    @MutationMapping
    public String deleteExpansionSet(@Argument String seriesId, @Argument String expansionSetId) {
        return expansionSetService.deleteExpansionSet(seriesId, expansionSetId);
    }
}
