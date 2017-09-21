package com.baway.fuzhiyan.fuzhiyan20170825.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.baway.fuzhiyan.fuzhiyan20170825.R;
import com.baway.fuzhiyan.fuzhiyan20170825.adapter.MyAdapter;
import com.baway.fuzhiyan.fuzhiyan20170825.bean.MyBean;
import com.baway.fuzhiyan.fuzhiyan20170825.utils.NetUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/25.
 * time:2017-8-25 15:52:08
 * author:付智焱
 * 第一个fragment，通过网络请求显示数据
 */

public class CircleFragment extends Fragment {

    private MyAdapter myAdapter;
    private RecyclerView recycle;
    private Handler hand = new Handler();
    private MyBean bean;
    private LinearLayoutManager linearLayoutManager;
    private List<MyBean.ZwlBean> list = new ArrayList<>();
    //最后一条可见条目
    private int findLastVisibleItemPosition;
    private String url = "http://139.196.140.118:8080/get/%7B%22%5B%5D%22:%7B%22page%22:0,%22count%22:10,%22Moment%22:%7B%22content$%22:%22%2525a%2525%22%7D,%22User%22:%7B%22id@%22:%22%252FMoment%252FuserId%22,%22@column%22:%22id,name,head%22%7D,%22Comment%5B%5D%22:%7B%22count%22:2,%22Comment%22:%7B%22momentId@%22:%22%5B%5D%252FMoment%252Fid%22%7D%7D%7D%7D";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        boolean netWork= NetUtils.isNetworkAvailable(getActivity());
        if(netWork){
            initData();
            Toast.makeText(getActivity(), "您已连接网络", Toast.LENGTH_SHORT).show();

        }else{
            //没有网络时 跳转设置页面
            AlertDialog.Builder b = new AlertDialog.Builder(getActivity()).setTitle("没有可用的网络").setMessage("请开启GPRS或WIFI网路连接");
            b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent mIntent = new Intent("android.settings.WIRELESS_SETTINGS");
                    startActivity(mIntent);
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //errorImage.setVisibility(View.VISIBLE);
                }
            }).create();
            b.show();
        }


    }

    private void initData() {

        //okhttp网络请求
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().
                url(url).
                build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response != null && response.isSuccessful() && response.body() != null) {
                    String htmlStr = response.body().string();
                    System.out.println(htmlStr);


                    Gson gson=new Gson();
                    MyBean myBean = gson.fromJson(htmlStr, MyBean.class);
                    list.addAll(myBean.zwl);

                }


                //handler.post开启子线程
                hand.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(list + "");
                        myAdapter = new MyAdapter(getActivity(), list);
                        recycle.setAdapter(myAdapter);

                    }
                });
            }
        });


    }

    private void initView(View view) {

        recycle = view.findViewById(R.id.recycle);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(linearLayoutManager);
        //滑动监听
        recycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断滑动状态是否为停止
                if (AbsListView.OnScrollListener.SCROLL_STATE_IDLE == newState) {

                    //当前滚动 停止
                    int findLastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    int findFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();


                    int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    //当最后一条可见条目这停止就加载更多

                    if (findLastVisibleItemPosition == list.size() - 1) {
                        initData();

                        myAdapter.notifyDataSetChanged();
                    }


                }

            }
        });

    }
}
