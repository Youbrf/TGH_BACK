package be.icc.tgh.controller;

import be.icc.tgh.model.AuthenticationRequest;
import be.icc.tgh.model.AuthenticationResponse;
import be.icc.tgh.model.RegisterRequest;
import be.icc.tgh.model.User;
import be.icc.tgh.service.AuthenticationS;
import be.icc.tgh.service.UserS;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationC {

  @Autowired
  private AuthenticationS service;

  @Autowired
  private UserS userService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @GetMapping("/confirm")
  public ResponseEntity<String> confirmEmail(@RequestParam("token") String token) {
    User user = userService.findByConfirmationToken(token);
    if (user == null) {
      return ResponseEntity.badRequest().body("Token de confirmation invalide.");
    }
    user.setConfirmationToken(null);
    userService.creerUser(user);
    return ResponseEntity.ok("Email confirmé avec succès");
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    User user = userService.findByEmail(request.getEmail())
            .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé."));

    if (user.getConfirmationToken() != null) {
      return ResponseEntity.badRequest().body(new AuthenticationResponse("Veuillez confirmer votre email avant de vous connecter."));
    }

    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/send-email-reset-password")
  public ResponseEntity<AuthenticationResponse> resetPassword(@RequestBody String email) {
    return ResponseEntity.ok(service.resetPassword(email));
  }



}
