package org.mail.ms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/health")
public class HealthController {
 private final static Logger logger= LoggerFactory.getLogger(HealthController.class);

    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    @GetMapping
    public void health(){
        logger.info("ActionLog.sign-up-ms.start");
    }
}
