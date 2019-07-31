package com.report.model.user;

import java.util.List;

public class User {
	private int id;
	private String userLogin;
	private String userPass;
	private String displayName;// 实际姓名
	private int count;//总的文章数量
	private int fineCount;//精品资源数量
	private int psdCount;//psd数量
	private int jpgCount;//jpg数量
	private short userStatus;//0正常

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public short getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(short userStatus) {
		this.userStatus = userStatus;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFineCount() {
		return fineCount;
	}

	public void setFineCount(int fineCount) {
		this.fineCount = fineCount;
	}

	public int getPsdCount() {
		return psdCount;
	}

	public void setPsdCount(int psdCount) {
		this.psdCount = psdCount;
	}

	public int getJpgCount() {
		return jpgCount;
	}

	public void setJpgCount(int jpgCount) {
		this.jpgCount = jpgCount;
	}
}
