package com.bstore.services.batch;

/**
 *
 * @author guta
 */
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener {

    private final Logger log = Logger.getLogger(SessionListener.class);

    private static int activeSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        this.log.info("Se crea nueva JSESSIONID: " + event.getSession().getId());
        activeSessions++;
        this.log.info("Total de usuarios activos en sistema: " + activeSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        this.log.info("Se borra JSESSIONID: " + event.getSession().getId());
        if (activeSessions > 0) {
            activeSessions--;
            this.log.info("Total de usuarios activos en sistema: " + activeSessions);
        }
    }

}
