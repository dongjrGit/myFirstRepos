package com.yinlian.wssc.web.po;

public class BankCard {
	
	private Integer id;
	
	private String openbank;
	
	private String subbank;
	
	private String name;
	
	private String number;
	
	private String numbers;
	
	private String phone;
	
	private Integer userid;

	public BankCard() {
		super();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenbank() {
		return openbank;
	}

	public void setOpenbank(String openbank) {
		this.openbank = openbank;
	}

	public String getSubbank() {
		return subbank;
	}

	public void setSubbank(String subbank) {
		this.subbank = subbank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}
   public String getNumbers(){
	   String numberss="";
	   for (int i = 0; i < number.length(); i++) {
		   if(i<12){
		   numberss += ("*");
		   }else{
			   numberss +=number.charAt(i);
		   }
		   }
	   return numberss;
   }
	public void setNumber(String number) {
		this.number = number;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	} 
	
}
