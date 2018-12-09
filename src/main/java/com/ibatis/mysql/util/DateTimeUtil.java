package com.ibatis.mysql.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/9 0009.
 */
public final class DateTimeUtil {
    private DateTimeUtil() {
    }


    public static void main(String[] args) throws ParseException {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date1=sdf.format(date);
        Date date2=sdf.parse(date1);
        System.out.println(date1);
        System.out.println(date2);

        testData();
    }

    /**
     * 直接将当前时间只按日期(时间为0)作为mysql时间戳字段的条件
     * 最终返回时间类型java.sql.Date
     */
    public void transformCurDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        java.sql.Date timePara  = null;
        try {
            timePara = new java.sql.Date(System.currentTimeMillis());
            System.out.println(timePara);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 将java的当前时间转成指定格式(yyyy-MM-0100:00:00")作为mysql时间戳字段的条件
     *  最终返回时间类型java.sql.Date
     */
    public void transformCurYearMon(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String time = format.format(new Date()).concat("-0100:00:00");
        java.sql.Date timePara  = null;
        try {
            timePara = new java.sql.Date(format.parse(time).getTime());
            System.out.println(timePara);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * 将java的当前时间转成Timestamp作为mysql时间戳字段的条件
     *  最终返回时间类型java.sql.Timestamp
     */
    public static void testData() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Timestamp date =  java.sql.Timestamp.valueOf("2012-12-12 01:12:11");
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 处理当前时间只按日期(时间为0)
     * 最终返回时间类型java.util.Date
     */
    public static void dataTest() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String time =  format.format(new Date());
            Date date = format.parse(time.concat("00:00:00"));
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
