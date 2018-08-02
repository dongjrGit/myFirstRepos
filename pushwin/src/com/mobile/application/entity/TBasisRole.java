package com.mobile.application.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_ROLE")
public class TBasisRole implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private String roleDesc;
	private String roleType;
	private Set<TBasisUserRole> TBasisUserRoles = new HashSet<TBasisUserRole>(0);
	private Set<TBasisRoleMenu> TBasisRoleMenus = new HashSet<TBasisRoleMenu>(0);
	private Set<TBasisRoleApk> TBasisRoleApks = new HashSet<TBasisRoleApk>(0);

	// Constructors

	/** default constructor */
	public TBasisRole() {
	}

	public TBasisRole(String roleId) {
		this.roleId = roleId;
	}
	
	/** full constructor */
	public TBasisRole(String roleName, String roleDesc,String roleType,
			Set<TBasisUserRole> TBasisUserRoles,
			Set<TBasisRoleMenu> TBasisRoleMenus,
			Set<TBasisRoleNotice> TBasisRoleNotices) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleType = roleType;
		this.TBasisUserRoles = TBasisUserRoles;
		this.TBasisRoleMenus = TBasisRoleMenus;
	}


	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ROLE_ID", unique = true, nullable = false, length = 32)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", length = 32)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "ROLE_TYPE")
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Column(name = "ROLE_DESC", length = 128)
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TBasisRole")
	public Set<TBasisUserRole> getTBasisUserRoles() {
		return this.TBasisUserRoles;
	}

	public void setTBasisUserRoles(Set<TBasisUserRole> TBasisUserRoles) {
		this.TBasisUserRoles = TBasisUserRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TBasisRole")
	public Set<TBasisRoleMenu> getTBasisRoleMenus() {
		return this.TBasisRoleMenus;
	}

	public void setTBasisRoleMenus(Set<TBasisRoleMenu> TBasisRoleMenus) {
		this.TBasisRoleMenus = TBasisRoleMenus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TBasisRole")
	public Set<TBasisRoleApk> getTBasisRoleApks() {
		return TBasisRoleApks;
	}

	public void setTBasisRoleApks(Set<TBasisRoleApk> tBasisRoleApks) {
		TBasisRoleApks = tBasisRoleApks;
	}
}