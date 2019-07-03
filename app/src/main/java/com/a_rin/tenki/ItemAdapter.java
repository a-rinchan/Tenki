package com.a_rin.tenki;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


class ItemAdapter extends ArrayAdapter<Item> {
    List<Item> mItem;
    List<String> weathers;
    int wearlevel = 0;


    public ItemAdapter(Context context, int layoutResourcedID, List<Item> objects) {
        super(context, layoutResourcedID, objects);
        mItem = objects;
        weathers = new ArrayList<>();
    }

    public int getCount() {
        return mItem.size();
    }

    public Item getItem(int position) {
        return mItem.get(position);
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        //リストの一項目が読み込まれるたびにwearlevelは初期化される
        wearlevel = 0;


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

        wearlevel = level(item.isThick, item.hasDecoration);
        System.out.println("title" + item.title + "wearlevel:" + wearlevel + "isThick" + item.isThick + "hasDecoration" + item.hasDecoration);

        //set data
        viewHolder.titleTextView.setText(item.title);

        //ここから下に条件文書こうとしてる

        //天気がロードできるまで待つ
        if (weathers.isEmpty()) {
            return convertView;
        }

        if (weathers.get(0).equals("Rain")) {
            viewHolder.WashingIndexTextView.setText("0%");
            if (weathers.get(1).equals("Rain")) {
                viewHolder.RecomendedDayTextView.setText("明後日");
            } else {
                viewHolder.RecomendedDayTextView.setText("明日");
            }
        } else if (weathers.get(0).equals("Clouds")) {
            if (wearlevel == 0)
                viewHolder.WashingIndexTextView.setText("60%");
            if (wearlevel == 1)
                viewHolder.WashingIndexTextView.setText("50%");
            if (wearlevel == 2)
                viewHolder.WashingIndexTextView.setText("40%");
            viewHolder.RecomendedDayTextView.setText("今日");
        } else {
            if (wearlevel == 0 || wearlevel == 1)
                viewHolder.WashingIndexTextView.setText("100%");
            if (wearlevel == 2)
                viewHolder.WashingIndexTextView.setText("80%");
            viewHolder.RecomendedDayTextView.setText("今日");
        }

        return convertView;
    }

    public void setWeathers(List<String> weathers) {
        this.weathers = weathers;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView titleTextView;
        TextView RecomendedDayTextView;
        TextView WashingIndexTextView;
    }

    public int level(boolean isThick, boolean hasDecoration) {
        if (isThick)
            wearlevel++;
        if (hasDecoration)
            wearlevel++;
        return wearlevel;
    }

}
