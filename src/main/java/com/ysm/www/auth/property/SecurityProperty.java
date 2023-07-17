package com.ysm.www.auth.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/17
 * @Version V1.0
 **/
@Component
@Data
@ConfigurationProperties(prefix = "security.url")
public class SecurityProperty {

    private  String[] allowed;

    private  String[] need;

    private  String[] ignore;


}
