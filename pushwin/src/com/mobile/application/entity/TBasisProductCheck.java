
	package com.mobile.application.entity;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.FetchType;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.ManyToOne;
	import javax.persistence.Table;
	import org.hibernate.annotations.GenericGenerator;

	/**
	 * TBasisProductCheck entity. @author MyEclipse Persistence Tools
	 */
	@Entity
	@Table(name = "T_BASIS_PRODUCT_CHECK")
	public class TBasisProductCheck implements java.io.Serializable {

		// Fields

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String id;
		private TBasisProduct TBasisProduct;
		private String opinion;
		private String checkTime;
		private String checkUser;

		// Constructors

		/** default constructor */
		public TBasisProductCheck() {
		}

		/** full constructor */
		public TBasisProductCheck( TBasisProduct TBasisProduct,String opinion,String checkTime,String checkUser) {
			this.TBasisProduct = TBasisProduct;
			this.opinion = opinion;
			this.checkTime = checkTime;
			this.checkUser = checkUser;
		}

		// Property accessors
		@GenericGenerator(name = "generator", strategy = "uuid.hex")
		@Id
		@GeneratedValue(generator = "generator")
		@Column(name = "ID", unique = true, nullable = false, length = 32)
		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "PRODUCT_ID", nullable = false)
		public TBasisProduct getTBasisProduct() {
			return this.TBasisProduct;
		}

		public void setTBasisProduct(TBasisProduct TBasisProduct) {
			this.TBasisProduct = TBasisProduct;
		}
		@Column(name = "OPINION")
		public String getOpinion() {
			return opinion;
		}

		public void setOpinion(String opinion) {
			this.opinion = opinion;
		}
		@Column(name = "CHECK_TIME", length = 32)
		public String getCheckTime() {
			return checkTime;
		}

		public void setCheckTime(String checkTime) {
			this.checkTime = checkTime;
		}
		@Column(name = "CHECK_USER", length = 32)
		public String getCheckUser() {
			return checkUser;
		}

		public void setCheckUser(String checkUser) {
			this.checkUser = checkUser;
		}

	}

