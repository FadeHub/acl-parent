package com.sl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shuliangzhao
 * @Title: ServiceAclApplication
 * @ProjectName acl-parent
 * @Description: TODO
 * @date 2021/5/22 22:47
 */
@SpringBootApplication
@MapperScan("com.sl.mapper")
@EnableDiscoveryClient
public class ServiceAclApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class,args);
    }
}
