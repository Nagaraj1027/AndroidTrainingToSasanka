package com.example.androidtrainingtosasanka.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.androidtrainingtosasanka.MainActivity;
import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.appclass.ApplicationClass;

public class LoginActivity extends AppCompatActivity {

    EditText userName, passWord;
    Button btnLogin;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        userName = (EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.passWord);
        switch1 =  (Switch) findViewById(R.id.switch1);


        if (ApplicationClass.getRememberMe()){
            gotoMainActivity();
        }

        else {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String username = userName.getText().toString();
                    String password = passWord.getText().toString();
                    Boolean switchState = switch1.isChecked();

                    if (!(username.isEmpty()) && !(password.isEmpty())) {

                        ApplicationClass.putUserNameIntoSp(username);
                        ApplicationClass.putPasswordIntoSp(password);
                        ApplicationClass.setRememberMe(switchState);

                        Log.e("username from SP", ApplicationClass.getUsernameFromSp());
                        Log.e("password from SP", ApplicationClass.getPasswordFromSp());
                        Log.e("remember me from SP", ApplicationClass.getRememberMe().toString());
                        gotoMainActivity();
                    }
                }
            });
        }

    }


    void gotoMainActivity() {
        Intent to_home = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(to_home);
    }

}
