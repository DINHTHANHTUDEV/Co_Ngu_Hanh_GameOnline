package com.example.backend.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backend.dto.request.ExchangeTokenRequest;
import com.example.backend.dto.response.ExchangeTokenResponse;

@FeignClient(name = "outboundIdentityClient", url = "https://oauth2.googleapis.com")
public interface OutboundIdentityClient {
    @PostMapping("/token")
    ExchangeTokenResponse exchangeToken(@RequestBody ExchangeTokenRequest request);
}
