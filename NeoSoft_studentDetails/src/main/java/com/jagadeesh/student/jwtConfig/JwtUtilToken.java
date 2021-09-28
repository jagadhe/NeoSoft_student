package com.jagadeesh.student.jwtConfig;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtilToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9102521731007097933L;

	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	public <T> T getClaimForTocken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);

	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes(Charset.forName("UTF_8")))
				.parseClaimsJws(token.replace("{", "").replace("}", "")).getBody();
	}

	public String getUserNameFromToken(String token) {
		return getClaimForTocken(token, Claims::getSubject);
	}

	public Date getIssuedDateFromToken(String Token) {
		return getClaimForTocken(Token, Claims::getIssuedAt);

	}

	public Date getExpirationDateFromToken(String Token) {
		return getClaimForTocken(Token, Claims::getExpiration);

	}

	private Boolean isTokenExpired(String token) {
		final Date expDate = getExpirationDateFromToken(token);
		return expDate.before(new Date());
	}

	private Boolean ignoreTokenExpiretion(String token) {
		return false;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());

	}

	private String doGenerateToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes(Charset.forName("UTF_8"))).compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token)||ignoreTokenExpiretion(token));
	}

	public Boolean validateToken(String token,UserDetails userDetails){
		 String username = getUserNameFromToken(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	

	
}

