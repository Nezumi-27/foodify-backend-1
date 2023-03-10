package fpt.sep490.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FirebaseTokenProvider {
    public boolean validateToken(String token){
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public String getUsername(String token){
        try{
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token);
            return firebaseToken.getEmail();
        }
        catch (FirebaseAuthException e){
            throw new RuntimeException("Failed to validate Firebase JWT token", e);
        }
    }
}
