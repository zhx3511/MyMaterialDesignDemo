package com.mc.modemd.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mc.modemd.R;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class Testadapter extends RecyclerView.Adapter<Testadapter.MyViewHolder> {

    private Activity activity;
    private List<String> mData;

    public Testadapter(Activity activity, List<String> mData) {
        this.activity = activity;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.item_tiel.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView item_tiel;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_tiel = (TextView) itemView.findViewById(R.id.item_tiel);
        }
    }

}
