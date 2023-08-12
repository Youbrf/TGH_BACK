package be.icc.tgh.service;

import be.icc.tgh.config.JwtService;
import be.icc.tgh.model.AuthenticationRequest;
import be.icc.tgh.model.AuthenticationResponse;
import be.icc.tgh.model.RegisterRequest;
import be.icc.tgh.model.User;
import be.icc.tgh.repository.UserR;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationS {
  private final UserR repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final EmailS emailService;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .confirmationToken(UUID.randomUUID().toString())
        .build();
    repository.save(user);

    String confirmationLink = "http://localhost:4200/confirm?token=" + user.getConfirmationToken();
    emailService.sendConfirmationEmail(user.getEmail(), confirmationLink);

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .build();
  }
}
