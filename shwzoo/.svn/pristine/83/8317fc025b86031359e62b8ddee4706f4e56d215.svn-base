package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;
/**
 * 用户消费记录
 * @author Administrator
 *
 */
public class UserfinanceDto {
	
	 private Integer id;

   private Integer userid;

   private Double   balance;

   private Double   money;

   private Integer type;

   private String  number;

   private Date    createtime;

   private Integer status;

   private String  paynum;

   private String  userip;

   private String  description;
   
   private Integer financetype;

   private String  creattimestr;

   private String username;
   
   private String  codingnum;  //编号
   
   private String  buyername;  //商户名称
   
   private  String  buyernum;   //商户编号
   
   private String  groupnum;  //供应商对应组编号
   
   private Integer paytype;
   
	public Integer getPaytype() {
	return paytype;
}

public void setPaytype(Integer paytype) {
	this.paytype = paytype;
}

	public String getBuyernum() {
		return buyernum;
	}

	public void setBuyernum(String buyernum) {
		this.buyernum = buyernum;
	}

	public String getCodingnum() {
		return codingnum;
	}

	public void setCodingnum(String codingnum) {
		this.codingnum = codingnum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getFinancetype() {
		return financetype;
	}

	public void setFinancetype(Integer financetype) {
		this.financetype = financetype;
	}

	public Integer getId() {
       return id;
   }

   public void setId(Integer id) {
       this.id = id;
   }

   public Integer getUserid() {
       return userid;
   }

   public void setUserid(Integer userid) {
       this.userid = userid;
   }

   public Double getBalance() {
       return balance;
   }

   public void setBalance(Double balance) {
       this.balance = balance;
   }

   public Double getMoney() {
       return money;
   }

   public void setMoney(Double money) {
       this.money = money;
   }

   public Integer getType() {
       return type;
   }

   public void setType(Integer type) {
       this.type = type;
   }

   public String getNumber() {
       return number;
   }

   public void setNumber(String number) {
       this.number = number == null ? null : number.trim();
   }

   public Date getCreatetime() {
       return createtime;
   }

   public void setCreatetime(Date createtime) {
       this.createtime = createtime;
   }

   public Integer getStatus() {
       return status;
   }

   public void setStatus(Integer status) {
       this.status = status;
   }

   public String getPaynum() {
       return paynum;
   }

   public void setPaynum(String paynum) {
       this.paynum = paynum == null ? null : paynum.trim();
   }

   public String getUserip() {
       return userip;
   }

   public void setUserip(String userip) {
       this.userip = userip == null ? null : userip.trim();
   }

   public String getDescription() {
       return description;
   }

   public void setDescription(String description) {
       this.description = description == null ? null : description.trim();
   }

   /**
    * Getter method for property <tt>creattimestr</tt>.
    * 
    * @return property value of creattimestr
    */
   public String getCreattimestr() {
       this.creattimestr = DateUtil.dateConvert(createtime);
       return creattimestr;
   }

   /**
    * Setter method for property <tt>creattimestr</tt>.
    * 
    * @param creattimestr value to be assigned to property creattimestr
    */
   public void setCreattimestr(String creattimestr) {
       this.creattimestr = creattimestr;
   }

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public String getGroupnum() {
		return groupnum;
	}

	public void setGroupnum(String groupnum) {
		this.groupnum = groupnum;
	}
}
