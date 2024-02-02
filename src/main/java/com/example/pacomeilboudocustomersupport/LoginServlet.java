package com.example.pacomeilboudocustomersupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    public static final Map<String, String> userDB = new HashMap<>();
    static {
        userDB.put("user1", "Password1");
        userDB.put("user2", "Password2");
        userDB.put("user3", "Password3");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // if logout exists, log us out
        if (request.getParameter("logout") != null) {
            session.invalidate(); // logs us out
            response.sendRedirect("login");
            return;
        }
        // check if logged in - then go to main page
        else if (session.getAttribute("username") != null) {
            response.sendRedirect("ticket");
            return;
        }

        // not logged in, go to login page - initial login page
        request.setAttribute("loginFailed", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // again check if already logged in
        if (session.getAttribute("username") != null) {
            response.sendRedirect("ticket");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || !LoginServlet.userDB.containsKey(username) ||
                !password.equals(LoginServlet.userDB.get(username))) {
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
        }
        // login is successful
        else {
            session.setAttribute("username", username);
            request.changeSessionId(); // protects against session fixation attacks
            response.sendRedirect("ticket");
        }
    }
}
