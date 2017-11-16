package com.bstore.services.batch;

/**
 *
 * @author gtrejo
 */
import com.bstore.services.service.SesionService;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SessionListener implements HttpSessionListener {

    private final Logger log = Logger.getLogger(SessionListener.class);

    private static int activeSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        this.log.info("Se crea nueva SESSIONID: " + event.getSession().getId());
        SessionTracker.instance().add(event.getSession());
        activeSessions++;
        this.log.info("Total de usuarios activos en sistema: " + activeSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        this.log.info("Se ha borrado la SESSIONID por salida del usuario o por inactividad: " + event.getSession().getId());
        if (event.getSession() != null && event.getSession().getId() != null) {
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(event.getSession().getServletContext());
            SesionService counterService = (SesionService) ctx.getBean("sesionService");
            if (counterService != null) {
                counterService.removeSesionOnline(event.getSession().getId());
            }
        }
        SessionTracker.instance().remove(event.getSession());
        if (activeSessions > 0) {
            activeSessions--;
            this.log.info("Total de usuarios activos en sistema: " + activeSessions);
        }
    }

}