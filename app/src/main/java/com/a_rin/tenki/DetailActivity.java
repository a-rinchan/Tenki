package com.a_rin.tenki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class DetailActivity extends AppCompatActivity {

    Item item;
    TextView title;
    TextView content;
    SharedPreferences pref;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        title = (TextView)findViewById(R.id.detail_title);
        content = (TextView)findViewById(R.id.content);


        //前の画面からデータを取得する
        item = (Item) getIntent().getSerializableExtra("item");


        //前文でItemから受け取ったデータをitemに入れて情報は持ってるから、ViewにsetTextして表示する
        title.setText(item.title);
        content.setText(item.content);

        //倉庫名を指定してsharedpreferencesの初期化を行う
        pref = this.getSharedPreferences("pref_input",MODE_PRIVATE);

        //アクションバーに戻る機能をつける
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(item.title);
        }
    }

    //前の画面に戻る処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //データ削除ボタン
    public void remove(View v){
        //読み込み
        ArrayList<Item> arrayList;
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("data", "[]");
        if(json.equals("[]"))
        {
            arrayList = new ArrayList<>();
        } else {
            arrayList = gson.fromJson(json, new TypeToken<ArrayList<Item>>(){}.getType());
        }

        String titleText = item.title;
        String contentText = item.content;
        Boolean isThick = item.isThick;
        Boolean hasDecoration = item.hasDecoration;

        Item item = new Item(titleText, isThick, hasDecoration, contentText);

        arrayList.remove(item);

        //書き込み
        pref.edit().putString("data", gson.toJson(arrayList)).apply();

        System.out.println(pref.getString("data", gson.toJson(arrayList)));

        finish();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
