package com.yinlian.pc.dto;

import java.util.Date;

import data.ParseUtil;

public class PcPointsDto {
	    private Integer id;

	    private Integer userid;

	    /**
	     * 积分数量
	     */
	    private Integer points;
	    
	    /**
	     * 积分
	     */
	    private Integer type;
	    
	    private Integer fromtype;

	    private Date createtime;
	    
	    private String createtimetr;
	    
	    private String username;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Date getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}

		public String getCreatetimetr() {
			this.createtimetr=ParseUtil.parseDateToString(createtime, "yyyy-MM-dd HH:mm:ss");
			return createtimetr;
		}

		public void setCreatetimetr(String createtimetr) {
			this.createtimetr = createtimetr;
		}

		public Integer getType() {
	        return type;
	    }

	    public void setType(Integer type) {
	        this.type = type;
	    }
	    public Integer getFromtype() {
	        return fromtype;
	    }

	    public void setFromtype(Integer fromtype) {
	        this.fromtype = fromtype;
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

	    public Integer getPoints() {
	        return points;
	    }

	    public void setPoints(Integer points) {
	        this.points = points;
	    }
	    
	    

}
