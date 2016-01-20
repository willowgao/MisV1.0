package ui.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import util.BeanUtil;
import util.DateUtil;

import bean.Merchandise;

public class MerchandiseInfoTableModel extends AbstractTableModel {

	public MerchandiseInfoTableModel(List<Merchandise> list) {
		this.list = list;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8825560266275236327L;

	String[] colnumNames = new String[] { "商品类型", "商品名称", "商品规格", "商品品牌", "商品价格" };

	String[] fieldNames = new String[] { "merchandiseType", "merchandiseName", "merchandiseTandard",
						"merchandiseBrand", "merchandisePrice" };

	private List<Merchandise> list = new ArrayList<Merchandise>();

	@Override
	public int getColumnCount() {
		return fieldNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return colnumNames[column];
	}

	public Merchandise getMer(int index) {
		return list.get(index);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Merchandise merchandise = list.get(rowIndex);
		Object obj = "";
		try {
			Method method = BeanUtil.getGetMethodByFieldName(fieldNames[columnIndex], Merchandise.class);
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
		this.list = list;
	}

}
