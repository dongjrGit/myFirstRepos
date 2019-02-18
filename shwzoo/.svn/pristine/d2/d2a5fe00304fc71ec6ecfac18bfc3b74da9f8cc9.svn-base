package com.yinlian.wssc.web.po;

import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.util.DateUtil;


public class Commentreply {
    private Integer id;

    private Integer type;

    private Integer parentid;

    private Integer createuserid;

    private Integer byreplyuserid;

    private String content;

    private Integer showname;

    private Date createtime;

    private Integer vaildflag;

    private Date deldate;
    
    private String createusername;
    
    private String byreplyusername;
    
    private String createtimestr;
    
    private List<Commentreply> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Integer getByreplyuserid() {
        return byreplyuserid;
    }

    public void setByreplyuserid(Integer byreplyuserid) {
        this.byreplyuserid = byreplyuserid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getShowname() {
        return showname;
    }

    public void setShowname(Integer showname) {
        this.showname = showname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getVaildflag() {
        return vaildflag;
    }

    public void setVaildflag(Integer vaildflag) {
        this.vaildflag = vaildflag;
    }

    public Date getDeldate() {
        return deldate;
    }

    public void setDeldate(Date deldate) {
        this.deldate = deldate;
    }
    
	public String getCreateusername() {
		return createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public String getByreplyusername() {
		return byreplyusername;
	}

	public void setByreplyusername(String byreplyusername) {
		this.byreplyusername = byreplyusername;
	}

	public String getCreatetimestr() {
		this.createtimestr = DateUtil.dateConvert(createtime);
		return createtimestr;
	}

	public void setCreatetimestr(String createtimestr) {
		this.createtimestr = createtimestr;
	}

	public List<Commentreply> getChildren() {
		return children;
	}

	public void setChildren(List<Commentreply> children) {
		this.children = children;
	}
    
}