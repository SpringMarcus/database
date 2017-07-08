package com.marcuschiu.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by marcus.chiu on 10/16/16.
 * This is the root Configuration Class
 * @Configuration - indicates this class contains annotated bean method(s)
 * @EnableWebMvc - is equivalent to <html:annotation-driven /> in XML.
 * It enables support for @Controller-annotated classes that use @RequestMapping
 * to map incoming requests to a certain method.
 * @ComponentScan - refers to package locations to find the associated beans (specifically @Controller for this class)
 * @Import - imports another configuration class into this class (essentially appending it)
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.marcuschiu.controller")
//@Import() not needed for now
public class MVCConfig extends WebMvcConfigurationSupport {

    ///////////////
    // OVERRIDES //
    ///////////////

    /**
     * Adding static resources
     * more at http://www.baeldung.com/spring-mvc-static-resources
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler() - configures the external facing URI path
        // addResourceLocations() - maps that external facing URI path internally, to the physical path where the resources are actually located.
        // Now – the following line in an html page would get us the myCss.css resource inside the webapp/resources directory
        // <link href="<c:url value="/resources/myCss.css" />" rel="stylesheet">
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/image/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");

        ////////////
        // extras //
        ////////////
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("/resources/","classpath:/other-resources/") // configuring multiple locations for a resource
//                .setCachePeriod(3600) // The resources served will be cached in the browser for 3600 seconds.
//                .resourceChain(true) // chain is finally configured
//                .addResolver(new PathResourceResolver()); // registering the PathResourceResolver in the resource chain as the sole ResourceResolver in it
    }

    ///////////
    // BEANS //
    ///////////

    /**
     * Configures a html resolver to identify the real html
     * @return ViewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        //Create new View Resolver
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        //Set html class as JSP Standard Tags Library
        viewResolver.setViewClass(JstlView.class);

        //Set prefix and suffix
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        //return View Resolver
        return viewResolver;
    }
    // another way to configure a html resolver
    /*@Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }*/

    /**
     * In the case of a validation failure, default error messages are shown.
     * To override the defaults, we need to configure the ResourceBundleMessageSource
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        //Create new ResourceBundleMessageSource object
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        //Spring will search for file named 'en_messages.properties' in app class path
        // ~/src/main/resources/en_messages.properties
        messageSource.setBasename("properties/language/en_messages");

        //return as MessageSource
        return messageSource;
    }

    /**
     * Prerequisite - need 'commons-fileupload' in maven
     * @return
     */
    @Bean//(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

        // sets max upload size a client can make to this server
        commonsMultipartResolver.setMaxUploadSize(5242880);

        return commonsMultipartResolver;
    }
}
