package com.target.myretail.process;

import java.util.List;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.target.myretail.dao.ProductDo;
import com.target.myretail.dao.ProductDo.Price;
import com.target.myretail.dao.ProductRepository;
import com.target.myretail.doc.Product;
import com.target.myretail.exception.MyRetailDaoException;

/**
 * Processsor class for product price
 * 
 * 
 * @author madhavi
 *
 */
@Component
@EnableMongoRepositories
public class ProductPriceProcessor {

	private static final Logger logger = LogManager.getLogger("ProductPriceProcessor");

	@Autowired
	ProductRepository productRepository;

	public ProductDo getProduct(int productId) throws MyRetailDaoException {

		ProductDo productDo = new ProductDo();
		try {
			List<ProductDo> productDos = productRepository.findById(productId);
			if (productDos != null && productDos.size() > 0) {
				productDo = productDos.get(0);
			}
		} catch (Exception e) {
			logger.error("Exception while fetching the product price from mongo " + e.getMessage());
			throw new MyRetailDaoException("Exception while fetching the product price from mongo " + e.getMessage());
		}
		return productDo;
	}

	/**
	 * Save the product into data base
	 * 
	 * @param product
	 * @return
	 * @throws MyRetailDaoException
	 */
	public ProductDo updateProduct(Product product) throws MyRetailDaoException {

		ProductDo productDo = new ProductDo();
		Price price = null;

		try {
			// Find the product from database
			List<ProductDo> productDos = productRepository.findById(product.getId());
			// If the product is already available in db then update price
			if (productDos != null && productDos.size() > 0) {
				productDo = productDos.get(0);
				price = productDo.getCurrentPrice();
			} // Else create product and save in db
			else {
				productDo.setId(product.getId().doubleValue());
				price = productDo.new Price();
			}
			// Set price
			price.setValue(product.getCurrentPrice().getValue());
			price.setCurrency(product.getCurrentPrice().getCurrencyCode());
			productDo.setCurrentPrice(price);

			// Save the product
			productDo = productRepository.save(productDo);

		} catch (Exception e) {
			logger.error("Exception while updating the product into mongo " + e.getMessage());
			throw new MyRetailDaoException("Exception while updating the product into mongo " + e.getMessage());
		}
		return productDo;
	}

}
