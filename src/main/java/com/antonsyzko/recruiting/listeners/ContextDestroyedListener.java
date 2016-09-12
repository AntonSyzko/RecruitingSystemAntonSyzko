package com.antonsyzko.recruiting.listeners;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
/**
 * @author ihor zadyra
 */

@Component
public class ContextDestroyedListener implements ServletContextListener {

    // Аny operation
    @Override
    public void contextInitialized(ServletContextEvent arg) {

    }

    // Аny operation
    @Override
    public void contextDestroyed(ServletContextEvent arg) {

    }
}