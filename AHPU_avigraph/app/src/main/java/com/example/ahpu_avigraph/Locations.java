package com.example.ahpu_avigraph;

import com.baidu.lbsapi.panoramaview.PanoramaView;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;

public class Locations {
    //设置全景清晰度
    static boolean aswitch = true;
    static PanoramaView.ImageDefinition code=PanoramaView.ImageDefinition.ImageDefinitionMiddle;

    //校内建筑经纬度

    //安徽工程大学校门
    static final LatLng ahpu = new LatLng(31.3415184040,118.4184639492);
    //西门
    static final LatLng xi = new LatLng(31.3413397080,118.4149234333);
    //小北门
    static final LatLng xiaobei = new LatLng(31.3492203345,118.4171013870);
    //大北门
    static final LatLng bei = new LatLng(31.3494848091,118.4273295965);


    //安徽工程大学新图书馆
    static final LatLng library = new LatLng(31.3422927491,118.4181849995);

    //安徽工程大学生化学院/旧图书馆
    static final LatLng shenghuaxueyuan_0 = new LatLng(31.3445607648,118.4180079737);
    //安徽工程大学生化学院
    static final LatLng shenghuaxueyuan_1 = new LatLng(31.3441025842,118.4172408619);

    //体育馆
    static final LatLng t1 = new LatLng(31.3467370921,118.4207760134);
    //老操场
    static final LatLng t2 = new LatLng(31.3461414706,118.4190540352);
    //新操场
    static final LatLng t3 = new LatLng(31.3480107929,118.4197084942);
    //风雨操场
    static final LatLng t4 = new LatLng(31.3463018306,118.4199016132);
    //网球场
    static final LatLng t5 = new LatLng(31.3482719452,118.4192203321);

    //网络信息服务部&工程训练中心
    static final LatLng web = new LatLng(31.3498021918,118.4206740894);

    //师生活动中心
    static final LatLng shihuo = new LatLng(31.3485605865,118.4215002098);

    //师生服务中心
    static final LatLng r1 = new LatLng(31.3498788228,118.4257417288);

    //地下超市
    static final LatLng r2 = new LatLng(31.3485514233,118.4178202190);

    //浴室
    static final LatLng x0 = new LatLng(31.3460086007,118.4170584717);
    static final LatLng x1 = new LatLng(31.3487163607,118.4183888474);
    static final LatLng x2 = new LatLng(31.3493748516,118.4255325165);

    //学术报告厅
    static final LatLng xueshu = new LatLng(31.3445653466,118.4175841846);


    //食堂
    //安徽工程大学一食堂
    static final LatLng food_1 = new LatLng(31.3454175565,118.4171603956);

    //安徽工程大学二食堂
    static final LatLng food_2 = new LatLng(31.3462972489,118.4170423784);

    //安徽工程大学三/四食堂
    static final LatLng food_3 = new LatLng(31.3483589959,118.4177773037);
    //安徽工程大学五食堂
    static final LatLng food_5 = new LatLng(31.3493611068,118.4257524576);
    //安徽工程大学松庐餐厅
    static final LatLng food_6 = new LatLng(31.3426867921,118.4164308348);

    //教室
    //1J/电气学院
    static final LatLng j1 = new LatLng(31.3431220703,118.4174661675);
    //2J
    static final LatLng j2 = new LatLng(31.3433511632,118.4187268057);
    //3J
    static final LatLng j3 = new LatLng(31.3440338569,118.4186463394);
    //4J
    static final LatLng j4 = new LatLng(31.3426638827,118.4188340940);
    //5J
    static final LatLng j5 = new LatLng(31.3470611310,118.4237461653);
    //6J
    static final LatLng j6 = new LatLng(31.3479179015,118.4227912989);
    //7j
    static final LatLng j7 = new LatLng(31.3486967770,118.4240358438);
    //8j
    static final LatLng j8 = new LatLng(31.3492374044,118.4241431322);
    //9J
    static final LatLng j9 = new LatLng(31.3492236597,118.4235584106);
    //A座
    static final LatLng A = new LatLng(31.3436031648,118.4199445286);
    //B座
    static final LatLng B = new LatLng(31.3439513841,118.4208028355);
    //C座
    static final LatLng C = new LatLng(31.3442721113,118.4210710564);
    //D座
    static final LatLng D = new LatLng(31.3448035996,118.4208350220);



    //宿舍
    //女
    //1栋
    static final LatLng n1 = new LatLng(31.3468699609,118.4161733427);
    //2栋
    static final LatLng n2 = new LatLng(31.3472364948,118.4161143341);
    //3栋
    static final LatLng n3 = new LatLng(31.3476076089,118.4161036053);
    //4栋
    static final LatLng n4 = new LatLng(31.3479833031,118.4160606899);
    //5栋
    static final LatLng n5 = new LatLng(31.3482902717,118.4161036053);
    //6栋
    static final LatLng n6 = new LatLng(31.3466408765,118.4165703096);
    //7栋
    static final LatLng n7 = new LatLng(31.3460177642,118.4162752666);
    //8栋
    static final LatLng n8 = new LatLng(31.3474884860,118.4164952078);
    //9栋
    static final LatLng n9 = new LatLng(31.3469982479,118.4173964300);
    //10栋
    static final LatLng n10 = new LatLng(31.3465904779,118.4174125233);

    //男
    //1栋
    static final LatLng a1 = new LatLng(31.3456649708,118.4175090828);
    //2栋
    static final LatLng a2 = new LatLng(31.3461185621,118.4174822607);
    //5栋
    static final LatLng a5 = new LatLng(31.3474747410,118.4182869234);
    //6栋
    static final LatLng a6 = new LatLng(31.3474793227,118.4185658731);
    //10栋
    static final LatLng a10 = new LatLng(31.3478321093,118.4165166655);
    //11栋
    static final LatLng a11 = new LatLng(31.3490279085,118.4177504816);
    //12栋
    static final LatLng a12 = new LatLng(31.3493577815,118.4186838903);
    //13栋
    static final LatLng a13 = new LatLng(31.3494402495,118.4190272131);
    //14栋
    static final LatLng a14 = new LatLng(31.3496143485,118.4182869234);
    //15栋
    static final LatLng a15 = new LatLng(31.3497197241,118.4185390511);
    //16栋
    static final LatLng a16 = new LatLng(31.3498709149,118.4191881456);
    //17栋
    static final LatLng a17 = new LatLng(31.3500437582,118.4237461653);
    //18栋
    static final LatLng a18 = new LatLng(31.3496634899,118.4239929285);
    //19栋
    static final LatLng a19 = new LatLng(31.3500391767,118.4264283743);
    //20栋
    static final LatLng a20 = new LatLng(31.3496634899,118.4264283743);
    //21栋
    static final LatLng a21 = new LatLng(31.3492374044,118.4264283743);
    //22栋
    static final LatLng a22 = new LatLng(31.3488525513,118.4264820185);
    //25栋
    static final LatLng a25 = new LatLng(31.3476704926,118.4264712896);

    //研一
    static final LatLng y1 = new LatLng(31.3483819039,118.4164683857);
    //研二
    static final LatLng y2 = new LatLng(31.3486384736,118.4164737501);
    //研三
    static final LatLng y3 = new LatLng(31.3496680715,118.4234833088);
    //研四
    static final LatLng y4 = new LatLng(31.3500437582,118.4234886732);

    //校医院
    static final LatLng hospital = new LatLng(31.3430166873,118.4151648321);



    static ArrayList<LatLng> latLngList = new ArrayList<LatLng>() {
        {
            add(ahpu);
            add(xi);
            add(xiaobei);
            add(bei);
            add(library);
            add(shenghuaxueyuan_0);
            add(shenghuaxueyuan_1);
            add(t1);
            add(t2);
            add(t3);
            add(t4);
            add(t5);
            add(web);
            add(shihuo);
            add(r1);
            add(r2);
            add(x0);
            add(x1);
            add(x2);
            add(xueshu);
            add(food_1);
            add(food_2);
            add(food_3);
            add(food_5);
            add(food_6);
            add(j1);
            add(j2);
            add(j3);
            add(j4);
            add(j5);
            add(j6);
            add(j7);
            add(j8);
            add(j9);
            add(A);
            add(B);
            add(C);
            add(D);
            add(n1);
            add(n2);
            add(n3);
            add(n4);
            add(n5);
            add(n6);
            add(n7);
            add(n8);
            add(n9);
            add(n10);
            add(a1);
            add(a2);
            add(a5);
            add(a6);
            add(a10);
            add(a11);
            add(a12);
            add(a13);
            add(a14);
            add(a15);
            add(a16);
            add(a17);
            add(a18);
            add(a19);
            add(a20);
            add(a21);
            add(a22);
            add(a25);
            add(y1);
            add(y2);
            add(y3);
            add(y4);
            add(hospital);
        }
    };

    static ArrayList<String> titleList = new ArrayList<String>() {
        {
            add("安徽工程大学校门");
            add("西门&建行");
            add("小北门&优里");
            add("北门");
            add("图书馆");
            add("安徽工程大学生化学院");
            add("安徽工程大学生化学院");
            add("体育馆");
            add("老操场");
            add("新操场");
            add("风雨操场");
            add("网球场");
            add("网络信息服务部&工程训练中心");
            add("师生活动中心");
            add("师生服务中心");
            add("地下超市");
            add("浴室");
            add("浴室");
            add("东苑浴室");
            add("学术报告厅");
            add("一食堂");
            add("二食堂");
            add("三&四食堂");
            add("五食堂");
            add("松庐餐厅");
            add("1J/电气学院");
            add("2J");
            add("3J");
            add("4J");
            add("5J");
            add("6J");
            add("7J&艺术学院");
            add("8J&艺术学院");
            add("9J");
            add("A座&行政楼");
            add("B座&机械学院");
            add("C座&计院\\管院\\建工");
            add("D座&纺织服装学院");
            add("女1");
            add("女2");
            add("女3");
            add("女4");
            add("女5");
            add("女6");
            add("女7");
            add("女8");
            add("女9");
            add("女10");
            add("男1");
            add("男2");
            add("男5");
            add("男6");
            add("男10");
            add("男11");
            add("男12");
            add("男13");
            add("男14");
            add("男15");
            add("男16");
            add("男17");
            add("男18");
            add("男19");
            add("男20");
            add("男21");
            add("男22");
            add("男25");
            add("研一");
            add("研二");
            add("研三");
            add("研四");
            add("校医院");
        }
    };

}
