package com.marcuschiu.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
    // no definitions here yet
}

