package com.example.demo.openid_connect.service;

import com.example.demo.openid_connect.dto.CalcResult;

/**
 * Interface for Calculation Service
 * 
 * @author Igor Peonte <igor.144@gmail.com>
 *
 */

public interface CalcService {

	public CalcResult calcExpression(String expression);

}
