package com.a_rin.tenki;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Item item;
    TextView title;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        title = (TextView)findViewById(R.id.detail_title);

        //前の画面からデータを取得する
        item = (Item) getIntent().getSerializableExtra("item");

        //前文でItemから受け取ったデータをitemに入れて情報は持ってるから、ViewにsetTextして表示する
        title.setText(item.title);

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
                return super.onOptionsItemSelected(item);
        }
    }
}