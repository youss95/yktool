package com.ksy.syserver.security;

public class SecurityConstants {
//wont change
	public static final String SIGN_UP_URLS = "/api/user/**"; 
	public static final String SECRET = "SecretKeyToGenJWTs"; 
	public static final String TOKEN_PREFIX = "Bearer "; //Bearer 다음 한칸 띄어야 함
	public static final String HEADER_STRING = "Authorization";
	public static final long EXPIRATION_TIME = 30_000; //30s
	
}
