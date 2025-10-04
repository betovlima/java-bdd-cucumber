package com.example.bdd.auth;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    public enum LoginResult { SUCCESS, BAD_CREDENTIALS, USER_NOT_FOUND }

    private static final Map<String, String> USERS = Map.of(
            "joao", "123456",
            "maria", "senhaSecreta"
    );

    public LoginResult login(String user, String password) {
        if (user == null || password == null) return LoginResult.BAD_CREDENTIALS;
        if (!USERS.containsKey(user)) return LoginResult.USER_NOT_FOUND;
        String expected = USERS.get(user);
        return expected.equals(password) ? LoginResult.SUCCESS : LoginResult.BAD_CREDENTIALS;
    }
}