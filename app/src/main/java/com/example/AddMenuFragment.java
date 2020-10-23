package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.AddMenuBinding;
import com.example.tubes01e.databinding.FragmentMainBinding;

public class AddMenuFragment extends Fragment {
    private TextView tv_Text1;
    private TextView tv_Text2;
    private TextView tv_Text3;
    private TextView tv_Text4;
    private RadioButton rBtn1;
    private RadioButton rBtn2;
    private EditText et1;
    private EditText et2;
    private  EditText et3;
    private FragmentListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AddMenuBinding binding = AddMenuBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.tv_Text1 = binding.tvTitle;
        this.tv_Text2 = binding.tvNamaMakanan;
        this.tv_Text3 = binding.tvTag;
        this.tv_Text4 = binding.tvResep;
        this.rBtn1 = binding.resep;
        this.rBtn2 = binding.tersediaRestoran;
        this.et1 = binding.etNamaMakanan;
        this.et2 = binding.etTag;
        this.et3 = binding.etResep;
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
