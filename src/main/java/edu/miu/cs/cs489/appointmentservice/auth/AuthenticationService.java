package edu.miu.cs.cs489.appointmentservice.auth;


import edu.miu.cs.cs489.appointmentservice.model.Role;
import edu.miu.cs.cs489.appointmentservice.model.User;
import edu.miu.cs.cs489.appointmentservice.repository.RoleRepository;
import edu.miu.cs.cs489.appointmentservice.repository.UserRepository;
import edu.miu.cs.cs489.appointmentservice.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );
        //generate token for the user
        var user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        List<String> rolesList = user.getRoles().stream().map(Role::getName).toList();
        return new AuthenticationResponse(token, rolesList);
    }


    public AuthenticationResponse createUserWithRoles(RegisterRequest createUserRequest) {
        // Encode the password
        String encodedPassword = passwordEncoder.encode(createUserRequest.password());

        // Fetch roles from the database
        Set<Role> roles;
        if (createUserRequest.roleNames() == null || createUserRequest.roleNames().isEmpty()) {
            roles = Set.of(roleRepository.findByName("MEMBER").orElseThrow(()->  new IllegalArgumentException("No roles found for the provided role names.")));

        }else {
            roles = roleRepository.findByNameIn(createUserRequest.roleNames());
        }

        // Create user with multiple roles
        User user = new User(
                createUserRequest.firstName(),
                createUserRequest.lastName(),
                createUserRequest.username(),
                encodedPassword,
                roles
        );

        User registeredUser = userRepository.save(user);
        //Generate token for this user
        String token = jwtService.generateToken(registeredUser);
        List<String> rolesList = registeredUser.getRoles().stream().map(Role::getName).toList();
        return new AuthenticationResponse(token, rolesList);

    }


}