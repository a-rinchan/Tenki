package com.a_rin.tenki;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static android.content.Context.MODE_PRIVATE;

public class InputFragment extends Fragment {
    EditText titleEditText;
    //EditText isThickEditText;
    //EditText hasDecoration;
    SharedPreferences pref;

   // OnClickListener _clickListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View v = inflater.inflate(R.layout.activity_input, container, false);

        //titleの入力処理
        final EditText titleEditText = (EditText)v.findViewById(R.id.input_title);

        Button button = (Button)v.findViewById(R.id.save);

        pref = this.getActivity().getSharedPreferences("pref_title",MODE_PRIVATE);

        titleEditText.setText(pref.getString("key_title",""));

        //question1 Switch
       /* Switch s = (Switch)findViewById(R.id.question1);
        if(s != null){
            s.setOnCheckedChangeListener(this);
        }
        public void OnCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                int level = 0;
                level++;
            }
        }*/

       button.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
             //  _clickListener.onClick();

               String titleText = titleEditText.getText().toString();

               SharedPreferences.Editor editor = pref.edit();
               editor.putString("key_title",titleText);
               editor.commit();

               getActivity().finish();
           }
       });
        return v;
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            _clickListener = (OnClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement OnArticleSelectedListener.");
        }
    }*/

   public interface OnClickListener{
        void onClick();
   }
}
