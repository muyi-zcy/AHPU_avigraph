package com.example.ahpu_avigraph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_setting);

        feedback=(TextView)findViewById(R.id.feedback);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener(){   //前往注册
            @Override
            public void onClick(View v){
                Intent intent =new Intent(SettingActivity.this,FeedbackActivity.class);
                startActivity(intent);
            }
        });
    }
}
