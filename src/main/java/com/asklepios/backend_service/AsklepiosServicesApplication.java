package com.asklepios.backend_service;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.dao.ApCdtDAO;
import com.asklepios.backend_service.model.generated.dao.ApServiceCdtDAO;
import com.asklepios.backend_service.model.generated.dao.ApServiceDAO;
import com.asklepios.backend_service.model.generated.pojo.ApCdt;
import com.asklepios.backend_service.model.generated.pojo.ApLovValues;
import com.asklepios.backend_service.model.generated.pojo.ApService;
import com.asklepios.backend_service.model.generated.pojo.ApServiceCdt;
import com.asklepios.backend_service.service.ApLovValuesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@SpringBootApplication
public class AsklepiosServicesApplication {


    public static void main(String[] args) {
        SpringApplication.run(AsklepiosServicesApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner() {


        return args -> {
            // ThAis code will run on application startup
            try  {

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };

    }
}
