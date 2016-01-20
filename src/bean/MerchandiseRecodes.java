package bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class MerchandiseRecodes extends Merchandise {
	private String xh;
	private String m_id;
	private String m_type;
	private String m_dealType;
	private Date m_date;
	private BigDecimal m_price;
	private Integer m_count;
	private BigDecimal m_total;
	private String m_desc;
	private String m_creator;
	private Timestamp m_createDt;

	public String getM_dealType() {
		return m_dealType;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public void setM_dealType(String mDealType) {
		m_dealType = mDealType;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String mId) {
		m_id = mId;
	}

	public String getM_type() {
		return m_type;
	}

	public void setM_type(String mType) {
		m_type = mType;
	}

	public Date getM_date() {
		return m_date;
	}

	public void setM_date(Date mDate) {
		m_date = mDate;
	}

	public BigDecimal getM_price() {
		return m_price;
	}

	public void setM_price(BigDecimal mPrice) {
		m_price = mPrice;
	}

	public Integer getM_count() {
		return m_count;
	}

	public void setM_count(Integer mCount) {
		m_count = mCount;
	}

	public BigDecimal getM_total() {
		return m_total;
	}

	public void setM_total(BigDecimal mTotal) {
		m_total = mTotal;
	}

	public String getM_desc() {
		return m_desc;
	}

	public void setM_desc(String mDesc) {
		m_desc = mDesc;
	}

	public String getM_creator() {
		return m_creator;
	}

	public void setM_creator(String mCreator) {
		m_creator = mCreator;
	}

	public Timestamp getM_createDt() {
		return m_createDt;
	}

	public void setM_createDt(Timestamp mCreateDt) {
		m_createDt = mCreateDt;
	}

}
