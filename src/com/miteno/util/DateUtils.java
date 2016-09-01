package com.miteno.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//日期类
public class DateUtils {
	//字符串转DATE格式
	public static Date getStrDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			if(time != null && !"".equals(time)){
				if(time.indexOf("/") != -1){
					time = time.replace("/", "-");
				}
				 date=sdf.parse(time);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/*
	 *  获取指定日期之前三月。
	 *  time 指定日期
	 */
	public static String getTimeAfterMonthTime(String time){
		Date nowdate = getStrDate(time);
		Calendar   calendar   =   new   GregorianCalendar(); 
		calendar.setTime(nowdate); 
		calendar.add(calendar.MONTH,-3);//把日期往前三月.整数往后推,负数往前移动 
		Date afterdate=calendar.getTime();   //这个时间就是日期往后推一天的结果 
		SimpleDateFormat sdformat1 = new SimpleDateFormat("yyyy-MM-dd");//24小时制
		return sdformat1.format(afterdate); 
	}
	/*
	 *  获取当前日期。
	 */
    public  static String getDate(){
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
		String nowdate = outFormat.format(now);// 订单充值日期
		return nowdate;
    }
	/*
	 *  获取当前时间。
	 */
    public  static String getDateTime(){
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowdate = outFormat.format(now);// 订单充值日期
		return nowdate;
    }
	//
	public static void main(String[] args) {
		String time ="2015-04-03";
		String sy =getDateTime();
		System.out.println(sy);
	}
}
