package com.mobile.application.commmon.xmpp;

public class UserInfo  {

	   private long userId;

	   private String userName;

	   

	   public UserInfo(long userId, String userName) {

	       this.userId = userId;

	       this.userName = userName;

	   }


	   public long getUserId() {

	       return userId;

	   }



	   public void setUserId(long userId) {

	       this.userId = userId;

	   }



	   public String getUserName() {

	       return userName;

	   }



	   public void setUserName(String userName) {

	       this.userName = userName;

	   }



	  public String getXMPPUserName() {

	       return userName + "@www.miitsoft.com";

	   } 

	   

	}
