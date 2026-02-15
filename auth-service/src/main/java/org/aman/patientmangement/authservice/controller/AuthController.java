package org.aman.patientmangement.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.aman.patientmangement.authservice.dto.LoginRequestDTO;
import org.aman.patientmangement.authservice.dto.LoginResponseDTO;
import org.aman.patientmangement.authservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "User login endpoint", description = "Authenticates user and returns a JWT token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO){

       Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);

       if(tokenOptional.isEmpty()){
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
       }

       String token = tokenOptional.get();
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
