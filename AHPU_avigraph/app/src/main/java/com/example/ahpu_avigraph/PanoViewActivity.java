package com.example.ahpu_avigraph;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.panoramaview.PanoramaView;
import com.baidu.lbsapi.panoramaview.PanoramaViewListener;

/**
 * 全景Activity
 */
public class PanoViewActivity extends Activity {


    private PanoramaView mPanoView;
    private double lat=0;
    private double lon = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PanoDemoApplication app = (PanoDemoApplication) this.getApplication();
        if (app.mBMapManager == null) {
            app.mBMapManager = new BMapManager(app);
            app.mBMapManager.init(new PanoDemoApplication.MyGeneralListener());
        }
        setContentView(R.layout.activity_pano_view);

        Intent intent=getIntent();

        int id= intent.getIntExtra("ID",0);

        mPanoView = (PanoramaView) findViewById(R.id.panorama);
        mPanoView.setPanoramaImageLevel(Locations.code);
        mPanoView.setShowTopoLink(true);


        lon=Locations.latLngList.get(id).longitude;
        lat=Locations.latLngList.get(id).latitude;

        mPanoView.setPanorama(lon, lat);
        mPanoView.setPanoramaViewListener(new PanoramaViewListener() {

            @Override
            public void onLoadPanoramaBegin() {
                Log.i("TAG", "onLoadPanoramaStart...");
            }

            @Override
            public void onLoadPanoramaEnd(String json) {
                Log.i("TAG", "onLoadPanoramaEnd : " + json);
            }

            @Override
            public void onLoadPanoramaError(String error) {
                Log.i("TAG", "onLoadPanoramaError : " + error);
            }

            @Override
            public void onDescriptionLoadEnd(String json) {

            }

            @Override
            public void onMessage(String msgName, int msgType) {

            }

            @Override
            public void onCustomMarkerClick(String key) {

            }

            @Override
            public void onMoveStart() {

            }

            @Override
            public void onMoveEnd() {

            }
        });


    }



    @Override
    protected void onPause() {
        super.onPause();
        mPanoView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPanoView.onResume();
    }

    @Override
    protected void onDestroy() {
        PanoDemoApplication app = (PanoDemoApplication) this.getApplication();
        mPanoView.destroy();
        super.onDestroy();
    }
}
