package com.example.alquilernube2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication(scanBasePackages = {"Controller","Model","Repository","Service"})
@EntityScan("Model")
@EnableJpaRepositories("Repository")
public class AlquilerNube2Application {

    public static void main(String[] args) {
        SpringApplication.run(AlquilerNube2Application.class, args);
    }

}
