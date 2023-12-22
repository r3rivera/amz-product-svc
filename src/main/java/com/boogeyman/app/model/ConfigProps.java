package com.boogeyman.app.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConfigProps {

    @Value("${release.name:None}")
    private String releaseName;

    @Value(("${release.description:''}"))
    private String releaseDescription;

}
