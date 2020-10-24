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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MenuListFragment extends Fragment implements View.OnClickListener {
    private ListView list;
    private FloatingActionButton btnAdd;
    private FragmentListener listener;
    private MainActivity ui;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMenuListBinding binding = FragmentMenuListBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.list = binding.menuList;
        this.list.setAdapter(this.ui.getPresenter().getMenuListAdapter());
        this.btnAdd = (FloatingActionButton) binding.floatingAddBtn;
        this.btnAdd.setOnClickListener(this);
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

    public ListView getListView(){
        return this.list;
    }

    @Override
    public void onClick(View v) {
        if(v == this.btnAdd){
            ui.changePage(FragmentType.FRAGMENT_ADD_MENU);
        }
    }
}
