package com.example.myapplication1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public int resultCodeSignUp = 0;
    public static Users[] sUsers = new Users[10];
    public static final String INFORMATION_1 = "Information1";
    public static final String INFORMATION_2 = "Information2";
    private Button mButtonLogin, mButtonSignup;

    private EditText mEditTextUsername, mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();

        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                if (mEditTextUsername.getText() != null && mEditTextPassword.getText() != null) {
                    intent.putExtra(INFORMATION_1, mEditTextUsername.getText().toString());
                    intent.putExtra(INFORMATION_2, mEditTextPassword.getText().toString());
                    startActivityForResult(intent, resultCodeSignUp);
                }

            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextUsername.getText().toString().isEmpty() || mEditTextPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Invalid!!!", Toast.LENGTH_SHORT).show();
                }else {
                   if (mEditTextUsername.getText().toString() == sUsers[0].getUsername() && mEditTextPassword.getText().toString() == sUsers[0].getPassword()) {
                       Toast.makeText(LoginActivity.this, "Is Correct", Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && requestCode == RESULT_OK) {
            String uesrname = data.getStringExtra(SignUpActivity.USENAME);
            String password = data.getStringExtra(SignUpActivity.PASSWORD);
            sUsers[0].setUsername(uesrname);
            sUsers[0].setPassword(password);

        }
    }

    private void findViews() {
        mButtonLogin = findViewById(R.id.btn_login);
        mButtonSignup = findViewById(R.id.btn_signup);
        mEditTextUsername = findViewById(R.id.txt_username);
        mEditTextPassword = findViewById(R.id.txt_password);
    }


}