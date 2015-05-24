package com.panchoriz.myapp.mvcconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 11/23/13
 * Time: 12:27 PM
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.panchoriz.myapp"}) // Scans controllers
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
    public MappingJackson2JsonView jsonView() {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setPrefixJson(true);
        return jsonView;
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver internalViewResolver = new InternalResourceViewResolver();
		internalViewResolver.setPrefix("/WEB-INF/views/");
		internalViewResolver.setSuffix(".html");
		return internalViewResolver;
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
