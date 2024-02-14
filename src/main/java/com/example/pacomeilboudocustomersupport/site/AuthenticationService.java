package com.example.pacomeilboudocustomersupport.site;

import com.example.pacomeilboudocustomersupport.entities.UserPrincipal;

public interface AuthenticationService {
    UserPrincipal authenticate(String username, String password);
    void saveUser(UserPrincipal principal, String newPassword);
}
