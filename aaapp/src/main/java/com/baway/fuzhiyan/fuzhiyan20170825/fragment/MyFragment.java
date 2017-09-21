package com.baway.fuzhiyan.fuzhiyan20170825.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.fuzhiyan.fuzhiyan20170825.R;

/**
 * Created by Administrator on 2017/8/25.
 * time:
 * author:付智焱
 */

public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }
}
