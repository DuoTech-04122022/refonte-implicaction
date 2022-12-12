package com.dynonuggets.refonteimplicaction;

import com.dynonuggets.refonteimplicaction.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableMongoRepositories
@Import(SwaggerConfiguration.class)
public class RefonteImplicactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RefonteImplicactionApplication.class, args);
    }

}
