package com.example.ahpu_avigraph;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MenuActivity extends AppCompatActivity {

    ImageView navigation;
    ImageView panorama;
    ImageView setting;
    ImageView map;
    ImageView logo;

    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_menu);

        textview=(TextView)findViewById(R.id.text);
        a=(TextView)findViewById(R.id.a);
        b=(TextView)findViewById(R.id.b);
        c=(TextView)findViewById(R.id.c);
        d=(TextView)findViewById(R.id.d);

        navigation=(ImageView)findViewById(R.id.navigation);// 进入导航
        panorama=(ImageView)findViewById(R.id.panorama);//进入全景
        setting=(ImageView)findViewById(R.id.setting);//进入设置
        map=(ImageView)findViewById(R.id.map);//进入校园地图
        logo=(ImageView)findViewById(R.id.logo);

        textview.setTypeface(Typeface.createFromAsset(getAssets(),"font/STHUPO.TTF"));
        a.setTypeface(Typeface.createFromAsset(getAssets(),"font/STHUPO.TTF"));
        b.setTypeface(Typeface.createFromAsset(getAssets(),"font/STHUPO.TTF"));
        c.setTypeface(Typeface.createFromAsset(getAssets(),"font/STHUPO.TTF"));
        d.setTypeface(Typeface.createFromAsset(getAssets(),"font/STHUPO.TTF"));



        //加载图片
        Glide.with(this)
                .load(StartActivity.url)
                .into(logo);


        setting.setOnClickListener(new View.OnClickListener(){   //前往注册
            @Override
            public void onClick(View v){
                Intent intent =new Intent(MenuActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        panorama.setOnClickListener(new View.OnClickListener(){   //前往全景
            @Override
            public void onClick(View v){
                Intent intent =new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        navigation.setOnClickListener(new View.OnClickListener(){   //前往导航
            @Override
            public void onClick(View v){
                Intent intent =new Intent(MenuActivity.this,NavigationActivity.class);
                startActivity(intent);
            }
        });

        map.setOnClickListener(new View.OnClickListener(){   //前往导航
            @Override
            public void onClick(View v){
                Intent intent =new Intent(MenuActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
