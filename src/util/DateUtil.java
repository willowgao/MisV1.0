package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ����ת������
 * @author tanluole
 * @date 2013-11-4
 */
public class DateUtil {
	
	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss"; //������ʱ����
	public static final String YMDHM = "yyyy-MM-dd HH:mm"; //������ʱ����
	public static final String YMD = "yyyy-MM-dd";  //������

	public static final String MDYHMS = "MM/dd/yyyy HH:mm:ss.SSS"; //������ʱ����
	public static final String YMD2 = "yyyyMMdd"; 
	
	/**
	 * �ַ���ת��ΪTimestamp
	 * @param date
	 * @return
	 */
	public static Timestamp str2Timestamp(String date){
		Date dt = string2Date(date,YMDHMS);
		return new Timestamp(dt.getTime());
	}
	
	/**
	 * Date�͵�����ת����String
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
     * ���ַ���ת��ΪString
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
     * ���ַ���ת��ΪString
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
	 * ���ܣ�����ָ�����ڸ�ʽ�����ǰ����,Ĭ��yyyy-MM-dd
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
     * �������ַ���ת��Ϊָ���������ַ����ı���ʽ�� <br/>
     *
     * @param date �����ַ���
     * @param fromFormat �ַ���ԭ�������ڸ�ʽ
     * @param toFormat ��Ҫת��ɵ����ڸ�ʽ
     * @return ָ����ʽ�������ַ���
     * @author danruliu
     */
    public static String string2Format(String date, String fromFormat,String toFormat){
    	Date d = string2Date(date,fromFormat);
    	return  date2String(d,toFormat);
    }
    
    /**
     * ��Date���������������ڸ�ʽ�����и�ʽ�ڵĽ�ȡ��<br/>
     * �磺<code>date</code>2014-01-29 12:00:04,<code>format</code> yyyy-MM-dd,
     * ת���������2014-01-29 00:00:00 000 ������yyyy-MM-dd�ڵ�Ϊ��ʼֵ״̬��
     * 
     * @param date ���ڶ���
     * @param format ��ʽ
     * @return ��ȡ�������
     * @author danruliu
     */
    public static Date date2Format(Date date,String format){
    	String dateStr = date2String(date, format);
    	return string2Date(dateStr, format);
    }
    
    /**
     * ��ȡ�������� <br/>
     *
     * @param date ������ʱ��
     * @return ��������
     * @author danruliu
     */
    public static Calendar newCalendar(Date date){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	
    	return calendar;
    }
    
    /**
     * ���ݼ����������ȡ��ʼ������ʱ��ε�����ʱ��
     * @param start ��ʼʱ��  yyyy-MM-dd HH:mm:ss
     * @param end   ����ʱ�� yyyy-MM-dd HH:mm:ss
     * @param secondSetp �������
     * @return  
     *       ���迪ʼʱ��Ϊ��"2014-06-01 02:50:00"������ʱ��Ϊ����"2014-06-01 02:50:30"
     *       �򷵻ظ�ʽ�� ["2014-06-01 02:50:00","2014-06-01 02:50:10","2014-06-01 02:50:20","2014-06-01 02:50:30"]
     * @author tanluole
     */
    public static List<String> getPeriods(String start, String end, int secondSetp){
    	List<String> allTimes = new ArrayList<String>(); //���е�ʱ���
		Date sd = string2Date(start, YMDHMS);
		Date ed = string2Date(end, YMDHMS);
		Date temp = (Date) sd.clone();
		String starts = start.substring((start.length() - 1), start.length());
		if("0".equals(starts)){ //�����ʼʱ��β��Ϊ0
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
     * ����ʱ��������
     * @param start ��ʼʱ��
     * @param end ����ʱ��
     * @param secondSetp ������룩
     * @return
     */
    public static int getCount(String start, String end, int secondSetp){
    	String starts = start.substring((start.length() - 1), start.length());
		String ends = end.substring((end.length() - 1), end.length());
		if (!"0".equals(starts)) { // �����ʼ����β����Ϊ0����Ӧ�����ƣ���"2014-09-19 00:00:02"Ӧ���"2014-09-19 00:00:10"
			start = start.substring(0, (start.length() - 1))+"0";
			Calendar cald = DateUtil.newCalendar(DateUtil.string2Date(start, DateUtil.YMDHMS));
			cald.add(Calendar.SECOND, secondSetp);
			start = DateUtil.date2String(cald.getTime(), DateUtil.YMDHMS);
		}
		if(!"0".equals(ends)){ //�����β��Ϊ0������ǰ�ƣ���"2014-09-19 00:01:01"Ӧ���"2014-09-19 00:01:00"
			end = end.substring(0, (end.length() - 1))+"0";
		}
		
		Date sd = DateUtil.string2Date(start, DateUtil.YMDHMS);
		Date ed = DateUtil.string2Date(end, DateUtil.YMDHMS);

		int shouldBe = (int) ((ed.getTime() - sd.getTime()) / 10000) + 1; // Ӧ���ж���������
		return shouldBe;
    }
    
}
