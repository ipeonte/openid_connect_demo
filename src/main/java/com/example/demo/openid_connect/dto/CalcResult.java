package com.example.demo.openid_connect.dto;

/**
 * DTO class for expression calculation result
 * 
 * @author Igor Peonte <igor.144@gmail.com>
 *
 */
public class CalcResult {

	private double result;

	public CalcResult(double result) {
		this.result = result;
	}

	public Double getResult() {
		return result;
	}

}
