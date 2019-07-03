package com.a_rin.tenki;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class InputFragment extends Fragment {
    EditText titleEditText;
    Switch isThickBoolean;
    Switch hasDecorationBoolean;
    EditText contentEditText;
    SharedPreferences pref;

    OnClickListener _clickListener;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View v = inflater.inflate(R.layout.activity_input, container, false);



        //titleの入力処理
        final EditText titleEditText = (EditText)v.findViewById(R.id.input_title);
        final Switch isThickBoolean = (Switch)v.findViewById(R.id.question1);
        final Switch hasDecorationBoolean = (Switch) v.findViewById(R.id.question2);
        final EditText contentEditText = (EditText)v.findViewById(R.id.input_content);

        Button button = (Button)v.findViewById(R.id.save);

        //倉庫名を指定してsharedpreferencesの初期化を行う
        pref = this.getActivity().getSharedPreferences("pref_input",MODE_PRIVATE);

        //保存ボタンが押されたとき
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //読み込み
                ArrayList<Item> arrayList;
                SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
                Gson gson = new Gson();
                String json = pref.getString("data", "[]");
                if(json.equals("[]"))
                {
                    arrayList = new ArrayList<>();
                } else {
                    arrayList = gson.fromJson(json, new TypeToken<ArrayList<Item>>(){}.getType());
                }

                String titleText = titleEditText.getText().toString();
                String contentText = contentEditText.getText().toString();
                Boolean isThick = isThickBoolean.isChecked();
                Boolean hasDecoration = hasDecorationBoolean.isChecked();

                Item item = new Item(titleText, isThick, hasDecoration, contentText);

                arrayList.add(item);

                //書き込み
                pref.edit().putString("data", gson.toJson(arrayList)).apply();

                _clickListener.onClick(item);

            }
        });
        return v;
    }

    //FragmentをActivityに追加する際に呼ばれるメソッド
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            _clickListener = (OnClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement OnArticleSelectedListener.");
        }
    }

    public interface OnClickListener{
        void onClick(Item item);
    }
}