package com.ra.config_clouddinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class CloudinaryConfig {
    @Value("${cloudinary.cloud_name}")
    private String cloudName;
    @Value("${cloudinary.api_key}")
    private String apiKey;
    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
//        return new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
        //return new Cloudinary(new Cloudinary.Config().setCloudName(cloudName).setApiKey(apiKey).setApiSecret(apiSecret));
        //return new Cloudinary(new CloudinaryConfig.Builder().cloudName(cloudName).apiKey(apiKey).apiSecret(apiSecret).build());
        //return new Cloudinary(new CloudinaryConfig.Builder().cloudName(cloudName).apiKey(apiKey).apiSecret(apiSecret).build().url());
        return new Cloudinary(ObjectUtils.asMap("cloud_name", cloudName, "api_key", apiKey, "api_secret", apiSecret));

    }
}
