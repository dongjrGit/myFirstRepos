package com.techown.wssc.web.po;

import java.util.Date;

import data.ParseUtil;

public class RcmdScenic {
    private Integer id;

    private Integer scenicid;

    private String img;

    private String remark;

    private Integer type;

    private String operator;

    private Date createtime;

    private Date updatetime;

    private Integer state;

    private String ext1;

    private String ext2;

    private String scenicname;
    
    private String updatetimestr;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScenicid() {
		return scenicid;
	}

	public void setScenicid(Integer scenicid) {
		this.scenicid = scenicid;
	}

	public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

	public String getScenicname() {
		return scenicname;
	}

	public void setScenicname(String scenicname) {
		this.scenicname = scenicname;
	}

	public String getUpdatetimestr() {
		if(null!=updatetime){
			this.updatetimestr =ParseUtil.parseDateToString(updatetime, "yyyy-MM-dd");	
		}
		return updatetimestr;
	}

	public void setUpdatetimestr(String updatetimestr) {
		this.updatetimestr = updatetimestr;
	}
	
}