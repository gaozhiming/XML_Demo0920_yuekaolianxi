package com.baway.fuzhiyan.fuzhiyan20170825.bean;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;



/**
 * Created by Administrator on 2017/8/25.
 * time:2017-8-25 14:26:17
 * author:付智焱
 * 类用途：实体封装类、网络请求的数据。。
 */

public class MyBean implements Serializable{

    public long code;
    public String msg;
    @SerializedName("[]")
    public List<ZwlBean> zwl;

    public static class ZwlBean {

        public MomentBean Moment;
        public UserBean User;
        @SerializedName("Comment[]")
        public List<CommentzwlBean> Commentzwl;

        public static class MomentBean {
            public long id;
            public long userId;
            public String date;
            public String content;
            public List<Long> praiseUserIdList;
            public List<String> pictureList;
        }

        public static class UserBean {
            public long id;
            public String name;
            public String head;
        }

        public static class CommentzwlBean {

            public long id;
            public long toId;
            public long userId;
            public long momentId;
            public String date;
            public String content;

            public static CommentzwlBean objectFromData(String str) {
                return new Gson().fromJson(str, CommentzwlBean.class);
            }
        }

        public static class Comment {
            public long id;
            public long toId;
            public long userId;
            public long momentId;
            public String date;
            public String content;
        }
    }
}
