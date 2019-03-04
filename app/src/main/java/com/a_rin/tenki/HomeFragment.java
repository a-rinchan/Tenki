package com.a_rin.tenki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ItemAdapter itemAdapter;
    ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup conttainer, Bundle bundle) {
        //第二引数のcontainerはFragmentのレイアウトが挿入される親のViewgroupのこと
        //第三引数のBundleはフラグメントが再開された場合にフラグメント前のインスタンスに関する情報を提供する

        //FragmentのサブクラスであるHomeFragmentがfragment_home.xmlファイルからレイアウトを読み込む
        View v = inflater.inflate(R.layout.fragment_home, conttainer, false);
        //inflate()は三つの引数を受け取る
        //第一引数レイアウトのリソースID
        //第二引数inflateされた親のViewGroup
        //第三引数インフレート中にインフレートされたレイアウトを第二引数のViewGroupにアタッチするべきか示すブール値


        listView = (ListView) v.findViewById(R.id.listView);
        //リストがクリックされた時にDetailに移動するためにIntentする
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                //第一引数は出発地点　第二引数は遷移させたいところ
                //値を渡す処理　変数positionはlistの何番目にあるものかを識別するためのもの
                intent.putExtra("item", itemAdapter.getItem(position));
                startActivity(intent);
            }
        });

        itemAdapter = new ItemAdapter(getContext(), 0, new ArrayList<Item>());
        //listのデータをAdapterに渡す
        listView.setAdapter((ListAdapter) itemAdapter);

        itemAdapter.addAll(getSampleData());

            // 非同期処理(AsyncHttpRequest#doInBackground())を呼び出す
            try {
                new TenkiApi(getActivity()).execute(new URL("http://weather.livedoor.com/forecast/webservice/json/v1?city=270000"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        return v;

    }

    public List<Item> getSampleData() {
        List<Item> items = new ArrayList<>();

        items.add(new Item("タイトル1",false,false));
        items.add(new Item("タイトル2",true,true));
        items.add(new Item("タイトル3",false,true));
        items.add(new Item("タイトル4",true,false));

        return items;
    }


}
