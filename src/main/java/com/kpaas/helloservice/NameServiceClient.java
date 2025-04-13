package com.kpaas.helloservice;

import com.kpaas.helloservice.model.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "name-service",url="${msa.gateway-url}")
public interface NameServiceClient {

    @GetMapping("/info")
    ResultData getInfo();
}
