package com.mc.modemd;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mc.modemd.adapter.MyViewPagerAdapter;
import com.mc.modemd.dialog.IMyDialog;
import com.mc.modemd.dialog.MyDiaLog;
import com.mc.modemd.fragments.HomeFragment;
import com.mc.modemd.fragments.TestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main)
    CoordinatorLayout activityMain;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.content_vp)
    ViewPager contentVp;

    @BindView(R.id.title_teb_home)
    ImageButton titleTebHome;
    @BindView(R.id.title_teb_address)
    ImageButton titleTebAddress;
    @BindView(R.id.title_teb_me)
    ImageButton titleTebMe;
    @BindView(R.id.title_teb_more)
    ImageView titleTebMore;
    @BindView(R.id.title_teb_search)
    ImageView titleTebSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

        MyDiaLog.setmIMyDialog(new MyIMyDiaLog());
    }

    private MyViewPagerAdapter adapter;

    private void init() {

        List<Fragment> fragments = new ArrayList<>();
        List<String> fragmenttitldts = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragmenttitldts.add("sss");
        fragments.add(new TestFragment());
        fragmenttitldts.add("aaaa");
        fragments.add(new TestFragment());
        fragmenttitldts.add("cccc");

        adapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragments, fragmenttitldts);
        contentVp.setAdapter(adapter);
        contentVp.setOffscreenPageLimit(0);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                MyDiaLog frgment = new MyDiaLog();
                ft.add(frgment, "Currency");
                ft.commit();
                fab.setVisibility(View.GONE);

            }
        });
        contentVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTebHost(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        tabLayout.addTab(tabLayout.newTab().setText("nihao"));
//        tabLayout.addTab(tabLayout.newTab().setText("nihao"));
//        tabLayout.addTab(tabLayout.newTab().setText("nihao"));
//        tabLayout.addTab(tabLayout.newTab().setText("niha   o"));
//        tabLayout.setupWithViewPager(contentVp);


    }

    /**
     * 关闭回调点击事件
     */
    private class MyIMyDiaLog implements IMyDialog {

        @Override
        public void onClickDelLisners() {
            fab.setVisibility(View.VISIBLE);
        }
    }


    private void selectTebHost(int i) {
        titleTebHome.setBackgroundResource(R.color.color_null);
        titleTebAddress.setBackgroundResource(R.color.color_null);
        titleTebMe.setBackgroundResource(R.color.color_null);
        if (i == 0)
            titleTebHome.setBackgroundResource(R.color.title_seles);
        else if (i == 1)
            titleTebAddress.setBackgroundResource(R.color.title_seles);
        else if (i == 2)
            titleTebMe.setBackgroundResource(R.color.title_seles);
    }
}
