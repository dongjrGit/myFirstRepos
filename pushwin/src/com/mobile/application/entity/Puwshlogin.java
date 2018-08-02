package com.mobile.application.entity;

//{"sim":"460017514001697","ver":"nj:1.0.3.9","access_id":"600109","pin":"ffffffff-8eb4-fd4e-ffff-ffff8eb4fd4e","password":"c33367701511b4f6020ec61ded352059"}
public class Puwshlogin {

	private String sim;
	private String ver;
	private String access_id;
	private String pin;
	private String password;

	public Puwshlogin() {
		super();
	}

	public Puwshlogin(String sim, String ver, String access_id, String pin,
			String password) {
		super();
		this.sim = sim;
		this.ver = ver;
		this.access_id = access_id;
		this.pin = pin;
		this.password = password;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getAccess_id() {
		return access_id;
	}

	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "puwshlogin [sim=" + sim + ", ver=" + ver + ", access_id="
				+ access_id + ", pin=" + pin + ", password=" + password + "]";
	}

}
