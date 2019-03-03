package com.a_rin.tenki;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup conttainer, Bundle bundle) {
        View v = inflater.inflate(R.layout.fragment_home, conttainer, false);
        return v;
    }
}
