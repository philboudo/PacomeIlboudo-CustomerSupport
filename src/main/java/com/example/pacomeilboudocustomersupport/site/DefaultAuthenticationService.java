package com.example.pacomeilboudocustomersupport.site;

import com.example.pacomeilboudocustomersupport.entities.UserPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class DefaultAuthenticationService implements AuthenticationService {
    private static final int HASHING_ROUNDS = 10;

    private final UserPrincipalRepository userRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(HASHING_ROUNDS);

    public DefaultAuthenticationService(UserPrincipalRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserPrincipal authenticate(String username, String password) {
        UserPrincipal principal = userRepo.getByUsername(username);
        if (principal == null) {
            return null;
        }

        // Check if the password matches
        if (!passwordEncoder.matches(password, new String(principal.getPassword(), StandardCharsets.UTF_8))) {
            return null;
        }

        // Password is correct
        return principal;
    }

    @Override
    public void saveUser(UserPrincipal principal, String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            String hashedPassword = passwordEncoder.encode(newPassword);
            principal.setPassword(hashedPassword.getBytes());
        }

        // Check if the user is new or existing
        if (principal.getId() < 1) {
            this.userRepo.add(principal);
        } else {
            this.userRepo.update(principal);
        }
    }
}
