package com.target.myretail.service;

import static com.target.myretail.constants.MyRetailConstants.ERROR_INVALID_DATA;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.target.myretail.dao.NameDo;
import com.target.myretail.dao.NameRepository;

/**
 * Webservice class for hosting name rest services
 * 
 * @author madhavi
 *
 */
@RestController
@RequestMapping("/name")
public class NameService {

	private static final Logger logger = LogManager.getLogger("NameService");

	@Autowired
	NameRepository nameRepository;

	@RequestMapping("")
	public String welcome() {
		return "Welcome to Name Service";
	}

	@RequestMapping("/get/{productId}")
	public ResponseEntity<?> getName(@PathVariable Integer productId) {
		try {
			List<NameDo> resultDo = nameRepository.findById(productId);
			if (resultDo != null && resultDo.size() > 0) {
				return new ResponseEntity<NameDo>(resultDo.get(0), HttpStatus.OK);
			} else {
				NameDo nameDo = new NameDo();
				nameDo.setId(productId.doubleValue());
				nameDo.setName("DefaultName");
				return new ResponseEntity<NameDo>(nameDo, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Exception while fetching the name " + e.getMessage());
			return new ResponseEntity<String>(ERROR_INVALID_DATA, HttpStatus.BAD_REQUEST);
		}
	}
}
