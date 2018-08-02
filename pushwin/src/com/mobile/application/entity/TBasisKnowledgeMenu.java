package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


	/**
	 * TBasisMenu entity. @author MyEclipse Persistence Tools
	 */
	@Entity
	@Table(name = "T_BASIS_KNOWLEDGE_MENU")
public class TBasisKnowledgeMenu {
		// Fields

		private String menuId;
		private String menuName;
		private String menuDesc;
		private String menuPid;
		private Integer menuLevel;
		private String menuSort;
		private String menuIcon;

		// Constructors

		/** default constructor */
		public TBasisKnowledgeMenu() {
		}

		public TBasisKnowledgeMenu(String menuId) {
			this.menuId = menuId;
		}
		
		/** full constructor */
		public TBasisKnowledgeMenu(String menuId,String menuName, String menuDesc, String menuPid, Integer menuLevel,
				String menuSort, String menuIcon) {
			this.menuId = menuId;
			this.menuName = menuName;
			this.menuDesc = menuDesc;
			this.menuPid = menuPid;
			this.menuLevel = menuLevel;
			this.menuSort = menuSort;
			this.menuIcon = menuIcon;
		}
		@GenericGenerator(name = "generator", strategy = "uuid.hex")
		@Id
		@GeneratedValue(generator = "generator")
		@Column(name = "KMENU_ID", unique = true, nullable = false, length = 32)
		public String getMenuId() {
			return menuId;
		}
		
		public void setMenuId(String menuId) {
			this.menuId = menuId;
		}
		@Column(name = "KMENU_NAME", length = 32)
		public String getMenuName() {
			return menuName;
		}
		
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		@Column(name = "KMENU_DESC", length = 128)
		public String getMenuDesc() {
			return menuDesc;
		}

		public void setMenuDesc(String menuDesc) {
			this.menuDesc = menuDesc;
		}
		@Column(name = "KMENU_PID", length = 32)
		public String getMenuPid() {
			return menuPid;
		}

		public void setMenuPid(String menuPid) {
			this.menuPid = menuPid;
		}
		@Column(name = "KMENU_LEVEL", length = 8)
		public Integer getMenuLevel() {
			return menuLevel;
		}

		public void setMenuLevel(Integer menuLevel) {
			this.menuLevel = menuLevel;
		}
		@Column(name = "KMENU_SORT", length = 10)
		public String getMenuSort() {
			return menuSort;
		}

		public void setMenuSort(String menuSort) {
			this.menuSort = menuSort;
		}
		@Column(name = "KMENU_ICON", length = 32)
		public String getMenuIcon() {
			return menuIcon;
		}

		public void setMenuIcon(String menuIcon) {
			this.menuIcon = menuIcon;
		}
		
}
