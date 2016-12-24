package sk.tomas.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by tomas on 23.12.2016.
 */
@SpringBootApplication
@ComponentScan("sk.tomas.app")
public class Application {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
