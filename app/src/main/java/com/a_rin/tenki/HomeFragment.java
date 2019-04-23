package com.a_rin.tenki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements TenkiApi.OnPostEnded{
    ItemAdapter itemAdapter;
    ListView listView;

    ImageView today;
    ImageView tomorrow;
    ImageView aftertomorrow;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        //第二引数のcontainerはFragmentのレイアウトが挿入される親のViewgroupのこと
        //第三引数のBundleはフラグメントが再開された場合にフラグメント前のインスタンスに関する情報を提供する

        //FragmentのサブクラスであるHomeFragmentがfragment_home.xmlファイルからレイアウトを読み込む
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //inflate()は三つの引数を受け取る
        //第一引数レイアウトのリソースID
        //第二引数inflateされた親のViewGroup
        //第三引数インフレート中にインフレートされたレイアウトを第二引数のViewGroupにアタッチするべきか示すブール値

        today = (ImageView)v.findViewById(R.id.today);
        tomorrow = (ImageView)v.findViewById(R.id.tomorrow);
        aftertomorrow = (ImageView)v.findViewById(R.id.aftertomorrow);

        listView = (ListView) v.findViewById(R.id.listView);
        //リストがクリックされた時にDetailに移動するためにIntentする
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                //第一引数は出発地点　第二引数は遷移させたいところ
                //値を渡す処理　変数positionはlistの何番目にあるものかを識別するためのもの
                intent.putExtra("item", itemAdapter.getItem(position));
                startActivity(intent);
            }
        });

        itemAdapter = new ItemAdapter(getContext(), 0, new ArrayList<Item>());
        //listのデータをAdapterに渡す
        listView.setAdapter(itemAdapter);

        itemAdapter.addAll(getSampleData());




            // 非同期処理(AsyncHttpRequest#doInBackground())を呼び出す
            try {
                new TenkiApi(this).execute(new URL("https://api.openweathermap.org/data/2.5/forecast?q=tokyo,jp&units=metric&lang=ja&appid=c5c383b509e6de81f869dd20323ecf80"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        return v;

    }

    public List<Item> getSampleData() {
        List<Item> items = new ArrayList<>();

        items.add(new Item("衿レースブラウス",false,true,"2019.03 \nParavion"));
        items.add(new Item("ティアードスカート",false,false,"2019.01 \nHeather"));
        items.add(new Item("バルーントレンチコート",true,true,"Rili Shopping"));
        items.add(new Item("白のvネックニット",true,true,"2018 Winter"));
        items.add(new Item("ワンピース",false,true,"2018.08\nParavion"));
        items.add(new Item("クルーネックニットピンク",true,false,"2018.01"));
        items.add(new Item("アニマル柄スカート",true,true,"who is chico"));
        items.add(new Item("黒タートル",false,false,""));
        items.add(new Item("スキニージーンズ",true,false,""));
        items.add(new Item("台形スカート",false,false,"2018 Spring"));

        //ArrayList<Item> arrayList;
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("data", "[]");
        if(json.equals("[]")) {
            // nothing
        } else {
            items.addAll((List<Item>)gson.fromJson(json, new TypeToken<ArrayList<Item>>(){}.getType()));
        }

        return items;
    }

    @Override
    public void onGetWeather(List<String> result){
        itemAdapter.setWeathers(result);
        if (result.get(0).equals("Rain")){
           today.setBackgroundResource(R.drawable.rainy);
        } else if(result.get(0).equals("Clouds")) {
            today.setBackgroundResource(R.drawable.cloudy);
        }else{
            today.setBackgroundResource(R.drawable.sunny);
        }

        if (result.get(1).equals("Rain")){
            tomorrow.setBackgroundResource(R.drawable.rainy);
        } else if(result.get(1).equals("Clouds")){
            tomorrow.setBackgroundResource(R.drawable.cloudy);
        }else{
            tomorrow.setBackgroundResource(R.drawable.sunny);
        }

        if (result.get(2).equals("Rain")){
            aftertomorrow.setBackgroundResource(R.drawable.rainy);
        } else if(result.get(2).equals("Clouds")){
            aftertomorrow.setBackgroundResource(R.drawable.cloudy);
        }else{
            aftertomorrow.setBackgroundResource(R.drawable.sunny);
        }
    }



}
