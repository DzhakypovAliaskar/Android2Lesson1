package com.example.android2lesson1.ui.create;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android2lesson1.OnItemClickListener;
import com.example.android2lesson1.R;
import com.example.android2lesson1.databinding.ItemTaskBinding;



import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<TaskModel> list;
    ItemTaskBinding binding;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TaskAdapter(ArrayList<TaskModel> list){
        this.list =list;
    }
    public void delete(TaskModel model){
        list.remove(model);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.onFill(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class TaskViewHolder extends RecyclerView.ViewHolder {
        ItemTaskBinding binding;

        public TaskViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onFill(TaskModel model){
            binding.titleTv.setText(model.title);
            binding.timeTv.setText(model.time);
            binding.leftColorV.setBackgroundColor(model.color);
            Glide.with(binding.taskIm).load(model.image).centerCrop().into(binding.taskIm);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(model);
                    return false;
                }
            });
        }
    }
}
