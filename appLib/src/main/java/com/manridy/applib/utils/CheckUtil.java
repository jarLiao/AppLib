package com.manridy.applib.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

/**
 * 检测输入工具类
 * Created by Administrator on 2016/8/1.
 */
public class CheckUtil {

    /**
     * 判断是否为空
     * @param s
     * @return
     */
    public static boolean isNull(String s){
        if (null == s||s.equals("")){
            return true;
        }
        return false;
    }

    /**
     * 判断是否为手机号码
     * @param s
     * @return
     */
    public static boolean isPhone(String s){
        return s.matches("^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$");
    }

    /**
     * 判断密码与确认密码是否一致
     * @return
     */
    public static boolean checkRePwd(String pwd, String rePwd){
        return pwd.equals(rePwd);
    }

    /**
     * 判断身份证是否合法
     * @param s
     * @return
     */
    public static boolean isIdentity(String s){
        return s.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
    }

    /**
     * 只显示头尾字符
     * @param s
     * @return
     */
    public static String stringEnc(String s){
        String sub =s.substring(1,s.length()-1);
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < sub.length(); i++) {
            buf.append("*") ;
        }
        String result = s.replace(sub,buf.toString());
        return result;
    }

    /**
     * 判断密码强度
     * @param s
     * @return 0-3
     */
    public static int getPwdComplex(String s){
        int count = 0;
        boolean isLetter = false,isCapital = false,isNum = false,isSymbol = false;
        for (int i = 0; i < s.length(); i++) {
            String c =  String.valueOf(s.charAt(i));
            if (c.matches("[a-z]*")){
                isLetter = true;
            }
            if (c.matches("[A-Z]*")){
                isCapital = true;
            }
            if (c.matches("[0-9]*")) {
                isNum = true;
            }
            if (c.matches("\\p{Punct}+")) {
                isSymbol = true;
            }
        }
        if (isLetter){
            count++;
        }
        if (isCapital){
            count++;
        }
        if (isNum) {
            count++;
        }
        if (isSymbol) {
            count++;
        }
        return count;
    }

    /**
     * 判断service是否运行
     * @param context
     * @param service_Name
     * @return
     */
    public static boolean isServiceRunning(Context context, String service_Name) {
        ActivityManager manager =
                (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            if (service_Name.equals(service.service.getClassName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前App处于前台还是后台
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.GET_TASKS"/>}</p>
     * <p>并且必须是系统应用该方法才有效</p>
     *
     * @param context 上下文
     * @return {@code true}: 后台<br>{@code false}: 前台
     */
    public static boolean isAppBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * GPS是否开启
     * @param context
     * @return
     */
    public static boolean isGpsEnable(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    /**
     * 检测是否是蓝牙地址
     * @param address
     * @return
     */
    public static boolean checkBluetoothAddress(String address) {
        int ADDRESS_LENGTH = 17;
        if (address == null || address.length() != ADDRESS_LENGTH) {
            return false;
        }
        for (int i = 0; i < ADDRESS_LENGTH; i++) {
            char c = address.charAt(i);
            switch (i % 3) {
                case 0:
                case 1:
                    if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F')) {
                        // hex character, OK
                        break;
                    }
                    return false;
                case 2:
                    if (c == ':') {
                        break;  // OK
                    }
                    return false;
            }
        }
        return true;
    }

    public static boolean checkFilter(String deviceName,String[] filters){
        for (String filter : filters) {
            if (deviceName.contains(filter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
