package com.semillero.ubuntu.Security.userauth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Exceptions.token.AuthTokenNotFoundException;
import com.semillero.ubuntu.Exceptions.token.ValidateTokenException;
import com.semillero.ubuntu.Exceptions.usuario.UserNotFoundException;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Security.jwt.JwtService;
import com.semillero.ubuntu.Utils.GoogleClientID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthService {

    private final JwtService jwtService;
    private final GoogleClientID googleClientID;
    private final UsuarioRepository usuarioRepository;

    public String validateToken(String authHeader) {

        var token = extractTokenFromHeader(authHeader);
        var verifierToken = validateGoogleToken(token);
        String email = verifierToken.getEmail();
        Usuario findUser = findUserByEmail(email);
        var userAuth = new UserAuth(findUser);
        var jwtToken = jwtService.generateToken(userAuth);
        log.info("TOKEN: " + jwtToken);

        return jwtToken;
    }

    public String getUserInfo(String authHeader){

        var token = extractTokenFromHeader(authHeader);
        var email = jwtService.extractUsername(token);
        Usuario findUser = findUserByEmail(email);
        Map<String,Object> insertUserInfo = generateTokenWithUserDetails(findUser);
        UserAuth userAuth = new UserAuth(findUser);
        String jwtToken = jwtService.generateToken(insertUserInfo,userAuth);
        log.info("TOKEN: " + jwtToken);

        return jwtToken;
    }

    private String extractTokenFromHeader(String authHeader){
        if (authHeader.isEmpty()) {
            throw new AuthTokenNotFoundException("Token is missing in the request header.");
        } else if (!authHeader.startsWith("Bearer ")){
            throw new ValidateTokenException("Token sent in an invalid format");
        }
        return authHeader.substring(7);
    }

    private GoogleIdToken.Payload validateGoogleToken(String token) {
        var verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleClientID.getID_CLIENT()))
                .build();
        try {
            GoogleIdToken idToken = verifier.verify(token);
            if (idToken == null) {
                throw new ValidateTokenException("Token is null.");
            }
            return idToken.getPayload();
        } catch (Exception e) {
            throw new ValidateTokenException(e.getMessage());
        }
    }

    private Usuario findUserByEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("User not found with email: " + email));
    }

    private Map<String,Object> generateTokenWithUserDetails(Usuario usuario){
        Map<String,Object> insertUserInfo = new HashMap<>();
        insertUserInfo.put("Nombre",usuario.getNombre());
        insertUserInfo.put("Apellido",usuario.getApellido());
        insertUserInfo.put("Email",usuario.getEmail());
        insertUserInfo.put("IsDeleted",usuario.getIsDeleted());
        insertUserInfo.put("Rol",usuario.getRol());
        return insertUserInfo;
    }

}
