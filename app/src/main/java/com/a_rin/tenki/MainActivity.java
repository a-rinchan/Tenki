package com.a_rin.tenki;

import android.app.FragmentTransaction;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bar_home:
                        setFragment(new homeFragment());
                        break;
                    case R.id.bar_input:
                        setFragment(new inputFragment());
                        break;
                    case R.id.bar_closet:
                        setFragment(new closetFragment());
                        break;
                }
                return false;
            }
        });


    }

    //Fragment表示のメソッド
    private void setFragment(Fragment fragment) {
    //レイアウトの切り替えを行う
        FragmentTransaction ft = getFragmentManager().beginTransaction();
    //どのレイアウトに追加するか、すでにFragmentが存在すれば削除してから追加する
        ft.replace(R.id.coordinator, fragment);
    //Fragmentの遷移の仕方
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    //実際に表示
        ft.commit();
    }

    public class homeFragment extends Fragment {
        homeFragment() {
        }
        public View onCreateView(LayoutInflater inflater, ViewGroup conttainer, Bundle bundle) {
            View v = inflater.inflate(R.layout.activity_main, conttainer, false);
            return v;
        }
    }

    public class inputFragment extends Fragment {
        inputFragment() {
        }
        public View onCreateView(LayoutInflater inflater, ViewGroup conttainer, Bundle bundle) {
            View v = inflater.inflate(R.layout.activity_input, conttainer, false);
            return v;
        }
    }

    public class closetFragment extends Fragment {
        closetFragment() {
        }
        public View onCreateView(LayoutInflater inflater, ViewGroup conttainer, Bundle bundle) {
            View v = inflater.inflate(R.layout.activity_closet, conttainer, false);
            return v;
        }
    }
}