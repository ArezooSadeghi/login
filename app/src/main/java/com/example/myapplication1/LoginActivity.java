package com.example.myapplication1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextUsername, mEditTextPassword;
    private Button mButtonLogin, mButtonSignup;

    public static final String USERNAMELOGIN = "usernamesignup";
    public static final String PASSWORDLOGIN = "passwordsignup";
    public static final int REQUEST_CODE_SIGNUP_ACTIVITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();

        setListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGNUP_ACTIVITY && resultCode == RESULT_OK) {
            String username_signup = data.getStringExtra(SignUpActivity.USERNAMESIGNUP);
            String password_signup = data.getStringExtra(SignUpActivity.PASSWORDSIGNUP);
            mEditTextUsername.setText(username_signup);
            mEditTextPassword.setText(password_signup);
        }
    }

    private void setListeners() {
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextUsername.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(LoginActivity.this, "please enter your username", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 5);
                    toast.show();
                }
                if (mEditTextPassword.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(LoginActivity.this, "please enter your password", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 5);
                    toast.show();
                }
            }
        });

        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                if (!mEditTextUsername.getText().toString().isEmpty() && !mEditTextPassword.getText().toString().isEmpty()) {
                    intent.putExtra(USERNAMELOGIN, mEditTextUsername.getText().toString());
                    intent.putExtra(PASSWORDLOGIN, mEditTextPassword.getText().toString());
                }
                startActivityForResult(intent, 0);
            }
        });
    }

    private void findViews() {
        mEditTextUsername = findViewById(R.id.txt_usernamelogin);
        mEditTextPassword = findViewById(R.id.txt_passwordlogin);
        mButtonLogin = findViewById(R.id.btn_loginlogin);
        mButtonSignup = findViewById(R.id.btn_signuplogin);
    }
}