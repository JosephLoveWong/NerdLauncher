package com.promiseland.nerdlauncher.activity;

import android.support.v4.app.Fragment;

import com.promiseland.nerdlauncher.fragment.NerdLauncherFragment;

public class NerdLauncherActivity extends SingleFragmentActivity {
    private static final String TAG = "NerdLauncherActivity";

    @Override
    protected Fragment createFragment() {
        return new NerdLauncherFragment();
    }
}
