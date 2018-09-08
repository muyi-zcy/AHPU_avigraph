package com.example.ahpu_avigraph;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;


    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Context mContext;

    ArrayList<Marker> markerList = new ArrayList<>();
    ArrayList<Overlay> textList = new ArrayList<>();

    //构建MarkerOption，用于在地图上添加Marker
    private LatLng point;

    private final double latitude = 31.3415504776;
    private final double longitude = 118.4184424915;

   private final String baseUrl = "http://pcsv1.map.bdimg.com/scape/?qt=pdata&pos=0_0&z=0&sid=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this,"因为不可抗因素，部分全景图无法准确获得！",Toast.LENGTH_LONG).show();
        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mContext = this;
   //     handler = new myHandler();
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //定义Maker坐标点
        point = new LatLng(latitude, longitude);

        //定义地图状态
        final MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(18)
                .build();


        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        markerBuilder();

        textBuilder(Color.BLACK);

        //设置监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                try {
                    Intent intent = new Intent(mContext, PanoViewActivity.class);
                    int s = Integer.valueOf(marker.getExtraInfo().getInt("ID"));
                    intent.putExtra("ID", s);
                    startActivity(intent);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    //添加标识
    private void markerBuilder() {
        int i = 0;
        for (LatLng ll : Locations.latLngList) {
            BitmapDescriptor descriptor = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_markc);
            MarkerOptions markerOptions = new MarkerOptions().position(ll).icon(descriptor);
            markerOptions.animateType(MarkerOptions.MarkerAnimateType.grow);
            Marker marker = (Marker) mBaiduMap.addOverlay(markerOptions);
            Bundle bundle = new Bundle();
            bundle.putInt("ID", i++);
            marker.setExtraInfo(bundle);
            markerList.add(marker);
        }

    }

    //添加文字标识
    private void textBuilder(int color) {
        for (int i = 0; i < Locations.titleList.size(); i++) {
            OverlayOptions overlayOptions = new TextOptions().fontSize(25).text(Locations.titleList.get(i)).position(Locations.latLngList.get(i)).typeface(Typeface.DEFAULT_BOLD).fontColor(color);
            textList.add(mBaiduMap.addOverlay(overlayOptions));
        }
    }


}