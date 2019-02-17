package com.incapp.databaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button buttonRegister;
    private EditText editttextNumber;
    private EditText edittextPass;
    private EditText edittextName;
    private EditText edittextConfirmpass;
    private TextView textviewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonRegister = findViewById(R.id.button_register);
        editttextNumber = findViewById(R.id.editttext_number);
        edittextPass = findViewById(R.id.edittext_pass);
        edittextName = findViewById(R.id.edittext_name);
        edittextConfirmpass = findViewById(R.id.edittext_confirmpass);
        textviewLogin = findViewById(R.id.textview_login);

        textviewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edittextName.getText().toString();
                String phone = editttextNumber.getText().toString();
                String password = edittextPass.getText().toString();
                String confirmPassword = edittextConfirmpass.getText().toString();

                if (name.isEmpty()) {

                    edittextName.setError("Name Required");

                } else if (phone.isEmpty()) {

                    editttextNumber.setError("Required");

                } else if (password.isEmpty()) {

                    edittextPass.setError("Required");

                } else if (confirmPassword.isEmpty()) {

                    edittextConfirmpass.setError("Required");
                } else if (!password.equalsIgnoreCase(confirmPassword)) {

                    edittextConfirmpass.setError("Password don't match");

                } else {

                    User user = new User();

                    user.setName(name);
                    user.setPassword(password);
                    user.setPhone(phone);

                    UserDao userDao = AppDatabase
                            .getInstance(RegisterActivity.this)
                            .userDao();

                    try {
                        userDao.insert(user);

                        Toast.makeText(RegisterActivity.this,
                                "Successful", Toast.LENGTH_SHORT).show();
                    } catch (Exception e){
                        Toast.makeText(RegisterActivity.this,
                                "User Already exists.", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}
