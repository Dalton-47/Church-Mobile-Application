package com.example.newlife_narok_sda_church_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class enter_amount extends AppCompatActivity {
    private Button enteramount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_amount);

        enteramount= (Button)  findViewById(R.id.buttonenteramount);
        enteramount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(enter_amount.this,enter_pin.class) );
                finish();
            }
        });


    }
}