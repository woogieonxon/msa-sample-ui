package com.kpaas.helloservice;

import com.kpaas.helloservice.model.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @Autowired
    private NameServiceClient nameServiceClient;
    @Autowired
    private TitleServiceClient titleServiceClient;

    @GetMapping("/hello")
    public String getHello(Model model) {
        ResultData info = nameServiceClient.getInfo();
        ResultData titleInfo = titleServiceClient.getInfo();
        model.addAttribute("data1",info.getData1());
        model.addAttribute("data2",info.getData2());
        model.addAttribute("title1",titleInfo.getData1());
        model.addAttribute("title2",titleInfo.getData2());
        return "index";
    }
}