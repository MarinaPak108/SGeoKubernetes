package com.sgeokubernetes.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.sgeokubernetes.entity.GeoIpData;

import javax.servlet.http.HttpServletRequest;

import com.sgeokubernetes.service.MainGeoIpLocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoIpController {

    private final MainGeoIpLocService geoIpLocService;

    public GeoIpController(MainGeoIpLocService geoIPLocationService) {
        this.geoIpLocService = geoIPLocationService;
    }

    @GetMapping("/geoIP/{ipAddress}")
    public ResponseEntity<?> getLocation(@PathVariable String ipAddress, HttpServletRequest request
    ){
        try{
            GeoIpData data = geoIpLocService.getIpLocation(ipAddress, request);
            return ResponseEntity.ok(data);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
