package com.example.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LectorsSpinnerAdapter extends BaseAdapter {

    public void setLectors(List<String> lectors) {
        this.lectors = lectors;
    }

    private List<String> lectors;

    @Override
    public int getCount() {
        return lectors == null ? 0 : lectors.size();
    }

    @Override
    public String getItem(int i) {
        return lectors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            view.setTag(vh);
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String lector = lectors.get(i);
        viewHolder.lectorName.setText(lector);
        return view;
    }

    private static class ViewHolder {
        private final TextView lectorName;

        private ViewHolder (View root){
            lectorName = root.findViewById(android.R.id.text1);
        }
    }
}
