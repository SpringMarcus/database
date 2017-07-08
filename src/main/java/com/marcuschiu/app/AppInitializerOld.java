package com.marcuschiu.app;

//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
///**
// * Created by marcus.chiu on 10/16/16.
// * Resembles the web.xml file under the WEB-INF folder
// */
//public class AppInitializer implements WebApplicationInitializer {
//
//    /**
//     * This onStartup method is similar to web.xml file
//     * Gets invoked automatically when application starts up
//     * The gray comments are copied of from the web.xml to show parallels
//     * @param servletContext
//     * @throws ServletException
//     */
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
////        System.out.println("WebApplicationInitializer.onStartup");
////        System.out.println(EntityManager.class.getProtectionDomain()
////                .getCodeSource()
////                .getLocation());
//
//        //create new AnnotationConfigWebApplicationContext object
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//
//        /*
//        <!-- If you do not want to go with default filename as [servlet-name]-servlet.xml
//        and default location as WebContent/WEB-INF, you can customize this file name and
//        location by adding the servlet listener ContextLoaderListener in your web.xml file as follows: -->
//        <context-param>
//            <param-name>contextConfigLocation</param-name>
//            <param-value>/WEB-INF/spring-servlet.xml</param-value>
//        </context-param>
//        <listener>
//            <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
//        </listener>
//         */
//        //register Configuration Class
//        ctx.register(AppConfig.class);
//        ctx.setServletContext(servletContext);
//
//        //we are using front-controller DispatcherServlet
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//
//        /*
//        <servlet-mapping> tag indicates what URLs will be handled by the which DispatcherServlet.
//        Here all the HTTP requests will be handled by the HelloWeb DispatcherServlet.
//        <servlet-mapping>
//            <servlet-name>spring</servlet-name>
//            <url-pattern>/</url-pattern>
//        </servlet-mapping>
//        */
//        servlet.addMapping("/");
//
//        /*
//        'spring' DispatcherServlet, the framework will try to load the application context from a file named
//        [servlet-name]-servlet.xml located in the application's WebContent/WEB-INF directory.
//        In this case our file will be spring-servlet.xml. -->
//        <servlet>
//            <servlet-name>spring</servlet-name>
//            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
//            <load-on-startup>1</load-on-startup>
//            <async-supported>true</async-supported>
//        </servlet>
//         */
//        servlet.setAsyncSupported(true);
//        servlet.setLoadOnStartup(1);
//    }
//}
