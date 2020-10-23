package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private TextView tvText1;
    private TextView tvText2;
    private Button btn;
    private FragmentListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMainBinding binding = FragmentMainBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.tvText1 = binding.tvTitle1;
        this.tvText2 = binding.tvTitle2;
        this.btn = binding.btnCari;
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
