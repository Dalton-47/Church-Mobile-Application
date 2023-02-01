package com.example.newlife_narok_sda_church_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class admin_setting_event extends AppCompatActivity {

    private View viewShowCalendar, viewCalendar;
    private CalendarView calendarViewCALENDAR;
    private TextView textViewSaveDate, textViewEventDate, textViewReadEventDate;
    private Button buttonSaveDetails;
    private EditText editTextLocation, editTextEvent;
    DatabaseReference Events;
    String myEventKey;
    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_setting_event);

        Bundle bundle=getIntent().getExtras();
        myEventKey=bundle.getString("EVENT_KEY");

        Events= FirebaseDatabase.getInstance().getReference("ChurchEvents");

        textViewEventDate = (TextView) findViewById(R.id.textViewAdminEventDate);
        textViewReadEventDate = (TextView) this.findViewById(R.id.textViewAdminEventDate);
        editTextEvent = (EditText) this.findViewById(R.id.editTextTextAdminEvent);
        editTextLocation = (EditText) this.findViewById(R.id.editTextTextAdminLocation);
        buttonSaveDetails = (Button) this.findViewById(R.id.buttonAdminSave);
        viewShowCalendar =(View) this.findViewById(R.id.viewShowAdminCalendar);

        viewShowCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                // Date picker dialog
                picker = new DatePickerDialog(admin_setting_event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        textViewEventDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year, month, day);
                picker.show();
            }
        });

       // displayViews(Boolean.FALSE);

        //when the button is clicked it calls a method that gets users data and stores it in the database
        buttonSaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTextData();
            }
        });

    }

    private void displayViews(Boolean state) {
        if (state == Boolean.TRUE) {
            calendarViewCALENDAR.setVisibility(View.VISIBLE);
            viewCalendar.setVisibility(View.VISIBLE);
            textViewSaveDate.setVisibility(View.VISIBLE);
        } else {
            calendarViewCALENDAR.setVisibility(View.GONE);
            viewCalendar.setVisibility(View.GONE);
            textViewSaveDate.setVisibility(View.GONE);
        }
    }

    private void getTextData() {
        String errorMessage = "CANNOT BE BLANK!";
        String date = textViewEventDate.getText().toString().trim();
        String Location = editTextLocation.getText().toString().trim().toUpperCase();
        String About = editTextEvent.getText().toString().trim().toUpperCase();

        if (date.isEmpty()) {
            textViewEventDate.setError(errorMessage);
        } else if (Location.isEmpty()) {
            editTextLocation.setError(errorMessage);
        } else if (About.isEmpty()) {
            editTextEvent.setError(errorMessage);
        }
        else
        {
            String eventKey="FirstEvent";
            Events.child(eventKey).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        EventClass objEvents = new EventClass(date,Location,About);
                        Events.child(myEventKey).setValue(objEvents);
                        Toast.makeText(admin_setting_event.this,"DETAILS CAPTURED SUCCESSFULLY",Toast.LENGTH_SHORT ).show() ;

                    }
                }
            });


        }
    }
}