package com.exemple.authentication.config;

import com.exemple.authentication.config.interceptor.GestaoAuthorizationHandler;
import com.exemple.authentication.config.interceptor.PortalAuthorizationHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(mapper);

        converters.add(jsonConverter);

        addDefaultHttpMessageConverters(converters);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("Origin", "Access-Token", "Content-Type", "Authorization", "Referer", "Accept", "Cookie", "X-Cookie")
                .allowCredentials(true).maxAge(3600);
    }

    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(portalAuthorizationHandler()).addPathPatterns("/portal/**");
        registry.addInterceptor(gestaoAuthorizationHandler()).addPathPatterns("/gestao/**");
    }

    @Bean
    public HandlerInterceptorAdapter portalAuthorizationHandler() {
        return new PortalAuthorizationHandler();
    }

    @Bean
    public HandlerInterceptorAdapter gestaoAuthorizationHandler() {
        return new GestaoAuthorizationHandler();
    }

}
