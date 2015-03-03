package com.anthole.commentest.db;

import java.util.List;

import android.hardware.Camera.Area;

public class HAT_CITY {

	private int _id;
	
	private int cityid;
	
	private String city;
	
	private int father;
	
	private List<HAT_AREA> areas;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getFather() {
		return father;
	}

	public void setFather(int father) {
		this.father = father;
	}

	public List<HAT_AREA> getAreas() {
		return areas;
	}

	public void setAreas(List<HAT_AREA> areas) {
		this.areas = areas;
	}
}
