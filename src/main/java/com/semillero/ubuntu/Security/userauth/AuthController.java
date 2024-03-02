package com.semillero.ubuntu.Security.userauth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/token")
    public String validateToken(@RequestHeader("Authorization") String authHeader) {

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);


            final String ID_CLIENT = "";
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(ID_CLIENT))
                    .build();

            GoogleIdToken idToken;

            try {
                idToken = verifier.verify(token);
                if (idToken != null) {
                    GoogleIdToken.Payload payload = idToken.getPayload();

                    // Aquí puedes obtener y usar la información del usuario
                    String userId = payload.getSubject();
                    String email = payload.getEmail();
                    boolean emailVerified = payload.getEmailVerified();
                    String name = (String) payload.get("name");

                    // Ejemplo de uso de la información del usuario
                    return "Usuario validado: " + name + " (" + email + ")";
                } else {
                    return "Token inválido.";
                }
            } catch (Exception e) {
                return "Error al validar el token: " + e.getMessage();
            }
        }
        return "no se proporciono un token valido";
    }

}
