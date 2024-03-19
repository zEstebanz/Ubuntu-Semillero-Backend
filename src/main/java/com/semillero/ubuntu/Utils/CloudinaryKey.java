package com.semillero.ubuntu.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "key")
@Data
public class CloudinaryKey {
    private String CLOUD_API_KEY;
}
