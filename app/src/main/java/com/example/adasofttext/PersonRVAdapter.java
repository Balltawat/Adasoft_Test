package com.example.adasofttext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonRVAdapter extends RecyclerView.Adapter<PersonRVAdapter.ViewHolder> {

    private ArrayList<PersonModel> courseModalArrayList;
    private Context context;

    public PersonRVAdapter(ArrayList<PersonModel> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_person_r_v_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonModel model = courseModalArrayList.get(position);
        holder.TVIDNumber.setText(model.getIdnumber());
        holder.TVName.setText(model.getName());
        holder.TVSurname.setText(model.getSurname());
        holder.TVAge.setText(model.getAge());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, Update.class);

                i.putExtra("idnumber", model.getIdnumber());
                i.putExtra("name", model.getName());
                i.putExtra("surname", model.getSurname());
                i.putExtra("age", model.getAge());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView TVIDNumber , TVName , TVSurname, TVAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TVIDNumber = itemView.findViewById(R.id.tvIDNumber);
            TVName = itemView.findViewById(R.id.tvName);
            TVSurname = itemView.findViewById(R.id.tvSurname);
            TVAge = itemView.findViewById(R.id.tvAge);
        }
    }
}