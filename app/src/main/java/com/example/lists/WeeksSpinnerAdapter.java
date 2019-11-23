package com.example.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeeksSpinnerAdapter extends BaseAdapter {



    @Override
    public int getCount() {
        return DisplayMode.values().length;
    }

    @Override
    public DisplayMode getItem(int i) {
        return DisplayMode.values()[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, viewGroup, false);
            WeekViewHolder vh = new WeekViewHolder(view);
            view.setTag(vh);
        }
        WeekViewHolder holder = (WeekViewHolder) view.getTag();
        int titleResourceId = getItem(i).getTitleStringResourceId();
        holder.week.setText(titleResourceId);
        return view;
    }

    private static class WeekViewHolder {
        private final TextView week;

        private WeekViewHolder(View root){
            week = root.findViewById(android.R.id.text1);
        }
    }
}
