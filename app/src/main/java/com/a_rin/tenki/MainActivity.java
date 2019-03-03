package com.a_rin.tenki;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bar_home:
                        setFragment(new HomeFragment());
                        break;
                    case R.id.bar_input:
                        setFragment(new InputFragment());
                        break;
                    case R.id.bar_closet:
                        setFragment(new ClosetFragment());
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
        //どのレイアウトに追加するか、すでにFragmentが存在すれば削除してから追加する
        ft.replace(R.id.container, fragment);
        //Fragmentの遷移の仕方
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //実際に表示
        ft.commit();
    }
}