package fpt.sep490.service;

import com.google.firebase.auth.FirebaseAuthException;
import fpt.sep490.payload.StringBoolObject;

public interface FirebaseService {
    StringBoolObject deleteAccountByEmail(String email) throws FirebaseAuthException;
}
