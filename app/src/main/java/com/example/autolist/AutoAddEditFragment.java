package com.example.autolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.autolist.db.AutoRecord;

import java.util.Date;

public class AutoAddEditFragment extends Fragment {

    private static final String ARG_AUTO_ID = "auto_id";

    private AutoRecord mAuto;
    private EditText mYear;
    private EditText mMake;
    private EditText mModel;
    private EditText mNote;
    private ImageButton mImageFilePath;
    private TextView mDateEntered;
    private Button mFinishedButton;
    private AutoAddEditViewModel mAutoAddEditViewModel;

    // Argument Bundle created. Is called when needed to create an AutoAddEditFragment
    public static AutoAddEditFragment newInstance(Integer autoId) {

        // Creates new fragment instance
        AutoAddEditFragment fragment = new AutoAddEditFragment();

        // Checks if an autoID was passed in
        if (autoId != null) {
            Bundle args = new Bundle();
            args.putInt(ARG_AUTO_ID, autoId);
            // Attaches arguments to the fragment
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_auto, container, false);

        // Gets reference to string resource
        mYear = v.findViewById(R.id.year_edittext);
        mMake = v.findViewById(R.id.make_edittext);
        mModel = v.findViewById(R.id.model_edittext);
        mNote = v.findViewById(R.id.notes_edittext);
//        mDateEntered = v.findViewById(R.id.date_entered);
        mFinishedButton = v.findViewById(R.id.finished_button);

        // Finished button click listener
        mFinishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = mYear.getText().toString();
                String make = mMake.getText().toString();
                String model = mModel.getText().toString();
                String note = mNote.getText().toString();
//                mImageFilePath = v.findViewById(R.id.auto_image_button); //TODO create filepath strings for image
//                Date dateEntered = java.sql.Date.getDate(mDateEntered);
                AutoRecord newAuto = new AutoRecord(year, make, model, note, "", new Date());
                mAutoAddEditViewModel.insert(newAuto);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mAutoAddEditViewModel = ViewModelProviders.of(this).get(AutoAddEditViewModel.class);

        // ....
        if (getArguments() != null) {
            mAutoAddEditViewModel.getAutoRecord(getArguments().getInt(ARG_AUTO_ID, 0)).observe(this, new Observer<AutoRecord>() {
                @Override
                public void onChanged(AutoRecord auto) {
                    mYear.setText(auto.getYear());
                    mMake.setText(auto.getMake());
                    mModel.setText(auto.getModel());
                    mNote.setText(auto.getNote());
                }
            });
        }
    }
}
