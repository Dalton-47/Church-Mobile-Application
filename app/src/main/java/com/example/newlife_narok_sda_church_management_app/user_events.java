package com.example.newlife_narok_sda_church_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_events extends AppCompatActivity {

    DatabaseReference Events;
    private TextView textViewMyUserDate1,textViewMyUserDate2,textViewMyUserDate3;
    TextView textViewMyUserLocation1,textViewMyUserLocation2,textViewMyUserLocation3;
    TextView textViewMyUserAbout1,textViewMyUserAbout2,textViewMyUserAbout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);

        Events= FirebaseDatabase.getInstance().getReference().child("ChurchEvents");

        textViewMyUserDate1=(TextView)  this.findViewById(R.id.textViewUserDate1);
        textViewMyUserDate2 =(TextView)  this.findViewById(R.id.textViewUserDate2);
        textViewMyUserDate3 =(TextView) this.findViewById(R.id.textViewUserDate3);

        textViewMyUserLocation1 = (TextView)  this.findViewById(R.id.textViewUserLocation1);
        textViewMyUserLocation2 = (TextView)  this.findViewById(R.id.textViewUserLocation2);
        textViewMyUserLocation3 = (TextView)  this.findViewById(R.id.textViewUserLocation3);

        textViewMyUserAbout1=(TextView)  this.findViewById(R.id.textViewUserAbout1);
        textViewMyUserAbout2 = (TextView)  this.findViewById(R.id.textViewUserAbout2);
        textViewMyUserAbout3 = (TextView)  this.findViewById(R.id.textViewUserAbout3);

        getUserEvents();

    }

    @Override
    public void onStart() {
        super.onStart();
        getUserEvents();

    }

    public void onResume()
    {
        super.onResume();
        getUserEvents();
    }

    public void onRestart()
    {
        super.onRestart();
        getUserEvents();
    }


    private void getUserEvents()
    {

        String eventKey="FirstEvent";
        Events.child(eventKey).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful() )
                {
                    DataSnapshot myDataSnapShot=task.getResult();
                    String myDate=String.valueOf(myDataSnapShot.child("date").getValue());
                    String myLocation=String.valueOf(myDataSnapShot.child("location").getValue());
                    String myAbout=String.valueOf(myDataSnapShot.child("about").getValue());

                    textViewMyUserDate1.setText(myDate);
                    textViewMyUserLocation1.setText(myLocation);
                    textViewMyUserAbout1.setText(myAbout);

                }
                else
                {
                    Toast.makeText(user_events.this,"CHECK YOUR INTERNET CONNECTION",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Events.child("SecondEvent").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful() )
                {
                    DataSnapshot myDataSnapShot=task.getResult();
                    String myDate=String.valueOf(myDataSnapShot.child("date").getValue());
                    String myLocation=String.valueOf(myDataSnapShot.child("location").getValue());
                    String myAbout=String.valueOf(myDataSnapShot.child("about").getValue());

                    textViewMyUserDate2.setText(myDate);
                    textViewMyUserLocation2.setText(myLocation);
                    textViewMyUserAbout2.setText(myAbout);
                }
            }
        });

        Events.child("ThirdEvent").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful() )
                {
                    DataSnapshot myDataSnapShot=task.getResult();
                    String myDate=String.valueOf(myDataSnapShot.child("date").getValue());
                    String myLocation=String.valueOf(myDataSnapShot.child("location").getValue());
                    String myAbout=String.valueOf(myDataSnapShot.child("about").getValue());

                    textViewMyUserDate3.setText(myDate);
                    textViewMyUserLocation3.setText(myLocation);
                    textViewMyUserAbout3.setText(myAbout);

                }
            }
        });


    }
}