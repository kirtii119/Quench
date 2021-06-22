package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import static java.util.HashMap.*;

public class donationform extends AppCompatActivity {
    Button submit;
    EditText gname,gcontactNumber,gstate,gcity,gdistrict,garea,gpincode,gstreet,gbuildingName,ghouseNumber,gquantity;
    FirebaseDatabase rootNode;
    DatabaseReference reference,reqRef,dref;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    String CurrentState="nothing_happen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationform);
        gname = findViewById(R.id.editTextTextPersonName);
        gcontactNumber = findViewById(R.id.editTextPhone);
        gstate = findViewById(R.id.editTextTextPersonName3);
        gcity = findViewById(R.id.editTextTextPersonName2);
        gdistrict = findViewById(R.id.editTextTextPersonName4);
        garea = findViewById(R.id.editTextTextPersonName5);
        gpincode = findViewById(R.id.editTextNumber);
        gstreet = findViewById(R.id.editTextTextPersonName7);
        gbuildingName = findViewById(R.id.editTextTextPersonName8);
        ghouseNumber = findViewById(R.id.editTextTextPersonName9);
        gquantity = findViewById(R.id.editTextNumber2);
        submit = findViewById(R.id.submit);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        //LoadUser();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = gname.getText().toString();
                String contactNumber = gcontactNumber.getText().toString();
                String state = gstate.getText().toString();
                String city = gcity.getText().toString();
                String district = gdistrict.getText().toString();
                String area = garea.getText().toString();
                String pincode = gpincode.getText().toString();
                String street = gstreet.getText().toString();
                String buildingName = gbuildingName.getText().toString();
                String houseNumber = ghouseNumber.getText().toString();
                String quantity = gquantity.getText().toString();
                //String dUid=mUser.getUid();
                String phoneNumber=mUser.getPhoneNumber();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("donorForm");
                reqRef = rootNode.getReference().child("Requests").child(phoneNumber);
                dref=rootNode.getReference("users");


                donorHelperClass dHelperClass = new donorHelperClass(name,contactNumber,state,city,district,area,pincode,street,buildingName,houseNumber,quantity,phoneNumber);
                reference.child(contactNumber).setValue(dHelperClass);


                Intent intent = new Intent(donationform.this, mainfeed.class);
                startActivity(intent);
                Toast.makeText(donationform.this, "Data Saved",Toast.LENGTH_LONG).show();

                PerformAction(contactNumber);


            }
        });

    }

    private  void PerformAction(String contactNumber) {
        if (CurrentState.equals("nothing_happen"))
        {
            HashMap<String, Object> hashMap=new HashMap<String, Object>();
            hashMap.put("status","pending");
            reqRef.child(contactNumber).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull @NotNull Task task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(donationform.this, "Donation Request Sent", Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                            Toast.makeText(donationform.this, ""+task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /*private void LoadUser() {
         dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                   phoneNumber=snapshot.child("phonenumber").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });*/
    }


