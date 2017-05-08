
package com.target.myretail.doc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Pojo for json document current price
 * 
 * @author madhavi
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "value", "currencyCode" })
public class CurrentPrice {

	@JsonProperty("value")
	private Double value;
	
	@JsonProperty("currencyCode")
	private String currencyCode;

	@JsonProperty("value")
	public Double getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(Double value) {
		this.value = value;
	}

	@JsonProperty("currencyCode")
	public String getCurrencyCode() {
		return currencyCode;
	}

	@JsonProperty("currencyCode")
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
