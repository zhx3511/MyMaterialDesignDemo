package com.mc.modemd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mc.modemd.R;
import com.mc.modemd.adapter.Testadapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/18.
 */

public class TestFragment extends Fragment {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private List<String> mData = new ArrayList<>();


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {

        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        mData.clear();
        for (int i = 0; i < 50; i++) {
            mData.add("选项卡槽" + i);
        }
        Testadapter testadapter = new Testadapter(getActivity(), mData);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 3);
//        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.grid_layout_animation_from_bottom);
//        rvContent.setLayoutAnimation(animationController);
        rvContent.setLayoutManager(linearLayoutManager);
        rvContent.setAdapter(testadapter);

        Log.e("ss", "sss");

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_content, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


}
