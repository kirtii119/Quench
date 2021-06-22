package com.example.login.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.login.ChatActivity;
import com.example.login.R;
import com.example.login.donationform;
import com.example.login.receiverfeed;
import com.example.login.receiverform;
import com.example.login.volunteerform;

public class HomeFragment extends Fragment {
    Activity context;



    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        return root;
    }
    public void onStart(){
        super.onStart();
        Button buttondonate = (Button) context.findViewById(R.id.buttondonate);
        buttondonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, donationform.class);
                startActivity(intent);
            }
        });
        Button buttonreceive = (Button) context.findViewById(R.id.buttonreceive);
        buttonreceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, receiverfeed.class);
                startActivity(intent);
            }
        });
        Button buttonvolunteer = (Button) context.findViewById(R.id.buttonvolunteer);
        buttonvolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, volunteerform.class);
                startActivity(intent);
            }
        });
    }

}