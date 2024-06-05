package com.luckyGirls.ForYourNutrition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/index.html");
   }
   
   @Bean
   public ReloadableResourceBundleMessageSource messageSource() { 
       ReloadableResourceBundleMessageSource source 
           = new ReloadableResourceBundleMessageSource();
       source.setBasename("classpath:messages");
       source.setDefaultEncoding("UTF-8");
       source.setCacheSeconds(60);
       source.setUseCodeAsDefaultMessage(true);        
       return source;
   }
}
