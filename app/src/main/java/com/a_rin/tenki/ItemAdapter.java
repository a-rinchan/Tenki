package com.a_rin.tenki;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

class ItemAdapter extends ArrayAdapter<Item> {
    List<Item> mItem;

    public ItemAdapter(Context context,int layoutResourcedID, List<Item> objects){
        super(context,layoutResourcedID,objects);
        mItem = objects;
    }

    public int getCount(){
        return mItem.size();
    }

    public Item getItem(int position){
        return mItem.get(position);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.title);
            viewHolder.isThickTextView = (TextView) convertView.findViewById(R.id.isThick);
            viewHolder.hasDecoration = (TextView) convertView.findViewById(R.id.hasDecoration);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = getItem(position);

        //set data
        viewHolder.titleTextView.setText(item.title);
        viewHolder.isThickTextView.setText("今日");
        viewHolder.hasDecoration.setText("100%");

        return convertView;
    }

    class ViewHolder{
        TextView titleTextView;
        TextView isThickTextView;
        TextView hasDecoration;
    }
}
