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

        //起動時にsharedpreferencesに保存されている内容を表示
        titleEditText.setText(pref.getString("key_title",""));
        contentEditText.setText(pref.getString("key_content",""));

        //保存ボタンが押されたとき
       button.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
              _clickListener.onClick();

               String titleText = titleEditText.getText().toString();
               String contentText = contentEditText.getText().toString();

               SharedPreferences.Editor editor = pref.edit();
               editor.putString("key_title",titleText);
               editor.putBoolean("key_isThick",false);
               editor.putBoolean("key_hasDecoration",false);
               editor.putString("key_content",contentText);
               editor.commit();

               getActivity().finish();
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
        void onClick();
   }
}
