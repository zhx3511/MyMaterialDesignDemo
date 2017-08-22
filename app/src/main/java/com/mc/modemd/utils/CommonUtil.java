package com.mc.modemd.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/8/14.
 */

public class CommonUtil {

    //判断是否存在NavigationBar
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
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取底部导航栏高度
     *
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        //获取NavigationBar的高度
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 设置视图边距
     *
     * @param activity
     * @param navigationHeight
     */
    public static void setWindosPadding(Activity activity, int navigationHeight) {
        activity.getWindow().getDecorView().findViewById(android.R.id.content).setPadding(0, 0, 0, navigationHeight);
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels - (getStatusHeight(context) == -1 ? 0 : getStatusHeight(context));
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    private static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


    // dip转化成px
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 正方形
     *
     * @param layoutWh 调整Layout的宽高
     * @param w        屏幕宽度减去的值
     * @param b        倍数
     */
    public static void setLayoutWh(Activity activity, View layoutWh, int w, int b) {

        WindowManager wm1 = activity.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        width = (width - CommonUtil.dip2px(activity, w)) / b;
        ViewGroup.LayoutParams para1 = layoutWh.getLayoutParams();
        para1.height = width;
        para1.width = width;
        layoutWh.setLayoutParams(para1);
    }

    /**
     * @param layoutWh 调整Layout的宽高
     * @param w        修改后的宽度
     * @param h        修改后的高度
     */
    public static void setLayoutWh(View layoutWh, int w, int h) {

        ViewGroup.LayoutParams para1 = layoutWh.getLayoutParams();
        para1.height = h;
        para1.width = w;
        layoutWh.setLayoutParams(para1);
    }


}
