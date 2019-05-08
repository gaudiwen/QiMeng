package com.cndatacom.qmhz.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;


/**
 * 让Textview一直有焦点的走马灯
 */
public class MarqueeTextView extends AppCompatTextView {
/*
     <com.cndatacom.qmhz.view.MarqueeTextView
    android:id="@+id/tvMarqueeOne"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingTop="@dimen/h_10"
    android:layout_marginLeft="@dimen/w_50"
    android:layout_marginRight="@dimen/w_50"
    android:layout_centerInParent="true"
    android:ellipsize="marquee"
    android:focusable="true"
    android:focusableInTouchMode="true"
    android:marqueeRepeatLimit="marquee_forever"
    android:singleLine="true"
    android:text="Always believe that somethinn" />*/

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置单行
        setSingleLine();
        //设置Ellipsize
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        //获取焦点
        setFocusable(true);
        //走马灯的重复次数，-1代表无限重复
        setMarqueeRepeatLimit(-1);
        //强制获得焦点
        setFocusableInTouchMode(true);
    }

    /*
     *这个属性这个View得到焦点,在这里我们设置为true,这个View就永远是有焦点的
     */
    @Override
    public boolean isFocused() {
        return true;
    }
    public void setFocused(){
        //获取焦点
        setFocusable(true);
    }

    /*
     * 用于EditText抢注焦点的问题
     * */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }
}