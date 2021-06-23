package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Currentstatus extends AppCompatActivity {
    DatabaseReference reqref,acceptedref,receiverref,reference;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentstatus);
        String userPhoneNumber=FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        reqref=FirebaseDatabase.getInstance().getReference("Requests").child(userPhoneNumber);
        acceptedref=FirebaseDatabase.getInstance().getReference("Accepted Requests");
        reference=FirebaseDatabase.getInstance().getReference("donorForm");
        receiverref=FirebaseDatabase.getInstance().getReference().child("Receivers");

        reqref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    //he is donor
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        receiverref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    //he is receiver
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });




    }
}