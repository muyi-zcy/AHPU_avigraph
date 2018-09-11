package com.example.ahpu_avigraph;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity {
    Toolbar toolbar;

    public LocationClient mLocationClient;
    private boolean isFirstLocate = true;

    private Context mContext;
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    private Marker mMarkerA;
    private Marker mMarkerB;

    ArrayList<Marker> markerList = new ArrayList<>();
    ArrayList<Overlay> textList = new ArrayList<>();

    private LatLng startPt,endPt;
    private double lat=0;
    private double lon = 0;


    private WalkNavigateHelper mWNaviHelper;
    WalkNaviLaunchParam walkParam;
    private static boolean isPermissionRequested = false;

    BitmapDescriptor bdA = BitmapDescriptorFactory
            .fromResource(R.drawable.a);
    BitmapDescriptor bdB = BitmapDescriptorFactory
            .fromResource(R.drawable.b);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());


        mContext = this;
        requestPermission();
        mMapView = (MapView) findViewById(R.id.mapview);
        initMapStatus();
        initOverlay();

        endPt = new LatLng(31.3493577815, 118.4186838903);

        walkParam = new WalkNaviLaunchParam();

        mBaiduMap.setMyLocationEnabled(true);
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(NavigationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(NavigationActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(NavigationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(NavigationActivity.this, permissions, 1);
        } else {
            requestLocation();
        }



        try {
            mWNaviHelper = WalkNavigateHelper.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMapStatus(){
        mBaiduMap = mMapView.getMap();
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(new LatLng(31.3415184040,118.4184639492)).zoom(19);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        mBaiduMap.showMapPoi(false);
        markerBuilder();
        textBuilder(Color.BLACK);

    }

    public void initOverlay() {

        LatLng llA = new LatLng(31.3415184040,118.4184639492);
        LatLng llB = new LatLng(31.3415184040,118.4184639492);

        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA)
                .zIndex(9).draggable(true);

        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
        mMarkerA.setDraggable(true);
        MarkerOptions ooB = new MarkerOptions()
                .position(llB)
                .icon(bdB)
                .zIndex(5);
        mMarkerB = (Marker) (mBaiduMap.addOverlay(ooB));
        mMarkerB.setDraggable(true);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                try {
                    int s = Integer.valueOf(marker.getExtraInfo().getInt("ID"));
                    endPt=Locations.latLngList.get(s);
                    walkParam.stPt(startPt).endPt(endPt);
                    startWalkNavi();
                }catch(Exception e)
                {
                    Toast.makeText(NavigationActivity.this,"出错！！！",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                return true;
            }
        });

        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {

            public void onMarkerDrag(Marker marker) {
            }

            public void onMarkerDragEnd(Marker marker) {
                if(marker == mMarkerA){
                    startPt = marker.getPosition();
                }else if(marker == mMarkerB){
                    endPt = marker.getPosition();
                }
                walkParam.stPt(startPt).endPt(endPt);
            }

            public void onMarkerDragStart(Marker marker) {
            }
        });
    }
    private void startWalkNavi() {
        try {
            mWNaviHelper.initNaviEngine(this, new IWEngineInitListener() {
                @Override
                public void engineInitSuccess() {
                    routePlanWithWalkParam();
                }

                @Override
                public void engineInitFail() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void routePlanWithWalkParam() {
        mWNaviHelper.routePlanWithParams(walkParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
            }
            @Override
            public void onRoutePlanSuccess() {
                Intent intent = new Intent();
                intent.setClass(NavigationActivity.this, WNaviGuideActivity.class);
                startActivity(intent);
            }
            @Override
            public void onRoutePlanFail(WalkRoutePlanError error) {
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {
            isPermissionRequested = true;
            ArrayList<String> permissions = new ArrayList<>();
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (permissions.size() == 0) {
                return;
            } else {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 0);
            }
        }
    }

    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        bdA.recycle();
        bdB.recycle();
    }

    //添加标识
    private void markerBuilder() {
        int i = 0;
        for (LatLng ll : Locations.latLngList) {
            BitmapDescriptor descriptor = BitmapDescriptorFactory
                    .fromResource(R.drawable.a);
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
            OverlayOptions overlayOptions = new TextOptions().fontSize(30).text(Locations.titleList.get(i)).position(Locations.latLngList.get(i)).typeface(Typeface.DEFAULT_BOLD).fontColor(color);
            textList.add(mBaiduMap.addOverlay(overlayOptions));
        }
    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            location.setCoorType("bd09ll");

            lon=location.getLongitude();
            lat=location.getLatitude();
            startPt = new LatLng(lat,lon);

            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            mBaiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.
                Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        mBaiduMap.setMyLocationData(locationData);
    }
    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }
        }

    }


    /*
        菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu doorMenu = menu.addSubMenu("校门&图书馆");
        for (int i=0;i<5;i++){
            doorMenu.add(0,Locations.latLngList.get(i).hashCode(),0,Locations.titleList.get(i));
        }
        SubMenu foodMenu = menu.addSubMenu("食堂");
        for (int i=20;i<25;i++){
            foodMenu.add(0,Locations.latLngList.get(i).hashCode(),0,Locations.titleList.get(i));
        }
        SubMenu jMenu = menu.addSubMenu("教学楼");
        for (int i=25;i<34;i++){
            jMenu.add(0,Locations.latLngList.get(i).hashCode(),0,Locations.titleList.get(i));
        }
        SubMenu zuoMenu = menu.addSubMenu("学院");
        for (int i=34;i<38;i++){
            zuoMenu.add(0,Locations.latLngList.get(i).hashCode(),0,Locations.titleList.get(i));
        }
        zuoMenu.add(0,Locations.latLngList.get(5).hashCode(),0,Locations.titleList.get(5));
        zuoMenu.add(0,Locations.latLngList.get(6).hashCode(),0,Locations.titleList.get(6));

        SubMenu nMenu = menu.addSubMenu("男寝");
        for (int i=48;i<66;i++){
            nMenu.add(0,Locations.latLngList.get(i).hashCode(),0,Locations.titleList.get(i));
        }
        SubMenu aMenu = menu.addSubMenu("女寝");
        for (int i=38;i<48;i++){
            aMenu.add(0,Locations.latLngList.get(i).hashCode(),0,Locations.titleList.get(i));
        }
        SubMenu yMenu = menu.addSubMenu("研究生宿舍");
        for (int i=67;i<70;i++){
            yMenu.add(0,Locations.latLngList.get(i).hashCode(),0,Locations.titleList.get(i));
        }
        SubMenu sMenu = menu.addSubMenu("生活");
        for (int i=12;i<20;i++){
            sMenu.add(0,Locations.latLngList.get(i).hashCode(),0,Locations.titleList.get(i));
        }
        sMenu.add(0,Locations.latLngList.get(70).hashCode(),0,Locations.titleList.get(70));
        SubMenu tMenu = menu.addSubMenu("操场");
        for (int i=7;i<12;i++){
            tMenu.add(0,Locations.latLngList.get(i).hashCode(),0,Locations.titleList.get(i));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i=0;
        for(i=0;i<71;i++){
            if(item.getItemId()==Locations.latLngList.get(i).hashCode()){
                try {
                    endPt=Locations.latLngList.get(i);
                    walkParam.stPt(startPt).endPt(endPt);
                    startWalkNavi();
                }catch(Exception e)
                {
                    Toast.makeText(NavigationActivity.this,"出错！！！",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPrepareOptionsPanel(view, menu);
    }
}
