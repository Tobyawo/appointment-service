package edu.miu.cs.cs489.appointmentservice.auth;


import java.util.Set;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Set<String> roleNames
) {
    public RegisterRequest(String mail, String password123, String roleUser) {
        this("", "", mail, password123, Set.of(roleUser));
    }
}
