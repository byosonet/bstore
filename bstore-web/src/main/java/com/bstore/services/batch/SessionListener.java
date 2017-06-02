package com.bstore.services.batch;

/**
 *
 * @author guta
 */
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener {

    private final Logger log = Logger.getLogger(SessionListener.class);

    private static Map<String, HttpSession> map = new HashMap<String, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        String id = event.getSession().getId();
        map.put(id, event.getSession());
        this.log.info("Se crea nueva JSESSIONID: " + event.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        this.log.info("Se borra JSESSIONID: " + event.getSession().getId());
    }

}
