package com.example.newlife_narok_sda_church_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class admin_code extends AppCompatActivity {

    private Button buttonMyAdminCode;
    private EditText  editTextTextMyAdminPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_code);

        editTextTextMyAdminPassword=(EditText)  this.findViewById(R.id.editTextTextAdminPassword);



        buttonMyAdminCode = (Button)  findViewById(R.id.buttonAdminCode);
        buttonMyAdminCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code=editTextTextMyAdminPassword.getText().toString().trim();
                if(!code.equals("0000"))
                {
                    editTextTextMyAdminPassword.setError("WRONG CODE TRY AGAIN");
                }
                else
                {
                    startActivity(new Intent(admin_code.this,Admin_Home_Page.class));
                    finish();
                }

            }
        });
    }
}