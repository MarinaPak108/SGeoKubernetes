package com.sgeokubernetes.entity;

import lombok.Data;

@Data
public class GeoIpData {

    private String ipAddress;
    private String device;
    private String city;
    private String fullLocation;
    private Double latitude;
    private Double longitude;



}
