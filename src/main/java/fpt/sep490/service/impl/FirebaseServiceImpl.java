package fpt.sep490.service.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.GetUsersResult;
import com.google.firebase.auth.UserRecord;
import fpt.sep490.payload.StringBoolObject;
import fpt.sep490.service.FirebaseService;
import org.springframework.stereotype.Service;

@Service
public class FirebaseServiceImpl implements FirebaseService {
    private FirebaseAuth firebaseAuth;

    public StringBoolObject deleteAccountByEmail(String email) throws FirebaseAuthException {
        UserRecord user = firebaseAuth.getInstance().getUserByEmail(email);
        firebaseAuth.getInstance().deleteUser(user.getUid());

        return new StringBoolObject("isDeleted", true);
    }
}
