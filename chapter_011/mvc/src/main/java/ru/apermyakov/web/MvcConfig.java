package ru.apermyakov.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Class for spring web web.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/adverts").setViewName("adverts");
        registry.addViewController("/login").setViewName("login");
    }
}
