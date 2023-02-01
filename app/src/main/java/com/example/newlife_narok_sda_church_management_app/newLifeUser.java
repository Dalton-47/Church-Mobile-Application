package com.example.newlife_narok_sda_church_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class newLifeUser extends AppCompatActivity {

    private View viewTithes,viewMyUserEvents,viewMyUserBible;
   public ImageView imageViewMyUserProfile;
   private TextView textViewWelcomeMyUser;
   private ProgressBar progressBarMyUser;
    private FirebaseAuth authUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_life_user);

        textViewWelcomeMyUser=(TextView)  this.findViewById(R.id.textViewWelcomeUser);
        progressBarMyUser=(ProgressBar)  this.findViewById(R.id.progressBarUser);
        viewMyUserBible = (View) this.findViewById(R.id.viewUserBible);

        viewMyUserBible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri myUri=Uri.parse("https://www.kingjames.bible");
                Intent intent=new Intent(Intent.ACTION_VIEW,myUri);
                startActivity(intent);
            }
        });
        //
        viewMyUserEvents = (View) this.findViewById(R.id.viewUserEvents);
        viewMyUserEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(newLifeUser.this,user_events.class));
            }
        });

        viewTithes =(View) findViewById(R.id.viewTithesandOfferings);
        viewTithes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(newLifeUser.this, financial_page.class));
            }
        });

        imageViewMyUserProfile=(ImageView) this.findViewById(R.id.imageViewUserProfile);
        imageViewMyUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(newLifeUser.this,UserProfileActivity.class));
            }
        });

        authUserProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseMyUser = authUserProfile.getCurrentUser();

        if (firebaseMyUser == null){
            Toast.makeText(newLifeUser.this, "Something went wrong! User's details are not available at the moment", Toast.LENGTH_LONG).show();
        } else {
            checkIfEmailVerified(firebaseMyUser);
            progressBarMyUser.setVisibility(View.VISIBLE);
            showUserProfile(firebaseMyUser);
        }


    }

    //
    // Users coming to UserProfileActivity after successful registration
    private void checkIfEmailVerified(FirebaseUser firebaseUser) {
        if (!firebaseUser.isEmailVerified()){
            showAlertDialog();
        }
    }

    private void showAlertDialog() {
// Set up the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(newLifeUser.this);
        builder.setTitle("Email not verified");
        builder.setMessage("Please verify your email now. You can not login without email verification next time.");

        // Open email Apps if User clicks/taps Continue button
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // To email app in new window and not within our app
                startActivity(intent);
            }
        });

        // Create the AlertDialog
        AlertDialog alertDialog = builder.create();

        // Show the AlertDialog
        alertDialog.show();
    }
    //



    //

    private void showUserProfile(FirebaseUser firebaseUser) {

        String userID = firebaseUser.getUid();

        // Extracting User reference from Database for "Registered Users"
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null){
                    String fullName = firebaseUser.getDisplayName();
                   String  email = firebaseUser.getEmail();
                   String doB = readUserDetails.doB;
                    String gender = readUserDetails.gender;
                    String mobile = readUserDetails.mobile;

                    textViewWelcomeMyUser.setText("Welcome, " + fullName + "!");


                    // Set User DP (After user has uploaded)
                    Uri uri = firebaseUser.getPhotoUrl();

                    if(uri==null)
                    {
                        imageViewMyUserProfile.setBackgroundResource(R.drawable.blank_user) ;
                        Toast.makeText(newLifeUser.this,"Click User Icon to set Profile!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        // ImageViewer setImageURI() should not be used with regular URIs. So we are using Picasso
                        Picasso.with(newLifeUser.this).load(uri).into(imageViewMyUserProfile);
                    }

                } else {
                    Toast.makeText(newLifeUser.this,"Something went wrong!", Toast.LENGTH_LONG).show();
                }
                progressBarMyUser.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(newLifeUser.this,"Something went wrong!", Toast.LENGTH_LONG).show();

            }
        });
    }
}