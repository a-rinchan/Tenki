package com.a_rin.tenki;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.support.v7.app.AppCompatActivity;
import static android.content.Context.MODE_PRIVATE;

public class InputActivity extends appCompatActivity{
    EditText titleEditText;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //titleの入力処理
        EditText titleEditText = (EditText)findViewById(R.id.title);

        pref = getSharedPreferences("pref_title",MODE_PRIVATE);
        titleEditText.setText(pref.getString("key_title",""));

        //question1 Switch
        Switch s = (Switch)findViewById(R.id.question1);
        if(s != null){
            s.setOnCheckdChangeListener(this);
        }
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                int level = 0;
                level++;
            }
        }

    }

    //入力されたtitleを保存
    public void save(View v){
        String titleText = titleEditText.getText().toString();

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("key_title",titleText);
        editor.commit();

        finish();
    }
}
