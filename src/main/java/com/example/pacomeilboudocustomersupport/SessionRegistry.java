package com.example.pacomeilboudocustomersupport;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionRegistry {
    private static final List<HttpSession> sessions = new ArrayList<>();

    public static void addSession(HttpSession session) {
        sessions.add(session);
    }

    public static void removeSession(HttpSession session) {
        sessions.remove(session);
    }

    public static List<HttpSession> getActiveSessions() {
        return sessions;
    }
}
