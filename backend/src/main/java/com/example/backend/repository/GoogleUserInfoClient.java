package com.example.backend.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.backend.dto.response.GoogleUserInfo;

@FeignClient(name = "googleUserInfoClient", url = "https://www.googleapis.com")
public interface GoogleUserInfoClient {
    @GetMapping("/oauth2/v3/userinfo")
    GoogleUserInfo getUserInfo(@RequestHeader("Authorization") String authHeader);
}
