package com.cndatacom.qmhz.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class NetWorkUtil {

    /**
     * 判断是否有网络连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取NetworkInfo对象
            NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
            //判断NetworkInfo对象是否为空
            return null != networkInfo && networkInfo.isAvailable();
            //return null != networkInfo && networkInfo.isConnected();
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取NetworkInfo对象
            NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
            //判断NetworkInfo对象是否为空 并且类型是否为WIFI
            if (null != networkInfo && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            //获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取NetworkInfo对象
            NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
            //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
            if (null != networkInfo && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 测试当前网络连接后是否能上网
     * 注意：该方法不能直接在UI线程中执行
     */
    public static synchronized boolean isConnect() {
        boolean isConnect = false;
        int count = 0;
        while (count < 3) {
            try {
                URL url = new URL("https://www.baidu.com");
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                int state = connection.getResponseCode();
                System.out.println(count + "=" + state);
                if (connection.getResponseCode() == 200) {
                    System.out.println("网络可用");
                    isConnect = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println("网络不可用，连接第" + count + "次 ：" + e);
                count++;
            }
        }
        return isConnect;
    }

    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 获取当前ip地址
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {

            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2ip(i);
        } catch (Exception ex) {
            return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
        }
        // return null;
    }

}
