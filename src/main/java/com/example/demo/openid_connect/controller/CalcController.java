package com.example.demo.openid_connect.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.openid_connect.dto.CalcResult;
import com.example.demo.openid_connect.service.CalcService;

/**
 * Calculation Controller
 * 
 * @author Igor Peonte <igor.144@gmail.com>
 *
 */
@Validated
@RestController
public class CalcController {

	// Logger
	private static final Logger LOG = LoggerFactory.getLogger(CalcController.class);

	@Autowired
	private CalcService calcService;

	@RequestMapping("/calc")
	public CalcResult test(@NotBlank @RequestParam("exp") String expression) {
		CalcResult res = calcService.calcExpression(expression);
		LOG.debug(expression + " = " + res.getResult());
		return res;
	}
}
