package com.example.demo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-05 10:33
 **/
@Data
@Component
@ConfigurationProperties(prefix = "uploadfile")
public class UploadFileConfig {

    private String uploadfilepath;
}
