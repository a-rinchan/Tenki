package com.a_rin.tenki;


import java.io.Serializable;
import java.util.List;

//MainActivityに並べる情報をまとめて管理、値の初期化
public class Item implements Serializable {

    String title;
    boolean isThick;
    boolean hasDecoration;

    Item(String title,boolean isThick,boolean hasDecoration){
        this.title = title;
        this.isThick = false;
        this.hasDecoration = false;
    }

}
