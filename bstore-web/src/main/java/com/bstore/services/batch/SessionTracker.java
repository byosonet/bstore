package com.bstore.services.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gtrejo
 */
public class SessionTracker {

    private static SessionTracker ourInstance = new SessionTracker();
    private WeakHashMap<String, HttpSession> sessions = new WeakHashMap<String, HttpSession>();

    public static SessionTracker instance() {
        return ourInstance;
    }

    private SessionTracker() {
    }

    public List<HttpSession> getSessions() {
        return new ArrayList<HttpSession>(sessions.values());
    }

    public void add(HttpSession session) {
        sessions.put(session.getId(), session);
    }

    public void remove(HttpSession session) {
        sessions.remove(session.getId());
    }

    public HttpSession getSession(String id) {
        return sessions.get(id);
    }
}
