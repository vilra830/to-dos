package io.nology.to_dos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // do this when you start the app
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) { // we can define what incoming request can do , ie.this path is allowed, this path is denied - it could be null hence the warning
        String[] allowedOrigins = {"http://localhost:5173" , "http://127.0.0.1:5173" }; // these are the locations origins that I am letting make a request to this backend
        registry.addMapping("/**").allowedOrigins(allowedOrigins).allowedMethods("*").allowedHeaders("*"); //any endpoint in our lets allow it 
        //we can also control which methods and headers to allow - 
        //access tokens needed here as well

    }



}
