package com.example.tubes01e;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.FragmentMenuDetailBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuDetailFragment extends Fragment {
    private TextView tvTitleDetail;
    private ImageView ivMakananDetail;
    private FloatingActionButton floatingEditBtn;
    private FragmentListener listener;
    private MainActivity ui;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMenuDetailBinding binding = FragmentMenuDetailBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.tvTitleDetail = binding.tvTitleDetail;
        this.floatingEditBtn = binding.floatingEditBtn;
        this.ivMakananDetail = binding.ivMakananDetail;


        return view;
    }


    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
            if(context instanceof MainActivity){
                this.ui = (MainActivity) context;
            }
        }else{
            throw new ClassCastException(context.toString()+" must implement FragmentListener");
        }
    }
}
