package com.a_rin.tenki;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TenkiApi extends AsyncTask<URL, Void, List<String>>{

    private int TODAY_FORCAST_INDEX = 0;
    private Activity mainActivity;

    public TenkiApi(Activity activity) {
        // 呼び出し元のアクティビティ
        this.mainActivity = activity;
    }

    /**
     * 非同期処理で天気情報を取得する.
     * @param urls 接続先のURL
     * @return 取得した天気情報
     */
    @Override
    protected List<String> doInBackground(URL... urls) {

        final URL url = urls[0];
        HttpURLConnection con = null;

        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            // リダイレクトを自動で許可しない設定
            con.setInstanceFollowRedirects(false);
            con.connect();

            final int statusCode = con.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.err.println("正常に接続できていません。statusCode:" + statusCode);
                return null;
            }

            // レスポンス(JSON文字列)を読み込む準備
            final InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            if(null == encoding){
                encoding = "UTF-8";
            }
            final InputStreamReader inReader = new InputStreamReader(in, encoding);
            final BufferedReader bufReader = new BufferedReader(inReader);
            StringBuilder response = new StringBuilder();
            String line = null;
            // 1行ずつ読み込む
            while((line = bufReader.readLine()) != null) {
                response.append(line);
            }
            bufReader.close();
            inReader.close();
            in.close();


            // 受け取ったJSON文字列をパース
            ArrayList<String> objects = new ArrayList<>();
            JSONArray jsonArray = new JSONObject(response.toString()).getJSONArray("list");
            for(int i = 0; i < jsonArray.length(); i++){
                String dtString = jsonArray.getJSONObject(i).getString("dt_txt");
                if(dtString.indexOf("12:00:00") > 0){
                    JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0);
                    objects.add(jsonObject.getString("main"));
                }
            }

            return objects;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    //ここに天気に合わせてお天気画像返す、条件書く
    protected void onPostExecute(List<String> result) {

        if (result.get(0).equals("Rain")){
            mainActivity.findViewById(R.id.today).setBackgroundResource(R.drawable.rainy);
        } else if(result.get(0).equals("Clouds")) {
            mainActivity.findViewById(R.id.today).setBackgroundResource(R.drawable.cloudy);
        }else{
            mainActivity.findViewById(R.id.today).setBackgroundResource(R.drawable.sunny);
        }

        if (result.get(1).equals("Rain")){
            mainActivity.findViewById(R.id.tomorrow).setBackgroundResource(R.drawable.rainy);
        } else if(result.get(1).equals("Clouds")){
            mainActivity.findViewById(R.id.tomorrow).setBackgroundResource(R.drawable.cloudy);
        }else{
            mainActivity.findViewById(R.id.tomorrow).setBackgroundResource(R.drawable.sunny);
        }

        if (result.get(2).equals("Rain")){
            mainActivity.findViewById(R.id.aftertomorrow).setBackgroundResource(R.drawable.rainy);
        } else if(result.get(2).equals("Clouds")){
            mainActivity.findViewById(R.id.aftertomorrow).setBackgroundResource(R.drawable.cloudy);
        }else{
            mainActivity.findViewById(R.id.aftertomorrow).setBackgroundResource(R.drawable.sunny);
        }

    }

}
