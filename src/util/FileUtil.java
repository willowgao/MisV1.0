package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * �ļ����빤����
 * ʹ��POI��������
 * @author wangshiyu
 *
 */
public class FileUtil<T> {

	private static Log log = LogFactory.getLog(FileUtil.class);
	/**ȱʡ������ݿ�ʼ��*/
	private static int DEFAULT_BEGIN_ROW = 1;
	/**
	 * excel�ĵ�ת��Ϊbean����ֻ֧�ֵ���sheet��ֻ֧�ֻ����������ͺ�String����
	 * @param <T>
	 * @param startRowNum		�ĵ���ʼ��(����1)
	 * @param clazz				ת��Ϊbean���class
	 * @param file				�ļ�
	 * @param fieldNameIndex	excel���±��Ӧbeanʵ����������
	 * @return
	 * @throws Exception 
	 */
	public static <T> List<T> excel2Bean(File file, int startRowNum, Class<T> clazz, Map<Integer, String> fieldNameIndex) throws Exception{
		if(file == null){
			return null;
		}
		startRowNum = startRowNum < 0 ?  DEFAULT_BEGIN_ROW : startRowNum;
		InputStream is = null;
		HSSFWorkbook hssfWorkbook = null;
		try {
			is = new FileInputStream(file);
			hssfWorkbook = new HSSFWorkbook(is);
		} catch (FileNotFoundException e) {
			throw new Exception("ָ��·���ļ������ڣ�", e);
		} catch (IOException e) {
			throw new Exception("��ȡ�ļ��쳣��", e);
		}
		
		//��ȡ��һ��sheetҳ
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		HSSFRow hssfRow = null;
		List<T> list = new ArrayList<T>();
		for (int i = startRowNum; i <= hssfSheet.getLastRowNum(); i++) {
			hssfRow = hssfSheet.getRow(i);
			T bean = clazz.newInstance();
			for (Integer index : fieldNameIndex.keySet()) {
				String fieldName = fieldNameIndex.get(index);
				//��������ֵ����
				Field field = clazz.getDeclaredField(fieldName);
				Method setMethod = BeanUtil.getSetMethodByFieldName(fieldName, clazz);
				Object value = null;
				if(field.getType().equals("java.lang.Integer")){
					value = hssfRow.getCell(index).getNumericCellValue();
				}else{
					value = hssfRow.getCell(index).toString();
				}
				setMethod.invoke(bean, value);
			}
			list.add(bean);
		}
		return list;
	}
	
	
	/**
	 * excel�ĵ�ת��Ϊbean����ֻ֧�ֵ���sheet��ֻ֧�ֻ����������ͺ�String����
	 * @param <T>
	 * @param startRowNum		�ĵ���ʼ��(����1)
	 * @param clazz				ת��Ϊbean���class
	 * @param filePath			�ļ�·��
	 * @param fieldNameIndex	excel���±��Ӧbeanʵ����������
	 * @return
	 * @throws Exception 
	 */
	public static <T> List<T> excel2Bean(String filePath, int startRowNum, Class<T> clazz, Map<Integer, String> fieldNameIndex) throws Exception{
		return excel2Bean(new File(filePath), startRowNum, clazz, fieldNameIndex);
	}
	
	/**
	 * ���ı��ļ��ж�ȡ�ַ���
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static String txtFile2Str(File file) throws IOException{
		if(!file.exists()){
			log.error("���ػ���������ָ�����ļ���" + file.getName());
			return null;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer fileContent = new StringBuffer();
		
		String a = null;
		do{
			try {
				a = br.readLine();
			} catch (IOException e) {
				log.warn("��ȡ�ļ������г���", e);
				throw e;
			}
			if(a!=null){
				fileContent.append(a);
			}
		}while(a!=null);
		
		br.close();
		
		return fileContent.toString();
	}
	
	/**
	 * ��ʵ����󼯺ϵ���excel�����ֻ֧��һ��sheetҳ��
	 * @param headNames		�������е�Ԫ������
	 * @param fieldNames	����ж�Ӧbeanʵ���������ƣ�˳���headNamesһ�£�
	 * @param beanList		ʵ�弯��
	 * @param cellStyle		���Ա����ʽ������Ϊnull
	 * @param clazz
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static <T> HSSFWorkbook bean2Excel(String[] headNames, String[] fieldNames, List<T> beanList, Class<T> clazz, HSSFCellStyle cellStyle) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		//����Ĭ�Ϲ��������ʽ
		if(cellStyle == null){
			//���õ�Ԫ���ʽ
			cellStyle = workbook.createCellStyle();
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setWrapText(true);
		}
		HSSFSheet sheet = workbook.createSheet();
		int rowNum = 0;
		//���ñ�����
		if(headNames != null && headNames.length > 0){
			HSSFRow row = sheet.createRow(rowNum++);
			for (int i = 0; i < headNames.length; i++ ){
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(new HSSFRichTextString(headNames[i]));
				cell.setCellStyle(cellStyle);
			}
		}
		//����������
		if(beanList != null && fieldNames != null && fieldNames.length > 0)
		 for (T t : beanList) {
			 HSSFRow row = sheet.createRow(rowNum++);
			 for (int i = 0; i < fieldNames.length; i++) {
				Method method = BeanUtil.getGetMethodByFieldName(fieldNames[i], clazz);
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(new HSSFRichTextString(method.invoke(t, null).toString()));
				cell.setCellStyle(cellStyle);
			}
		}
		return workbook;
	}
}
