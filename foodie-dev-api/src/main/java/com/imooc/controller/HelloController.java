package com.imooc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class HelloController {
    final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public Object hello(){
        logger.error("hello  erro");
        logger.info("hello info");
        logger.warn("hello warn ");
        logger.debug("hello debug");
        return "hello!";
    }
}
