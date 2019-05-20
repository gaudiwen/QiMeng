package com.cndatacom.qmhz.utils;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeThread extends Thread {
    public TextView tvDate;
    private int msgKey1 = 22;
 
    public TimeThread(TextView tvDate) {
        this.tvDate = tvDate;
    }
 
    @Override
    public void run() {
        do {
            try {
                Message msg = new Message();
                msg.what = msgKey1;
                mHandler.sendMessage(msg);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }
 
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 22:
 
//                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//                    String date = sdf.format(new Date());
                    String time= TimeUtils.date2String2(new Date());
                    tvDate.setText(time);
 
                    break;
 
                default:
                    break;
            }
        }
    };
 
    /**
     * 获取今天星期几
     * @return
     */
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
            default:
                return "";
        }
    }
}