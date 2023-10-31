package com.sgeokubernetes.config;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class GeoLocConfig {

    private static DatabaseReader reader = null;
    private final ResourceLoader resourceLoader;

    public GeoLocConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public DatabaseReader databaseReader() {
        try {
            log.info("GeoLocationConfig: Trying to load GeoLite2-Country database...");

            Resource resource = resourceLoader.getResource("classpath:maxmind/GeoLite2-City.mmdb");
            InputStream dbAsStream = resource.getInputStream();

            log.info("GeoLocationConfig: Database was loaded successfully.");

            return reader = new DatabaseReader
                    .Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .build();

        } catch (IOException | NullPointerException e) {
            log.error("Database reader could not be initialized. ", e);
            return null;
        }
    }
}
