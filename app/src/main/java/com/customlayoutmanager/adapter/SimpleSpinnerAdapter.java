package com.customlayoutmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


import com.customlayoutmanager.R;

import java.util.List;


/**
 * Created by Fishy on 2016/7/12.
 */
public class SimpleSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
    private Context context;
    private List<String> lists;

    public SimpleSpinnerAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_simple_list_item, viewGroup, false);
        TextView textSelected = (TextView) view.findViewById(R.id.textSelected);
        textSelected.setText(lists.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_simple_spinner_item, parent, false);
        TextView textItem = (TextView) view.findViewById(R.id.textItem);
        textItem.setText(lists.get(position));
        return view;
    }
}
