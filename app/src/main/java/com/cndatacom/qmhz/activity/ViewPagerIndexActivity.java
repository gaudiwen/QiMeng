package com.cndatacom.qmhz.activity;

import android.animation.Animator;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.OpenTabPagerAdapter;
import com.cndatacom.qmhz.adapter.OpenTabTitleAdapter;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.bridge.OpenEffectBridge;
import com.open.androidtvwidget.utils.OPENLOG;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.OpenTabHost;

import java.util.List;

/**
 * 描述: 描述一下类的作用
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/6/10 11:15.
 */
public class ViewPagerIndexActivity extends AppCompatActivity  implements OpenTabHost.OnTabSelectListener{

    private List<View> viewList;// view数组
    private View view1, view2, view3, view4;
    ViewPager viewpager;
    OpenTabHost mOpenTabHost;
    OpenTabTitleAdapter mOpenTabTitleAdapter;
    // 移动边框.
    MainUpView mainUpView1;
    EffectNoDrawBridge mEffectNoDrawBridge;
    View mNewFocus;
    View mOldView;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_viewpager_activity);
        // 初始化标题栏.
        initAllTitleBar();
        // 初始化viewpager.
        initAllViewPager();
        // 初始化移动边框.
        initMoveBridge();
    }

    private void initMoveBridge() {
        float density = getResources().getDisplayMetrics().density;
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        mEffectNoDrawBridge = new EffectNoDrawBridge();
        mainUpView1.setEffectBridge(mEffectNoDrawBridge);
        mEffectNoDrawBridge.setUpRectResource(R.drawable.white_light_10); // 设置移动边框图片.
        RectF rectF = new RectF(getDimension(R.dimen.w_10) * density, getDimension(R.dimen.h_10) * density,
                getDimension(R.dimen.w_10) * density, getDimension(R.dimen.h_10) * density);
        mEffectNoDrawBridge.setDrawUpRectPadding(rectF);
    }

    private void initAllTitleBar() {
        mOpenTabHost = (OpenTabHost) findViewById(R.id.openTabHost);
        mOpenTabTitleAdapter = new OpenTabTitleAdapter();
        mOpenTabHost.setOnTabSelectListener(this);
        mOpenTabHost.setAdapter(mOpenTabTitleAdapter);
    }

    private void initAllViewPager() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        // 初始化滚动窗口适配. (请注意哈，在不同的dpi下, 滚动相差的间距不一样哈)
        /*for (View view : viewList) {
            float density = getResources().getDisplayMetrics().density;
            SmoothHorizontalScrollView shsv = (SmoothHorizontalScrollView) view.findViewById(R.id.test_hscroll);
            shsv.setFadingEdge((int) (getDimension(R.dimen.w_200) * density));
        }*/
        //
        viewpager.setAdapter(new OpenTabPagerAdapter(getSupportFragmentManager()));
        // 全局焦点监听. (这里只是demo，为了方便这样写，你可以不这样写)
        viewpager.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                // 判断 : 避免焦点框跑到标题栏. (只是demo，你自己处理逻辑)
                // 你也可以让标题栏放大，有移动边框.
                if (newFocus != null && !(newFocus instanceof TextView)) {
                    mEffectNoDrawBridge.setVisibleWidget(false);
                    mNewFocus = newFocus;
                    mOldView = oldFocus;
                    mainUpView1.setFocusView(newFocus, oldFocus, 1.2f);
                    // 让被挡住的焦点控件在前面.
                    newFocus.bringToFront();
                    OPENLOG.D("addOnGlobalFocusChangeListener");
                } else { // 标题栏处理.
                    mNewFocus = null;
                    mOldView = null;
                    mainUpView1.setUnFocusView(oldFocus);
                    mEffectNoDrawBridge.setVisibleWidget(true);
                }
            }
        });
        viewpager.setOffscreenPageLimit(4);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switchTab(mOpenTabHost, position);
                OPENLOG.D("onPageSelected position:" + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // viewPager 正在滚动中.
                OPENLOG.D("onPageScrolled position:" + position + " positionOffset:" + positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE: // viewpager 滚动结束.
                        mainUpView1.setFocusView(mNewFocus, mOldView, 1.2f);
                        // 监听动画事件.
                        mEffectNoDrawBridge.setOnAnimatorListener(new OpenEffectBridge.NewAnimatorListener() {
                            @Override
                            public void onAnimationStart(OpenEffectBridge bridge, View view, Animator animation) {
                            }
                            @Override
                            public void onAnimationEnd(OpenEffectBridge bridge, View view, Animator animation) {
                                // 动画结束的时候恢复原来的时间. (这里只是DEMO)
                                mEffectNoDrawBridge.setTranDurAnimTime(OpenEffectBridge.DEFAULT_TRAN_DUR_ANIM);
                            }
                        });
                        // 让被挡住的焦点控件在前面.
                        if (mNewFocus != null)
                            mNewFocus.bringToFront();
                        OPENLOG.D("SCROLL_STATE_IDLE");
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        OPENLOG.D("SCROLL_STATE_DRAGGING");
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING: // viewPager开始滚动.
                        mEffectNoDrawBridge.clearAnimator(); // 清除之前的动画.
                        mEffectNoDrawBridge.setTranDurAnimTime(0); // 避免边框从其它地方跑出来.
                        OPENLOG.D("SCROLL_STATE_SETTLING");
                        break;
                }
            }
        });
        // 初始化.
        viewpager.setCurrentItem(0);
        switchTab(mOpenTabHost, 0);
    }

    @Override
    public void onTabSelect(OpenTabHost openTabHost, View titleWidget, int position) {
        if (viewpager != null) {
            viewpager.setCurrentItem(position);
        }
    }

    /**
     * demo (翻页的时候改变状态)
     * 将标题栏的文字颜色改变. <br>
     * 你可以写自己的东西，我这里只是DEMO.
     */
    public void switchTab(OpenTabHost openTabHost, int postion) {
        List<View> viewList = openTabHost.getAllTitleView();
        for (int i = 0; i < viewList.size(); i++) {
            /*if(i==0){
                ImageView view = (ImageView) openTabHost.getTitleViewIndexAt(i);
                if (i == postion) {
                    view.setSelected(true); // 为了显示 失去焦点，选中为 true的图片.
                } else {
                    view.setSelected(false);
                }
                continue;
            }*/
                TextView view = (TextView) openTabHost.getTitleViewIndexAt(i);
                if (view != null) {
                    Resources res = view.getResources();
                    if (res != null) {
                        if (i == postion) {
                            view.setTextColor(res.getColor(R.color.color_gold));
                            view.setTypeface(null, Typeface.BOLD);
                            view.setSelected(true); // 为了显示 失去焦点，选中为 true的图片.
                        } else {
                            view.setTextColor(res.getColor(R.color.white_50));
                            view.setTypeface(null, Typeface.NORMAL);
                            view.setSelected(false);
                        }
                    }
                }
        }
    }

    private float getDimension(int id) {
        return getResources().getDimension(id);
    }

}
