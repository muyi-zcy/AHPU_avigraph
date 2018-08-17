package com.example.ahpu_avigraph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {


    WebView show;
    String Url="http://47.100.36.214:8111/AHPU_avigraph/";

    private static final String APP_CACAHE_DIRNAME = "/webcache";//缓存网页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_map);
        show=(WebView)findViewById(R.id.show);

        show.getSettings().setJavaScriptEnabled(true);
// 设置可以支持缩放
        show.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        show.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
        show.getSettings().setUseWideViewPort(true);
//自适应屏幕
        show.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        show.getSettings().setLoadWithOverviewMode(true);


        show.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        show.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式
        // 开启 DOM storage API 功能
        show.getSettings().setDomStorageEnabled(false);
        //开启 database storage API 功能
        show.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME;
//      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;、
        //设置数据库缓存路径
//        mWebView.getSettings().setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        show.getSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        show.getSettings().setAppCacheEnabled(true);

        show.loadUrl(Url);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //显示版权提示
        Toast.makeText(MapActivity.this,"图片版权见图中，如有侵权联系删除！",Toast.LENGTH_LONG).show();
    }
}
