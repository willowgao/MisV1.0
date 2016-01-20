package bean;

import java.sql.Timestamp;

/**
 * @type UserInfo
 * @title UserInfo.java
 * @desc 用户对象
 * @author gaochengliu
 * @date 2015-3-2
 * @version V1.0
 */
public class UserInfo {

	private String userName;

	private String userPwd;

	private String userCode;

	private String userId;

	private String userType;

	private Timestamp registerDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

}
