package edu.miu.cs.cs489.appointmentservice.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
