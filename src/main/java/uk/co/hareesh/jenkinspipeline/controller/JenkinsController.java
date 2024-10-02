package uk.co.hareesh.jenkinspipeline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class JenkinsController {

    @GetMapping("/ping")
    public String ping () {
        return new Date()+ " - hareesh replying with message";
    }
}
