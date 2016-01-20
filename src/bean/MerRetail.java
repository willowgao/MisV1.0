package bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MerRetail {

	private boolean isCheck;
	private String merretaildetailId;
	private String merretailId;
	private String merRetailName;
	private String merRetailStandard;
	private String merRetailBrand;
	private BigDecimal merRetailPrice;
	private Integer merRetailCount;
	private BigDecimal merRetailTotalPrice;
	private Timestamp merRetailCreateDt;
	private String merRetailCreator;
	private String merRetailDesc;
	private String merchandiseid;

	public String getMerchandiseid() {
		return merchandiseid;
	}

	public void setMerchandiseid(String merchandiseid) {
		this.merchandiseid = merchandiseid;
	}

	public String getMerRetailDesc() {
		return merRetailDesc;
	}

	public void setMerRetailDesc(String merRetailDesc) {
		this.merRetailDesc = merRetailDesc;
	}

	public String getMerretaildetailId() {
		return merretaildetailId;
	}

	public void setMerretaildetailid(String merretaildetailId) {
		this.merretaildetailId = merretaildetailId;
	}

	public String getMerretailId() {
		return merretailId;
	}

	public void setMerretailId(String merretailId) {
		this.merretailId = merretailId;
	}

	public boolean getIsCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = true;
	}

	public String getMerRetailName() {
		return merRetailName;
	}

	public void setMerRetailName(String merRetailName) {
		this.merRetailName = merRetailName;
	}

	public String getMerRetailBrand() {
		return merRetailBrand;
	}

	public String getMerRetailStandard() {
		return merRetailStandard;
	}

	public void setMerRetailStandard(String merRetailStandard) {
		this.merRetailStandard = merRetailStandard;
	}

	public void setMerRetailBrand(String merRetailBrand) {
		this.merRetailBrand = merRetailBrand;
	}

	public BigDecimal getMerRetailPrice() {
		return merRetailPrice;
	}

	public void setMerRetailPrice(BigDecimal merRetailPrice) {
		this.merRetailPrice = merRetailPrice;
	}

	public Integer getMerRetailCount() {
		return merRetailCount;
	}

	public void setMerRetailCount(Integer merRetailCount) {
		this.merRetailCount = merRetailCount;
	}

	public BigDecimal getMerRetailTotalPrice() {
		return merRetailTotalPrice;
	}

	public void setMerRetailTotalPrice(BigDecimal merRetailTotalPrice) {
		this.merRetailTotalPrice = merRetailTotalPrice;
	}

	public Timestamp getMerRetailCreateDt() {
		return merRetailCreateDt;
	}

	public void setMerRetailCreateDt(Timestamp merRetailCreateDt) {
		this.merRetailCreateDt = merRetailCreateDt;
	}

	public String getMerRetailCreator() {
		return merRetailCreator;
	}

	public void setMerRetailCreator(String merRetailCreator) {
		this.merRetailCreator = merRetailCreator;
	}

}
