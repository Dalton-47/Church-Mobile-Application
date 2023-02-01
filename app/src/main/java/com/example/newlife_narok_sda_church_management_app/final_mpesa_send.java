package com.example.newlife_narok_sda_church_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class final_mpesa_send extends AppCompatActivity {

    private Button btnCancelMPESA,btnSendMpesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_mpesa_send);

        btnCancelMPESA=(Button)  findViewById(R.id.buttonCancelMPESA);
        btnSendMpesa =(Button)  findViewById(R.id.buttonSendMpesa);

        btnSendMpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent=new Intent(final_mpesa_send.this,LoginActivity.class);

               // myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
              //  startActivity(myIntent);
                finish(); // Close UserProfile Activity

               // startActivity(new Intent(final_mpesa_send.this, newLifeUser.class));

            }
        });

        btnCancelMPESA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(final_mpesa_send.this, newLifeUser.class));
            }
        });


    }
}