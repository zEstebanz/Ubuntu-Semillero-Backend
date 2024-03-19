package com.semillero.ubuntu.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "name")
@Data
public class CloudinaryName {
    private String CLOUD_NAME;
}
