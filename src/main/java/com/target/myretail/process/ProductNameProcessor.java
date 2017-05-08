package com.target.myretail.process;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.myretail.client.RestClient;
import com.target.myretail.exception.MyRetailBusinessException;

/**
 * Processor class for product name
 * 
 * @author madhavi
 *
 */
@Component
public class ProductNameProcessor {

	private static final Logger logger = LogManager.getLogger("ProductNameProcessor");

	@Autowired
	RestClient restClient;

	public String getProductName(int productId) {
		String name = null;
		try {
			name = restClient.getProductName(productId).getName();

		} catch (MyRetailBusinessException myRetailBusinessException) {
			logger.error("Exception while processing product name " + myRetailBusinessException.getMessage());
		} catch (Exception e) {
			logger.error("Exception while processing product name " + e.getMessage());
		}
		return name;
	}

}
