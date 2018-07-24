package com.example.demo.common.units;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Message implements ApplicationContextAware {

    private ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        this.ac = arg0;
    }

    public String getMessage(String key, Object[] params, Locale locale) {
        return this.ac.getMessage(key, params, key, locale);
    }

    public String getMessage(String key, Locale locale) {
        return this.getMessage(key, null, locale);
    }
}
