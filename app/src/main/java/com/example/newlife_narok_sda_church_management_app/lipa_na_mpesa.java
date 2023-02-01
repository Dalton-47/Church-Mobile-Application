package com.example.newlife_narok_sda_church_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class lipa_na_mpesa extends AppCompatActivity {

    private Button buttonlipanampesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lipa_na_mpesa);

        buttonlipanampesa =(Button) findViewById(R.id.buttonlipanampesa);
        buttonlipanampesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(lipa_na_mpesa.this,enter_amount.class) );
                finish();
            }
        });

    }
}