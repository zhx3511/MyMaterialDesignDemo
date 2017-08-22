package com.mc.modemd.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class CustomDialog extends Dialog {
    private static int default_width = WindowManager.LayoutParams.MATCH_PARENT;
    private static int default_height = WindowManager.LayoutParams.WRAP_CONTENT;

    private int mWidth;
    private int mHeight;

    private Context context;

    public CustomDialog(Context context, int layout, int style) {
        this(context, default_width, default_height, layout, style);
    }

    public CustomDialog(Context context, int width, int height, int layout,
                        int style) {
        super(context, style);


        this.context = context;
        init();
        // set content
        setContentView(layout);
        // set window params
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        // set width,height by density and gravity
        float density = getDensity(context);
        params.width = mWidth;
        params.height = default_height;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        setCanceledOnTouchOutside(false);
    }

    private void init() {

//        WindowManager wm1 = context.getWindowManager();
//        int width1 = wm1.getDefaultDisplay().getWidth();
//        int height1 = wm1.getDefaultDisplay().getHeight();
//
//        WindowManager manager = context.getWindowManager();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        manager.getDefaultDisplay().getMetrics(outMetrics);

        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;


    }

    private float getDensity(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.density;
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();

    }

    /*
     * 监听返回键 并使其无效 ** (non- *Javadoc)
     *
     * @see android.app.Dialog#onKeyDown(int, android.view.KeyEvent)
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something...
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
