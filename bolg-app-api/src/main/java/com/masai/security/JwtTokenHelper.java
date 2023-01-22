package com.masai.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private String secret = "jwtTokenKey";

//	retrieve user name from token
	public String getUsernameFromToken(String token) {

		return getClaimFromToken(token, Claims::getSubject);
	}

//	retrieve expiration date from token
	public Date getExpirationDateFromToken(String token) {

		return getClaimFromToken(token, Claims::getExpiration);

	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

//	for retrieving any information from token we will need secret
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

	}

//	check if token has expired
	private Boolean isTokenExpired(String token) {

		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

//	generate token 
	public String generatetoken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

//	while creating the token
//	1. Define claims of the token , like issuer, expiration, subject and the Id
//	2. sign the JWT using the HSS12 algorithm and secret key
//	3. according to jws compact serialization (http://tools.ietf.org/html/draft-ietf-jose)
//	compaction of jwt to a url-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

//	validate token
	public Boolean validate(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
