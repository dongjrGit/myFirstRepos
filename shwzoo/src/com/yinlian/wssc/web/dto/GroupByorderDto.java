package com.yinlian.wssc.web.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupByorderDto {
		private Integer id;
		
		private Integer shopid;

	    private String ordercode;

	    private Date createtime;
	    
	    private Date starttime;

	    private Date endtime;

	    private Integer status;
	    
	    private Float orderprice;
	    
	    private Float payprice;
	    
	    private Integer count;
	    
	    private String starttimestr;
	    
	    private String endtimestr;
	    
	    private String createtimestr;
	    
	    private String shopname;
	    
	    private String username;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getShopid() {
			return shopid;
		}

		public void setShopid(Integer shopid) {
			this.shopid = shopid;
		}

		public String getOrdercode() {
			return ordercode;
		}

		public void setOrdercode(String ordercode) {
			this.ordercode = ordercode;
		}

		public Date getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}

		public Date getStarttime() {
			return starttime;
		}

		public void setStarttime(Date starttime) {
			this.starttime = starttime;
		}

		public Date getEndtime() {
			return endtime;
		}

		public void setEndtime(Date endtime) {
			this.endtime = endtime;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Float getOrderprice() {
			return orderprice;
		}

		public void setOrderprice(Float orderprice) {
			this.orderprice = orderprice;
		}

		public Float getPayprice() {
			return payprice;
		}

		public void setPayprice(Float payprice) {
			this.payprice = payprice;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		 SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			public String getStarttimestr() {
				this.starttimestr=sim.format(starttime);
				return starttimestr;
			}

			public void setStarttimestr(String starttimestr) {
				this.starttimestr = starttimestr;
			}

			public String getEndtimestr() {
				this.endtimestr=sim.format(endtime);
				return endtimestr;
			}

			public void setEndtimestr(String endtimestr) {
				this.endtimestr = endtimestr;
			}

			public String getCreatetimestr() {
				this.createtimestr=sim.format(createtime);
				return createtimestr;
			}

			public void setCreatetimestr(String createtimestr) {
				this.createtimestr = createtimestr;
			}

			public String getShopname() {
				return shopname;
			}

			public void setShopname(String shopname) {
				this.shopname = shopname;
			}

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}
}
