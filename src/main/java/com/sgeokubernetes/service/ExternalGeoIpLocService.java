package com.sgeokubernetes.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.sgeokubernetes.entity.GeoIpData;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.InetAddress;

import static java.util.Objects.nonNull;

@Service
public class ExternalGeoIpLocService {

    private final DatabaseReader databaseReader;

    public ExternalGeoIpLocService(DatabaseReader databaseReader) {
        this.databaseReader = databaseReader;
    }

    public GeoIpData extGetIpLocation(String ip, String deviceDetails) throws IOException, GeoIp2Exception {
        GeoIpData position = new GeoIpData();
        String location;
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse cityResponse = databaseReader.city(ipAddress);
        if (nonNull(cityResponse) && nonNull(cityResponse.getCity())) {

            String continent = (cityResponse.getContinent() != null) ? cityResponse.getContinent().getName() : "";
            String country = (cityResponse.getCountry() != null) ? cityResponse.getCountry().getName() : "";

            location = String.format("%s, %s, %s", continent, country, cityResponse.getCity().getName());
            position.setCity(cityResponse.getCity().getName());
            position.setFullLocation(location);
            position.setLatitude((cityResponse.getLocation() != null) ? cityResponse.getLocation().getLatitude() : 0);
            position.setLongitude((cityResponse.getLocation() != null) ? cityResponse.getLocation().getLongitude() : 0);
            position.setDevice(deviceDetails);
            position.setIpAddress(ip);

        }
        return position;

    }
}
