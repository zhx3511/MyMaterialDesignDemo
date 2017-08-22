package com.mc.modemd.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import com.mc.modemd.R;
import com.mc.modemd.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/8/18.
 */

public class MyDiaLog extends DialogFragment implements View.OnClickListener {


    @BindView(R.id.layout_content_bg)
    FrameLayout layoutContentBg;

    private static IMyDialog mIMyDialog;

    public static void setmIMyDialog(IMyDialog mIMyDialog) {
        MyDiaLog.mIMyDialog = mIMyDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果setCancelable()中参数为true，若点击dialog覆盖不到的activity的空白或者按返回键，则进行cancel，状态检测依次onCancel()和onDismiss()。如参数为false，则按空白处或返回键无反应。缺省为true
        setCancelable(false);
        //可以设置dialog的显示风格，如style为STYLE_NO_TITLE，将被显示title。遗憾的是，我没有在DialogFragment中找到设置title内容的方法。theme为0，表示由系统选择合适的theme。
        int style = DialogFragment.STYLE_NO_TITLE, theme = 0;
        setStyle(style, theme);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.maisnew, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;

        window.setAttributes(windowParams);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = CommonUtil.getScreenWidth(getActivity());
        attributes.height = CommonUtil.getScreenHeight(getActivity());
        getDialog().getWindow().setAttributes(attributes);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog_bg_d15);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutContentBg.setVisibility(View.GONE);
        startAnimator(true);
    }


    private void startAnimator(final boolean is) {
        layoutContentBg.post(new Runnable() {//原因是因为只有在  view，post（）方法里面才能获取到组件的尺寸
            @Override
            public void run() {
                if (is)
                    status(layoutContentBg, 0, CommonUtil.getScreenHeight(getActivity()), is);
                else
                    status(layoutContentBg, CommonUtil.getScreenHeight(getActivity()), 0, is);
            }
        });

    }

    private void status(final View view, final int startRadius, final int endRadius, final boolean is) {
        view.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator mAnimator;
            mAnimator = ViewAnimationUtils.createCircularReveal(view,
                    CommonUtil.getScreenWidth(getActivity()) - CommonUtil.dip2px(getActivity(), 50), CommonUtil.getScreenHeight(getActivity()) - CommonUtil.dip2px(getActivity(), 50), startRadius, endRadius);
            mAnimator.setDuration(200);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.start();
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (!is) {
                        MyDiaLog.this.getDialog().dismiss();
                        mIMyDialog.onClickDelLisners();
                    }
                }
            });
        }
    }


    @OnClick({R.id.del})
    @Override
    public void onClick(View v) {
        startAnimator(false);
    }
}
