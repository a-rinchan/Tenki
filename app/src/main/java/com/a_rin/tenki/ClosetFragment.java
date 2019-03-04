package com.a_rin.tenki;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClosetFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View v = inflater.inflate(R.layout.fragment_closet,container,false);
        return v;
    }
}
