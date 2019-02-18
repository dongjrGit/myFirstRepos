package com.yinlian.wssc.web.util;

public class CriteriaAccounts extends Criteria {

	    private String loginname; //登录账号
	    private String mobile;    //手机号码
	    private String email;     //邮箱
	    private int    roleid;    //关联角色
	    private int    departid;  //所属部门
	    private int    usertype;  //用户类型
	    private int    userid;    //用户id
	    private int    id;        //账号id
	    
		public String getLoginname() {
			return loginname;
		}
		public void setLoginname(String loginname) {
			this.loginname = loginname;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}
		public int getDepartid() {
			return departid;
		}
		public void setDepartid(int departid) {
			this.departid = departid;
		}
		public int getUsertype() {
			return usertype;
		}
		public void setUsertype(int usertype) {
			this.usertype = usertype;
		}
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
	    
	  
		
}
