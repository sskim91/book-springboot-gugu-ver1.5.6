package com.sskim.controller;

import com.sskim.domain.SampleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String sayHello() {

        return "Hello World";
    }

    @GetMapping("/sample")
    public SampleVO makeSample() {

        SampleVO sampleVO = new SampleVO();
        sampleVO.setVal1("v1");
        sampleVO.setVal2("v2");
        sampleVO.setVal3("v3");

        System.out.println(sampleVO);

        return sampleVO;
    }
}
