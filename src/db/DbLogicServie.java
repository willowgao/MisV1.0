package db;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import service.DbService;
import util.DateUtil;
import util.RunUntil;
import bean.Merchandise;
import bean.MerchandiseRecodes;
import bean.UserInfo;

/**
 * @type LoginCheckServie
 * @title LoginCheckServie.java
 * @desc 数据操作服务类
 * @author gaochengliu
 * @date 2015-3-2
 * @version V1.0
 */
public class DbLogicServie {

	private static Log log = LogFactory.getLog(DbService.class);
	/** 登录查询 **/
	public static final String QUERY_TYPE_LOGIN = "1";
	/** 商品出入库记录查询 **/
	public static final String QUERY_TYPE_MERCHANDISRECORDS = "2";
	/** 商品信息查询 **/
	public static final String QUERY_TYPE_MERCHANDISE = "3";
	/** 商品信息查询 **/
	public static final String QUERY_TYPE_MERCHANDISETOTAL = "4";
	/** 库存统计 **/
	public static final String QUERY_TYPE_MERCOUNT = "5";
	/** 库存统计 **/
	public static final String QUERY_TYPE_MERRETAILDETAIL = "6";
	/** 库存统计 **/
	public static final String QUERY_TYPE_MERRETAIL = "7";

	@SuppressWarnings("unchecked")
	public static List checkUserInfo(String[] arg) {
		String sql = "SELECT USERNAME,USERCODE,USERPWD,USERTYPE FROM USERINFO WHERE USERCODE=? ";
		return DbService.executeQuery(sql, arg, QUERY_TYPE_LOGIN);
	}

	/**
	 * 
	 * @type DbLogicServie.java
	 * @return List
	 * @desc arg [0] merretaildetailid
	 * @desc arg [1] merretailid
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-11
	 */
	@SuppressWarnings("unchecked")
	public static List getMerRetailDetails(String[] arg) {
		String sql = "SELECT MERRETAILDETAILID,MERRETAILID,B.MERCHANDISENAME,B.MERCHANDISESTANDARD,B.MERCHANDISEBRAND,B.MERCHANDISEPRICE,"
							+ "MERRETAILCOUNT,MERRETAILTOTALPRICE, MERRETAILCREATEDT,MERRETAILCREATOR, B.MERCHANDISEID   FROM MERRETAILDETAIL "
							+ " A,  MERCHANDISEINFO B WHERE A.MERCHANDISEID = B.MERCHANDISEID ";
		if (arg[0] != null) {
			sql += " and MERRETAILDETAILID = ?";
			arg = new String[] { arg[0] };
		} else if (arg[0] == null && arg[1] == null) {
			sql += " and 1=2 ";
		} else {
			sql += " and MERRETAILID = ? ";
			arg = new String[] { arg[1] };
		}
		return DbService.executeQuery(sql, arg, QUERY_TYPE_MERRETAILDETAIL);
	}

	/**
	 * 
	 * @type DbLogicServie.java
	 * @return List
	 * @desc arg [0] merretailid
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-11
	 */
	@SuppressWarnings("unchecked")
	public static List getMerRetails(String[] arg) {
		String sql = "SELECT MERRETAILID ,MERRETAILTOTALPRICE, MERRETAILCREATEDT,MERRETAILCREATOR,MERRETAILDESC   FROM MERRETAIL  ";
		if (arg.length == 1) {
			sql += " WHERE MERRETAILID = ? ";
		} else if (arg.length > 1) {
			sql += " WHERE TO_CHAR(MERRETAILCREATEDT,'YYYY-MM-DD')>=? AND TO_CHAR(MERRETAILCREATEDT,'YYYY-MM-DD')<=? ";
		}
		sql += " order by MERRETAILCREATEDT desc";
		return DbService.executeQuery(sql, arg, QUERY_TYPE_MERRETAIL);
	}

	@SuppressWarnings("unchecked")
	public static boolean insertMerRetailInfo(String[] arg) {
		List list = getMerchandiseInfo(new String[] { arg[0] });
		Merchandise merchandise = (Merchandise) list.get(0);
		Object[] inserArg = {
							UUID.randomUUID().toString().replace("-", ""),
							arg[3],
							arg[0],
							arg[1],
							new BigDecimal(merchandise.getMerchandisePrice()).multiply(new BigDecimal(arg[1]))
												.toString(),
							Timestamp.valueOf(DateUtil.getNowDateByFormat(DateUtil.YMDHMS)), arg[2] };
		String sql = " INSERT INTO MERRETAILDETAIL( MERRETAILDETAILID,MERRETAILID,MERCHANDISEID,MERRETAILCOUNT,MERRETAILTOTALPRICE,MERRETAILCREATEDT,"
							+ "MERRETAILCREATOR )VALUES(?,?,?,?,?,?,? )";
		return DbService.executeUpdate(sql, inserArg);
	}

	public static boolean insertMerRetailTotal(Object[] arg) {

		String sql = "INSERT INTO MERRETAIL( MERRETAILID,MERRETAILTOTALPRICE,MERRETAILCREATEDT,MERRETAILCREATOR,MERRETAILDESC )VALUES(?,?,?,?,? )";
		return DbService.executeUpdate(sql, arg);
	}

	public static boolean delMerRetailInfo(List<String> ids) {
		String sql = "DELETE FROM MERRETAILDETAIL WHERE MERRETAILDETAILID IN (" + RunUntil.transObjAsSqlInStr(ids)
							+ ")";
		return DbService.executeDelete(sql);
	}

	public static boolean delMerchandise(List<String> ids) {
		String sql = "DELETE FROM MERCHANDISE WHERE M_ID IN (" + RunUntil.transObjAsSqlInStr(ids) + ")";
		return DbService.executeDelete(sql);
	}

	/**
	 * @type LoginCheckServie.java
	 * @return List<Map<String,String>>
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-2
	 */
	@SuppressWarnings("unchecked")
	public static List getUserInfo(String[] arg) {
		String sql = "SELECT USERNAME,USERCODE,USERPWD,USERTYPE FROM USERINFO WHERE USERCODE=? AND USERPWD=?";
		return DbService.executeQuery(sql, arg, QUERY_TYPE_LOGIN);
	}

	@SuppressWarnings("unchecked")
	public static List getMerCount(String[] arg) {
		String sql = "SELECT SUM(NVL(DECODE(M_DEALTYPE, '1', M_COUNT, '2', -M_COUNT), 0)) M_COUNT from MERCHANDISE WHERE M_TYPE =?";
		return DbService.executeQuery(sql, arg, QUERY_TYPE_MERCOUNT);
	}

	/**
	 * @type DbLogicServie.java
	 * @return List
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */
	@SuppressWarnings("unchecked")
	public static List getMerchandiseTotal(String[] arg) {
		String sql = "SELECT B.MERCHANDISENAME,B.MERCHANDISESTANDARD,B.MERCHANDISEBRAND, "
							+ "     SUM(NVL(DECODE(M_DEALTYPE, '1', M_TOTAL, '2', -M_TOTAL), 0)) M_TOTAL, "
							+ "SUM(NVL(DECODE(M_DEALTYPE, '1', M_COUNT, '2', -M_COUNT), 0)) M_COUNT  FROM MERCHANDISE A, MERCHANDISEINFO B"
							+ " WHERE A.M_TYPE = B.MERCHANDISEID ";
		boolean bool0 = false;
		if (!arg[0].equals("0") && !arg[0].equals("")) {
			sql += " AND M_TYPE = ? ";
			bool0 = true;
		}
		boolean bool1 = false;
		if (!arg[1].equals("0") && !arg[1].equals("")) {
			sql += " AND M_DEALTYPE = ? ";
			bool1 = true;
		}
		sql += " AND TO_CHAR(M_CREATEDT,'YYYY-MM-DD') >= ? "
							+ "AND TO_CHAR(M_CREATEDT,'YYYY-MM-DD')<=? GROUP BY B.MERCHANDISENAME,B.MERCHANDISESTANDARD,"

							+ "B.MERCHANDISEBRAND  ORDER BY MERCHANDISENAME";
		if (!bool0 && bool1) {
			arg = new String[] { arg[1], arg[2], arg[3] };
		}
		if (bool0 && !bool1) {
			arg = new String[] { arg[0], arg[2], arg[3] };
		}
		if (!bool0 && !bool1) {
			arg = new String[] { arg[2], arg[3] };
		}
		return DbService.executeQuery(sql, arg, QUERY_TYPE_MERCHANDISETOTAL);
	}

	/**
	 * @type DbLogicServie.java
	 * @return List
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */
	@SuppressWarnings("unchecked")
	public static List getMerchandiseRecodes(String[] arg) {
		String sql = null;
		if (arg == null) {
			sql = "select  rownum xh,M_ID, M_TYPE, M_DATE,M_PRICE,M_COUNT, M_TOTAL, M_DESC,  M_CREATOR,  M_CREATEDT,M_DEALTYPE,MERCHANDISENAME,MERCHANDISEID "
								+ "from (SELECT M_ID, M_TYPE, M_DATE,M_PRICE,M_COUNT, M_TOTAL, M_DESC,  M_CREATOR,  M_CREATEDT,"
								+ "DECODE(M_DEALTYPE,'1','入库',2,'出库') M_DEALTYPE,B.MERCHANDISENAME,B.MERCHANDISEID FROM MERCHANDISE A,MERCHANDISEINFO B "
								+ "WHERE A.M_TYPE= B.MERCHANDISEID ORDER BY M_CREATEDT DESC)order by xh";
		} else if (arg.length > 1) {
			sql = "select rownum xh,M_ID, M_TYPE, M_DATE,M_PRICE,M_COUNT, M_TOTAL, M_DESC,  M_CREATOR,  M_CREATEDT,M_DEALTYPE,MERCHANDISENAME,MERCHANDISEID"
								+ " from (SELECT M_ID, M_TYPE, M_DATE,M_PRICE,M_COUNT, M_TOTAL, M_DESC,  M_CREATOR,  M_CREATEDT,"
								+ "DECODE(M_DEALTYPE,'1','入库',2,'出库') M_DEALTYPE,B.MERCHANDISENAME,B.MERCHANDISEID FROM MERCHANDISE A,MERCHANDISEINFO B "
								+ "WHERE A.M_TYPE= B.MERCHANDISEID ";
			boolean bool0 = false;
			if (!arg[0].equals("0") && !arg[0].equals("")) {
				sql += " AND M_TYPE = ? ";
				bool0 = true;
			}
			boolean bool1 = false;
			if (!arg[1].equals("0") && !arg[1].equals("")) {
				sql += " AND M_DEALTYPE = ? ";
				bool1 = true;
			}
			if (!bool0 && bool1) {
				arg = new String[] { arg[1], arg[2], arg[3] };
			}
			if (bool0 && !bool1) {
				arg = new String[] { arg[0], arg[2], arg[3] };
			}
			if (!bool0 && !bool1) {
				arg = new String[] { arg[2], arg[3] };
			}

			sql += " AND TO_CHAR(M_CREATEDT,'YYYY-MM-DD') >= ?  AND TO_CHAR(M_CREATEDT,'YYYY-MM-DD') <=? ORDER BY M_CREATEDT DESC)order by xh";
		} else {
			sql = "select rownum xh,M_ID, M_TYPE, M_DATE,M_PRICE,M_COUNT, M_TOTAL, M_DESC,  M_CREATOR,  M_CREATEDT,M_DEALTYPE,MERCHANDISENAME,MERCHANDISEID"
								+ " from (SELECT M_ID, M_TYPE, M_DATE,M_PRICE,M_COUNT, M_TOTAL, M_DESC,  M_CREATOR,  M_CREATEDT,"
								+ "DECODE(M_DEALTYPE,'1','入库',2,'出库') M_DEALTYPE,B.MERCHANDISENAME,B.MERCHANDISEID FROM MERCHANDISE A,MERCHANDISEINFO B "
								+ "WHERE A.M_TYPE= B.MERCHANDISEID AND M_ID =?  ORDER BY M_CREATEDT DESC)order by xh";
		}
		return DbService.executeQuery(sql, arg, QUERY_TYPE_MERCHANDISRECORDS);
	}

	/**
	 * @type DbLogicServie.java
	 * @return List
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-4
	 */

	@SuppressWarnings("unchecked")
	public static List getMerchandiseInfo(String[] arg) {
		String sql = "SELECT MERCHANDISEID, MERCHANDISETYPE, MERCHANDISENAME,MERCHANDISESTANDARD,MERCHANDISEBRAND,MERCHANDISEPRICE  FROM MERCHANDISEINFO ";
		if (arg != null) {
			sql += " where MERCHANDISEID = ?";
		}
		return DbService.executeQuery(sql, arg, QUERY_TYPE_MERCHANDISE);
	}

	/**
	 * @type insertMerchandise
	 * @return boolean
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-3
	 */
	public static boolean insertMerchandise(MerchandiseRecodes mc) {
		String sql = "INSERT INTO MERCHANDISE ( M_ID, M_TYPE,M_DEALTYPE, M_DATE,M_PRICE,M_COUNT, M_TOTAL, M_DESC,  M_CREATOR, "
							+ " M_CREATEDT ) VALUES(?, ?,?,?,?,?,?,?,?,?)";
		Object[] arg = { UUID.randomUUID().toString().replace("-", ""), mc.getM_type(), mc.getM_dealType(),
							mc.getM_date(), mc.getM_price(), mc.getM_count(), mc.getM_total(), mc.getM_desc(),
							mc.getM_creator(), mc.getM_createDt() };
		return DbService.executeUpdate(sql, arg);
	}

	/**
	 * @type updateMerchandiseInfo
	 * @return boolean
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-3
	 */
	public static boolean updateMerchandiseInfo(Merchandise mc) {
		String sql = "UPDATE MERCHANDISEINFO  SET  MERCHANDISENAME = ?,MERCHANDISETYPE= ?, MERCHANDISESTANDARD= ?,MERCHANDISEBRAND= ? "
							+ ",MERCHANDISEPRICE= ? WHERE MERCHANDISEID = ? ";
		Object[] arg = { mc.getMerchandiseName(), mc.getMerchandiseType(), mc.getMerchandiseTandard(),
							mc.getMerchandiseBrand(), mc.getMerchandisePrice(), mc.getMerchandiseId() };
		log.info(mc.getMerchandiseId());
		return DbService.executeUpdate(sql, arg);
	}

	/**
	 * @type updateMerchandiseInfo
	 * @return boolean
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-3
	 */
	public static boolean updateMerchandiseRecords(MerchandiseRecodes mc) {
		String sql = "update MERCHANDISE  set  M_TYPE = ?,M_DEALTYPE= ?, M_PRICE= ?,M_COUNT= ?, M_TOTAL= ? where M_id = ? ";
		Object[] arg = { mc.getM_type(), mc.getM_dealType(), mc.getM_price(), mc.getM_count(),
							mc.getM_price().multiply(new BigDecimal(mc.getM_count())), mc.getM_id() };
		return DbService.executeUpdate(sql, arg);
	}

	/**
	 * @type LoginCheckServie.java
	 * @return boolean
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-3
	 */
	public static boolean insertUserInfo(UserInfo ui) {
		String sql = "INSERT INTO USERINFO ( USERID,USERNAME,USERTYPE,USERPWD,USERCODE,REGISTERDATE) VALUES(?, ?,?,?,?,?)";
		Object[] arg = { UUID.randomUUID().toString().replace("-", ""), ui.getUserName(), ui.getUserType(),
							ui.getUserPwd(), ui.getUserCode(), ui.getRegisterDate() };
		return DbService.executeUpdate(sql, arg);
	}

	/**
	 * @type LoginCheckServie.java
	 * @return boolean
	 * @desc
	 * @param
	 * @author gaochengliu
	 * @date 2015-3-3
	 */
	public static boolean insertMerchandiseInfo(Merchandise mc) {
		String sql = "INSERT INTO MERCHANDISEINFO ( MERCHANDISEID, MERCHANDISETYPE,MERCHANDISENAME, MERCHANDISESTANDARD,MERCHANDISEBRAND,MERCHANDISEPRICE "
							+ "   ) VALUES(?, ?,?,?,?,?)";
		Object[] arg = { UUID.randomUUID().toString().replace("-", ""), mc.getMerchandiseType(),
							mc.getMerchandiseName(), mc.getMerchandiseTandard(), mc.getMerchandiseBrand(),
							mc.getMerchandisePrice() };
		return DbService.executeUpdate(sql, arg);
	}
}
