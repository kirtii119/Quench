package com.example.login.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Model;
import com.example.login.MyAdapter;
import com.example.login.R;
import com.example.login.receiverform;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterVolunteer extends RecyclerView.Adapter <AdapterVolunteer.MyViewHolder>{

    ArrayList<Model> mList;
    Context context;


    public MyAdapter(Context context , ArrayList<Model> mList)
    {
        this.mList=mList;
        this.context=context;
    }


    @NonNull
    @NotNull
    @Override
    public AdapterVolunteer.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterVolunteer.MyViewHolder holder, int position) {
        ModelVolunteer model = mList.get(position);
        holder.dquantity.setText(model.getdQuantity());
        holder.darea.setText(model.getdArea());
        holder.dcity.setText(model.getdCity());
        holder.rquantity.setText(model.getdQuantity());
        holder.rarea.setText(model.getdArea());
        holder.rcity.setText(model.getdCity());
//        holder.contactNumber.setText(model.getContactNumber());
        holder.recbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context.getApplicationContext(), receiverform.class);
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

        TextView dquantity, darea, dcity,rquantity, rarea, rcity;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dquantity=itemView.findViewById(R.id.dquantity);
            darea=itemView.findViewById(R.id.darea);
            dcity=itemView.findViewById(R.id.dcity);
            rquantity=itemView.findViewById(R.id.rquantity);
            rarea=itemView.findViewById(R.id.rarea);
            rcity=itemView.findViewById(R.id.rcity);

        }

    }
}
