package com.anthole.commentest.db;

import java.util.List;

import net.tsz.afinal.annotation.sqlite.Table;

@Table(name="HAT_PROVINCE")
public class HAT_PROVINCE {

	private int _id;
	
	private int provinceid;
	
	private String province;
	
	private List<HAT_CITY> citys;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<HAT_CITY> getCitys() {
		return citys;
	}

	public void setCitys(List<HAT_CITY> citys) {
		this.citys = citys;
	}
}
