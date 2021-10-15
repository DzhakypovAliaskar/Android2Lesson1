package com.example.android2lesson1.ui.create;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android2lesson1.R;
import com.example.android2lesson1.databinding.FragmentCreateTaskBinding;
import com.example.android2lesson1.databinding.FragmentHomeBinding;
import com.example.android2lesson1.utils.Constants;

public class CreateTaskFragment extends Fragment {
    private @NonNull FragmentCreateTaskBinding binding;
    String userTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userTask = binding.taskEd.getText().toString();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        binding.applyBtn.setOnClickListener(view1 -> {
           Bundle bundle = new Bundle();
           bundle.putString(Constants.USER_TASK, userTask);
           getParentFragmentManager().setFragmentResult(Constants.REQUEST_TASK, bundle);
            Log.e("TAG", "onClick: " + bundle.toString());

           navController.navigate(R.id.homeFragment2);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}