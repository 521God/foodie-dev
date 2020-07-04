package com.imooc.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {
    public CorsConfig() {
    }
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");

        //在请求中允许设置cookie信息
        config.setAllowCredentials(true);

        //允许放行请求
        config.addAllowedMethod("*");

        //设置允许放行的Header
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);

        return new CorsFilter(corsSource);


    }

}
