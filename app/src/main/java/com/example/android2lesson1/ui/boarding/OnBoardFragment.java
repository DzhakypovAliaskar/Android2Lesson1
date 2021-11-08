package com.example.android2lesson1.ui.boarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.android2lesson1.MainActivity;
import com.example.android2lesson1.R;
import com.example.android2lesson1.databinding.FragmentOnBoardBinding;
import com.example.android2lesson1.utils.Constants;

import org.jetbrains.annotations.NotNull;

public class OnBoardFragment extends Fragment {
    FragmentOnBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPositionFromViewPagerAdapter();
    }

    private void getPositionFromViewPagerAdapter() {
        if (getArguments()!=null) {
            int position = getArguments().getInt(Constants.FRAGMENT_POSITION);
            switch (position){
                case 0:
                    binding.titleTv.setText("Приветствую");
                    binding.skipBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Animation logoMoveAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bounse_animation);
                            binding.skipBtn.startAnimation(logoMoveAnimation);
                        }
                    });
                    break;
                case 1:
                    binding.titleTv.setText("Второй экран");
                    binding.boardImage.setAnimation("checked.json");
                    break;
                case 2:
                    binding.titleTv.setText("Третий экран");
                    binding.boardImage.setAnimation("jumping.json");
                    binding.startBtn.setVisibility(View.VISIBLE);
                    binding.startBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.IS_SHOW_FILE, Context.MODE_PRIVATE);
                            sharedPreferences.edit().putBoolean(Constants.IS_SHOW,true).apply();
                            startActivity(new Intent(requireContext(), MainActivity.class));
                        }
                    });
                    break;
            }
        }
    }
}
