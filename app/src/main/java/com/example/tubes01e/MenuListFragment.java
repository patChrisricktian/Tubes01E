package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.FragmentMenuListBinding;

import java.util.List;

public class MenuListFragment extends Fragment {
    private ListView list;
    private FragmentListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMenuListBinding binding = FragmentMenuListBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.list = binding.menuList;
        return view;
    }


    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString()+" must implement FragmentListener");
        }
    }

}
