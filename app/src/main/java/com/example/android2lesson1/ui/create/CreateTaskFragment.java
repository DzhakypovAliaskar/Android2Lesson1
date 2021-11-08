package com.example.android2lesson1.ui.create;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.example.android2lesson1.R;
import com.example.android2lesson1.databinding.FragmentCreateTaskBinding;
import com.example.android2lesson1.utils.Constants;

import java.util.Calendar;

public class CreateTaskFragment extends Fragment {

    private FragmentCreateTaskBinding binding;
    String userTask;
    String userChoosedDate;
    String image;
    String time;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userTask = binding.taskEd.getText().toString();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        binding.setTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });
        binding.applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userTask = binding.taskEd.getText().toString();
                TaskModel model = new TaskModel(R.color.purple_200,userTask,userChoosedDate + "/" + time,image);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.USER_TASK, model);
                navController.navigate(R.id.nav_home, bundle);

            }
        });
        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    image = uri.toString();
                    Glide.with(binding.imageView).load(image).centerCrop().into(binding.imageView);
                }
            });

    public void showDateTimePicker() {
        final java.util.Calendar currentDate = java.util.Calendar.getInstance();

        java.util.Calendar date = java.util.Calendar.getInstance();
        new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(requireContext(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(java.util.Calendar.MINUTE, minute);
                        time = hourOfDay + " : " + minute;
                        userChoosedDate = date.get(java.util.Calendar.MONTH) + "." + date.get(java.util.Calendar.DAY_OF_MONTH);

                        binding.timeTv.setText(userChoosedDate + "/" + time);
                    }
                }, currentDate.get(java.util.Calendar.HOUR_OF_DAY), currentDate.get(java.util.Calendar.MINUTE), false).show();
            }
        }, currentDate.get(java.util.Calendar.YEAR), currentDate.get(java.util.Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
