package com.example.pacomeilboudocustomersupport;


import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class SessionListUtil {
    private static List<HttpSession> activeSessions = new ArrayList<>();

    public static int getNumberOfSessions() {
        return activeSessions.size();
    }

    public static List<HttpSession> getAllSessions() {
        return activeSessions;
    }

    public static void addSession(HttpSession session) {
        activeSessions.add(session);
    }

    public static void removeSession(HttpSession session) {
        activeSessions.remove(session);
    }
}

