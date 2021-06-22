package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;


public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder>{

    ArrayList<Model> mList;
    Context context;


    public MyAdapter(Context context , ArrayList<Model> mList)
    {
        this.mList=mList;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.name.setText(model.getName());
        holder.quantity.setText(model.getQuantity());
        holder.area.setText(model.getArea());
        holder.city.setText(model.getCity());
//        holder.contactNumber.setText(model.getContactNumber());
        holder.recbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context.getApplicationContext(),Donordetails.class);
                String contactNumber= model.contactNumber;
                String dphoneNumber= model.phoneNumber;
                intent.putExtra("keycontactNumber", contactNumber);
                intent.putExtra("keydphoneNumber",dphoneNumber);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, quantity, area, city,contactNumber;
        Button recbutton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            quantity=itemView.findViewById(R.id.quantity);
            area=itemView.findViewById(R.id.area);
            city=itemView.findViewById(R.id.city);
            recbutton = itemView.findViewById(R.id.recbutton);
            //contactNumber= itemView.findViewById(R.id.contactNumber);

        }

    }
}
