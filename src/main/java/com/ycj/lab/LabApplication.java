package com.ycj.lab;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.ycj.lab.common.Const.PREFIX;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class LabApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(LabApplication.class, args);
//        Environment env = context.getBean(Environment.class);
//        log.info("访问链接：http//localhost:" + env.getProperty("server.port") + env.getProperty("server.servlet.context-path"));
        SpringApplication.run(LabApplication.class, args);
        log.info(System.getProperty("os.name"));
        if (System.getProperty("os.name").equals("Windows 10")) {
            PREFIX = "d:/cache/var/lab";
        }else{
            PREFIX = "/var/lab/";
        }
        log.info("缓存路径:"+PREFIX);
    }

}
