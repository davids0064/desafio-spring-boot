package com.nuevospa.gestion.controller;

import com.nuevospa.gestion.api.AuthApi;
import com.nuevospa.gestion.exception.GestionException;
import com.nuevospa.gestion.model.AuthRequestDTO;
import com.nuevospa.gestion.model.AuthResponseDTO;
import com.nuevospa.gestion.security.JwtUtil;
import com.nuevospa.gestion.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacionController implements AuthApi{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUsuarioService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(
            @Valid AuthRequestDTO authRequest) throws GestionException {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new GestionException("Credenciales incorrectas");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        // Asumiendo que AuthResponseDTO es un Record:
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }
}
