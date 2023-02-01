package com.example.newlife_narok_sda_church_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Admin_Home_Page extends AppCompatActivity {

    private View adminEvents,viewMyAdminBible,viewMyAdminTithe,viewMyAdminHymns;
    DatabaseReference Events;
    private static final String TAG = "ADMIN HOME PAGE";
    private ProgressBar progressBarMyAdminHome;
    private TextView textViewMyAdminHomeDate1,textViewMyAdminHomeDate2,textViewMyAdminHomeDate3;
    private TextView textViewMyAdminHomeLocation1,textViewMyAdminHomeLocation2,textViewMyAdminHomeLocation3;
    private TextView textViewMyAdminHomeAbout1,textViewMyAdminHomeAbout2,textViewMyAdminHomeAbout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        Events=(DatabaseReference) FirebaseDatabase.getInstance().getReference("ChurchEvents");

        progressBarMyAdminHome =(ProgressBar)  this.findViewById(R.id.progressBarAdminHome);

        textViewMyAdminHomeDate1=(TextView)  this.findViewById(R.id.textViewAdminHomeDate1);
        textViewMyAdminHomeDate2=(TextView)  this.findViewById(R.id.textViewAdminHomeDate2);
        textViewMyAdminHomeDate3=(TextView)  this.findViewById(R.id.textViewAdminHomeDate3);

        textViewMyAdminHomeLocation1=(TextView)  this.findViewById(R.id.textViewAdminHomeLocation1);
        textViewMyAdminHomeLocation2=(TextView)  this.findViewById(R.id.textViewAdminHomeLocation2);
        textViewMyAdminHomeLocation3=(TextView)  this.findViewById(R.id.textViewAdminHomeLocation3);

        textViewMyAdminHomeAbout1 =(TextView)  this.findViewById(R.id.textViewAdminHomeAbout1);
        textViewMyAdminHomeAbout2 =(TextView)  this.findViewById(R.id.textViewAdminHomeAbout2);
        textViewMyAdminHomeAbout3 =(TextView)  this.findViewById(R.id.textViewAdminHomeAbout3);

        viewMyAdminHymns =(View)  this.findViewById(R.id.viewAdminHymns);
        viewMyAdminHymns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri myUri=Uri.parse("https://www.sdahymnals.com");
                Intent intent=new Intent(Intent.ACTION_VIEW,myUri);
                startActivity(intent);
            }
        });


        viewMyAdminTithe = (View)  this.findViewById(R.id.viewAdminTithe);
        viewMyAdminTithe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Home_Page.this,financial_page.class) );
            }
        });

        viewMyAdminBible = (View) this.findViewById(R.id.viewAdminBible);
        viewMyAdminBible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri myUri=Uri.parse("https://www.kingjames.bible");
                Intent intent=new Intent(Intent.ACTION_VIEW,myUri);
                startActivity(intent);
            }
        });

        getEventDetails();

        adminEvents=(View) findViewById(R.id.viewAdminEvents);

        adminEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Home_Page.this, add_event.class));
            }
        });
    }

    public void onStart() {
        super.onStart();
        getEventDetails();

    }

    public void onResume()
    {
        super.onResume();
        getEventDetails();
    }

    public void onRestart()
    {
        super.onRestart();
        getEventDetails();
    }

    private void getEventDetails()
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

                    textViewMyAdminHomeDate1.setText(myDate);
                    textViewMyAdminHomeLocation1.setText(myLocation);
                    textViewMyAdminHomeAbout1.setText(myAbout);

                }
                else
                {
                    try {
                        throw Objects.requireNonNull(task.getException());
                    }
                    catch (Exception e) {
                        Log.e(TAG,e.getMessage());
                        Toast.makeText(Admin_Home_Page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                progressBarMyAdminHome.setVisibility(View.GONE);
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

                    textViewMyAdminHomeDate2.setText(myDate);
                    textViewMyAdminHomeLocation2.setText(myLocation);
                    textViewMyAdminHomeAbout2.setText(myAbout);

                }
                progressBarMyAdminHome.setVisibility(View.GONE);
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

                    textViewMyAdminHomeDate3 .setText(myDate);
                    textViewMyAdminHomeLocation3.setText(myLocation);
                    textViewMyAdminHomeAbout3.setText(myAbout);

                }
                progressBarMyAdminHome.setVisibility(View.GONE);
            }
        });





    }


}