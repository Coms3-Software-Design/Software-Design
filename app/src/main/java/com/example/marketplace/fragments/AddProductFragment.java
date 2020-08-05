package com.example.marketplace.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.marketplace.R;
import com.example.marketplace.classes.User;

public class AddProductFragment extends AppCompatDialogFragment {

    private User user;
    private EditText prodName, prodBrand
            ,prodDesc , prodPrice , prodQuant;
    private Spinner caterogries, productType;
    private ImageView imgView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.fragment_addprod , null);

        AlertDialog createProd = builder.create();
        return createProd;
    }

    public void setUser(User user){
        this.user = user;
    }

}
