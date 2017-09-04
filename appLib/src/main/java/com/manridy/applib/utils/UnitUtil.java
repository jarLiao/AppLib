package com.manridy.applib.utils;

import android.content.Context;

/**
 * 工具类
 * Created by jarLiao.
 */

public class UnitUtil {
    /**
     * 计步转换距离
     * @param num 步数（步）
     * @param height 身高（cm）
     * @return
     */
    public static float stepToMi(float num,int height){
        return 70 - (170 - (height == 0 ? 170 : height)  * num) / 100;
    }

    /**
     * 计步转换卡路里
     * @param num 步数（步）
     * @param height 身高（cm）
     * @param weight 体重（kg）
     * @return
     */
    public static float stepToKa(float num,int height,int weight){
        return miToKa(stepToMi(num,height),weight);
    }

    /**
     * 距离转换卡路里
     * @param mi 距离（米）
     * @param weight 身高（kg）
     * @return
     */
    public static float miToKa(float mi,int weight){
        return (float) ((mi / 100) * 0.0766666 * (weight == 0 ? 60 : weight));
    }

    public String miToKm(int mi,int unit){
        return String .format("%.1f",(mi/1000.0));
    }

    /**
     * 厘米转英寸(公制转英制)
     * @param cm
     * @return
     */
    public static int cmToIn(double cm){
        int in = (int) Math.round(cm * 0.3937);
        return in ;
    }

    /**
     * 英寸转厘米(英制转公制)
     * @param in
     * @return
     */
    public static int inToCm(double in){
        return (int) (in / 0.3937);
    }

    /**
     * 千米转英里（公制转英制）
     * @param km
     * @return
     */
    public static float kmToMi(double km){
        return (float)(km * 0.6214);
    }

    /**
     * 千克转磅(公制转英制)
     * @param kg
     * @return
     */
    public static int kgToLb(double kg){
        return (int) Math.round(kg * 2.205);
    }

    /**
     * 磅转千克（英制转公制）
     * @param lb
     * @return
     */
    public static int lbToKg(double lb){
        return (int) (lb / 2.205);
    }

    public String getOne(float f) {
        return String.format("%.1f", f);
    }

    /**
     * 设备像素(dip,dp)转屏幕像素(px)
     * px就是像素，如果用px,就会用实际像素画，比个如吧，用画一条长度为240px的横线，在480宽的模拟器上看就是一半的屏宽，而在320宽的模拟器上看就是2／3的屏宽了。
     * 而dip，就是把屏幕的高分成480分，宽分成320分。比如你做一条160dip的横线，无论你在320还480的模拟器上，都是一半屏的长度。
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue
     * @param pxValue（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @param spValue（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
