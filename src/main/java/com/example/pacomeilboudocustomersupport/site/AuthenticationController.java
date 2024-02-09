package com.example.pacomeilboudocustomersupport.site;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthenticationController {
    private static final Map<String, String> userDB = new HashMap<>();

    static {
        userDB.put("user1", "Password1");
        userDB.put("user2", "Password2");
        userDB.put("user3", "Password3");
    }

    @RequestMapping("logout")
    public View logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("login", true, false);
    }

    @GetMapping("login")
    public ModelAndView loginForm(Model model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return new ModelAndView(new RedirectView("/ticket", true, false));
        }
        model.addAttribute("loginFailed", false);
        return new ModelAndView("login", "loginForm", new LoginForm());
    }

    @PostMapping("login")
    public ModelAndView loginCheck(@ModelAttribute("loginForm")LoginForm form,
                                   Model model,
                                   HttpSession session,
                                   HttpServletRequest request) {
        // already logged in
        if (session.getAttribute("username") != null) {
            return new ModelAndView(new RedirectView("/ticket", true, false));
        }

        String username = form.getUsername();
        String password = form.getPassword();

        if (username == null || password == null || !userDB.containsKey(username) ||
                !password.equals(userDB.get(username))) {
            model.addAttribute("loginFailed", true);
            model.addAttribute("loginForm", form);
            return new ModelAndView("login");
        }

        // we are logged in successfully
        session.invalidate();
        HttpSession newSession = request.getSession(true); // create a new session
        newSession.setAttribute("username", username);
        return new ModelAndView(new RedirectView("/ticket", true, false));
    }


    public static class LoginForm {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
