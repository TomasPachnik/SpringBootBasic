package sk.tomas.app.configuration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import sk.tomas.app.mapper.ClassMapper;
import sk.tomas.app.mapper.PasswordStringConverter;
import sk.tomas.app.mapper.SetArrayConverter;
import sk.tomas.app.mapper.UuidStringConverter;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
@EnableCaching
@Configuration
public class Config {

    @Bean
    public MapperFacade mapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new UuidStringConverter());
        mapperFactory.getConverterFactory().registerConverter(new PasswordStringConverter());
        mapperFactory.getConverterFactory().registerConverter(new SetArrayConverter());
        ClassMapper.mapClass(mapperFactory);
        return mapperFactory.getMapperFacade();
    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cmfb.setShared(true);
        return cmfb;
    }

}
