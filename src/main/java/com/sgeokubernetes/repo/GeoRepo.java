package com.sgeokubernetes.repo;

import com.sgeokubernetes.entity.GeoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoRepo extends JpaRepository<GeoEntity, Long> {

    GeoEntity findGeoEntityByIpId (Long ipId);
}
