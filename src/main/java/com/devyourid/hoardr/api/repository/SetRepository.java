package com.devyourid.hoardr.api.repository;

import com.devyourid.hoardr.api.model.entity.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends MongoRepository<Set, String> { }
