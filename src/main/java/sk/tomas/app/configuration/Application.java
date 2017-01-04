package sk.tomas.app.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by tomas on 23.12.2016.
 */
@SpringBootApplication
@ComponentScan("sk.tomas.app")
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@ImportResource("classpath:application-context.xml")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }

}
