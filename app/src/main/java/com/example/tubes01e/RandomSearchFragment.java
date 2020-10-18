package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.tubes01e.databinding.FragmentRandomSearchBinding;

public class RandomSearchFragment extends Fragment {
    private TextView tvText;
    private FragmentListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentRandomSearchBinding binding = FragmentRandomSearchBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.tvText = binding.tvRandomSearch;
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
