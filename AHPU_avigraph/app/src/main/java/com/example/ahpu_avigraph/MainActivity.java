package com.example.ahpu_avigraph;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.lbsapi.model.BaiduPoiPanoData;
import com.baidu.lbsapi.panoramaview.PanoramaRequest;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
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
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView snackbar;


    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Context mContext;

    ArrayList<Marker> markerList = new ArrayList<>();
    ArrayList<Overlay> textList = new ArrayList<>();
    //构建MarkerOption，用于在地图上添加Marker
    private LatLng point;
    private myHandler handler;
    //构建Marker图标
    private BitmapDescriptor bitmap;
    //构建MarkerOption，用于在地图上添加Marker
    private OverlayOptions option;
    private final double latitude = 31.3415504776;
    private final double longitude = 118.4184424915;

    String uid = "e13a4326c3460e0b29fc48ad";//安徽工程大学UID
    //添加自定义View至mapView
    private View view;
    private ImageView pic;
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


        snackbar=(TextView)findViewById(R.id.snackbar);

        mContext = this;
        handler = new myHandler();
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

        //构建Marker图标
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_markc);
        mBaiduMap.showMapPoi(false);
        //构建MarkerOption，用于在地图上添加Marker
        option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);

        markerBuilder();
        textBuilder(Color.BLACK);
        view = LayoutInflater.from(mContext).inflate(R.layout.pano_overlay, null);
        pic = (ImageView) view.findViewById(R.id.panoImageView);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, PanoViewActivity.class);
//                intent.putExtra("latitude", latitude);
//                intent.putExtra("longitude", longitude);
//                startActivity(intent);
//            }
//        });

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                try {
                    Intent intent = new Intent(mContext, PanoViewActivity.class);
//                    String s = String.valueOf(marker.getExtraInfo().get("ID"));
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                PanoramaRequest request = PanoramaRequest.getInstance(mContext);
                BaiduPoiPanoData poiPanoData = request.getPanoramaInfoByUid(uid);
                //开发者可以判断是否有外景(街景)
                if (poiPanoData.hasStreetPano()) {
                    String url = baseUrl + poiPanoData.getPid();
                    Message message = new Message();
                    message.what = 0x01;
                    message.obj = url;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    private class myHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x01) {
                String url = (String) msg.obj;
                Glide.with(mContext).load(url).into(pic);
                InfoWindow mInfoWindow = new InfoWindow(view, point, -57);
                //显示InfoWindow
                mBaiduMap.showInfoWindow(mInfoWindow);
            }
        }
    }


    //添加标识
    private void markerBuilder() {
        int i = 0;
        for (LatLng ll : Locations.latLngList) {
            BitmapDescriptor descriptor = BitmapDescriptorFactory
                    .fromResource(R.drawable.local);
            MarkerOptions markerOptions = new MarkerOptions().position(ll).icon(descriptor);
            markerOptions.animateType(MarkerOptions.MarkerAnimateType.grow);
            Marker marker = (Marker) mBaiduMap.addOverlay(markerOptions);
            Bundle bundle = new Bundle();
//            bundle.putString("ID", (i++)+"");
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