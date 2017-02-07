package com.example.tapos.instragramandroidapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private EditText usernameTxt;
    private EditText passwordTxt;
    private Button submitLoginButton;
    private TextView changeTextView;
    private boolean isSignUpStage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Instragram Apps");
        usernameTxt = (EditText) findViewById(R.id.usernameEditText);
        passwordTxt = (EditText) findViewById(R.id.passwordEditText);
        submitLoginButton = (Button) findViewById(R.id.signUpLoginButton);
        changeTextView = (TextView) findViewById(R.id.changeTextView);

        ImageView imageView = (ImageView) findViewById(R.id.mainImageView);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainActivity);


        // add click listener
        submitLoginButton.setOnClickListener(this);
        changeTextView.setOnClickListener(this);
        imageView.setOnClickListener(this);
        layout.setOnClickListener(this);
        passwordTxt.setOnKeyListener(this);
    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.signUpLoginButton) {

            if(usernameTxt.getText().toString().isEmpty() || passwordTxt.getText().toString().isEmpty()){
                Toast.makeText(this,"please complete all the field",Toast.LENGTH_LONG).show();
            }else{





            }

        } else if (view.getId() == R.id.changeTextView) {
            // for textview

            if (this.isSignUpStage) {
                submitLoginButton.setText("login");
                changeTextView.setText("or, SignUp");
                this.isSignUpStage = false;
            } else {
                submitLoginButton.setText("Signup");
                changeTextView.setText("or, Login");
                this.isSignUpStage = true;
            }
        } else if (view.getId() == R.id.mainActivity || view.getId() == R.id.mainImageView) {
            // for imageView or Layout
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i==keyEvent.KEYCODE_ENTER)
        {
            onClick(submitLoginButton);
        }
        return false;

    }
}
