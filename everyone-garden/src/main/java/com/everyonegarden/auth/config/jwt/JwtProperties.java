package com.everyonegarden.auth.config.jwt;

public interface JwtProperties {
    String SECRET="236979CB6F1AD6B6A6184A31E6BE37DB3818CC36871E26235DD67DCFE4041492";
    int EXPIRATION_TIME = 3600000;//1시간
    String TOKEN_PREFIX="Bearer ";
    String HEADER_STRING = "Authorization";
}
