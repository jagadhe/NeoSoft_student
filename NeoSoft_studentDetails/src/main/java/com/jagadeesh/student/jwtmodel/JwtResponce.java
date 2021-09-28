package com.jagadeesh.student.jwtmodel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class JwtResponce implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140194346481190118L;

	
	private final String jwtToken ;


	public String getJwtToken() {
		return jwtToken;
	}
	
}
