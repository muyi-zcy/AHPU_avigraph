package com.example.ahpu_avigraph;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    Intent it;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_start);

        link();

        textView=(TextView)findViewById(R.id.text);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"font/STHUPO.TTF"));

        it = new Intent(this, MenuActivity.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(it);
                finish();               //销毁当前页面
            }
        };
        timer.schedule(task, 1000 * 3);//模拟启动界面的运行时间为3秒
    }
    private void link() {
        String imei = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        String imeiUrlStr = "http://47.100.36.214:8111/AHPU_avigraph/ImeiServlet?imei=" + imei;
        Imei_AsyncTask feedback_asyncTask =new Imei_AsyncTask();
        feedback_asyncTask.execute(imeiUrlStr);
    }


    class Imei_AsyncTask extends AsyncTask<String, Integer, String> {
        public Imei_AsyncTask() {
        }
        @Override
        public void onPreExecute() {
        }
        @Override
        public String doInBackground(String... params) {
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(80000);
                connection.setReadTimeout(80000);
                BufferedReader reader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString();
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
        }
        @Override
        protected void onPostExecute(String s) {

        }
    }
}
