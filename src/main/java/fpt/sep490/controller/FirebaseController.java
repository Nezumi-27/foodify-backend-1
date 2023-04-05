package fpt.sep490.controller;

import com.google.firebase.auth.FirebaseAuthException;
import fpt.sep490.payload.StringBoolObject;
import fpt.sep490.service.FirebaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Firebase Controller")
@RestController
@RequestMapping("/api/firebase")
public class FirebaseController {
    private final FirebaseService firebaseService;

    public FirebaseController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @ApiOperation("Delete firebase user by email")
    @DeleteMapping("{email}")
    public ResponseEntity<StringBoolObject> deleteFirebaseUserByEmail(@PathVariable(value = "email") String email) throws FirebaseAuthException {
        return ResponseEntity.ok(firebaseService.deleteAccountByEmail(email));
    }
}
