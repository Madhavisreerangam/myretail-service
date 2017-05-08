package com.target.myretail.dao;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import static com.target.myretail.constants.MyRetailConstants.PRODUCT_COLLLECTION;

/**
 * Data Object for mongo collection product
 * 
 * @author madhavi
 *
 */
@Document(collection = PRODUCT_COLLLECTION)
public class ProductDo {

	@Id
	Double id;
	Double productId;
	Price currentPrice;

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public Double getProductId() {
		return productId;
	}

	public void setProductId(Double productId) {
		this.productId = productId;
	}

	public Price getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Price currentPrice) {
		this.currentPrice = currentPrice;
	}

	public class Price {
		Double value;
		String currency;

		public Double getValue() {
			return value;
		}

		public void setValue(Double value) {
			this.value = value;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}
	}

}
