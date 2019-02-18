package com.gps.VO;

import com.gps.entity.Address;

public class GeograHome extends Address{
	private String userAccessId;//外访人员工号
	private Latlons userLatlon; //外访人员经纬度
	private String minDistance; //最短距离
	private Latlons workerLatlon; //受访地点经纬度
	private String visitType;    //外访类型
	private String address;		//受访位置
//	private String cellid;
//	private String lac;
//	private String mcc;
//	private String resultError;
//	private String sdb;
	
	public GeograHome(String userAccessId, Latlons userLatlon, String minDistance,
			Latlons workerLatlon, String visitType, String address) {
		super();
		this.userAccessId = userAccessId;
		this.userLatlon = userLatlon;
		this.minDistance = minDistance;
		this.workerLatlon = workerLatlon;
		this.visitType = visitType;
		this.address = address;
	}
	
	public GeograHome() {
	super();
}

	public String getUserAccessId() {
		return userAccessId;
	}
	public void setUserAccessId(String userAccessId) {
		this.userAccessId = userAccessId;
	}
	public Latlons getUserLatlon() {
		return userLatlon;
	}
	public void setUserLatlon(Latlons userLatlon) {
		this.userLatlon = userLatlon;
	}
	public String getMinDistance() {
		return minDistance;
	}
	public void setMinDistance(String minDistance) {
		this.minDistance = minDistance;
	}
	public Latlons getWorkerLatlon() {
		return workerLatlon;
	}
	public void setWorkerLatlon(Latlons workerLatlon) {
		this.workerLatlon = workerLatlon;
	}
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "GeograHome [userAccessId=" + userAccessId + ", userLatlon="
				+ userLatlon + ", minDistance=" + minDistance
				+ ", workerLatlon=" + workerLatlon + ", visitType=" + visitType
				+ ", address=" + address + "]";
	}
	
	

}
