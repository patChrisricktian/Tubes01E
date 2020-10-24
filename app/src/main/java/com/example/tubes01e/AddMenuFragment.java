package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.AddMenuBinding;
import com.example.tubes01e.databinding.FragmentMainBinding;

public class AddMenuFragment extends Fragment implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvNamaMakanan;
    private TextView tvTag;
    private TextView tvResep;
    private RadioButton radioResep;
    private RadioButton radioTersediaRestoran;
    private EditText et1;
    private EditText et2;
    private  EditText et3;
    private FragmentListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AddMenuBinding binding = AddMenuBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.tvTitle = binding.tvTitle;
        this.tvNamaMakanan = binding.tvNamaMakanan;
        this.tvTag = binding.tvTag;
        this.tvResep = binding.tvResep;
        this.radioResep = binding.radioResep;
        this.radioResep.setOnClickListener(this);
        this.radioTersediaRestoran = binding.radioTersediaRestoran;
        this.radioTersediaRestoran.setOnClickListener(this);
        this.et1 = binding.etNamaMakanan;
        this.et2 = binding.etTag;
        this.et3 = binding.etResep;




        return view;
    }

    public void onRadioButtonClicked(View v){
        Log.d("debug", "radio button clicked!");
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
        if(v == this.radioResep){
            Log.d("debug", "radio resep button clicked!");
        }else if(v == this.radioTersediaRestoran){
            Log.d("debug", "radio restoran button clicked!");
        }
    }
}
