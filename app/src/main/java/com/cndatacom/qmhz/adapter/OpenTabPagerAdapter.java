package com.cndatacom.qmhz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.cndatacom.qmhz.activity.ViewPagerIndexActivity;
import com.cndatacom.qmhz.delegates.MyFragment1;


public class OpenTabPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private MyFragment1 myFragment1 = null;
    private MyFragment1 myFragment2 = null;
    private MyFragment1 myFragment3 = null;
    private MyFragment1 myFragment4 = null;

    public OpenTabPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment1();
        myFragment3 = new MyFragment1();
        myFragment4 = new MyFragment1();
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case ViewPagerIndexActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case ViewPagerIndexActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case ViewPagerIndexActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case ViewPagerIndexActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;}
        return fragment;
    }
}