package com.a_rin.tenki;


import java.io.Serializable;

//MainActivityに並べる情報をまとめて管理、値の初期化
public class Item implements Serializable {

    String title;
    String date;
    Double percent;

    Item(String title,String date,Double percent){
        this.title = title;
        this.date = date;
        this.percent = percent;
    }
}
