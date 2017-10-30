/**
 * 
 */
package com.example.demo.openid_connect.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.openid_connect.dto.CalcResult;
import com.example.demo.openid_connect.service.CalcService;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Calculation Service implementation class
 * 
 * @author Igor Peonte <igor.144@gmail.com>
 *
 */

@Service
public class CalcServiceImpl implements CalcService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.openid_connect.service.CalcService#calcExpression(java.lang.
	 * String)
	 */
	
	@Override
	public CalcResult calcExpression(String expression) {
		Expression e = new ExpressionBuilder(expression).build();

		return new CalcResult(e.evaluate());
	}

}
