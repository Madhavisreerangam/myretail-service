package com.target.myretail.client;

import javax.annotation.PostConstruct;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.target.myretail.dao.NameDo;
import com.target.myretail.exception.MyRetailBusinessException;

/**
 * Rest client to request rest services with the help of RestTemplate
 * 
 * @author madhavi
 *
 */
@Component
public class RestClient {

	private static final Logger logger = LogManager.getLogger("RestClient");

	private RestTemplate restTemplate;

	@Value("${nameservice.url}")
	private String nameServiceUrl;

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}

	/**
	 * Request the name service and gets the product name for the given product
	 * id
	 * 
	 * @param productId
	 * @return
	 */
	public NameDo getProductName(Integer productId) throws MyRetailBusinessException {
		
		NameDo name = null;
		try {
			logger.debug(nameServiceUrl + productId);
			name = restTemplate.getForObject(nameServiceUrl + productId, NameDo.class);
		} catch (Exception e) {
			logger.error("Exception while requesting the name service" + e.getMessage());
			throw new MyRetailBusinessException("Exception while requesting the name service" + e.getMessage());
		}
		return name;
	}

}
