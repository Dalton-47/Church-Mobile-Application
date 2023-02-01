package com.example.newlife_narok_sda_church_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_event extends AppCompatActivity {

    private FloatingActionButton myFloatingActionButton;
    private TextView textViewMyDate1,textViewMyDate2,textViewMyDate3;
    private TextView textViewMyLocation1,textViewMyLocation2,textViewMyLocation3;
    private TextView textViewMyAbout1,textViewMyAbout2,textViewMyAbout3;
    private View dividerMyEvent2,dividerMyEvent3;
    private View viewMyEvent1,viewMyEvent2,viewMyEvent3;
    private ImageView imageViewMyEvent1,imageViewMyEvent2,imageViewMyEvent3;
    private View deleteEvent1,deleteEvent2,deleteEvent3;
    private ProgressBar progressBarAdmin;
    private static final String TAG = "ADMIN EVENT PAGE";
    DatabaseReference Events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Events=FirebaseDatabase.getInstance().getReference("ChurchEvents");

        progressBarAdmin =(ProgressBar)  this.findViewById(R.id.progressBarAdminEvents);
        progressBarAdmin.setVisibility(View.VISIBLE);

        textViewMyDate1 =(TextView) this.findViewById(R.id.textViewDate1);
        textViewMyDate2 =(TextView) this.findViewById(R.id.textViewDate2);
        textViewMyDate3 =(TextView) this.findViewById(R.id.textViewDate3);

        textViewMyLocation1 =(TextView) this.findViewById(R.id.textViewLocation1);
        textViewMyLocation2 =(TextView) this.findViewById(R.id.textViewLocation2);
        textViewMyLocation3 =(TextView) this.findViewById(R.id.textViewLocation3);

        textViewMyAbout1 =(TextView) this.findViewById(R.id.textViewAbout1);
        textViewMyAbout2 =(TextView) this.findViewById(R.id.textViewAbout2);
        textViewMyAbout3 =(TextView) this.findViewById(R.id.textViewAbout3);

        dividerMyEvent2 = (View) this.findViewById(R.id.dividerEvent2);
        dividerMyEvent3 = (View) this.findViewById(R.id.dividerEvent3);

        viewMyEvent1=(View) this.findViewById(R.id.viewEvent1);
        viewMyEvent2=(View) this.findViewById(R.id.viewEvent2);
        viewMyEvent3=(View) this.findViewById(R.id.viewEvent3);

        imageViewMyEvent1=(ImageView)  this.findViewById(R.id.imageViewEvent1);
        imageViewMyEvent2=(ImageView)  this.findViewById(R.id.imageViewEvent2);
        imageViewMyEvent3=(ImageView)  this.findViewById(R.id.imageViewEvent3);

        deleteEvent1=(View) this.findViewById(R.id.viewDeleteFirstEvent);
        deleteEvent2=(View) this.findViewById(R.id.viewDeleteSecondEvent);
        deleteEvent3=(View) this.findViewById(R.id.viewDeleteThirdEvent);

        deleteEvent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              showAlertDialog("FirstEvent");
            }
        });

        deleteEvent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               showAlertDialog("SecondEvent");

            }
        });

        deleteEvent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 showAlertDialog("ThirdEvent");
            }
        });


        getEventDetails();//adds details to TextViews

        myFloatingActionButton = (FloatingActionButton)  findViewById(R.id.floatingActionButton);
        myFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(add_event.this, admin_setting_event.class));

                checkEvents();
            }
        });
    }

    @Override
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

    //
    private void showAlertDialog(String eventName) {
// Set up the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(add_event.this);
        builder.setTitle("DELETE EVENT");
        builder.setMessage("ARE YOU SURE YOU WANT TO DELETE THIS EVENT?");


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Events.child(eventName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            EventClass objEvents = new EventClass("", "", "");
                            Events.child(eventName).setValue(objEvents);
                            Toast.makeText(add_event.this, "EVENT DELETED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                           getEventDetails();

                        }
                        else
                        {
                            Toast.makeText(add_event.this, "OPERATION FAILED,CHECK NETWORK CONNECTION", Toast.LENGTH_SHORT).show();

                        }


                    }
                });


            }

        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(add_event.this, "OPERATION CANCELLED!", Toast.LENGTH_SHORT).show();

            }
        });

        // Create the AlertDialog
        AlertDialog alertDialog = builder.create();

        // Show the AlertDialog
        alertDialog.show();

    }
    //

    private void checkEvents()
    {

        getEventDetails();

        String eventKey="FirstEvent";
        Events.child(eventKey).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful() )
                {
                    DataSnapshot myDataSnapShot=task.getResult();
                    String myDate=String.valueOf(myDataSnapShot.child("date").getValue());

                    if(myDate.isEmpty())
                    {
                        Intent intent=new Intent(add_event.this,admin_setting_event.class);
                        Bundle bundle =new Bundle();
                        bundle.putString("EVENT_KEY","FirstEvent");//key & Value
                        intent.putExtras(bundle);
                        startActivity(intent);


                    }
                    else
                    {
                        Events.child("SecondEvent").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if(task.isSuccessful() )
                                {
                                    DataSnapshot myDataSnapShot=task.getResult();
                                    String myDate=String.valueOf(myDataSnapShot.child("date").getValue());

                                    if(myDate.isEmpty())
                                    {
                                        Intent intent=new Intent(add_event.this,admin_setting_event.class);
                                        Bundle bundle =new Bundle();
                                        bundle.putString("EVENT_KEY","SecondEvent");//key & Value
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }
                                    else
                                    {
                                        Events.child("ThirdEvent").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                if(task.isSuccessful() )
                                                {
                                                    DataSnapshot myDataSnapShot=task.getResult();
                                                    String myDate=String.valueOf(myDataSnapShot.child("date").getValue());

                                                    if(myDate.isEmpty())
                                                    {
                                                        Intent intent=new Intent(add_event.this,admin_setting_event.class);
                                                        Bundle bundle =new Bundle();
                                                        bundle.putString("EVENT_KEY","ThirdEvent");//key & Value
                                                        intent.putExtras(bundle);
                                                        startActivity(intent);


                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(add_event.this,"ALL SLOTS ARE FULLY BOOKED, CLEAR ONE TO PROCEED",Toast.LENGTH_SHORT ).show();
                                                    }

                                                }
                                            }
                                        });
                                    }

                                }

                            }
                        });
                    }

                }
                else
                {
                    try {
                        throw task.getException();
                    }
                    catch (Exception e) {
                        Log.e(TAG,e.getMessage());
                        Toast.makeText(add_event.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                   // Toast.makeText(add_event.this,"CHECK YOUR NETWORK CONNECTION AND TRY AGAIN",Toast.LENGTH_SHORT).show();
                }


                //progressBarAdmin.setVisibility(View.GONE);
            }
        });



    }


private void getEventDetails()
{

    progressBarAdmin.setVisibility(View.VISIBLE);
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

                textViewMyDate1.setText(myDate);
                textViewMyLocation1.setText(myLocation);
                textViewMyAbout1.setText(myAbout);

            }
            else
            {
                try {
                    throw task.getException();
                }
                catch (Exception e) {
                    Log.e(TAG,e.getMessage());
                    Toast.makeText(add_event.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            progressBarAdmin.setVisibility(View.GONE);
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

                textViewMyDate2.setText(myDate);
                textViewMyLocation2.setText(myLocation);
                textViewMyAbout2.setText(myAbout);

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

                textViewMyDate3.setText(myDate);
                textViewMyLocation3.setText(myLocation);
                textViewMyAbout3.setText(myAbout);

            }
        }
    });





}


}