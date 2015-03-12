package com.example.blin.benlib;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lin on 2015/3/8.
 */
public class BenNetwork {



    public static class AsyncHttpTask extends AsyncTask<String, Void, Integer> {
        private static final String TAG = "Http Connection";

        private ArrayAdapter arrayAdapter = null;
        private Context mcontext;
        private TextView ED1;
        public String blogTitles[];


        public AsyncHttpTask(Context mcontext,TextView ED1) {
            this.mcontext = mcontext;
            this.ED1=ED1;
        }
        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;

            HttpURLConnection urlConnection = null;
            Log.i("TAG","Backgroud");
            Integer result = 0;
            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                 /* optional request header */
                urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
                urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
                urlConnection.setRequestMethod("GET");

                int statusCode = urlConnection.getResponseCode();
                Log.i("TAG",Integer.toString(statusCode) );
                /* 200 represents HTTP OK */
                if (statusCode ==  200) {

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    String response = convertInputStreamToString(inputStream);
                    blogTitles=parseResult(response);
                    Log.i("TAG","Parse over!");
                    result = 1; // Successful

                }else{
                    result = 0; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return result; //"Failed to fetch data!";
        }


        @Override
        protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */
            Log.i("TAG","onPostExecut");
            if(result == 1){

//                arrayAdapter = new ArrayAdapter(mcontext, android.R.layout.simple_list_item_1, blogTitles);

//                listView.setAdapter(arrayAdapter);
                ED1.setText(blogTitles[0]);
//                ED1.setText("OK");
            }else{
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";
        Log.i("TAG","convertInputStreamToString");
        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }
    private static String[] parseResult(String result) {
             String s1[]=null;
        Log.i("TAG","Parse");
        try{
            JSONObject response = new JSONObject(result);

            JSONArray posts = response.optJSONArray("posts");

            s1= new String[posts.length()];

            for(int i=0; i< posts.length();i++ ){
                JSONObject post = posts.optJSONObject(i);
                String title = post.optString("title");

                s1[i] = title;
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return s1;
    }

    public void toggleWiFi(Context mcontext,boolean status) {
        WifiManager wifiManager = (WifiManager) mcontext
                .getSystemService(Context.WIFI_SERVICE);
        if (status == true && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (status == false && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

}



