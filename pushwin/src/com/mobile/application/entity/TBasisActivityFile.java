package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisActivityFile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_ACTIVITY_FILE")
public class TBasisActivityFile implements java.io.Serializable {

	// Fields

	private String activityFileid;
	private String activityId;
	private String filename;
	private String filepath;
	private String filelen;
	private String uptime;
	private String content;
	
	private String type;
	
	
	// Constructors
	


	/** default constructor */
	public TBasisActivityFile() {
	}

	/** minimal constructor */
	public TBasisActivityFile(String activityId) {
		this.activityId = activityId;
	}

	/** full constructor */
	public TBasisActivityFile(String activityId, String filename,
			String filepath, String filelen, String uptime,String content) {
		this.activityId = activityId;
		this.filename = filename;
		this.filepath = filepath;
		this.filelen = filelen;
		this.uptime = uptime;
		this.content = content;
	}
	@Column(name = "TYPE",length=25)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "content",length=600)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getActivityFileid() {
		return activityFileid;
	}

	public void setActivityFileid(String activityFileid) {
		this.activityFileid = activityFileid;
	}

	@Column(name = "ACTIVITY_ID", nullable = false, length = 32)
	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Column(name = "FILENAME")
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "FILEPATH")
	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Column(name = "FILELEN")
	public String getFilelen() {
		return this.filelen;
	}

	public void setFilelen(String filelen) {
		this.filelen = filelen;
	}

	@Column(name = "UPTIME", length = 256)
	public String getUptime() {
		return this.uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

}