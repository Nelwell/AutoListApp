package com.example.autolist;

import androidx.fragment.app.Fragment;

public class AutoListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AutoListFragment();
    }
}
