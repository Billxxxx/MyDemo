//package com.arsenal.bill;
//
//import static java.lang.Math.atan2;
//import static java.lang.Math.cos;
//import static java.lang.Math.sin;
//import static java.lang.Math.sqrt;
//
///**
// * 根据values/dimens.xml, 自动计算比例并生成不同分辨率的dimens.xml
// * 注意用dp和sp，不要用dip，否则生成可能会出错；xml值不要有空格
// * Created by zhangxitao on 15/9/22.
// */
//public class DimenTool {
//
//    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
//
//    public static void main(String[] args) {
//        map_bd2tx(23.134079, 114.426836);
////        System.out.print("hello");
//    }
//
//    public static LatLng map_bd2tx(double lat, double lon) {
//        System.out.print("input " + String.valueOf(lat) + " " + String.valueOf(lon) + "\n");
//        double tx_lat;
//        double tx_lon;
//        double x = lon - 0.0065, y = lat - 0.006;
//        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * x_pi);
//
//        System.out.print("z = " + String.valueOf(z) + "\n");
//        double theta = atan2(y, x) - 0.000003 * cos(x * x_pi);
//        tx_lon = z * cos(theta);
//        tx_lat = z * sin(theta);
//
//
//        LatLng latLng = new LatLng();
//        latLng.lat = tx_lat;
//        latLng.lon = tx_lon;
//        System.out.print("output " + String.valueOf(tx_lat) + " " + String.valueOf(tx_lon));
//
////                .lat(tx_lat)
////                .lon(tx_lon)
////                .build();
//        return latLng;
//    }
//
//    static class LatLng {
//
//        double lat, lon;
//
//    }
////    /**
////     * 将百度坐标转变成火星坐标
////     * @param lngLat_bd 百度坐标（百度地图坐标）
////     * @return 火星坐标(高德、腾讯地图等)
////     */
////    static LatLng bd_decrypt(LatLng lngLat_bd)
////    {
////        double x = lngLat_bd.lon - 0.0065, y = lngLat_bd.lat - 0.006;
////        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * x_pi);
////        double theta = atan2(y, x) - 0.000003 * cos(x * x_pi);
////
////        return new LngLat( dataDigit(6,z * cos(theta)),dataDigit(6,z * sin(theta)));
////
////    }
//
//}