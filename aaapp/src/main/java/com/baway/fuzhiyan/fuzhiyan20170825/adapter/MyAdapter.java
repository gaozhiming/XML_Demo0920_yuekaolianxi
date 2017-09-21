package com.baway.fuzhiyan.fuzhiyan20170825.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.fuzhiyan.fuzhiyan20170825.R;
import com.baway.fuzhiyan.fuzhiyan20170825.bean.MyBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 * time:
 * author:付智焱
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<MyBean.ZwlBean> list=new ArrayList<>();

    public MyAdapter(Context context, List<MyBean.ZwlBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new MyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyBean.ZwlBean bean = list.get(position);
        holder.title.setText(bean.Moment.content);
        holder.time.setText(bean.Moment.date);
        holder.good.setText(bean.Commentzwl.get(0).momentId + "点赞");
        holder.say.setText(bean.Commentzwl.get(0).toId + "评论");
        Glide.with(context).load(bean.Moment.pictureList.get(0)).into(holder.itemImage);
        Glide.with(context).load(bean.User.head).into(holder.contentImage1);
        Glide.with(context).load(bean.User.head).into(holder.contentImage2);
        Glide.with(context).load(bean.User.head).into(holder.contentImage3);




    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title, good, say, time;
        private ImageView itemImage, contentImage1, contentImage2, contentImage3;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
            contentImage1 = (ImageView) itemView.findViewById(R.id.item_content_image1);
            contentImage2 = (ImageView) itemView.findViewById(R.id.item_content_image2);
            contentImage3 = (ImageView) itemView.findViewById(R.id.item_content_image3);
            title = (TextView) itemView.findViewById(R.id.item_title);
            time = (TextView) itemView.findViewById(R.id.item_time);
            good = (TextView) itemView.findViewById(R.id.item_good);
            say = (TextView) itemView.findViewById(R.id.item_say);



        }
    }

}
