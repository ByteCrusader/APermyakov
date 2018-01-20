package ru.apermyakov.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Class for spring web web.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 20.01.2018.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/statistic/*").setViewName("statistic");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/account").setViewName("account");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/help").setViewName("help");
    }
}
