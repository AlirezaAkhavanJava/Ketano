package com.arcade.ketano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class KetanoApplication {
    static void main(String[] args) {
        SpringApplication.run(KetanoApplication.class, args);
    }

}
