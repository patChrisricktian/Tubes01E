package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes01e.databinding.FragmentRandomSelectMenuBinding;

public class RandomSelectMenuFragment extends Fragment implements View.OnClickListener{

    private TextView tvTitleDetail;
    private TextView tvDeskripsiDetailChild;
    private TextView tvTagDetailChild;
    private TextView tvResepDetail;
    private TextView tvResepDetailChild;
    private ImageView ivMakananDetail;
    private Button btnNewSearch;
    private FragmentListener listener;
    private MainActivity activity;

    public static RandomSelectMenuFragment newInstance(Bundle args) {
        RandomSelectMenuFragment fragment = new RandomSelectMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentRandomSelectMenuBinding binding = FragmentRandomSelectMenuBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.tvTitleDetail = binding.tvTitleDetail;
        this.tvDeskripsiDetailChild = binding.tvDeskripsiDetailChild;
        this.tvTagDetailChild = binding.tvTagDetailChild;
        this.tvResepDetail = binding.tvResepDetail;
        this.tvResepDetailChild = binding.tvResepDetailChild;
        this.btnNewSearch = binding.btnNewSearch;
        this.btnNewSearch.setOnClickListener(this);

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

        this.btnNewSearch.setOnClickListener(this);
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
        if (v == this.btnNewSearch) {
            Menu menu = this.listener.randomSelectMenu();
            Bundle args = new Bundle();
            args.putString("id", menu.getId());
            args.putString("name", menu.getName());
            args.putString("description", menu.getDescription());
            args.putString("tags", menu.getTag());
            args.putBoolean("hasRecipe", menu.hasRecipe());
            args.putString("recipe", menu.getRecipe());
            activity.destroyFragment(activity.randomSelectMenuFragment);
            activity.randomSelectMenuFragment = RandomSelectMenuFragment.newInstance(args);
            activity.fragments.add(activity.randomSelectMenuFragment);
            activity.changePage(FragmentType.FRAGMENT_RANDOM_SELECT);
        }
    }




}
