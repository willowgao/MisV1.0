package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期转换工具
 * @author tanluole
 * @date 2013-11-4
 */
public class DateUtil {
	
	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss"; //年月日时分秒
	public static final String YMDHM = "yyyy-MM-dd HH:mm"; //年月日时分秒
	public static final String YMD = "yyyy-MM-dd";  //年月日

	public static final String MDYHMS = "MM/dd/yyyy HH:mm:ss.SSS"; //年月日时分秒
	public static final String YMD2 = "yyyyMMdd"; 
	
	/**
	 * 字符串转换为Timestamp
	 * @param date
	 * @return
	 */
	public static Timestamp str2Timestamp(String date){
		Date dt = string2Date(date,YMDHMS);
		return new Timestamp(dt.getTime());
	}
	
	/**
	 * Date型的数据转换成String
	 * @param date
	 * @param format
	 * @return
	 */
    public static String date2String(Date date, String format){
    	if(date == null){
    		return "";
    	}
    	SimpleDateFormat sf = new SimpleDateFormat(format);
    	return sf.format(date);
    }
    
    /**
     * 将字符串转换为String
     * @param date
     * @param format
     * @return
     */
    public static Date string2Date(String date, String format){
    	SimpleDateFormat sf = new SimpleDateFormat(format);
    	Date rel = null;
		try {
			rel = sf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rel;
    }
    /**
     * 将字符串转换为String
     * @param date
     * @param format
     * @return
     */
    public static java.sql.Date string2SqlDate(String date, String format){
    	SimpleDateFormat sf = new SimpleDateFormat(format);
    	Date rel = null;
		try {
			rel = sf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate=new java.sql.Date(rel.getTime());
		
    	return sqlDate;
    }
    
	/**
	 * 功能：根据指定日期格式输出当前日期,默认yyyy-MM-dd
	 * @param formatstr
	 * @return 
	 */
	public static String getNowDateByFormat(String formatstr){
		if(formatstr == null || "".equals(formatstr))
			formatstr = YMD;
		SimpleDateFormat format = new SimpleDateFormat(formatstr);
		return format.format(new Date());
	}

    /**
     * 将日期字符串转换为指定的日期字符串文本格式。 <br/>
     *
     * @param date 日期字符串
     * @param fromFormat 字符串原本的日期格式
     * @param toFormat 将要转变成的日期格式
     * @return 指定格式的日期字符串
     * @author danruliu
     */
    public static String string2Format(String date, String fromFormat,String toFormat){
    	Date d = string2Date(date,fromFormat);
    	return  date2String(d,toFormat);
    }
    
    /**
     * 将Date对象按照所给的日期格式，进行格式内的截取。<br/>
     * 如：<code>date</code>2014-01-29 12:00:04,<code>format</code> yyyy-MM-dd,
     * 转换后输出：2014-01-29 00:00:00 000 ，即非yyyy-MM-dd内的为初始值状态。
     * 
     * @param date 日期对象
     * @param format 格式
     * @return 截取后的日期
     * @author danruliu
     */
    public static Date date2Format(Date date,String format){
    	String dateStr = date2String(date, format);
    	return string2Date(dateStr, format);
    }
    
    /**
     * 获取日历对象。 <br/>
     *
     * @param date 日历的时间
     * @return 日历对象
     * @author danruliu
     */
    public static Calendar newCalendar(Date date){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	
    	return calendar;
    }
    
    /**
     * 根据间隔的秒来获取开始至结束时间段的所有时间
     * @param start 开始时间  yyyy-MM-dd HH:mm:ss
     * @param end   结束时间 yyyy-MM-dd HH:mm:ss
     * @param secondSetp 间隔的秒
     * @return  
     *       假设开始时间为："2014-06-01 02:50:00"　结束时间为：　"2014-06-01 02:50:30"
     *       则返回格式： ["2014-06-01 02:50:00","2014-06-01 02:50:10","2014-06-01 02:50:20","2014-06-01 02:50:30"]
     * @author tanluole
     */
    public static List<String> getPeriods(String start, String end, int secondSetp){
    	List<String> allTimes = new ArrayList<String>(); //所有的时间段
		Date sd = string2Date(start, YMDHMS);
		Date ed = string2Date(end, YMDHMS);
		Date temp = (Date) sd.clone();
		String starts = start.substring((start.length() - 1), start.length());
		if("0".equals(starts)){ //如果开始时间尾数为0
			allTimes.add(start);
		}
		while(true){
			
			Calendar cald = newCalendar(temp);
			cald.add(Calendar.SECOND, secondSetp);
			temp = cald.getTime();
			if(temp.after(ed)){
				break;
			}
			allTimes.add(date2String(temp, YMDHMS));
		}
		
		return allTimes;
    } 
    
    /**
     * 计算时间间隔总数
     * @param start 开始时间
     * @param end 结束时间
     * @param secondSetp 间隔（秒）
     * @return
     */
    public static int getCount(String start, String end, int secondSetp){
    	String starts = start.substring((start.length() - 1), start.length());
		String ends = end.substring((end.length() - 1), end.length());
		if (!"0".equals(starts)) { // 如果开始日期尾数不为0，则应往后推，如"2014-09-19 00:00:02"应变成"2014-09-19 00:00:10"
			start = start.substring(0, (start.length() - 1))+"0";
			Calendar cald = DateUtil.newCalendar(DateUtil.string2Date(start, DateUtil.YMDHMS));
			cald.add(Calendar.SECOND, secondSetp);
			start = DateUtil.date2String(cald.getTime(), DateUtil.YMDHMS);
		}
		if(!"0".equals(ends)){ //如果结尾不为0，则往前推，如"2014-09-19 00:01:01"应变成"2014-09-19 00:01:00"
			end = end.substring(0, (end.length() - 1))+"0";
		}
		
		Date sd = DateUtil.string2Date(start, DateUtil.YMDHMS);
		Date ed = DateUtil.string2Date(end, DateUtil.YMDHMS);

		int shouldBe = (int) ((ed.getTime() - sd.getTime()) / 10000) + 1; // 应该有多少条数据
		return shouldBe;
    }
    
}
