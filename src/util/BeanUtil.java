package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtil {

	/**
	 * 根据属性名字获取相应bean对应的get方法
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
	 * 根据属性名字获取相应bean对应的set方法
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
	 * 根据属性名获取set方法放置值。 <br/>
	 * 支持成员的内部属性的放置，格式 如opcServerConnectConfig.serverProgID
	 * 
	 * @param targetObject
	 *            用来放置值的目标对象，如{@code OPCPointConfig}的对象
	 * @param fieldName
	 *            属性名称，如{@code OPCPointConfig}
	 *            中属性pointCode，或者属性opcServerConnectConfig中的serverProgID成员
	 * @param value
	 *            放入field的对应值
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

		// 非设置成员的内部属性
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
			// 获取成员
			String getMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method getMethod = targetClass.getMethod(getMethodName, null);

			Object member = getMethod.invoke(targetObject, null);

			if (member == null) {
				return;
			}

			// 设置成员的内部属性
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
	 * 根据属性名获取get方法放置值。 <br/>
	 * 支持成员的内部属性的放置，格式 如opcServerConnectConfig.serverProgID
	 * 
	 * @param targetObject
	 *            用来放置值的目标对象，如{@code OPCPointConfig}的对象
	 * @param fieldName
	 *            属性名称，如{@code OPCPointConfig}
	 *            中属性pointCode，或者属性opcServerConnectConfig中的serverProgID成员
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

		// 非设置成员的内部属性
		if (innerSet == false) {
			String getMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method method = targetClass.getMethod(getMethodName, null);

			return method.invoke(targetObject, null);

		} else {
			// 获取成员
			String getMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method method = targetClass.getMethod(getMethodName, null);

			Object member = method.invoke(targetObject, null);

			if (member == null) {
				return null;
			}

			// 设置成员的内部属性
			Class innerClass = member.getClass();

			temp = fieldName.substring(fieldName.indexOf(".") + 1);

			String getInnerMethodName = "get" + temp.substring(0, 1).toUpperCase() + temp.substring(1);

			Method innerMethod = innerClass.getMethod(getInnerMethodName, null);

			return innerMethod.invoke(member, null);

		}

	}


	/**
	 * 把list转换为以实体对象的某个属性为key的map
	 * 
	 * @param <T>
	 * @param targetBeanList
	 * @param targetClazz
	 * @param idFieldName
	 * @return
	 * @throws Exception
	 */
	public static <T> Map<Object, T> list2MapByField(List<T> beanList, Class<T> clazz, String idFieldName) throws Exception {
		// 利用map的key值保证唯一性
		Map<Object, T> map = new HashMap<Object, T>();
		// 利用反射获取bean标识字段值
		Method getIdMethod = getGetMethodByFieldName(idFieldName, clazz);

		if (beanList != null) {

			for (T t : beanList) {
				map.put(getIdMethod.invoke(t, null), t);
			}
		}

		return map;
	}

}
