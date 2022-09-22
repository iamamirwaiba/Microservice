package com.treeleaf.authentication.AppUser;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AppUserService {
    abstract ResponseEntity<Map<String, String>> signUp(AppUser appUser) throws Exception;
    abstract ResponseEntity<Map<String, String>> signIn(AppUser appUser) throws Exception;
    abstract ResponseEntity<Map<String, String>> getAllUser() throws Exception;
}
