package com.example.autolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autolist.db.AutoRecord;

import java.util.Date;
import java.util.List;

public class AutoListFragment extends Fragment {

    private final static String TAG = "AUTO_LIST_ADAPTER";

    private RecyclerView mAutoRecyclerView;
    private AutoAdapter mAutoAdapter;
    private AutoListViewModel mAutoListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto_list, container, false);

        // Gets reference to recycler view's attribute ID in activity_fragment layout file
        mAutoRecyclerView = view.findViewById(R.id.auto_recycler_view);
        // Calls layout manager object to linearly display items in list
        mAutoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Creates adapter
        mAutoAdapter = new AutoAdapter();
        // Sets adapter in recycler view
        mAutoRecyclerView.setAdapter(mAutoAdapter);

        return view;
    }

    // Overrides onResume() to reload list with newest data
    @Override
    public void onResume() {
        super.onResume();
        // Creates AutoList view model connection
        mAutoListViewModel = ViewModelProviders.of(this).get(AutoListViewModel.class);

        // Gets all Auto Records from AutoList view model
        mAutoListViewModel.getAllAutoRecords().observe(this, new Observer<List<AutoRecord>>() {
            @Override
            // When a change in AutoRecords is detected, sets new data in adapter
            public void onChanged(List<AutoRecord> autos) {
                Log.d(TAG, "autos are: " + autos);
                // Set data in the adapter
                mAutoAdapter.setAutos(autos);
            }
        });
    }

    // Objects of this class represent the view for one data item
    class AutoItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        AutoRecord mAuto;
        TextView mTitle;
        TextView mDateCreatedTextView;
        TextView mNotesTextView;
        ImageView mThumbnail;

        AutoItemViewHolder(ConstraintLayout layout) {
            super(layout);
            // Get object references
            mTitle = layout.findViewById(R.id.auto_title);
            mDateCreatedTextView = layout.findViewById(R.id.date_entered);
            mNotesTextView = layout.findViewById(R.id.notes_text_view);
            mThumbnail = layout.findViewById(R.id.imageView);
            // Set click listeners
            layout.setOnClickListener(this);
            layout.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Notify the listener of the event, and which item was clicked
            // Passes autoID from the newIntent method created in AutoActivity
            Intent intent = AutoActivity.newIntent(getActivity(), mAuto.getAutoId());
            startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            return true; // Indicates event is consumed, no further processing.
            // If this is false, in this app, the click event is fired too.
        }

        // Called each time a new AutoRecord needs displayed in AutoItemHolder
        public void bind(AutoRecord auto) {
            mAuto = auto;
            mTitle.setText(auto.getTitle());
//            mDateCreatedTextView.setText("Created on " + auto.getDateCreated());
//            mNotesTextView.setText(auto.getReason());
        }
    }

    private class AutoAdapter extends RecyclerView.Adapter<AutoItemViewHolder> {

        // Adapter's internal data store
        private List<AutoRecord> mAutos;

        @Override
        public int getItemCount() {
            if (mAutos == null) {
                return 0;
            } else {

                return mAutos.size();
            }
        }

        @NonNull
        @Override
        public AutoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Get a reference to the Auto_list_element LinearLayout container and inflate in, in this context
            ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_auto, parent, false);
            // Create a new viewHolder, to contain this LinearLayout
            AutoItemViewHolder viewHolder = new AutoItemViewHolder(layout);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AutoItemViewHolder holder, int position) {
            // Configures a ViewHolder to display the data for the given position
            // In Android terminology, bind the view and its data
            AutoRecord auto = mAutos.get(position);
            holder.bind(auto);
        }

        public void setAutos(List<AutoRecord> autos) {
            this.mAutos = autos;
            // Tell recyclerView to reload
            notifyDataSetChanged();
        }
    }

}
