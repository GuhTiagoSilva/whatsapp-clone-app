package com.example.whatsapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.R;
import com.example.whatsapp.config.FirebaseConfig;
import com.example.whatsapp.helper.Base64Custom;
import com.example.whatsapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etName;
    private Button btCreate;
    private FirebaseAuth auth;
    private FirebaseDatabase fireRef;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etName);

        btCreate = findViewById(R.id.btCreateAccount);


        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createUser();
            }
        });

    }

    public void createUser() {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String name = etName.getText().toString();

        user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        if(validateFields(email,password)==true){
            auth = FirebaseConfig.getFirebaseAuthentication();
            auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    String message = "";
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT);
                        finish();

                        try{
                            String userId = Base64Custom.EncodeBase64(user.getEmail());
                            user.setUserId(userId);
                            user.save();

                        }catch(Exception e){
                            e.printStackTrace();
                        }


                    } else {

                        try {
                            throw task.getException();
                        }catch (FirebaseAuthInvalidCredentialsException e){
                            message = "Email e senha não correspondem a usuário cadastrado";
                        }catch(FirebaseAuthUserCollisionException e ){
                            message = "Usuário já cadastrado no sistema";
                        }catch(Exception e ){
                            message = e.getMessage() ;
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext() ,message, Toast.LENGTH_SHORT).show();
                    }
                }


            });
        }else{
            validateFields(email,password);
        }


    }

    public boolean validateFields(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Preencha todos os campos para realizar o cadastro", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}


