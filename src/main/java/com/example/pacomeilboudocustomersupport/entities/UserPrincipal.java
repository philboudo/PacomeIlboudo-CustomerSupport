package com.example.pacomeilboudocustomersupport.entities;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;

import javax.security.auth.Subject;
import java.io.Serial;
import java.io.Serializable;
import java.security.Principal;

@Entity
@Table(name="userprincipals")
public class UserPrincipal implements Principal, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String SESSION_ATTRIBUTE_KEY = "com.example.pacomeilboudocustomersupport.userprincipal.principal";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private long id;

    @Basic
    private String username;

    @Basic
    @Column(name="hashedpassword")
    private byte[] password;

    // Constructors, Getters, and Setters

    public UserPrincipal() {
    }

    public UserPrincipal(String username, byte[] password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserPrincipal user = (UserPrincipal) obj;
        return user.getUsername().equals(username);
    }

    public static Principal getPrincipal(HttpSession session) {
        return session == null ? null : (Principal) session.getAttribute(SESSION_ATTRIBUTE_KEY);
    }

    public static void setPrincipal(HttpSession session, Principal principal) {
        session.setAttribute(SESSION_ATTRIBUTE_KEY, principal);
    }

    @Override
    @Transient
    public String getName() {
        return username;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
