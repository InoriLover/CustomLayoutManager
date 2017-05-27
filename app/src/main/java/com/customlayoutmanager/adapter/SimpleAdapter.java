package com.customlayoutmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.customlayoutmanager.R;

import java.util.List;

/**
 * Created by Fishy on 2017/3/27.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {
    List<String> data;
    Context context;

    public SimpleAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_simple, parent, false);
        SimpleViewHolder holder = new SimpleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        String info = data.get(position);
        holder.tvIndex.setText(info);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndex;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            tvIndex = (TextView) itemView.findViewById(R.id.tvIndex);
        }
    }
}
