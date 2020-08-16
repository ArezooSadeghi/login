package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEditTextUsername, mEditTextPassword;
    private Button mButtonSignup;

    public static final String USERNAMESIGNUP = "usernamesignup";
    public static final String PASSWORDSIGNUP = "passwordsignup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Signup");

        findViews();

        Intent intent = getIntent();
        String username_login = intent.getStringExtra(LoginActivity.USERNAMELOGIN);
        String password_login = intent.getStringExtra(LoginActivity.PASSWORDLOGIN);
        mEditTextUsername.setText(username_login);
        mEditTextPassword.setText(password_login);

        setlisteners();
    }

    private void setlisteners() {
        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent();
                result.putExtra(USERNAMESIGNUP, mEditTextUsername.getText().toString());
                result.putExtra(PASSWORDSIGNUP, mEditTextPassword.getText().toString());
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

    private void findViews() {
        mEditTextUsername = findViewById(R.id.txt_usernamesignup);
        mEditTextPassword = findViewById(R.id.txt_passwordsignup);
        mButtonSignup = findViewById(R.id.btn_signupsignup);
    }
}