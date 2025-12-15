package com.devyourid.hoardr.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "series")
public class Series {

    @Id
    private String id;
    private String name;
    private String icon;
    private List<ExpansionSet> expansionSets;

}
