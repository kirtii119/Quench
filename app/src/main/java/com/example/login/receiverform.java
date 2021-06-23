package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class receiverform extends AppCompatActivity {

    Button submit;
    EditText gname,gcontactNumber,gstate,gcity,gdistrict,garea,gpincode,gstreet,gbuildingName,ghouseNumber,gquantity;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiverform);
        gname = findViewById(R.id.editTextTextPersonName);
        gcontactNumber = findViewById(R.id.editTextPhone);
        gstate = findViewById(R.id.editTextTextPersonName4);
        gcity = findViewById(R.id.editTextTextPersonName3);
        gdistrict = findViewById(R.id.editTextTextPersonName2);
        garea = findViewById(R.id.editTextTextPersonName5);
        gpincode = findViewById(R.id.editTextNumber);
        gstreet = findViewById(R.id.editTextTextPersonName6);
        gbuildingName = findViewById(R.id.editTextTextPersonName7);
        ghouseNumber = findViewById(R.id.editTextTextPersonName8);
        gquantity = findViewById(R.id.editTextNumber2);
        submit = findViewById(R.id.submit);


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

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("receiverForm");


                receiverHelperClass rHelperClass = new receiverHelperClass(name,contactNumber,state,city,district,area,pincode,street,buildingName,houseNumber,quantity);
                reference.child(contactNumber).setValue(rHelperClass);

                Toast.makeText(receiverform.this, "Data Saved",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(receiverform.this,  Donordetails.class);
                String ncontactNumber = getIntent().getStringExtra("keycontactNumber");
                intent.putExtra("keycontactNumber", ncontactNumber);
                startActivity(intent);
                    }
                });



            }
        }

