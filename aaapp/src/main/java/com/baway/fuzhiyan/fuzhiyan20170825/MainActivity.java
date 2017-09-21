package com.baway.fuzhiyan.fuzhiyan20170825;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.baway.fuzhiyan.fuzhiyan20170825.adapter.TabFragmentPagerAdapter;
import com.baway.fuzhiyan.fuzhiyan20170825.fragment.CircleFragment;
import com.baway.fuzhiyan.fuzhiyan20170825.fragment.FriendFragment;
import com.baway.fuzhiyan.fuzhiyan20170825.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout mTabMain;
    private LinearLayout mTabCommunity;
    private LinearLayout mTabShopping;
    private LinearLayout mTabMe;
    private ImageButton mImageTabMain;
    private ImageButton mImageTabCommunity;
    private ImageButton mImageTabShopping;
    private ImageButton mImageTabMe;

    private ViewPager mViewPager;
    private TabFragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClickListener();
        TopBar topbar = (TopBar) findViewById(R.id.MyTopbar);

    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mTabMain = (LinearLayout) findViewById(R.id.id_tab_main);
        mTabCommunity = (LinearLayout) findViewById(R.id.id_tab_community);
        mTabShopping = (LinearLayout) findViewById(R.id.id_tab_shopping);
        mImageTabMain = (ImageButton) findViewById(R.id.tab_main_icon_grey);
        mImageTabCommunity = (ImageButton) findViewById(R.id.tab_community_icon_grey);
        mImageTabShopping = (ImageButton) findViewById(R.id.tab_shopping_cart_icon_grey);


        mFragments = new ArrayList<Fragment>();
        Fragment mTab_01 = new CircleFragment();
        Fragment mTab_02 = new FriendFragment();
        Fragment mTab_03 = new MyFragment();

        mFragments.add(mTab_01);
        mFragments.add(mTab_02);
        mFragments.add(mTab_03);


        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
//设置滑动监听器
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //滑动时 改变图标状态
            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                initTabImage();
                switch (currentItem) {
                    case 0:
                        mImageTabMain.setImageResource(R.drawable.circle1);
                        break;
                    case 1:
                        mImageTabCommunity.setImageResource(R.drawable.friend1);
                        break;
                    case 2:
                        mImageTabShopping.setImageResource(R.drawable.my1);
                        break;


                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initTabImage() {
        mImageTabMain.setImageResource(R.drawable.circle);
        mImageTabCommunity.setImageResource(R.drawable.friend);
        mImageTabShopping.setImageResource(R.drawable.my);

    }

    private void initClickListener() {
        mTabMain.setOnClickListener(this);
        mTabCommunity.setOnClickListener(this);
        mTabShopping.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_tab_main:
                // initTabImage();
                //mImageTabMain.setImageResource(R.drawable.tab_main_icon_green);
                //注意上面修改的只是图标的状态,还要修改相对应的fragment;
                setSelect(0);
                break;
            case R.id.id_tab_community:
                setSelect(1);
                break;
            case R.id.id_tab_shopping:
                setSelect(2);
                break;

        }
    }

    //设置将点击的那个图标为亮色,切换内容区域
    private void setSelect(int i) {

        initTabImage();
        switch (i) {
            case 0:
                mImageTabMain.setImageResource(R.drawable.circle1);
                break;
            case 1:
                mImageTabCommunity.setImageResource(R.drawable.friend1);
                break;
            case 2:
                mImageTabShopping.setImageResource(R.drawable.my1);
                break;

            default:
                break;
        }
        mViewPager.setCurrentItem(i);
    }
}
