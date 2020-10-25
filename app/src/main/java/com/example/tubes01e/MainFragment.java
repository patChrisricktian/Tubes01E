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

public class MainFragment extends Fragment implements View.OnClickListener {
    private TextView tvText1;
    private Button btnSearch;
    private FragmentListener listener;
    private MainActivity activity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMainBinding binding = FragmentMainBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        this.tvText1 = binding.tvTitle1;
        this.btnSearch = binding.btnCari;
        this.btnSearch.setOnClickListener(this);
        return view;
    }


    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString()+" must implement FragmentListener");
        }
        if(context instanceof MainActivity){
            this.activity = (MainActivity) context;
        }
    }


    @Override
    public void onClick(View v) {
        if(v == this.btnSearch){
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
