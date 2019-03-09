package com.a_rin.tenki;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

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
        int level = 0;


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.title);
            viewHolder.RecomendedDayTextView = (TextView) convertView.findViewById(R.id.RecomemdedDay);
            viewHolder.WashingIndexTextView = (TextView) convertView.findViewById(R.id.WashingIndex);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = getItem(position);

        //set data
        viewHolder.titleTextView.setText(item.title);

        if (item.isThick)
            level++;
        if(item.hasDecoration)
            level++;
        //ここから下に条件文書こうとしてる


        viewHolder.WashingIndexTextView.setText("100%");

        viewHolder.RecomendedDayTextView.setText("今日");


        return convertView;
    }

    class ViewHolder{
        TextView titleTextView;
        TextView RecomendedDayTextView;
        TextView WashingIndexTextView;
    }
    
}
