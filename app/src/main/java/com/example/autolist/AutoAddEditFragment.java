package com.example.autolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.autolist.db.AutoRecord;

public class AutoAddEditFragment extends Fragment {

    private static final String ARG_AUTO_ID = "auto_id";

    private AutoRecord mAuto;
    private TextView mTitleField;
    private Button mFinishedButton;
    private AutoAddEditViewModel mAutoAddEditViewModel;

    // Argument Bundle created. Is called when needed to create an AutoAddEditFragment
    public static AutoAddEditFragment newInstance(int autoId) {
        Bundle args = new Bundle();
        args.putInt(ARG_AUTO_ID, autoId);

        // Creates fragment instance and attaches arguments to the fragment
        AutoAddEditFragment fragment = new AutoAddEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_auto, container, false);

        // Gets reference to string resource
        mTitleField = v.findViewById(R.id.auto_title);
        // Listener for EditText TitleField
        mFinishedButton = v.findViewById(R.id.finished_button);

//        // Gets reference to checkbox's string resource
////        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
////        // Displays Crime's solved status
////        mSolvedCheckBox.setChecked(mAuto.isSolved());
////        // CheckBox listener to determine if checked or not
////        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////            @Override
////            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////                mAuto.setSolved(isChecked); // Sets checked status to new crime
////            }
////        });

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
                    mTitleField.setText(auto.getTitle());
                }
            });
        }
    }
}
