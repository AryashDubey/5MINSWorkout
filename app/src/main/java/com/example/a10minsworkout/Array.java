package com.example.a10minsworkout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Array extends RecyclerView.Adapter<Array.ViewHolder> {
    private ArrayList<String> exercise;

    public Array(ArrayList exercise) {
        this.exercise = exercise;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.datarecycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String Title = exercise.get(position);
        holder.textView.setText(Title);

    }


    @Override
    public int getItemCount() {
        return exercise.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View view) {
            super(view);
            textView = itemView.findViewById(R.id.yayno);
        }
    }
}
