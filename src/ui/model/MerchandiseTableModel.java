package ui.model;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import util.BeanUtil;
import util.DateUtil;
import bean.MerchandiseRecodes;

import java.lang.reflect.Method;

public class MerchandiseTableModel extends AbstractTableModel {
	public MerchandiseTableModel(List<MerchandiseRecodes> data) {
		this.data = data;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 列头 */
	public static  String[] colnumNames = new String[] {"ID",  "序号", "货物类型", "出入库类型", "单价", "数量", "总价","操作人", "操作时间" };

	/** 列头对应bean属性名称 */
	public static String[] fieldNames = new String[] { "m_id","xh", "merchandiseName", "m_dealType", "m_price", "m_count",
						"m_total", "m_creator", "m_createDt" };

	/** 数据 */
	private List<MerchandiseRecodes> data = new ArrayList<MerchandiseRecodes>();

	@Override
	public int getColumnCount() {
		return colnumNames.length;
	}

	public MerchandiseRecodes getMer(int index) {
		return data.get(index);
	}

	@Override
	public String getColumnName(int column) {
		return colnumNames[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		MerchandiseRecodes merchandise = data.get(rowIndex);
		Object obj = "";
		try {
			Method method = BeanUtil.getGetMethodByFieldName(fieldNames[columnIndex], MerchandiseRecodes.class);
			obj = method.invoke(merchandise, null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (obj instanceof Date) {
			return DateUtil.date2String((Date) obj, DateUtil.YMDHMS);
		}
		return obj;
	}

	public void setData(List list) {
		this.data = list;
	}

}
