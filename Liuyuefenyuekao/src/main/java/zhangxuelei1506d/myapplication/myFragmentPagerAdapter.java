package zhangxuelei1506d.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import static android.R.id.list;

/**
 * date 2017/9/19
 * author:张学雷(Administrator)
 * functinn:
 */

public class myFragmentPagerAdapter extends FragmentPagerAdapter {


    public myFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    private List<Fragment> list;

    public void setFragment( List<Fragment> fragments){
        list=fragments;

    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}