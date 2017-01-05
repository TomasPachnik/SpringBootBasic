package sk.tomas.app.configuration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tomas.app.mapper.UuidStringConverter;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
@Configuration
public class Config {

    @Bean
    public MapperFacade mapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new UuidStringConverter());
        return mapperFactory.getMapperFacade();
    }

}
