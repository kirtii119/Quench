package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Currentdonordetails extends AppCompatActivity {
    TextView gname,gcontactNumber,gstate,gcity,garea,gpincode,gstreet,gbuildingName,ghouseNumber,gquantity;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentdonordetails);
        String dphoneNumber=getIntent().getStringExtra("keydphonenumber");
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



        reference= FirebaseDatabase.getInstance().getReference("donorForm").child(dphoneNumber);
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
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}