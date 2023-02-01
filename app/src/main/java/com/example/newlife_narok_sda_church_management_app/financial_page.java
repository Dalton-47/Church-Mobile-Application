package com.example.newlife_narok_sda_church_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class financial_page extends AppCompatActivity {

    private View viewlipanampesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_page);

        viewlipanampesa =(View)  findViewById(R.id.viewlipanampesa);
        viewlipanampesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(financial_page.this,lipa_na_mpesa.class));
            }
        });
    }
}