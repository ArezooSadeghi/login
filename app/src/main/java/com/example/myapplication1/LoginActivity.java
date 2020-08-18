package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    public static final String ARRAY_OF_USERS = "ArrayOfUsers";
    private EditText mEditTextUsername, mEditTextPassword;
    private Button mButtonLogin, mButtonSignup;

    private LinearLayout mLayoutLogin;

    public static final String USERNAMELOGIN = "usernamelogin";
    public static final String PASSWORDLOGIN = "passwordlogin";
    public static final int REQUEST_CODE_SIGNUP_ACTIVITY = 0;

    public Users[] mUsers = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        if (savedInstanceState != null) {
            mUsers = (Users[]) savedInstanceState.getSerializable("mUsersArray");
        }

        setListeners();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mUsersArray", mUsers);
    }
    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGNUP_ACTIVITY && resultCode == RESULT_OK) {
            String username_signup = data.getStringExtra(SignUpActivity.USERNAMESIGNUP);
            String password_signup = data.getStringExtra(SignUpActivity.PASSWORDSIGNUP);
            mUsers = (Users[]) data.getSerializableExtra("ArrayOfUsers");
            mEditTextUsername.setText(username_signup);
            mEditTextPassword.setText(password_signup);
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
                }
                else {
                    String username = mEditTextUsername.getText().toString();
                    String password = mEditTextPassword.getText().toString();

                            for (int i = 0; i < mUsers.length; i++) {
                            if (mUsers[i].getUsername().equals(username) && mUsers[i].getPassword().equals(password)) {
                                Snackbar snackbar = Snackbar.make(mLayoutLogin, "find user!!!", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }else {
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
                    intent.putExtra(USERNAMELOGIN, mEditTextUsername.getText().toString());
                    intent.putExtra(PASSWORDLOGIN, mEditTextPassword.getText().toString());
                }
                intent.putExtra(ARRAY_OF_USERS, mUsers);
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