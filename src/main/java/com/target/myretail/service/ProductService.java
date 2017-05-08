package com.target.myretail.service;

import static com.target.myretail.constants.MyRetailConstants.ERROR_INVALID_DATA;
import static com.target.myretail.constants.MyRetailConstants.ERROR_PRODUCT_NOTFOUND;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.target.myretail.dao.ProductDo;
import com.target.myretail.doc.CurrentPrice;
import com.target.myretail.doc.Product;
import com.target.myretail.exception.MyRetailDaoException;
import com.target.myretail.process.ProductNameProcessor;
import com.target.myretail.process.ProductPriceProcessor;

/**
 * Webservice class for hosting product rest services
 * 
 * @author madhavi
 *
 */
@RestController
@RequestMapping("/product")
public class ProductService {

	private static final Logger logger = LogManager.getLogger("ProductService");

	@Autowired
	private ProductNameProcessor productNameProcessor;

	@Autowired
	private ProductPriceProcessor productPriceProcessor;

	private static Gson gson = new Gson();

	/**
	 * Welcome service
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String welcome() {
		return "Welcome to MyRetail Product Service";
	}

	/**
	 * Get service for getting the product details from the given product id
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/get/{productId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getProductInfo(@PathVariable Integer productId) {
		// Create the response object
		Product product = new Product();
		product.setId(productId);
		try {

			// Fetch the name of the product from a rest service
			String name = productNameProcessor.getProductName(productId);
			product.setName(name);

			// Fetch the product price from backend NOSQL
			ProductDo pDo = productPriceProcessor.getProduct(productId);

			if (pDo != null && pDo.getCurrentPrice() != null) {
				CurrentPrice price = new CurrentPrice();
				price.setValue(pDo.getCurrentPrice().getValue());
				price.setCurrencyCode(pDo.getCurrentPrice().getCurrency());
				product.setCurrentPrice(price);
			} else {
				return new ResponseEntity<String>(ERROR_PRODUCT_NOTFOUND, HttpStatus.BAD_REQUEST);
			}
		} catch (MyRetailDaoException myRetailDaoException) {
			logger.error("Exception while fetching the product details " + myRetailDaoException.getMessage());
		} catch (Exception exception) {
			logger.error("Exception while fetching the product details " + exception.getMessage());
		}

		// return complete product details
		return new ResponseEntity<String>(gson.toJson(product), HttpStatus.OK);
	}

	/**
	 * Put service for create/update the product object in NOSQL database
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/put/", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> update(@RequestBody Product product) {

		// Create the response object
		Product resultProduct = new Product();
		resultProduct.setId(product.getId());
		try {

			// Update the product into backend NOSQL
			ProductDo pDo = productPriceProcessor.updateProduct(product);

			// Populate the response object
			if (pDo != null && pDo.getCurrentPrice() != null) {
				CurrentPrice price = new CurrentPrice();
				price.setValue(pDo.getCurrentPrice().getValue());
				price.setCurrencyCode(pDo.getCurrentPrice().getCurrency());
				resultProduct.setCurrentPrice(price);
			} else {
				return new ResponseEntity<String>(ERROR_INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (MyRetailDaoException myRetailDaoException) {
			logger.error("Exception while updating the product details " + myRetailDaoException.getMessage());
		} catch (Exception exception) {
			logger.error("Exception while updating the product details " + exception.getMessage());
		}

		// return result product details
		return new ResponseEntity<Product>(resultProduct, HttpStatus.OK);
	}
}
