package com.example.demo.openid_connect.model;

/**
 * User Details return by Google OpenId Connect
 * 
 * @author Igor Peonte <igor.144@gmail.com>
 *
 */
public class OpenIdConnUserDetails {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
