package com.administration.controller;

import com.administration.dto.TracageResponse;
import com.administration.entity.Tracage;
import com.administration.service.ITracageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@Api(tags = "Gestion Tracage")
@Slf4j
public class TracageController {
    private  ITracageService tracageService;


    public TracageController(ITracageService tracageService) {
        this.tracageService = tracageService;
    }

    @GetMapping("/tracages")
    public List<TracageResponse> getTraceByAll(
            @RequestParam(defaultValue = "",required = false) String idUser,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date time,
            @RequestParam(defaultValue = "",required = false) String ip,
            @RequestParam(defaultValue = "",required = false) String browser,
            @RequestParam(defaultValue = "",required = false) String op) {

        return tracageService.getTraceByAll(idUser, time, ip, browser, op);
    }
    @PostMapping("/addtracage")
    public void addTrace(@RequestBody Tracage tracage) {

        tracageService.addTrace(tracage);
    }


}
