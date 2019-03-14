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
    //EditText isThickEditText;
    //EditText hasDecoration;
    SharedPreferences pref;

   OnClickListener _clickListener;

    int level = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View v = inflater.inflate(R.layout.activity_input, container, false);

        //titleの入力処理
        final EditText titleEditText = (EditText)v.findViewById(R.id.input_title);

        Button button = (Button)v.findViewById(R.id.save);

        pref = this.getActivity().getSharedPreferences("pref_title",MODE_PRIVATE);

        titleEditText.setText(pref.getString("key_title",""));

        //question1 Switch
       Switch s1 = (Switch)v.findViewById(R.id.question1);
       /* if(s1 != null){
            s1.setOnCheckedChangeListener(this);
        }*/
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked)
                  level++;
            }
        });

        //question2 Switch
        Switch s2 = (Switch)v.findViewById(R.id.question2);
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    level++;
            }
        });

        //保存ボタンが押されたとき
       button.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
              _clickListener.onClick();

               String titleText = titleEditText.getText().toString();

               SharedPreferences.Editor editor = pref.edit();
               editor.putString("key_title",titleText);
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
