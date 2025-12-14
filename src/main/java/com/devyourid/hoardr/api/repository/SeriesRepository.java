package com.devyourid.hoardr.api.repository;

import com.devyourid.hoardr.api.model.entity.Series;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends MongoRepository<Series, String> { }
