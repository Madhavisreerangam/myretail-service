package com.target.myretail.dao;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import static com.target.myretail.constants.MyRetailConstants.NAME_COLLLECTION;
/**
 * Data Object for mongo collection name
 * 
 * @author madhavi
 *
 */
@Document(collection = NAME_COLLLECTION)
public class NameDo {

	@Id
	Double id;
	String name;

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
