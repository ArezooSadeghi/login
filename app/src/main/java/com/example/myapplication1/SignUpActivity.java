package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    public static final String USENAME = "usename";
    public static final String PASSWORD = "password";
    private EditText mEditTextUsername, mEditTextPassword;

    private Button mButtonSignup;
    String username, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViews();

        final Intent intent = getIntent();
        username = intent.getStringExtra(LoginActivity.INFORMATION_1);
        password = intent.getStringExtra(LoginActivity.INFORMATION_2);


        mEditTextUsername.setText(username);
        mEditTextPassword.setText(password);




        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                intent.putExtra(USENAME, mEditTextUsername.getText().toString());
                intent.putExtra(PASSWORD, mEditTextPassword.getText().toString());
                setResult(RESULT_OK, intent);
                finish();

            }
        });


    }

    private void findViews() {
        mButtonSignup = findViewById(R.id.btn_signup);
        mEditTextUsername = findViewById(R.id.txt_username);
        mEditTextPassword = findViewById(R.id.txt_password);
    }
}