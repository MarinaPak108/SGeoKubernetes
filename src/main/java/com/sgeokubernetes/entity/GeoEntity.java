package com.sgeokubernetes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="geodata")
public class GeoEntity {
    @Id
    @Column(name="ip_id", nullable = false)
    private Long ipId;
    @Column
    private String city;
    @Column(name = "full_location")
    private String fullLocation;
    @Column
    private Double latitude;
    @Column
    private Double longitude;

    public GeoEntity(Long ipId, String city, String fullLocation, Double latitude, Double longitude) {
        this.ipId = ipId;
        this.city = city;
        this.fullLocation = fullLocation;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoEntity() {

    }

    public Long getIpId() {
        return ipId;
    }

    public void setIpId(Long ipId) {
        this.ipId = ipId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public void setFullLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GeoEntity{" +
                "ipId=" + ipId +
                ", city='" + city + '\'' +
                ", fullLocation='" + fullLocation + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
