package com.a_rin.tenki;


import android.widget.ImageView;
import java.io.Serializable;
import java.util.List;

//MainActivityに並べる情報をまとめて管理、値の初期化
public class Item implements Serializable {

    String title;
    boolean isThick;
    boolean hasDecoration;
    String content;

    Item(String title,boolean isThick,boolean hasDecoration,String content){
        this.title = title;
        this.isThick = isThick;
        this.hasDecoration = hasDecoration;
        this.content = content;
    }

    public boolean equals(Object o){
        if(o == null)
            return false;
        else if(o == this)
            return true;
        Item i = (Item)o;
        return this.title.equals(i.title) && this.isThick == i.isThick && this.hasDecoration == i.hasDecoration && this.content.equals(i.content);
    }
}

