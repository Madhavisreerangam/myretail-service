package com.target.myretail.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

/**
 * Mongo repository for product mongo collection. Provides basic CRUD/query
 * methods for product mongo collection
 * 
 * @author madhavi
 *
 */
public interface ProductRepository extends MongoRepository<ProductDo, Integer> {

	List<ProductDo> findById(@Param("productId") Integer productId);
}
