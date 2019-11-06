package com.example.autolist;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class AutoActivity extends SingleFragmentActivity {

    private static final String EXTRA_AUTO_ID = "autoId";

    // Passes autoID as an Intent extra when this activity is started
    public static Intent newIntent(Context packageContext, Integer autoID) {

        Intent intent = new Intent(packageContext, AutoActivity.class);

        if (autoID != null) {
            // Passes in a String key and Serializable value
            intent.putExtra(EXTRA_AUTO_ID, autoID);
        }

        return intent;
    }

    @Override
    protected Fragment createFragment() {

        Integer autoId = null;

        if (getIntent().hasExtra(EXTRA_AUTO_ID)) {
            // Retrieves extra from intent, passes into AutoAddEditFragment new instance
            autoId = getIntent().getIntExtra(EXTRA_AUTO_ID, 0);
        }

        // return new instance of AutAddEditFrsgment
        return AutoAddEditFragment.newInstance(autoId);
    }
}
