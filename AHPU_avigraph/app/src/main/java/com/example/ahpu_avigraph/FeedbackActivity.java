package com.example.ahpu_avigraph;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FeedbackActivity extends AppCompatActivity {

    EditText number;
    EditText feedback;
    Button button;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_feedback);

        number=(EditText)findViewById(R.id.number);
        feedback=(EditText)findViewById(R.id.feedback);
        button=(Button)findViewById(R.id.button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(number.getText().toString().isEmpty())
                        && !(feedback.getText().toString().isEmpty())) {
                    link(number.getText().toString(), feedback.getText().toString());
                } else {
                    Toast.makeText(FeedbackActivity.this, "联系方式和建议\\反馈都不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void link(String number, String feedback) {
        String feedbackUrlStr = "http://47.100.36.214:8111/AHPU_avigraph/FeedbackServlet?number="
                + number + "&feedback=" + feedback;
        Feedback_AsyncTask feedback_asyncTask =new Feedback_AsyncTask();
        feedback_asyncTask.execute(feedbackUrlStr);
    }


    class Feedback_AsyncTask extends AsyncTask<String, Integer, String> {
        public Feedback_AsyncTask() {
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
            switch (s){
                //判断返回的状态码，并把对应的说明显示在UI
                case "100":
                    Toast.makeText(FeedbackActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
                    break;
                case "200":
                    Toast.makeText(FeedbackActivity.this, "提交失败！", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(FeedbackActivity.this, "后台傻掉了！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
