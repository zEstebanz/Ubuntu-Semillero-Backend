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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final GoogleClientID googleClientID;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/token")
    public String validateToken(@RequestHeader("Authorization") String authHeader) {

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
                    GoogleIdToken.Payload payload = idToken.getPayload();
                    String email = payload.getEmail();
                    String name = (String) payload.get("name");
                    Usuario findUser = usuarioRepository.findByEmail(email)
                            .orElseThrow(()-> new UserNotFoundException("User not found with email: " + email));



                    return "Usuario validado: " + name + " (" + email + ")";
                } else {
                    return "Token inválido.";
                }
            } catch (Exception e) {
                return "Error al validar el token: " + e.getMessage();
            }

    }

}
