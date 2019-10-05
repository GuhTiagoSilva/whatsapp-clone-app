package com.example.whatsapp.model;

import com.example.whatsapp.config.FirebaseConfig;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class User {

    private String userId;

    private String name;

    private String email;

    private String password;

    public User(){}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void save(){
        DatabaseReference databaseReference = FirebaseConfig.getFirebaseReference();
        DatabaseReference user = databaseReference.child("users").child(getUserId());
        user.setValue(this);
    }

    @Exclude
    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
