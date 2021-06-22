package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateNewAccount extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb;

    public Button button;

    EditText regFirstName, regLastName, regPhoneNo;
    Button regBtn;
    CheckBox checkbox;

    FirebaseDatabase db;
    DatabaseReference root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);


        regFirstName = findViewById(R.id.reg_firstname);
        regLastName = findViewById(R.id.reg_lastname);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regBtn = findViewById(R.id.reg_btn);
        rg = findViewById(R.id.radioGroup);
        checkbox = findViewById(R.id.checkbox);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = FirebaseDatabase.getInstance();
                root = db.getReference().child("Users");

                String firstname =regFirstName.getText().toString();
                String lastname =regLastName.getText().toString();
                String phoneNo = regPhoneNo.getText().toString();

                int radioid= rg.getCheckedRadioButtonId();
                rb= findViewById(radioid);
                String role = rb.getText().toString();

//              String vol;
//                if(checkbox.isChecked())
//                  vol= "YES";


                HashMap<String, String> userMap = new HashMap<>();

                userMap.put("firstname" , firstname);
                userMap.put("lastname" , lastname);
                userMap.put("phoneNo" , phoneNo);
                userMap.put("role" , role);
                // userMap.put("Volunteer" , vol);

                root.child(phoneNo).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CreateNewAccount.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    public void checkbutton(View v)
    {
        int radioid= rg.getCheckedRadioButtonId();
        rb= findViewById(radioid);
        Toast.makeText(getBaseContext(), rb.getText(),Toast.LENGTH_SHORT).show();
    }
}