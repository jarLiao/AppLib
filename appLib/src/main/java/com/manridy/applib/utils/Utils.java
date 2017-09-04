package com.manridy.applib.utils;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Environment;
import android.text.TextPaint;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * App工具类
 * Created by jarLiao on 17/5/17.
 */

public class Utils {

    /**
     * 得到图片选项数据
     * @return
     */
    public static String[] getIconData(){
        String[] datas = new String[]{"本地相册","相机拍照","取消"};
        return datas;
    }

    /**
     * 得到性别选项数据
     * @return
     */
    public static String[] getSexData(){
        String[] datas = new String[]{"男","女"};
        return datas;
    }

    /**
     * 得到年龄选项数据
     * @return
     */
    public static String[] getAgeData(){
        String[] datas = new String[50];
        for (int i = 0; i < datas.length; i++) {
            datas[i] = (10+i)+"";
        }
        return datas;
    }

    /**
     * 得到身高选项数据
     * @param type
     * @return
     */
    public static String[] getHeightData(int type){
        String[] datas = new String[70];
        if (type == 0) {
            for (int i = 0; i < datas.length; i++) {
                datas[i] = (130+i)+"";
            }
        }else {
            datas = new String[40];
            for (int i = 0; i < datas.length; i++) {
                datas[i] = (50+i)+"";
            }
        }
        return datas;
    }

    /**
     * 得到体重选项数据
     * @param type
     * @return
     */
    public static String[] getWeightData(int type){
        String[] datas = new String[100];
        if (type == 0) {
            for (int i = 0; i < datas.length; i++) {
                datas[i] = (30+i)+"";
            }
        }else {
            datas = new String[200];
            for (int i = 0; i < datas.length; i++) {
                datas[i] = (60+i)+"";
            }
        }

        return datas;
    }

    /**
     * 保存图片到本地
     * @param mBitmap
     * @param name
     */
    public static void savePicToFile(Bitmap mBitmap, String name, String pathName) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(pathName);
        file.mkdirs();// 创建文件夹
        File[] files = file.listFiles();
        if (files.length >= 3) {
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        }
        String fileName = file.getPath() + name;// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {// 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检测设备虚拟按键是否存在
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;

    }

    /**
     * 得到虚拟按键高度
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        if ( !hasBackKey) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
        }
        else{
            return 0;
        }
    }

    public void setUnderline(TextView view){
        TextPaint textPaint = view.getPaint();
        textPaint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textPaint.setAntiAlias(true);
    }

//    private void jumbWechat() {
//        try {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setComponent(cmp);
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(mContext, R.string.error_not_install, Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private void jumb2Wechat() {
//        if (api == null) {
//            api = WXAPIFactory.createWXAPI(mContext, "wx8dda48129cdf2ba5", false);//AppID
//        }
//        if (!api.isWXAppInstalled()) {
//            // 提醒用户没有安装微信
//            Toast.makeText(mContext, R.string.error_not_install, Toast.LENGTH_SHORT).show();
//            return;
//        }
//        api.registerApp("wx8dda48129cdf2ba5");//AppID
//        JumpToBizProfile.Req req = new JumpToBizProfile.Req();
//        req.toUserName = "gh_6bc241fa53ba";//公众号原始ID
//        req.profileType = JumpToBizProfile.JUMP_TO_HARD_WARE_BIZ_PROFILE;
//        req.extMsg = QR_CODE + "#" + extMsg;//二维码路径 + 自定义数据
//        api.sendReq(req);
//    }
}
