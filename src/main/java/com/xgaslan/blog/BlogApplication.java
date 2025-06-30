package com.xgaslan.blog;

import com.xgaslan.blog.repositories.base.impl.BaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.xgaslan.blog.repositories",
        repositoryBaseClass = BaseRepository.class
)
@ConfigurationPropertiesScan(
        basePackages = "com.xgaslan.blog.config")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
