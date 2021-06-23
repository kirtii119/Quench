package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Donordetails extends AppCompatActivity {
    TextView gname,gcontactNumber,gstate,gcity,garea,gpincode,gstreet,gbuildingName,ghouseNumber,gquantity;
    DatabaseReference reference,reqref,acceptedref;
    FirebaseDatabase rootNode;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Button accept;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donordetails);
        String contactNumber = getIntent().getStringExtra("keycontactNumber");
        String dphoneNumber = getIntent().getStringExtra("keydphoneNumber");
        gname = findViewById(R.id.editTextTextPersonName);
        gcontactNumber = findViewById(R.id.editTextPhone);
        gstate = findViewById(R.id.editTextTextPersonName3);
        gcity = findViewById(R.id.editTextTextPersonName2);
        garea = findViewById(R.id.editTextTextPersonName5);
        gpincode = findViewById(R.id.editTextNumber);
        gstreet = findViewById(R.id.editTextTextPersonName7);
        gbuildingName = findViewById(R.id.editTextTextPersonName8);
        ghouseNumber = findViewById(R.id.editTextTextPersonName9);
        gquantity = findViewById(R.id.editTextNumber2);


        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        String phoneNumber=mUser.getPhoneNumber();



        reference=FirebaseDatabase.getInstance().getReference("donorForm").child(dphoneNumber);
        reqref=FirebaseDatabase.getInstance().getReference().child("Requests");
        acceptedref=FirebaseDatabase.getInstance().getReference().child("Accepted Requests");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String name=snapshot.child("name").getValue().toString();
                gname.setText(name);
                String contactNumber=snapshot.child("contactNumber").getValue().toString();
                gcontactNumber.setText(contactNumber);
                String state=snapshot.child("state").getValue().toString();
                gstate.setText(state);
                String city=snapshot.child("city").getValue().toString();
                gcity.setText(city);
                String area=snapshot.child("area").getValue().toString();
                garea.setText(area);
                String pincode=snapshot.child("pincode").getValue().toString();
                gpincode.setText(pincode);
                String street=snapshot.child("street").getValue().toString();
                gstreet.setText(street);
                String buildingName=snapshot.child("buildingName").getValue().toString();
                gbuildingName.setText(buildingName);
                String houseNumber=snapshot.child("houseNumber").getValue().toString();
                ghouseNumber.setText(houseNumber);
                String quantity=snapshot.child("quantity").getValue().toString();
                gquantity.setText(quantity);

                accept = (Button)findViewById(R.id.Accept);
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reqref.child(dphoneNumber).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    HashMap hashMap=new HashMap();
                                    hashMap.put("status","accepted");
                                    hashMap.put("Donor",name);
                                    hashMap.put("Receiver",phoneNumber);
                                    acceptedref.child(dphoneNumber).child(phoneNumber).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull @NotNull Task task) {
                                            Intent intent=new Intent(Donordetails.this,mainfeed.class);
                                            startActivity(intent);
                                        }
                                    });


                                }
                            }
                        });

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });




    }
}