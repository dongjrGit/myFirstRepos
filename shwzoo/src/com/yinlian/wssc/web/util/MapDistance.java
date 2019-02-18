package com.yinlian.wssc.web.util;

import java.util.HashMap;
import java.util.Map;

public class MapDistance {

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param lat1 用户纬度
     * @param lng1 用户经度
     * @param lat2 商家经度
     * @param lng2 商家经度
     * @return
     */
    public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                                                  + Math.cos(radLat1) * Math.cos(radLat2)
                                                  * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance + "";
        distanceStr = distanceStr.substring(0, distanceStr.indexOf("."));

        return distanceStr;
    }

    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minLat
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    public static Map<String, String> getAround(String latStr, String lngStr, String raidus) {
        Map<String, String> map = new HashMap<String, String>();

        Double longitude = Double.parseDouble(latStr);// 传值给经度
        Double latitude = Double.parseDouble(lngStr);// 传值给纬度

        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);

        Double mpdLng = Double.parseDouble((degree * Math.cos(longitude * (Math.PI / 180)) + "")
            .replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLat = latitude - radiusLng;
        // 获取最大经度
        Double maxLat = latitude + radiusLng;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLng = longitude - radiusLat;
        // 获取最大纬度
        Double maxLng = longitude + radiusLat;

        map.put("minLat最小维度", minLat + "");
        map.put("maxLat最大纬度", maxLat + "");
        map.put("maxLng最大经度", maxLng + "");
        map.put("minLng最小精度", minLng + "");

        return map;
    }

    /**
     * 计算经纬度点对应正方形2个点的坐标
     *
     * @param longitude
     * @param latitude
     * @param distance
     * @return
     */
    public static Map<String, String> getSquarePoint(String longitudestr, String latitudestr,
                                                     String distancestr) throws Exception {
        Double longitude = Double.parseDouble(longitudestr);
        Double latitude = Double.parseDouble(latitudestr);
        Double distance = Double.parseDouble(distancestr);
        Map<String, String> map = new HashMap<String, String>();
        // 计算经度弧度,从弧度转换为角度
        double dLongitude = 2 * (Math.asin(Math.sin(distance / (2 * EARTH_RADIUS))
                                           / Math.cos(Math.toRadians(latitude))));
        dLongitude = Math.toDegrees(dLongitude);
        // 计算纬度角度
        double dLatitude = distance / EARTH_RADIUS;
        dLatitude = Math.toDegrees(dLatitude);
        // 正方形
        String latmin = String.valueOf(latitude - dLatitude);
        String latmax = String.valueOf(latitude + dLatitude);
        String longmin = String.valueOf(longitude - dLongitude);
        String longmax = String.valueOf(longitude + dLongitude);
        map.put("latmin", latmin);
        map.put("latmax", latmax);
        map.put("longmin", longmin);
        map.put("longmax", longmax);
        return map;
    }

    /**
     * 计算经纬度点对应正方形4个点的坐标
     * 
     * @param longitude
     * @param latitude
     * @param distance
     * @return
     */
    public static Map<String, double[]> returnLLSquarePoint(double longitude, double latitude,
                                                            double distance) {
        Map<String, double[]> squareMap = new HashMap<String, double[]>();
        // 计算经度弧度,从弧度转换为角度
        double dLongitude = 2 * (Math.asin(Math.sin(distance / (2 * EARTH_RADIUS))
                                           / Math.cos(Math.toRadians(latitude))));
        dLongitude = Math.toDegrees(dLongitude);
        // 计算纬度角度
        double dLatitude = distance / EARTH_RADIUS;
        dLatitude = Math.toDegrees(dLatitude);
        // 正方形
        double[] leftTopPoint = { latitude + dLatitude, longitude - dLongitude };
        double[] rightTopPoint = { latitude + dLatitude, longitude + dLongitude };
        double[] leftBottomPoint = { latitude - dLatitude, longitude - dLongitude };
        double[] rightBottomPoint = { latitude - dLatitude, longitude + dLongitude };
        squareMap.put("leftTopPoint", leftTopPoint);
        squareMap.put("rightTopPoint", rightTopPoint);
        squareMap.put("leftBottomPoint", leftBottomPoint);
        squareMap.put("rightBottomPoint", rightBottomPoint);
        return squareMap;
    }

    public static void main(String[] args) throws Exception {
        //济南国际会展中心经纬度：117.11811  36.68484
        //趵突泉：117.00999000000002  36.66123

        //System.out.println(getDistance("36.68484", "117.11811", "36.66123", "117.00999000000002"));
        System.out.println(getDistance("39.932705", "116.424477", "31.866622", "117.290064"));

        System.out.println(getDistance("31.235204", "121.467539", "31.866622", "117.290064"));

        // System.out.println(getAround("117.11811", "36.68484", "530"));

        /*  Map<String, double[]> map = returnLLSquarePoint(117.209668, 31.850578, 10.0);
          for (Map.Entry<String, double[]> entry : map.entrySet()) {
              System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
              System.out.println(entry.getValue().clone()[0]);
              System.out.println(entry.getValue().clone()[1]);
          }*/

        Map<String, String> map = getSquarePoint("117.209668", "31.850578", ConstanValue.DISTANCE);
        System.out.println("map:" + map);
        //117.01028712333508(Double), 117.22593287666493(Double),
        //36.44829619896034(Double), 36.92138380103966(Double)
        System.out.println(getDistance("27.375045579402393", "122.57927896808997", "31.866622",
            "117.290064"));

        System.out.println(getDistance("36.35819842059761", "122.57927896808997", "31.866622",
            "117.290064"));
    }

}