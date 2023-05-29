package com.administration.security.Jwt;

public class JwtVariables {
    public static final String SECRET="is a secret :^D";
    public static final String AUTH_HEADER="Authorization";
    public static final long EXPIRE_ACCESS=800*60*1000; // 4h
    public static final long EXPIRE_REFRESH=600*60*1000; //10h
    public static final String PREFIX="Bearer ";
}
