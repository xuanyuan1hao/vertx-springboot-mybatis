package com.ayit.demo;

import javax.annotation.PostConstruct;

import com.ayit.demo.springboot.StaticServer;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import io.vertx.core.Vertx;

@SpringBootApplication
@MapperScan("com.ayit.demo.mapper")
public class VertxApplication {
    private static final Logger logger = LoggerFactory.getLogger(VertxApplication.class);


    @Autowired
    private StaticServer staticServer;



    public static void main(String[] args) {

        // This is basically the same example as the web-examples static site example but it's booted using
        // Spring Boot, not Vert.x
        SpringApplication.run(VertxApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        System.out.print("VertxApplication.deployVerticle \n");
        Vertx.vertx().deployVerticle(staticServer);
        staticServer.setFreeMarkerTemplateEngine(FreeMarkerTemplateEngine.create(Vertx.vertx()));
    }
}