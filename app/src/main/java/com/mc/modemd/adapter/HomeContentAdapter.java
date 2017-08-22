package com.mc.modemd.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mc.modemd.R;
import com.mc.modemd.utils.CommonUtil;
import com.mc.modemd.views.ClipViewPager;
import com.mc.modemd.views.ScalePageTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/8/21.
 */

public class HomeContentAdapter extends RecyclerView.Adapter<HomeContentAdapter.MyViewHolder> {


    private List<String> mData = new ArrayList<>();
    private Map<Integer, Integer> map = new HashMap<>();
    private Activity activity;
    private FragmentManager childFragmentManager;

    public HomeContentAdapter(Activity activity, List<String> mData) {
        this.mData = mData;
        this.activity = activity;
    }

    public HomeContentAdapter(Activity activity, List<String> mData, FragmentManager childFragmentManager) {
        this.mData = mData;
        this.activity = activity;
        this.childFragmentManager = childFragmentManager;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_viewpage, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        List<String> mtitles = new ArrayList<>();
        if (position % 2 == 0) {
            mtitles.add("ssss");
            mtitles.add("kkk");
            mtitles.add("kkksss");

        } else {
            mtitles.add("ssss");
            mtitles.add("kkk");
        }

//        holder.layout.
        CommonUtil.setLayoutWh(holder.layout, CommonUtil.getScreenWidth(activity), CommonUtil.dip2px(activity, 300));
        CommonUtil.setLayoutWh(holder.itemViewpage, CommonUtil.getScreenWidth(activity) - CommonUtil.dip2px(activity, 40), CommonUtil.dip2px(activity, 220));
        holder.itemViewpage.setId(position + 10000);
        HomeContentDataInfoAdapter mPagerAdapter = new HomeContentDataInfoAdapter(mtitles, activity);
        holder.itemViewpage.setPageTransformer(true, new ScalePageTransformer());
        holder.itemViewpage.setAdapter(mPagerAdapter);
        holder.itemViewpage.setOffscreenPageLimit(Math.min(mtitles.size(), 3));
        holder.itemTxt.setText(position + "");
        holder.layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return holder.itemViewpage.dispatchTouchEvent(event);
            }
        });
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int key = (int) entry.getKey();
            int val = (int) entry.getValue();
            if (position == key) {
                holder.itemViewpage.setCurrentItem(val);
            }
        }
        holder.itemViewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int recyclerPosition = holder.getLayoutPosition();
                map.put(recyclerPosition, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_viewpage)
        ClipViewPager itemViewpage;
        @BindView(R.id.item_txt)
        TextView itemTxt;
        @BindView(R.id.layout)
        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
