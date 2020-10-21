package com.example.a10minsworkout;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ExerciseProgressRecyclerView extends RecyclerView.Adapter<ExerciseProgressRecyclerView.ViewHolder> {
    private ArrayList<Integer> data;
    private ArrayList<Integer> status;
    public  ExerciseProgressRecyclerView(ArrayList data,ArrayList status){
        this.data= data;
        this.status=status;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String Title = data.get(position).toString();
        holder.textView.setText(Title);
        if (status.get(position)==1){
            holder.textView.setTextColor(Color.BLACK);
        }else if (status.get(position)==0){
            holder.textView.setTextColor(Color.WHITE);
            holder.textView.setBackground(ContextCompat.getDrawable(ExerciseActivity.context,R.drawable.circular_exercise_progress_completed));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclertxt);
        }
    }

}
