package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.FragmentMenuEditBinding;

public class MenuEditFragment extends Fragment implements View.OnClickListener{
    private String id;
    private EditText etNamaMakanan;
    private EditText etDeskripsiEdit;
    private EditText etTagEdit;
    private RadioButton radioResepEdit;
    private RadioButton radioRestoranEdit;
    private EditText etResepEdit;
    private Button btnSimpanEdit;
    private Button btnBatalEdit;
    private FragmentListener listener;
    private MainActivity activity;
    public static MenuEditFragment newInstance(Bundle args){
        MenuEditFragment fragment = new MenuEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMenuEditBinding binding = FragmentMenuEditBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        this.etNamaMakanan = binding.etNamaMakanan;
        this.etDeskripsiEdit = binding.etDeskripsiEdit;
        this.etTagEdit = binding.etTagEdit;
        this.radioResepEdit = binding.radioResep;
        this.radioRestoranEdit = binding.radioTersediaRestoran;
        this.etResepEdit = binding.etResep;
        this.btnBatalEdit = binding.btnCancel;
        this.btnBatalEdit.setOnClickListener(this);
        this.btnSimpanEdit = binding.btnSave;
        this.btnSimpanEdit.setOnClickListener(this);

        Bundle bundle = getArguments();
        this.id = "";
        if (bundle != null) {
            this.id = bundle.get("id").toString();
            String name = bundle.get("name").toString();
            String descriptionText = bundle.get("description").toString();
            String tagText = bundle.get("tags").toString();
            boolean hasRecipe = bundle.getBoolean("hasRecipe");
            String recipe = bundle.getString("recipe");

            this.etNamaMakanan.setText(name);
            this.etDeskripsiEdit.setText(descriptionText);
            this.etTagEdit.setText(tagText);
            if(hasRecipe){
                this.radioResepEdit.setChecked(true);
                this.etResepEdit.setText(recipe);
            }else{
                this.radioRestoranEdit.setChecked(true);
                this.etResepEdit.setFocusable(false);
                this.etResepEdit.setFocusableInTouchMode(false);
                this.etResepEdit.setClickable(false);
                this.etResepEdit.setText("");
            }
        }
        return root;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.listener = (FragmentListener) context;
            if (context instanceof MainActivity) {
                this.activity = (MainActivity) context;
            }
        } else {
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onClick(View v) {
        if(v == this.btnSimpanEdit){
            addEditedMenu();
        }else if(v == this.btnBatalEdit){
            Bundle args = getArguments();
            activity.fragments.remove(activity.menuDetailFragment);
            activity.menuDetailFragment = MenuDetailFragment.newInstance(args);
            activity.fragments.add(activity.menuDetailFragment);
            activity.changePage(FragmentType.FRAGMENT_MENU_DETAIL);
        }
    }

    public void addEditedMenu() {
        String nama = this.etNamaMakanan.getText().toString();
        String tags = this.etTagEdit.getText().toString();

        String deskripsi = this.etDeskripsiEdit.getText().toString();


        boolean hasRecipe = true;
        if (this.radioRestoranEdit.isChecked()) {
            hasRecipe = false;
        }

        //Log.d("debug", String.format("%s",hasRecipe));
        String recipe = "";

        if (hasRecipe) {
            recipe = this.etResepEdit.getText().toString();
        }


        //Log.d("debug", String.format("recipe: %s",recipe));

        boolean isSuccess = listener.editMenu(this.id, nama, deskripsi, tags, hasRecipe, recipe);

        //Log.d("debug", String.format("%s",isSuccess));
        if (isSuccess) {
            this.etNamaMakanan.setText("");
            this.etDeskripsiEdit.setText("");
            this.etTagEdit.setText("");
            this.etResepEdit.setText("");

            listener.changePage(FragmentType.FRAGMENT_MENU_LIST);
        }
    }
}
