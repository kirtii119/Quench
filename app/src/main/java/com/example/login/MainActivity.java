package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity
{
    CountryCodePicker ccp;
    EditText t1,reg_firstname2,reg_lastname2;
    Button b1,buttonskip;
;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg_firstname2 = findViewById(R.id.reg_firstname2);
        reg_lastname2 = findViewById(R.id.reg_lastname2);



        buttonskip = (Button) findViewById(R.id.buttonskip);
        buttonskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, mainfeed.class));
            }
        });
       


        t1=(EditText)findViewById(R.id.t1);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        b1=(Button)findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               String firstname = reg_firstname2.getText().toString();
               String lastname = reg_lastname2.getText().toString();
               String phonenumber = t1.getText().toString();


                Intent intent=new Intent(MainActivity.this,manageotp.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace("",""));
                intent.putExtra("keyfirstname", firstname);
                intent.putExtra("keylastname", lastname);
                intent.putExtra("keyphonenumber", phonenumber);
                startActivity(intent);
            }
        });
    }
}