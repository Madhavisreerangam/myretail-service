package com.target.myretail.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.target.myretail.constants.MyRetailConstants.MYRETAIL_WELCOME_MSG;

/**
 * Welcome Service for application context
 * 
 * @author madhavi
 *
 */
@RestController
@RequestMapping("/")
public class WelcomeService {

	/**
	 * Welcome service
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String welcome() {
		return MYRETAIL_WELCOME_MSG;
	}

}
