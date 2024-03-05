package com.semillero.ubuntu.Security.userauth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Exceptions.AuthTokenNotFoundException;
import com.semillero.ubuntu.Exceptions.usuario.UserNotFoundException;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Config.GoogleClientID;
import com.semillero.ubuntu.Security.jwt.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final JwtService jwtService;
    private final GoogleClientID googleClientID;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/token")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader, HttpServletResponse response) {
        log.info("ENTRE AL ENDPOINT");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AuthTokenNotFoundException("Token is missing in the request header.");
        }

        String token = authHeader.substring(7);
        final String ID_CLIENT = googleClientID.getID_CLIENT();
        var verifier = new GoogleIdTokenVerifier.Builder
                (new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(ID_CLIENT))
                .build();

        GoogleIdToken idToken;

            try {

                idToken = verifier.verify(token);

                if (idToken != null) {
                    log.info("TOKEN DE GOOGLE VALIDADO CON EXITO");
                    GoogleIdToken.Payload payload = idToken.getPayload();
                    String email = payload.getEmail();
                    Usuario findUser = usuarioRepository.findByEmail(email)
                            .orElseThrow(()-> new UserNotFoundException("User not found with email: " + email));

                    var userAuth = new UserAuth(findUser);

                    var jwtToken = jwtService.generateToken(userAuth);
                    log.info("TOKEN = " + jwtToken);
                    response.setHeader("Authorization", jwtToken);

                    return ResponseEntity.ok().build();

                } else {

                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.");
                }

            } catch (Exception e) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error validating token: " + e.getMessage());
            }
    }
}
