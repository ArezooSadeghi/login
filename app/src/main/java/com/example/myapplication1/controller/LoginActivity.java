package com.example.myapplication1.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication1.R;
import com.example.myapplication1.model.Users;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextUsername, mEditTextPassword;
    private Button mButtonLogin, mButtonSignup;

    private LinearLayout mLayoutLogin;

    private static final String M_USERS_ARRAY = "mUsersArray";

    public static final String EXTRA_USERNAMELOGIN = "com.example.myapplication1.usernamelogin";
    public static final String EXTRA_PASSWORDLOGIN = "com.example.myapplication1.passwordlogin";
    public static final String EXTRA_ARRAY_OF_USERS = "com.example.myapplication1.ArrayOfUsers";
    private static final int REQUEST_CODE_SIGNUP_ACTIVITY = 0;

    private Users[] mUsers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        findViews();

        if (savedInstanceState != null) {
            mUsers = (Users[]) savedInstanceState.getSerializable(M_USERS_ARRAY);
        }

        setListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(M_USERS_ARRAY, mUsers);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_SIGNUP_ACTIVITY) {
                String username_signup = data.getStringExtra(SignUpActivity.EXTRA_USERNAMESIGNUP);
                String password_signup = data.getStringExtra(SignUpActivity.EXTRA_PASSWORDSIGNUP);
                mUsers = (Users[]) data.getSerializableExtra(SignUpActivity.EXTRA_ARRAY_OF_USERS_SIGNUP);
                mEditTextUsername.setText(username_signup);
                mEditTextPassword.setText(password_signup);
            }
        }
    }

    private void setListeners() {
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextUsername.getText().toString().isEmpty() || mEditTextPassword.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(LoginActivity.this, "please enter your username and password", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 5);
                    toast.show();
                } else {
                    String username = mEditTextUsername.getText().toString();
                    String password = mEditTextPassword.getText().toString();
                    if (mUsers == null) {
                        Toast toast = Toast.makeText(LoginActivity.this, "yet anyOne Sighn up", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM, 0, 5);
                        toast.show();
                    } else {
                        boolean flag = false;
                        for (int i = 0; i < mUsers.length; i++) {
                            if (mUsers[i].getUsername().equals(username) && mUsers[i].getPassword().equals(password)) {
                                Snackbar snackbar = Snackbar.make(mLayoutLogin, "find user!!!", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                flag = true;
                                break;
                            }
                        }
                        if (flag == false) {
                            Toast toast = Toast.makeText(LoginActivity.this, "don't find user!!!", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.BOTTOM, 0, 5);
                            toast.show();
                        }
                    }
                }
            }
        });

        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                if (!mEditTextUsername.getText().toString().isEmpty() && !mEditTextPassword.getText().toString().isEmpty()) {
                    intent.putExtra(EXTRA_USERNAMELOGIN, mEditTextUsername.getText().toString());
                    intent.putExtra(EXTRA_PASSWORDLOGIN, mEditTextPassword.getText().toString());
                }
                intent.putExtra(EXTRA_ARRAY_OF_USERS, mUsers);
                startActivityForResult(intent, REQUEST_CODE_SIGNUP_ACTIVITY);
            }
        });
    }

    private void findViews() {
        mEditTextUsername = findViewById(R.id.txt_usernamelogin);
        mEditTextPassword = findViewById(R.id.txt_passwordlogin);
        mButtonLogin = findViewById(R.id.btn_loginlogin);
        mButtonSignup = findViewById(R.id.btn_signuplogin);
        mLayoutLogin = findViewById(R.id.loginlayout);
    }
}