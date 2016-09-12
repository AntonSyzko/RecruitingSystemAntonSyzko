package com.antonsyzko.recruiting.listeners;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;
/**
 * @author ihor zadyra
 */

@Component
public class SessionListener implements HttpSessionListener {


    // Аny operation
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(30 * 60);
    }

    // Аny operation
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

    }
}
