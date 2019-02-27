package com.a_rin.tenki;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ButtomNavigationView bnv = (ButtomNavigationView)findViewById(R.id.navigation);
       bnv.setOnNavigationItemSelectedListener(new ButtonNavigationView.OnNavigationItemSelectedListener(){
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()) {
                   case R.id.bar_home:
                       setFragment();
                       return true;
                   case R.id.bar_input:
                       setFragment();
                       return true;
                   case R.id.bar_closet:
                       setFragment();
                       return true;
               }
               return false;
           }
       }
    })


}
