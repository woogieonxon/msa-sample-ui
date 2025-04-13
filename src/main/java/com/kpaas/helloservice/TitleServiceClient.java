package com.kpaas.helloservice;

import com.kpaas.helloservice.model.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "title-service",url="${msa.gateway-url}")
public interface TitleServiceClient {

    @GetMapping("/title-info")
    ResultData getInfo();
}
