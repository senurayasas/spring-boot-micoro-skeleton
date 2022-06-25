package com.gifted.codes.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "gifted.app")
@ConstructorBinding
public record AppConfiguration(String platformBaseUrl, int asyncMinPoolSize, int asyncMaxPoolSize) {

}
