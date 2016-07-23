package com.promiseland.nerdlauncher.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by joseph on 2016/7/23.
 */
public class NerdLauncherFragment extends ListFragment {
    private static final String TAG = "NerdLauncherFragment";
    private List<ResolveInfo> mResolveInfos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent startupIntent = new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = getActivity().getPackageManager();
        mResolveInfos = pm.queryIntentActivities(startupIntent, 0);

        Collections.sort(mResolveInfos, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo src, ResolveInfo dest) {
                PackageManager pm = getActivity().getPackageManager();
                String srcLabel = src.loadLabel(pm).toString();
                String destLabel = dest.loadLabel(pm).toString();
                return srcLabel.compareTo(destLabel);
            }
        });

        ArrayAdapter<ResolveInfo> adapter = new ArrayAdapter<ResolveInfo>(getActivity(), android.R.layout.simple_list_item_1, mResolveInfos){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                PackageManager pm = getActivity().getPackageManager();
                String label = mResolveInfos.get(position).loadLabel(pm).toString();
                view.setText(label);
                return view;
            }
        };

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ResolveInfo resolveInfo = (ResolveInfo) l.getAdapter().getItem(position);
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName(activityInfo.packageName, activityInfo.name);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}
