package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    Button ddetails,rdetails,vdetails;
    TextView Reqacceptedbyreceiver, Reqacceptedbyvolunteer,congrats,Reqaccepted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentstatus);

        ddetails=(Button) findViewById(R.id.textView24);
        rdetails=(Button) findViewById(R.id.textView25);
        vdetails=(Button) findViewById(R.id.textView26);
        Reqacceptedbyreceiver=findViewById(R.id.textView20);
        Reqacceptedbyvolunteer=findViewById(R.id.textView22);
        congrats= findViewById(R.id.textView23);
        Reqaccepted=findViewById(R.id.textView19);

        String userPhoneNumber=FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        reqref=FirebaseDatabase.getInstance().getReference("Requests").child(userPhoneNumber);
        acceptedref=FirebaseDatabase.getInstance().getReference("Accepted Requests").child(userPhoneNumber);
        reference=FirebaseDatabase.getInstance().getReference("donorForm");
        receiverref=FirebaseDatabase.getInstance().getReference().child("Receivers").child(userPhoneNumber);

        reqref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    //he is donor and req pending
                    ddetails.setVisibility(View.GONE);
                    vdetails.setVisibility(View.GONE);
                    Reqacceptedbyreceiver.setVisibility(View.GONE);
                    Reqacceptedbyvolunteer.setVisibility(View.GONE);
                    congrats.setVisibility(View.GONE);

                }
                else {
                    acceptedref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            //Request Accepeted
                            String rphoneNumber=snapshot.child("Receiver").getValue().toString();
                            ddetails.setVisibility(View.GONE);
                            vdetails.setVisibility(View.GONE);
                            Reqacceptedbyvolunteer.setVisibility(View.GONE);
                            congrats.setVisibility(View.GONE);
                            rdetails.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(Currentstatus.this,Currentreceiverdetails.class);
                                    intent.putExtra("keyrphonenumber",rphoneNumber);
                                    startActivity(intent);
                                }
                            });

                        }
                        else{
                            receiverref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    //he is receiver
                                    String dphoneNumber=snapshot.getValue().toString();
                                    rdetails.setVisibility(View.GONE);
                                    vdetails.setVisibility(View.GONE);
                                    Reqacceptedbyvolunteer.setVisibility(View.GONE);
                                    congrats.setVisibility(View.GONE);
                                    ddetails.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent=new Intent(Currentstatus.this,Currentdonordetails.class);
                                            intent.putExtra("keydphonenumber",dphoneNumber);
                                            startActivity(intent);
                                        }
                                    });



                                }
                                else {ddetails.setVisibility(View.GONE);
                                    vdetails.setVisibility(View.GONE);
                                    rdetails.setVisibility(View.GONE);
                                    Reqacceptedbyreceiver.setVisibility(View.GONE);
                                    Reqacceptedbyvolunteer.setVisibility(View.GONE);
                                    congrats.setVisibility(View.GONE);
                                    Reqaccepted.setText("No ongoing request");

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });



                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });














    }
}