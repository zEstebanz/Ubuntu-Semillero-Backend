package com.semillero.ubuntu.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google")
@Data
public class GoogleClientID {
    String ID_CLIENT;
}
