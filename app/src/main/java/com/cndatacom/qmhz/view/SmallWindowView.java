package com.cndatacom.qmhz.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cndatacom.qmhz.R;

public class SmallWindowView extends RelativeLayout {

    public SmallWindowView(Context context) {
        this(context, null);
        init(context);
    }

    public SmallWindowView(Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmallWindowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundResource(R.color.color_blue);
        setGravity(Gravity.LEFT | Gravity.TOP);
    }

    /**
     * 设置宽和高的属性
     *
     * @param height
     * @param width
     */
    public void setHW(int height, int width) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, height);
        setLayoutParams(params);
    }
}
