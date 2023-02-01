package com.example.newlife_narok_sda_church_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class enter_pin extends AppCompatActivity {

    private Button btnenterPinMPESA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);

        btnenterPinMPESA=(Button)  findViewById(R.id.buttonenterPinMPESA);
        btnenterPinMPESA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(enter_pin.this,final_mpesa_send.class) );
                finish();
            }
        });
    }
}