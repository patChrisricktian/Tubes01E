package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.AddMenuBinding;
import com.example.tubes01e.databinding.FragmentMainBinding;

public class AddMenuFragment extends Fragment implements View.OnClickListener {
    private RadioButton radioResep;
    private RadioButton radioTersediaRestoran;
    private EditText etNamaMakanan;
    private EditText etDeskripsi;
    private EditText etTag;
    private  EditText etResep;
    private Button btnAdd;
    private Button btnCancel;
    private FragmentListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AddMenuBinding binding = AddMenuBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.etDeskripsi = binding.etDeskripsiEdit;
        this.radioResep = binding.radioResep;
        this.radioResep.setOnClickListener(this);
        this.radioResep.setChecked(true);
        this.radioTersediaRestoran = binding.radioTersediaRestoran;
        this.radioTersediaRestoran.setOnClickListener(this);
        this.etNamaMakanan = binding.etNamaMakanan;
        this.etTag = binding.etTag;
        this.etResep = binding.etResep;
        this.btnAdd = binding.btnAdd;
        this.btnAdd.setOnClickListener(this);
        this.btnCancel = binding.btnCancel;
        this.btnCancel.setOnClickListener(this);

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
        //Reference: https://stackoverflow.com/questions/6555455/how-to-set-editable-true-false-edittext-in-android-programmatically
        if(v == this.radioResep){
            Log.d("debug", "radio resep button clicked!");
            this.etResep.setFocusable(true);
            this.etResep.setFocusableInTouchMode(true);
            this.etResep.setClickable(true);
        }else if(v == this.radioTersediaRestoran){
            Log.d("debug", "radio restoran button clicked!");
            this.etResep.setFocusable(false);
            this.etResep.setFocusableInTouchMode(false);
            this.etResep.setClickable(false);
            this.etResep.setText("");
        }else if(v == this.btnAdd){
            addNewMenu();
        }else if(v == this.btnCancel){
            listener.changePage(FragmentType.FRAGMENT_MENU_LIST);
        }
    }

    public void addNewMenu(){
        String nama = this.etNamaMakanan.getText().toString();
        String tags = this.etTag.getText().toString();
        String deskripsi = this.etDeskripsi.getText().toString();
        boolean hasRecipe = true;
        if(this.radioTersediaRestoran.isChecked()){
            hasRecipe = false;
        }

        //Log.d("debug", String.format("%s",hasRecipe));
        String recipe = "";

        if(hasRecipe){
            recipe = this.etResep.getText().toString();
        }

        //Log.d("debug", String.format("recipe: %s",recipe));

        boolean isSuccess = listener.addMenu(nama, deskripsi, tags, hasRecipe, recipe);

        //Log.d("debug", String.format("%s",isSuccess));
        if(isSuccess){
            this.etNamaMakanan.setText("");
            this.etDeskripsi.setText("");
            this.etTag.setText("");
            this.etResep.setText("");
            listener.changePage(FragmentType.FRAGMENT_MENU_LIST);
        }
    }
}
