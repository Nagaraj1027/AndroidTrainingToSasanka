package com.example.androidtrainingtosasanka.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidtrainingtosasanka.MainActivity;
import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.appclass.ApplicationClass;

public class LoginActivity extends AppCompatActivity {

    EditText userName, passWord;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        userName = (EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.passWord);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userName.getText().toString();
                String password = passWord.getText().toString();

                if (!(username.isEmpty()) && !(password.isEmpty())) {

                    ApplicationClass.putUserNameIntoSp(username);
                    ApplicationClass.putPasswordIntoSp(password);

                    Log.e("username from SP", ApplicationClass.getUsernameFromSp());
                    Log.e("password from SP", ApplicationClass.getPasswordFromSp());

                    Intent to_home = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(to_home);

                }
            }
        });

    }
}
