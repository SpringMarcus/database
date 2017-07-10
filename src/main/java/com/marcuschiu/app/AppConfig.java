package com.marcuschiu.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * AppConfig takes care of:
 * - setting up the config files (which are @Configuration files)
 * - initializing beans
 * @Configuration - indicates this class contains annotated bean method(s)
 * @ComponentScan - refers to package locations to find the associated beans (specifically @Controller)
 */
@Configuration
@ComponentScan(basePackages = {"com.marcuschiu.config"})
public class AppConfig {

    /**
     * reads the 'order.manager.properties' and '${whoami}_order.manager.properties' files
     * example use in class '@Value("${jdbc.url}")' where jdbc.url is the key
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        // create Property Configurer
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();

        // configurations
        properties.setIgnoreResourceNotFound(true);
        properties.setIgnoreUnresolvablePlaceholders(true);

        List<Resource> resources = new ArrayList<>();

        // standard properties
        resources.add(new ClassPathResource("properties/system.properties"));

        // user defined properties (overrides standard properties)
        String userPropertiesPath = "properties/system/developer/" + System.getProperty("user.name") + "_system.properties";
        ClassPathResource userProperties = new ClassPathResource(userPropertiesPath);
        if (userProperties.exists()) {
            resources.add(userProperties);
        }

        // add resources array to Property Configurer
        properties.setLocations(resources.toArray(new Resource[resources.size()]));

        return properties;
    }
}

