package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.FragmentNavigationBinding;

public class NavigationFragment extends Fragment implements View.OnClickListener{
    private Button btnHome;
    private Button btnRandomSearch;
    private Button btnMenuList;
    private Button btnSetting;
    private Button btnExit;
    private FragmentListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentNavigationBinding binding = FragmentNavigationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        this.btnHome = binding.btnHome;
        btnHome.setOnClickListener(this);

        this.btnRandomSearch = binding.btnRandomSearch;
        btnRandomSearch.setOnClickListener(this);

        this.btnMenuList = binding.btnMenus;
        btnMenuList.setOnClickListener(this);

        this.btnSetting = binding.btnSetting;
        btnSetting.setOnClickListener(this);

        this.btnExit = binding.btnExit;
        btnExit.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if( v == this.btnHome){
            this.listener.changePage(1);
        }else if (v == this.btnRandomSearch){
            this.listener.changePage(2);
        }else if (v == this.btnMenuList){
            this.listener.changePage(3);
        }else if (v == this.btnSetting){
            this.listener.changePage(4);
        }else if(v == this.btnExit){
            this.listener.closeApplication();
        }
    }
}
