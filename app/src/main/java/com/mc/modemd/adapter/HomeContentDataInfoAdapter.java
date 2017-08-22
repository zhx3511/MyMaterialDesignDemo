package com.mc.modemd.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mc.modemd.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/22.
 */

public class HomeContentDataInfoAdapter extends RecyclingPagerAdapter {

    private Activity activity;
    private List<String> datas = new ArrayList<>();

    public HomeContentDataInfoAdapter(List<String> datas, Activity activity) {
        this.datas = datas;
        this.activity = activity;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_home_content, container, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.setTag(position);
        return convertView;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    class ViewHolder {

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
