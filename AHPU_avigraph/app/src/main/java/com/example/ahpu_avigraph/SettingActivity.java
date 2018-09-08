package com.example.ahpu_avigraph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.baidu.lbsapi.panoramaview.PanoramaView;
    /*
    设置
     */
public class SettingActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView feedback;
    Switch aSwitch;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_setting);

        feedback=(TextView)findViewById(R.id.feedback);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
         text=(TextView)findViewById(R.id.text);
         aSwitch=(Switch)findViewById(R.id.aswitch);
         aSwitch.setChecked(Locations.aswitch);

         if(Locations.aswitch){
             text.setText("省流");
         }else{
             text.setText("高清");
         }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent(SettingActivity.this,FeedbackActivity.class);
                startActivity(intent);
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean a) {
                if (a) {
                    Locations.code= PanoramaView.ImageDefinition.ImageDefinitionMiddle;
                    text.setText("省流");
                    Locations.aswitch=true;
                }else {
                    Locations.code= PanoramaView.ImageDefinition.ImageDefinitionHigh;
                    text.setText("高清");
                    Locations.aswitch=false;
                }
            }
        });
    }
}
