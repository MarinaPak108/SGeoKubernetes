package com.sgeokubernetes.service;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.sgeokubernetes.entity.GeoEntity;
import com.sgeokubernetes.entity.GeoIpData;
import com.sgeokubernetes.repo.GeoRepo;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ua_parser.Client;
import ua_parser.Parser;
import java.io.IOException;
import static java.util.Objects.nonNull;

@Service
public class MainGeoIpLocService {

    private GeoRepo repo;
    private ExternalGeoIpLocService extService;
    private ModelMapper modelMapper;
    private static final String UNKNOWN = "UNKNOWN";

    public MainGeoIpLocService(GeoRepo repo, ExternalGeoIpLocService extService, ModelMapper modelMapper) {
        this.repo = repo;
        this.extService = extService;
        this.modelMapper = modelMapper;
    }

    private String getDeviceDetails(String userAgent) throws IOException {
        String deviceDetails = UNKNOWN;

        Parser parser = new Parser();

        Client client = parser.parse(userAgent);
        if (nonNull(client)) {
            deviceDetails = client.userAgent.family + " " + client.userAgent.major + "." + client.userAgent.minor +
                    " - " + client.os.family + " " + client.os.major + "." + client.os.minor;
        }

        return deviceDetails;
    }

    public GeoIpData getIpLocation(String ip, HttpServletRequest request) throws IOException, GeoIp2Exception {
        String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));
        Long ipId = Long.parseLong(ip.replace(".",""));
        GeoEntity geoEntityByIp = repo.findGeoEntityByIpId(ipId);
        GeoIpData position = new GeoIpData();
        if(geoEntityByIp!=null){
            position = modelMapper.map(geoEntityByIp, GeoIpData.class);
            position.setIpAddress(ip);
            position.setDevice(deviceDetails);
//            position.setCity(geoEntityByIp.getCity());
//            position.setFullLocation(geoEntityByIp.getFullLocation());
//
//            position.setLatitude(geoEntityByIp.getLatitude());
//            position.setLongitude(geoEntityByIp.getLongitude());
            return position;
        } else {
            position = extService.extGetIpLocation(ip, deviceDetails);
            GeoEntity geoEntity = modelMapper.map(position, GeoEntity.class);
            geoEntity.setIpId(ipId);
//            GeoEntity geoEntity = new GeoEntity();
//            geoEntity.setIpId(ipId);
//            geoEntity.setFullLocation(position.getFullLocation());
//            geoEntity.setCity(position.getCity());
//            geoEntity.setLongitude(position.getLongitude());
//            geoEntity.setLatitude(position.getLatitude());
            repo.save(geoEntity);
            return position;
        }
    }



}
