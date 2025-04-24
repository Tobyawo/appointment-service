package edu.miu.cs.cs489.appointmentservice.auth;

import java.util.List;

public record AuthenticationResponse(
        String token,
        List<String> roles
) {
}
