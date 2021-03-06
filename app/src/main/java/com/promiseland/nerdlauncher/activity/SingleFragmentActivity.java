package com.promiseland.nerdlauncher.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.promiseland.nerdlauncher.R;

/**
 * Created by joseph on 2016/7/23.
 */
public abstract class SingleFragmentActivity extends FragmentActivity{
    private static final String TAG = "SingleFragmentActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment = createFragment();
        }

        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    protected abstract Fragment createFragment();
}
