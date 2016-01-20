package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bean.MerRetail;
import bean.Merchandise;
import bean.MerchandiseRecodes;
import bean.UserInfo;
import db.DbLogicServie;

/**
 * @type DbService
 * @title DbService.java
 * @desc 数据持久服务
 * @author gaochengliu
 * @date 2015-3-2
 * @version V1.0
 */
public class DbService {

	private static Log log = LogFactory.getLog(DbService.class);

	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	final static String JDBCDRIVER = "oracle.jdbc.driver.OracleDriver";

	/**
	 * 初始化数据连接
	 */
	static {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("./resources/jdbc.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String url = properties.getProperty("jdbc.url");
		String userName = properties.getProperty("jdbc.userName");
		String userPwd = properties.getProperty("jdbc.pwd");
		try {
			Class.forName(JDBCDRIVER).newInstance();
			conn = DriverManager.getConnection(url, userName, userPwd);
		} catch (Exception e) {
			log.error("数据连接初始化失败，失败信息:" + e.getMessage());
			e.printStackTrace();
		}

		log.info("数据连接初始化成功!");

	}

	/**
	 * @type DbService.java
	 * @return boolean
	 * @desc 字符串格式的删除、更新、写入方法
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-2
	 */
	public static boolean executeDelete(String sql) {
		try {
			ps = conn.prepareStatement(sql);

			int count = ps.executeUpdate(sql);

			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				conn.commit();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * @type DbService.java
	 * @return boolean
	 * @desc 字符串格式的删除、更新、写入方法
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-2
	 */
	public static boolean executeUpdate(String sql, Object[] arg) {
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < arg.length; i++) {
				if (arg[i] instanceof String) {
					ps.setString(i + 1, (String) arg[i]);
				} else if (arg[i] instanceof java.sql.Date) {
					ps.setDate(i + 1, (java.sql.Date) arg[i]);
				} else if (arg[i] instanceof Integer) {
					ps.setLong(i + 1, Integer.valueOf(arg[i].toString()));
				} else if (arg[i] instanceof BigDecimal) {
					ps.setBigDecimal(i + 1, new BigDecimal(arg[i].toString()));
				} else if (arg[i] instanceof Timestamp) {
					ps.setTimestamp(i + 1, (Timestamp) arg[i]);
				} else {
					ps.setString(i + 1, null);
				}
			}
			int count = ps.executeUpdate();

			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.commit();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * @type DbService.java
	 * @return List<Map<String,String>>
	 * @desc 查询方法，返回list
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-2
	 */
	@SuppressWarnings("unchecked")
	public static List executeQuery(String sql, String[] arg, String type) {
		List list = new ArrayList();
		try {

			ps = conn.prepareStatement(sql);
			if (arg != null) {
				for (int i = 0; i < arg.length; i++) {
					if (arg[i] instanceof String && !arg[i].equals("")) {
						ps.setString(i + 1, arg[i]);
					}
				}
			}
			rs = ps.executeQuery();
			if (type.equals(DbLogicServie.QUERY_TYPE_LOGIN)) {
				list.addAll(getUserInfo(rs));
			} else if (type.equals(DbLogicServie.QUERY_TYPE_MERCHANDISRECORDS)) {
				list.addAll(getMerchandiseRecodesFormRs(rs));
			} else if (type.equals(DbLogicServie.QUERY_TYPE_MERCHANDISE)) {
				list.addAll(getMerchandiseFormRs(rs));
			} else if (type.equals(DbLogicServie.QUERY_TYPE_MERCHANDISETOTAL)) {
				list.addAll(getMerchandiseTotal(rs));
			} else if (type.equals(DbLogicServie.QUERY_TYPE_MERCOUNT)) {
				list.addAll(getMerchandiseCount(rs));
			} else if (type.equals(DbLogicServie.QUERY_TYPE_MERRETAILDETAIL)) {
				list.addAll(getMerRetailDetails(rs));
			} else if (type.equals(DbLogicServie.QUERY_TYPE_MERRETAIL)) {
				list.addAll(getMerRetails(rs));
			}

		} catch (SQLException e) {
			log.error("调用查询出错，出错信息：" + e.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

	/**
	 * @type DbService.java
	 * @return List
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */
	@SuppressWarnings("unchecked")
	static List getMerRetails(ResultSet rs) {
		List list = new ArrayList();
		try {
			while (rs.next()) {
				MerRetail mr = new MerRetail();
				mr.setMerretailId(rs.getString(1));
				mr.setMerRetailTotalPrice(rs.getBigDecimal(2));
				mr.setMerRetailCreateDt(rs.getTimestamp(3));
				mr.setMerRetailCreator(rs.getString(4));
				mr.setMerRetailDesc(rs.getString(5));
				list.add(mr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @type DbService.java
	 * @return List
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */
	@SuppressWarnings("unchecked")
	static List getMerRetailDetails(ResultSet rs) {
		List list = new ArrayList();
		try {
			while (rs.next()) {
				MerRetail mr = new MerRetail();
				mr.setMerretaildetailid(rs.getString(1));
				mr.setMerretailId(rs.getString(2));
				mr.setMerRetailName(rs.getString(3));
				mr.setMerRetailStandard(rs.getString(4));
				mr.setMerRetailBrand(rs.getString(5));
				mr.setMerRetailPrice(rs.getBigDecimal(6));
				mr.setMerRetailCount(rs.getInt(7));
				mr.setMerRetailTotalPrice(rs.getBigDecimal(8));
				mr.setMerRetailCreateDt(rs.getTimestamp(9));
				mr.setMerRetailCreator(rs.getString(10));
				list.add(mr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @type DbService.java
	 * @return List
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */
	@SuppressWarnings("unchecked")
	static List getMerchandiseCount(ResultSet rs) {
		List list = new ArrayList();
		try {
			while (rs.next()) {
				MerchandiseRecodes mc = new MerchandiseRecodes();
				mc.setM_count(rs.getInt(1));
				list.add(mc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	static List getUserInfo(ResultSet rs) {
		List list = new ArrayList();
		try {
			while (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUserName(rs.getString(1));
				userInfo.setUserCode(rs.getString(2));
				userInfo.setUserPwd(rs.getString(3));
				userInfo.setUserType(rs.getString(4));
				list.add(userInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @type DbService.java
	 * @return List
	 * @desc 查询出入库记录
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */
	@SuppressWarnings("unchecked")
	static List getMerchandiseTotal(ResultSet rs) {
		List list = new ArrayList();
		try {
			while (rs.next()) {
				MerchandiseRecodes mc = new MerchandiseRecodes();
				mc.setMerchandiseName(rs.getString(1));
				mc.setMerchandiseTandard(rs.getString(2));
				mc.setMerchandiseBrand(rs.getString(3));
				mc.setM_total(rs.getBigDecimal(4));
				mc.setM_count(Integer.valueOf(rs.getLong(5) + ""));
				list.add(mc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @type DbService.java
	 * @return List
	 * @desc 查询出入库记录
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */
	@SuppressWarnings("unchecked")
	static List getMerchandiseRecodesFormRs(ResultSet rs) {
		List list = new ArrayList();
		try {
			while (rs.next()) {
				MerchandiseRecodes mc = new MerchandiseRecodes();
				mc.setXh(rs.getString(1));
				mc.setM_id(rs.getString(2));
				mc.setM_type(rs.getString(3));
				mc.setM_date(rs.getDate(4));
				mc.setM_price(rs.getBigDecimal(5));
				mc.setM_count(Integer.valueOf(rs.getLong(6) + ""));
				mc.setM_total(rs.getBigDecimal(7));
				mc.setM_desc(rs.getString(8));
				mc.setM_creator(rs.getString(9));
				mc.setM_createDt(rs.getTimestamp(10));
				mc.setM_dealType(rs.getString(11));
				mc.setMerchandiseName(rs.getString(12));
				mc.setMerchandiseId(rs.getString(13));
				list.add(mc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @type DbService.java
	 * @return List
	 * @desc 查询商品信息
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */
	@SuppressWarnings("unchecked")
	static List getMerchandiseFormRs(ResultSet rs) {
		List list = new ArrayList();
		try {
			while (rs.next()) {
				Merchandise mc = new Merchandise();
				mc.setMerchandiseId(rs.getString(1));
				mc.setMerchandiseType(rs.getString(2));
				mc.setMerchandiseName(rs.getString(3));
				mc.setMerchandiseTandard(rs.getString(4));
				mc.setMerchandiseBrand(rs.getString(5));
				mc.setMerchandisePrice(rs.getString(6));
				list.add(mc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
