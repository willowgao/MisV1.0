package util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RunUntil {
	/**
	 * ��ʽ��ʱ���ʽ ת��Ϊ����
	 * 
	 * @param time
	 *            ����
	 * @return fmTime
	 */
	public static String Format(String time) {
		String fmTime = "";
		if (time == null || time.length() == 0) { // ������ǿ��ַ�

		} else if (time.length() == 4) {// ��
			fmTime = time + "-12-31";
		} else if (time.length() == 6) {// ��
			String str = time.substring(5, 6);
			fmTime = time.substring(0, 4) + "-"
								+ (str.equals("4") ? "10" : "0" + String.valueOf((Integer.parseInt(str) - 1) * 3 + 1))
								+ "-01";
		} else if (time.length() == 7) {// ��
			fmTime = time + "-01";
		} else if (time.length() == 10) {
			fmTime = time;
		}

		return fmTime;
	}

	/**
	 * ������ת����Sql�е�In���
	 * 
	 * @param o
	 *            ���϶��� ��ΪList,Object []
	 * @return
	 */
	public static String transObjAsSqlInStr(Object o) {
		Object[] elements = null;
		if (o instanceof java.util.List) {
			elements = ((java.util.List) o).toArray();
		} else {
			elements = (Object[]) o;
		}
		int len = elements.length;
		if (len == 1) {
			return "'" + elements[0] + "'";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if (null == (String) elements[i]) {
				continue;
			}
			String element = elements[i].toString();
			sb.append("'").append(element).append("',");
		}
		// ȥ�����һ���ַ�����
		return truncLastChar(sb.toString());
	}

	/**
	 * ȥ�����һ���ַ�
	 * 
	 * @param str
	 * @return
	 */
	public static String truncLastChar(String str) {
		return str.substring(0, str.length() - 1);
	}

	/**
	 * ��map��key����List��
	 * 
	 * @param map
	 * @return
	 */
	public static List mapKeyTransAsList(Map map) {
		// map��û��Ԫ���򷵻ؿռ���
		if (null == map || map.size() == 0)
			return null;
		return Arrays.asList(map.keySet().toArray());
	}

	public static boolean isEmpty(Object o) {
		if (null == o || "null".equals(o) || "".equals(o) || o.toString().length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Object o) {
		if (null == o || "null".equals(o) || "".equals(o) || o.toString().length() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * ���ݿ�ת��Ϊ��������
	 * 
	 * @param voName
	 * @return
	 */
	public static String transDbAsField(String voName) {
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < voName.length(); i++) {
			char cur = voName.charAt(i);
			if (cur == '_') {
				flag = true;
			} else {
				if (flag) {
					sb.append(Character.toUpperCase(cur));
					flag = false;
				} else {
					sb.append(Character.toLowerCase(cur));
				}
			}
		}
		// //System.out.println(sb);
		return sb.toString();
	}

	/**
	 * ��������ת��Ϊ���ݿ��ж�Ӧ��
	 * 
	 * @param voName
	 *            ��������
	 * @return
	 */
	public static String transFieldAsDb(String voName) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < voName.length(); i++) {
			char cur = voName.charAt(i);
			if (Character.isUpperCase(cur) && i >= 1) {
				sb.append("_");
				sb.append(cur);
			} else {
				sb.append(cur);
			}
		}
		return sb.toString().toUpperCase();
	}

	public static List getPropertyList(Class clazz) {
		List zballList = new ArrayList();
		Field[] fields = clazz.getDeclaredFields();
		for (int len = 0; len < fields.length; len++) {
			Field field = fields[len];
			int n = field.getModifiers();
			// �ж���FINAL�ֶ�
			if (Modifier.isFinal(n)) {
				continue;
			}
			field.setAccessible(true);
			// ָ����
			String propName = field.getName();
			zballList.add(propName);
		}
		return zballList;
	}

}
