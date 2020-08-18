package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEditTextUsername, mEditTextPassword;
    private Button mButtonSignup;

    public static final String USERNAMESIGNUP = "usernamesignup";
    public static final String PASSWORDSIGNUP = "passwordsignup";

    public Users[] mUsers;
    public  Users[] newUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign up");

        findViews();

        Intent intent = getIntent();
        String username_login = intent.getStringExtra(LoginActivity.USERNAMELOGIN);
        String password_login = intent.getStringExtra(LoginActivity.PASSWORDLOGIN);
        mUsers = (Users[]) intent.getSerializableExtra(LoginActivity.ARRAY_OF_USERS);
        mEditTextUsername.setText(username_login);
        mEditTextPassword.setText(password_login);


        setlisteners();
    }

    private void setlisteners() {
        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEditTextUsername.getText().toString();
                String password = mEditTextPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast toast = Toast.makeText(SignUpActivity.this, "please enter your username and password", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 3);
                    toast.show();
                } else {
                    if (mUsers == null) {
                        newUsers = new Users[]{new Users(username, password)};
                    } else {
                        int mUserArrayLength = mUsers.length;
                        newUsers = new Users[mUserArrayLength + 1];
                        System.arraycopy(mUsers, 0, newUsers, 0, mUsers.length);
                        newUsers[mUsers.length] = new Users(username, password);
                    }

                    Intent result = new Intent();
                    result.putExtra(USERNAMESIGNUP, mEditTextUsername.getText().toString());
                    result.putExtra(PASSWORDSIGNUP, mEditTextPassword.getText().toString());
                    result.putExtra("ArrayOfUsers", newUsers);
                    setResult(RESULT_OK, result);
                    finish();
                }
            }
        });


    }

    private void findViews() {
        mEditTextUsername = findViewById(R.id.txt_usernamesignup);
        mEditTextPassword = findViewById(R.id.txt_passwordsignup);
        mButtonSignup = findViewById(R.id.btn_signupsignup);
    }
}