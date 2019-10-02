package com.example.whatsapp.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {

    private static DatabaseReference firebaseReference;
    private static FirebaseAuth auth;


    public static DatabaseReference getFirebaseReference(){
        if(firebaseReference==null)
            firebaseReference = FirebaseDatabase.getInstance().getReference();
        return firebaseReference;
    }

    public static FirebaseAuth getFirebaseAuthentication(){
        if(auth==null)
            auth = FirebaseAuth.getInstance();
        return auth;
    }


}
