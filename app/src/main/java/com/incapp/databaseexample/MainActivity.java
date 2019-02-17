package com.incapp.databaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edittextPhone;
    private Button buttonLogin;
    private EditText edittextPassword;
    private TextView textviewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittextPhone = findViewById(R.id.edittext_phone);
        buttonLogin = findViewById(R.id.button_login);
        edittextPassword = findViewById(R.id.edittext_password);
        textviewRegister = findViewById(R.id.textview_register);

        textviewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = edittextPhone.getText().toString();
                String password = edittextPassword.getText().toString();

                if (phone.isEmpty()) {
                    edittextPhone.setError("Required!");
                } else if (password.isEmpty()) {
                    edittextPassword.setError("Required!");
                } else {

                    UserDao userDao = AppDatabase
                            .getInstance(MainActivity.this)
                            .userDao();

                    User user = userDao.getUser(phone, password);

                    if (user == null) {
                        Toast.makeText(MainActivity.this,
                                "Login Failed.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Login Success.", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


    }
}
