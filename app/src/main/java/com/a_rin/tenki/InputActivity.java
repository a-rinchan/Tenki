package com.a_rin.tenki;

import android.os.Bundle;
import android.widget.EditText;

public class InputActivity {
    EditText titleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        EditText titleEditText = (EditText)findViewById(R.id.title);
    }

}
