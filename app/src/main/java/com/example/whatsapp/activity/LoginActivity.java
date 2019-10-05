package com.example.whatsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.R;
import com.example.whatsapp.config.FirebaseConfig;
import com.example.whatsapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextView textDontHaveAccount;


    private EditText etEmail;
    private EditText etPasword;
    private Button btLogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textDontHaveAccount = findViewById(R.id.textDontHaveAccount);
        etEmail = findViewById(R.id.etEmail);
        etPasword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btEnter);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        textDontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignInPage();
            }
        });
    }


    public void openSignInPage(){
        startActivity( new Intent(LoginActivity.this, SignInActivity.class));
    }

    public void login() {

        String email = etEmail.getText().toString();
        String password = etPasword.getText().toString();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        if (validateFields(email, password) == true) {
            auth = FirebaseConfig.getFirebaseAuthentication();
            auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Login realizado com sucesso", Toast.LENGTH_SHORT);
                        finish();
                        openMainPage();

                    }else{
                        Log.i("Error Login: ", "Erro ao fazer login");
                    }
                }
            });
        } else {
            validateFields(email, password);
        }


    }

    public boolean validateFields(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos para realizar o cadastro", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        verifyLoggedUser();
        super.onStart();
    }

    public void verifyLoggedUser() {
        auth = FirebaseConfig.getFirebaseAuthentication();
        if (auth.getCurrentUser() != null) {
            //auth.signOut();
            openMainPage();
        }
    }

    public void openMainPage() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

}
