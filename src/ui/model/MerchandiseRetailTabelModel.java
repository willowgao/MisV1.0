package ui.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import util.BeanUtil;
import util.DateUtil;
import bean.MerRetail;

public class MerchandiseRetailTabelModel extends AbstractTableModel {

	public MerchandiseRetailTabelModel(List list) {
		this.data = list;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8825560266275236327L;

	public String[] colnumNames = new String[] { "商品ID", "商品名称", "商品规格", "商品品牌", "商品价格", "商品数量", "商品总金额" };

	public String[] fieldNames = new String[] { "merretaildetailId", "merRetailName", "merRetailStandard",
						"merRetailBrand", "merRetailPrice", "merRetailCount", "merRetailTotalPrice" };

	private List<MerRetail> data = new ArrayList<MerRetail>();

	@Override
	public int getColumnCount() {
		return colnumNames.length;
	}

	public MerRetail getMer(int index) {
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
		MerRetail merRetail = data.get(rowIndex);
		Object obj = "";
		try {
			Method method = BeanUtil.getGetMethodByFieldName(fieldNames[columnIndex], MerRetail.class);
			obj = method.invoke(merRetail, null);
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
