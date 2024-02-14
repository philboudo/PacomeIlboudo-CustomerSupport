package com.example.pacomeilboudocustomersupport.site;

import com.example.pacomeilboudocustomersupport.entities.UserPrincipal;

public interface UserPrincipalRepository extends GenericRepository<Long, UserPrincipal> {
    UserPrincipal getByUsername(String username);
}

