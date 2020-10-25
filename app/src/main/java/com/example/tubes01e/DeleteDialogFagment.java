package com.example.tubes01e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.tubes01e.databinding.FragmentDeleteDialogBinding;

public class DeleteDialogFagment extends DialogFragment implements View.OnClickListener {
    private String id;
    private TextView tvFoodName;
    private Button btnConfirm;
    private Button btnCancel;
    private FragmentListener listener;


    static DeleteDialogFagment newInstance(Bundle args) {
        DeleteDialogFagment dialog = new DeleteDialogFagment();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDeleteDialogBinding binding = FragmentDeleteDialogBinding.inflate(getLayoutInflater());
        this.tvFoodName = binding.tvNamaMakananDelete;
        this.btnConfirm = binding.btnConfirmDelete;
        this.btnCancel = binding.btnCancelDelete;
        this.id = "";
        Bundle args = getArguments();
        if (args != null) {
            String foodname = args.getString("name");
            this.id = args.getString("id");
            this.tvFoodName.setText(foodname);
        }
        this.btnConfirm.setOnClickListener(this);
        this.btnCancel.setOnClickListener(this);

        return binding.getRoot();
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentListener) {
            this.listener = (FragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == this.btnConfirm) {
            if (this.id.length() > 0) {
                this.listener.deleteMenu(this.id);
                this.listener.changePage(FragmentType.FRAGMENT_MENU_LIST);
                dismiss();
            }
        } else if (v == this.btnCancel) {
            //reference:https://developer.android.com/guide/topics/ui/dialogs#java
            dismiss();
        }
    }
}
