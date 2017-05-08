package com.target.myretail.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

/**
 * Mongo repository for name mongo collection. Provides basic CRUD/query methods
 * for name mongo collection
 * 
 * @author madhavi
 *
 */
public interface NameRepository extends MongoRepository<NameDo, Integer> {

	List<NameDo> findById(@Param("_id") Integer id);
}
