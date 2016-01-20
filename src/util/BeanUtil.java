package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtil {

	/**
	 * �����������ֻ�ȡ��Ӧbean��Ӧ��get����
	 * 
	 * @param fieldName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Method getGetMethodByFieldName(String fieldName, Class<?> clazz) throws NoSuchMethodException {

		String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		try {
			return clazz.getDeclaredMethod(getMethodName, null);
		} catch (NoSuchMethodException e) {
			if (clazz != Object.class) {
				return getGetMethodByFieldName(fieldName, clazz.getSuperclass());
			} else {
				throw new NoSuchMethodException();
			}
		}

	}

	/**
	 * �����������ֻ�ȡ��Ӧbean��Ӧ��set����
	 * 
	 * @param fieldName
	 * @param clazz
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 */
	public static Method getSetMethodByFieldName(String fieldName, Class<?> clazz) throws SecurityException, NoSuchFieldException,
						NoSuchMethodException {

		String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		Field field = clazz.getDeclaredField(fieldName);
		return clazz.getDeclaredMethod(setMethodName, field.getType());

	}

	/**
	 * ������������ȡset��������ֵ�� <br/>
	 * ֧�ֳ�Ա���ڲ����Եķ��ã���ʽ ��opcServerConnectConfig.serverProgID
	 * 
	 * @param targetObject
	 *            ��������ֵ��Ŀ�������{@code OPCPointConfig}�Ķ���
	 * @param fieldName
	 *            �������ƣ���{@code OPCPointConfig}
	 *            ������pointCode����������opcServerConnectConfig�е�serverProgID��Ա
	 * @param value
	 *            ����field�Ķ�Ӧֵ
	 * @throws Exception
	 */
	public static <T> void pojoSetValueByFieldName(T targetObject, String fieldName, Object value) throws Exception {

		if (fieldName == null || targetObject == null) {
			return;
		}

		boolean innerSet = false;

		String temp = "";
		if (fieldName.indexOf(".") != -1) {
			temp = fieldName.substring(0, fieldName.indexOf("."));

			innerSet = true;

		} else {

			temp = fieldName;

		}

		Class<?> targetClass = targetObject.getClass();

		// �����ó�Ա���ڲ�����
		if (innerSet == false) {
			String setMethodName = "set" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method[] methods = targetClass.getMethods();

			for (Method method : methods) {
				if (setMethodName.equals(method.getName())) {

					method.invoke(targetObject, value);
					break;
				}
			}
		} else {
			// ��ȡ��Ա
			String getMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method getMethod = targetClass.getMethod(getMethodName, null);

			Object member = getMethod.invoke(targetObject, null);

			if (member == null) {
				return;
			}

			// ���ó�Ա���ڲ�����
			Class innerClass = member.getClass();

			temp = fieldName.substring(fieldName.indexOf(".") + 1);

			String setInnerMethodName = "set" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method[] methods = innerClass.getMethods();

			for (Method method : methods) {
				if (setInnerMethodName.equals(method.getName())) {

					method.invoke(member, value);
					break;
				}
			}
		}
	}

	/**
	 * ������������ȡget��������ֵ�� <br/>
	 * ֧�ֳ�Ա���ڲ����Եķ��ã���ʽ ��opcServerConnectConfig.serverProgID
	 * 
	 * @param targetObject
	 *            ��������ֵ��Ŀ�������{@code OPCPointConfig}�Ķ���
	 * @param fieldName
	 *            �������ƣ���{@code OPCPointConfig}
	 *            ������pointCode����������opcServerConnectConfig�е�serverProgID��Ա
	 * @throws Exception
	 */
	public static <T> Object pojoGetValueByFieldName(T targetObject, String fieldName) throws Exception {

		if (fieldName == null || targetObject == null) {
			return null;
		}

		boolean innerSet = false;

		String temp = "";
		if (fieldName.indexOf(".") != -1) {
			temp = fieldName.substring(0, fieldName.indexOf("."));

			innerSet = true;

		} else {
			temp = fieldName;

		}

		Class targetClass = targetObject.getClass();

		// �����ó�Ա���ڲ�����
		if (innerSet == false) {
			String getMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method method = targetClass.getMethod(getMethodName, null);

			return method.invoke(targetObject, null);

		} else {
			// ��ȡ��Ա
			String getMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method method = targetClass.getMethod(getMethodName, null);

			Object member = method.invoke(targetObject, null);

			if (member == null) {
				return null;
			}

			// ���ó�Ա���ڲ�����
			Class innerClass = member.getClass();

			temp = fieldName.substring(fieldName.indexOf(".") + 1);

			String getInnerMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method innerMethod = innerClass.getMethod(getInnerMethodName, null);

			return innerMethod.invoke(member, null);

		}

	}


	/**
	 * ��listת��Ϊ��ʵ������ĳ������Ϊkey��map
	 * 
	 * @param <T>
	 * @param targetBeanList
	 * @param targetClazz
	 * @param idFieldName
	 * @return
	 * @throws Exception
	 */
	public static <T> Map<Object, T> list2MapByField(List<T> beanList, Class<T> clazz, String idFieldName) throws Exception {
		// ����map��keyֵ��֤Ψһ��
		Map<Object, T> map = new HashMap<Object, T>();
		// ���÷����ȡbean��ʶ�ֶ�ֵ
		Method getIdMethod = getGetMethodByFieldName(idFieldName, clazz);

		if (beanList != null) {

			for (T t : beanList) {
				map.put(getIdMethod.invoke(t, null), t);
			}
		}

		return map;
	}

}
