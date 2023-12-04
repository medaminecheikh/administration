package com.administration.controller;

import com.administration.dto.TracageResponse;
import com.administration.entity.Tracage;
import com.administration.service.ITracageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Gestion Tracage")
@Slf4j
public class TracageController {
    private ITracageService tracageService;


    public TracageController(ITracageService tracageService) {
        this.tracageService = tracageService;
    }

    @GetMapping("/tracages")
    public List<TracageResponse> getTraceByAll(
            @RequestParam(defaultValue = "", required = false) String idUser,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date time,
            @RequestParam(defaultValue = "", required = false) String ip,
            @RequestParam(defaultValue = "", required = false) String browser,
            @RequestParam(defaultValue = "", required = false) String op) {

        return tracageService.getTraceByAll(idUser, time, ip, browser, op);
    }

    @PostMapping("/addtracage")
    public void addTrace(@RequestBody Tracage tracage, HttpServletRequest request) {
// Get client's IP address
        String clientIp = getClientIp(request);

        if (clientIp.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            // Set IP address in the Tracage object
            clientIp = "127.0.0.1";
        }
        tracage.setIp(clientIp);
        // Get browser information
        String browserInfo = getBrowserInfo(request.getHeader("User-Agent"));

        // Set browser information in the Tracage object
        tracage.setBrowser(browserInfo);

        tracageService.addTrace(tracage);
    }

    @GetMapping("/tracagesencaisssement")
    public List<TracageResponse> getTraceByencaissement() {
        return tracageService.getByencaissement();
    }

    @GetMapping("/tracagesfacture")
    public List<TracageResponse> getTraceByfacture() {
        return tracageService.getByfacture();
    }

    @GetMapping("/tracagescaisse")
    public List<TracageResponse> getTraceByCaisse() {
        return tracageService.getBycaisse();
    }

    private String getClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            // Try to get the IP address from other headers or fallback to default
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_FORWARDED");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_FORWARDED");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }

        // Extract the first IP address from the X-Forwarded-For list
        if (clientIp != null && clientIp.contains(",")) {
            clientIp = clientIp.split(",")[0].trim();
        }


        // Extract the first IP address from the X-Forwarded-For list
        if (clientIp != null && clientIp.contains(",")) {
            clientIp = clientIp.split(",")[0].trim();
        }
        return clientIp;
    }
    private String getBrowserInfo(String userAgentString) {
        if (userAgentString != null) {
            if (userAgentString.contains("MSIE")) {
                return "Internet Explorer";
            } else if (userAgentString.contains("Firefox")) {
                return "Mozilla Firefox";
            } else if (userAgentString.contains("Chrome")) {
                return "Google Chrome";
            } else if (userAgentString.contains("Safari")) {
                return "Apple Safari";
            } else if (userAgentString.contains("Opera")) {
                return "Opera";
            } else {
                return "Unknown Browser";
            }
        } else {
            return "Unknown Browser";
        }
    }
}
