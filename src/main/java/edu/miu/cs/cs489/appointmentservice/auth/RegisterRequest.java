package edu.miu.cs.cs489.appointmentservice.auth;


import edu.miu.cs.cs489.appointmentservice.model.Role;

import java.util.Set;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Set<String> roleNames
) {
}
