package com.a_rin.tenki;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InputFragment.OnClickListener {

    HomeFragment homeFragment;
    InputFragment inputFragment;
    ClosetFragment closetFragment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        inputFragment = new InputFragment();
        closetFragment = new ClosetFragment();

        setFragment(homeFragment);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bar_home:
                        setFragment(homeFragment);
                        break;
                    case R.id.bar_input:
                        setFragment(inputFragment);
                        break;
                    case R.id.bar_closet:
                        setFragment(closetFragment);
                        break;
                }
                return false;
            }
        });
    }


    //Fragment表示のメソッド
    private void setFragment(Fragment fragment) {
        //レイアウトの切り替えを行う
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //どのレイアウトaに追加するか、すでにFragmentが存在すれば削除してから追加する
        ft.replace(R.id.container, fragment);
        //Fragmentの遷移の仕方
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //実際に表示
        ft.commit();
    }

    @Override
    public void onClick(Item item){
        Toast.makeText(this,"保存されました！",Toast.LENGTH_SHORT).show();

        // データを追加する
        //homeFragment.addItem(item);


        setFragment(homeFragment);
    }


}