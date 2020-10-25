package com.example.tubes01e;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.FragmentMenuDetailBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuDetailFragment extends Fragment implements View.OnClickListener {
    private TextView tvTitleDetail;
    private TextView tvDeskripsiDetailChild;
    private TextView tvTagDetailChild;
    private TextView tvResepDetail;
    private TextView tvResepDetailChild;
    private ImageView ivMakananDetail;
    private FloatingActionButton floatingEditBtn;
    private FloatingActionButton floatingDeleteBtn;
    private FragmentListener listener;
    private MainActivity activity;

    public static MenuDetailFragment newInstance(Bundle args){
        MenuDetailFragment fragment = new MenuDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMenuDetailBinding binding = FragmentMenuDetailBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.tvTitleDetail = binding.tvTitleDetail;
        this.tvDeskripsiDetailChild = binding.tvDeskripsiDetailChild;
        this.tvTagDetailChild = binding.tvTagDetailChild;
        this.tvResepDetail = binding.tvResepDetail;
        this.tvResepDetailChild = binding.tvResepDetailChild;
        this.floatingEditBtn = binding.floatingEditBtn;
        this.floatingEditBtn.setOnClickListener(this);

        this.floatingDeleteBtn = binding.floatingDeleteBtn;
        this.floatingDeleteBtn.setOnClickListener(this);

        this.ivMakananDetail = binding.ivMakananDetail;

        Bundle bundle = getArguments();
        //Log.d("debug", "Bundle is null:"+(bundle == null));
        if (bundle != null) {
            String titleText = bundle.get("name").toString();
            String descriptionText = bundle.get("description").toString();
            String tagText = bundle.get("tags").toString();
            boolean hasRecipe = bundle.getBoolean("hasRecipe");
            String recipe = bundle.getString("recipe");

            this.tvTitleDetail.setText(titleText);
            this.tvDeskripsiDetailChild.setText(descriptionText);
            this.tvTagDetailChild.setText(tagText);
            if (!hasRecipe) {
                this.tvResepDetail.setText("Tersedia di Restoran");
                this.tvResepDetailChild.setVisibility(View.INVISIBLE);
            } else {
                this.tvResepDetailChild.setText(recipe);
            }
        }


        return view;
    }


    public void onAttach(Context context) {
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
        if (v == this.floatingEditBtn) {
            Bundle args = getArguments();
            this.activity.fragments.remove(this.activity.menuEditFragment);
            this.activity.menuEditFragment = MenuEditFragment.newInstance(args);
            this.activity.fragments.add(this.activity.menuEditFragment);
            this.listener.changePage(FragmentType.FRAGMENT_EDIT_MENU);
        } else if (v == this.floatingDeleteBtn) {

        }
    }
}
