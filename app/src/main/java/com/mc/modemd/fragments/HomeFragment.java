package com.mc.modemd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mc.modemd.R;
import com.mc.modemd.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21.
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.teb_layout)
    TabLayout tebLayout;
    @BindView(R.id.check_viewpage)
    ViewPager checkViewpage;
    private MyViewPagerAdapter adapter;

    /**
     * 当前页面是否显示
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    /**
     * UI实例化`
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    /**
     * 加载布局
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Activity  的onCreate（）
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Activity 通知Fragment的 onCreate()
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<Fragment> fragments = new ArrayList<>();
        List<String> fragmenttitldts = new ArrayList<>();

        fragments.add(new HomeContentFragment());
        fragmenttitldts.add("sss");
        fragments.add(new HomeContentFragment());
        fragmenttitldts.add("aaaa");
        fragments.add(new HomeContentFragment());
        fragmenttitldts.add("cccc");
        fragments.add(new HomeContentFragment());
        fragmenttitldts.add("xxxx");

        adapter = new MyViewPagerAdapter(getChildFragmentManager(), fragments, fragmenttitldts);
        checkViewpage.setAdapter(adapter);

        tebLayout.addTab(tebLayout.newTab().setText("nihao"));
        tebLayout.addTab(tebLayout.newTab().setText("nihao"));
        tebLayout.addTab(tebLayout.newTab().setText("nihao"));
        tebLayout.addTab(tebLayout.newTab().setText("nih0o"));
        tebLayout.setupWithViewPager(checkViewpage);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }
}
