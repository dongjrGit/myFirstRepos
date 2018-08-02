package com.mobile.application.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "T_BASIS_NOTICE")
public class TBasisNotice implements java.io.Serializable{
		// Fields
		private static final long serialVersionUID = 1L;
		private String noticeId;//主键ID
		private String noticeTitle;//公告标题
		private String noticeContent;//公告内容
		private String noticeTime;//发布时间
		private TBasisUser TBasisUser;
		private String noticeType;//类型
		private String expireFlag;

		private Set<TBasisRoleNotice> TBasisRoleNotices = new HashSet<TBasisRoleNotice>(0);
		private Set<TBasisNoticePushlist> TBasisNoticePushlists = new HashSet<TBasisNoticePushlist>(0);
		
		/** default constructor */
		public TBasisNotice() {
		}
		
		public TBasisNotice(String noticeTitle, String noticeContent, String noticeTime, String noticeType, TBasisUser TBasisUser) {
			this.noticeTitle = noticeTitle;
			this.noticeContent = noticeContent;
			this.noticeTime = noticeTime;
			this.noticeType = noticeType;
			this.TBasisUser = TBasisUser;
		}
		
		/** full constructor */
		public TBasisNotice(String noticeId,String noticeTitle,String noticeContent,String noticeTime,Set<TBasisRoleNotice> TBasisRoleNotices) {
			this.noticeId = noticeId;
			this.noticeTitle =noticeTitle;
			this.noticeContent =noticeContent;
			this.noticeTime = noticeTime;
			this.TBasisRoleNotices =  TBasisRoleNotices;
		}
		
		public TBasisNotice(String noticeId,String noticeTitle,String noticeContent,String noticeTime) {
			this.noticeId = noticeId;
			this.noticeTitle =noticeTitle;
			this.noticeContent =noticeContent;
			this.noticeTime = noticeTime;
		}

		// Property accessors
		@GenericGenerator(name = "generator", strategy = "uuid.hex")
		@Id
		@GeneratedValue(generator = "generator")
		@Column(name = "NOTICE_ID", unique = true, nullable = false, length = 32)
		public String getNoticeId() {
			return noticeId;
		}

		public void setNoticeId(String noticeId) {
			this.noticeId = noticeId;
		}
		
		@Column(name = "NOTICE_TITLE")
		public String getNoticeTitle() {
			return noticeTitle;
		}

		public void setNoticeTitle(String noticeTitle) {
			this.noticeTitle = noticeTitle;
		}
		
		@Column(name = "NOTICE_CONTENT")
		public String getNoticeContent() {
			return noticeContent;
		}

		public void setNoticeContent(String noticeContent) {
			this.noticeContent = noticeContent;
		}

		@Column(name = "NOTICE_TIME")
		public String getNoticeTime() {
			return noticeTime;
		}

		public void setNoticeTime(String noticeTime) {
			this.noticeTime = noticeTime;
		}

		@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE, mappedBy = "TBasisNotice")
		public Set<TBasisRoleNotice> getTBasisRoleNotices() {
			return TBasisRoleNotices;
		}
		
		public void setTBasisRoleNotices(Set<TBasisRoleNotice> tBasisRoleNotices) {
			TBasisRoleNotices = tBasisRoleNotices;
		}
		
		@Column(name = "NOTICE_TYPE")
		public String getNoticeType() {
			return noticeType;
		}

		public void setNoticeType(String noticeType) {
			this.noticeType = noticeType;
		}
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "CREATE_USER")
		public TBasisUser getTBasisUser() {
			return this.TBasisUser;
		}

		public void setTBasisUser(TBasisUser TBasisUser) {
			this.TBasisUser = TBasisUser;
		}
		
		@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE, mappedBy = "TBasisNotice")
		public Set<TBasisNoticePushlist> getTBasisNoticePushlists() {
			return TBasisNoticePushlists;
		}

		public void setTBasisNoticePushlists(
				Set<TBasisNoticePushlist> tBasisNoticePushlists) {
			TBasisNoticePushlists = tBasisNoticePushlists;
		}
		
		@Column(name = "EXPIRE_FLAG")
		public String getExpireFlag() {
			return expireFlag;
		}

		public void setExpireFlag(String expireFlag) {
			this.expireFlag = expireFlag;
		}
}
