package com.yinlian.api.app.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingNewCartDto {

	public static class CookieDto {

		// / <summary>
		// / SpuId
		// / </summary>
		private Integer SpuId;
		// / <summary>
		// / 店铺Id
		// / </summary>
		private Integer ShopId;
		// / <summary>
		// / 商品ID
		// / </summary>
		private Integer GoodsID;

		// / <summary>
		// / 商品数量
		// / </summary>
		private Integer GoodsCount;

		// / <summary>
		// / 商品类型
		// / </summary>
		private Integer CartType;

		// [DefaultValue(false)]
		private boolean IsSelected;

		// / <summary>
		// / 店铺活动Id
		// / </summary>
		// [DefaultValue(0)]
		private Integer ShopActId;

		// / <summary>
		// / Spu活动Id
		// / </summary>
		// [DefaultValue(0)]
		private Integer SpuActId;

		// / <summary>
		// / 闪购或秒杀Id
		// / </summary>
		private Integer SpikeID;

		/**
		 * 购物车子表ID
		 */
		private Integer shopcartproid;
		
		/**
		 * 是否包邮
		 */
		private Integer IsPostage;
		
		/**
		 * 使用时间
		 */
		private Date usetime;
		
		private Integer shoptype;
		
		private  BigDecimal  price;
		
		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public Integer getShoptype() {
			return shoptype;
		}

		public void setShoptype(Integer shoptype) {
			this.shoptype = shoptype;
		}

		public Date getUsetime() {
			return usetime;
		}

		public void setUsetime(Date usetime) {
			this.usetime = usetime;
		}

		public Integer getIsPostage() {
			return IsPostage;
		}

		public void setIsPostage(Integer isPostage) {
			IsPostage = isPostage;
		}

		public Integer getSpuId() {
			return SpuId;
		}

		public void setSpuId(Integer spuId) {
			SpuId = spuId;
		}

		public Integer getShopId() {
			return ShopId;
		}

		public void setShopId(Integer shopId) {
			ShopId = shopId;
		}

		public Integer getGoodsID() {
			return GoodsID;
		}

		public void setGoodsID(Integer goodsID) {
			GoodsID = goodsID;
		}

		public Integer getGoodsCount() {
			return GoodsCount;
		}

		public void setGoodsCount(Integer goodsCount) {
			GoodsCount = goodsCount;
		}

		public Integer getCartType() {
			return CartType;
		}

		public void setCartType(Integer cartType) {
			CartType = cartType;
		}

		public boolean isIsSelected() {
			return IsSelected;
		}

		public void setIsSelected(boolean isSelected) {
			IsSelected = isSelected;
		}

		public Integer getShopActId() {
			return ShopActId;
		}

		public void setShopActId(Integer shopActId) {
			ShopActId = shopActId;
		}

		public Integer getSpuActId() {
			return SpuActId;
		}

		public void setSpuActId(Integer spuActId) {
			SpuActId = spuActId;
		}

		public Integer getSpikeID() {
			return SpikeID;
		}

		public void setSpikeID(Integer spikeID) {
			SpikeID = spikeID;
		}

		public Integer getShopcartproid() {
			return shopcartproid;
		}

		public void setShopcartproid(Integer shopcartproid) {
			this.shopcartproid = shopcartproid;
		}

	}

	// / <summary>
	// / 购物车信息
	// / </summary>
	public static class CartDto {
		public CartDto() {
			shops = new ArrayList<ShopShoppingCartDto>();
		}

		// / <summary>
		// / 优惠金额
		// / </summary>
		private Double delmoney;
		// / <summary>
		// / 商品件数
		// / </summary>
		private int count;

		// / <summary>
		// / 已商品件数
		// / </summary>
		private int selectedcount;

		// / <summary>
		// / 总金额
		// / </summary>
		private Double money;

		// / <summary>
		// / 应付金额
		// / </summary>
		private Double amountpayable;

		// / <summary>
		// / 店铺商品信息
		// / </summary>
		public List<ShopShoppingCartDto> shops;

		// / <summary>
		// / 是否选中
		// / </summary>
		private Boolean isselected;

		public Double getDelmoney() {
			return delmoney;
		}

		public void setDelmoney(Double delmoney) {
			this.delmoney = delmoney;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getSelectedcount() {
			return selectedcount;
		}

		public void setSelectedcount(int selectedcount) {
			this.selectedcount = selectedcount;
		}

		public Double getMoney() {
			return money;
		}

		public void setMoney(Double money) {
			this.money = money;
		}

		public Double getAmountpayable() {
			return amountpayable;
		}

		public void setAmountpayable(Double amountpayable) {
			this.amountpayable = amountpayable;
		}

		public List<ShopShoppingCartDto> getShops() {
			return shops;
		}

		public void setShops(List<ShopShoppingCartDto> shops) {
			this.shops = shops;
		}

		public Boolean getIsselected() {
			return isselected;
		}

		public void setIsselected(Boolean isselected) {
			this.isselected = isselected;
		}

	}

	// / <summary>
	// / 店铺购物车信息
	// / </summary>
	public static class ShopShoppingCartDto {
		public ShopShoppingCartDto() {
			asd = new ArrayList<ActivityShopCartDto>();
			csd = new ArrayList<CouponShopCartDto>();
			spuscd = new ArrayList<SpuShopCartDto>();
			packscd = new ArrayList<PackShopCartDto>();
		}

		// / <summary>
		// / 店铺Id
		// / </summary>
		private int shopid;
		// / <summary>
		// / 店铺名称
		// / </summary>
		private String shopname;

		// / <summary>
		// / 活动Id
		// / </summary>
		private int activityid;

		// / <summary>
		// / 优惠金额
		// / </summary>
		private Double delmoney;

		// / <summary>
		// / 商品总数
		// / </summary>
		private int count;

		// / <summary>
		// / 选中的商品总数量
		// / </summary>
		private int selectCount;

		// / <summary>
		// / 总金额
		// / </summary>
		private Double totalmoney;

		// / <summary>
		// / 选中的商品总金额
		// / </summary>
		private Double selectTotalMoney;

		// / <summary>
		// / 是否有优惠卷
		// / </summary>
		private boolean iscoupon;

		// / <summary>
		// / 店铺活动列表
		// / </summary>
		public List<ActivityShopCartDto> asd;

		// / <summary>
		// / 店铺优惠卷列表
		// / </summary>
		public List<CouponShopCartDto> csd;

		// / <summary>
		// / Spu商品列表
		// / </summary>
		public List<SpuShopCartDto> spuscd;

		// / <summary>
		// / 组合商品
		// / </summary>
		public List<PackShopCartDto> packscd;

		// / <summary>
		// / 是否选中
		// / </summary>
		private Boolean isselected;

		/**
		 * 运费
		 */
		private Double freightMoney;

		public Double getFreightMoney() {
			return freightMoney;
		}

		public void setFreightMoney(Double freightMoney) {
			this.freightMoney = freightMoney;
		}

		public int getShopid() {
			return shopid;
		}

		public void setShopid(int shopid) {
			this.shopid = shopid;
		}

		public String getShopname() {
			return shopname;
		}

		public void setShopname(String shopname) {
			this.shopname = shopname;
		}

		public int getActivityid() {
			return activityid;
		}

		public void setActivityid(int activityid) {
			this.activityid = activityid;
		}

		public Double getDelmoney() {
			return delmoney;
		}

		public void setDelmoney(Double delmoney) {
			this.delmoney = delmoney;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Double getTotalmoney() {
			return totalmoney;
		}

		public void setTotalmoney(Double totalmoney) {
			this.totalmoney = totalmoney;
		}

		public boolean isIscoupon() {
			return iscoupon;
		}

		public void setIscoupon(boolean iscoupon) {
			this.iscoupon = iscoupon;
		}

		public List<ActivityShopCartDto> getAsd() {
			return asd;
		}

		public void setAsd(List<ActivityShopCartDto> asd) {
			this.asd = asd;
		}

		public List<CouponShopCartDto> getCsd() {
			return csd;
		}

		public void setCsd(List<CouponShopCartDto> csd) {
			this.csd = csd;
		}

		public List<SpuShopCartDto> getSpuscd() {
			return spuscd;
		}

		public void setSpuscd(List<SpuShopCartDto> spuscd) {
			this.spuscd = spuscd;
		}

		public List<PackShopCartDto> getPackscd() {
			return packscd;
		}

		public void setPackscd(List<PackShopCartDto> packscd) {
			this.packscd = packscd;
		}

		public Boolean getIsselected() {
			return isselected;
		}

		public void setIsselected(Boolean isselected) {
			this.isselected = isselected;
		}

		public int getSelectCount() {
			return selectCount;
		}

		public void setSelectCount(int selectCount) {
			this.selectCount = selectCount;
		}

		public Double getSelectTotalMoney() {
			return selectTotalMoney;
		}

		public void setSelectTotalMoney(Double selectTotalMoney) {
			this.selectTotalMoney = selectTotalMoney;
		}

	}

	// / <summary>
	// / spu购物车信息
	// / </summary>
	public static class SpuShopCartDto {
		public SpuShopCartDto() {
			skuscd = new ArrayList<SkuShopCartDto>();
			asd = new ArrayList<ActivityShopCartDto>();
		}

		// / <summary>
		// / spu Id
		// / </summary>
		private int id;
		// / <summary>
		// / 商品总数
		// / </summary>
		private int count;

		// / <summary>
		// / 总金额
		// / </summary>
		private Double totalmoney;

		// / <summary>
		// / 优惠金额
		// / </summary>
		private Double delmoney;

		// / <summary>
		// / Sku商品列表
		// / </summary>
		public List<SkuShopCartDto> skuscd;

		// / <summary>
		// / 商品活动列表
		// / </summary>
		public List<ActivityShopCartDto> asd;
		
		/**
		 * 是否包邮
		 */
		private Boolean IsPostage;

		public Boolean getIsPostage() {
			return IsPostage;
		}

		public void setIsPostage(Boolean isPostage) {
			IsPostage = isPostage;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Double getTotalmoney() {
			return totalmoney;
		}

		public void setTotalmoney(Double totalmoney) {
			this.totalmoney = totalmoney;
		}

		public Double getDelmoney() {
			return delmoney;
		}

		public void setDelmoney(Double delmoney) {
			this.delmoney = delmoney;
		}

		public List<SkuShopCartDto> getSkuscd() {
			return skuscd;
		}

		public void setSkuscd(List<SkuShopCartDto> skuscd) {
			this.skuscd = skuscd;
		}

		public List<ActivityShopCartDto> getAsd() {
			return asd;
		}

		public void setAsd(List<ActivityShopCartDto> asd) {
			this.asd = asd;
		}

	}

	// / <summary>
	// / 组合商品购物车信息
	// / </summary>
	public static class PackShopCartDto {
		public PackShopCartDto() {
			skuscd = new ArrayList<SkuShopCartDto>();
		}

		// / <summary>
		// / 套餐Id
		// / </summary>
		private int id;

		// / <summary>
		// / 套餐名称
		// / </summary>
		private String name;

		// / <summary>
		// / 数量
		// / </summary>
		private int count;

		// / <summary>
		// / 价格
		// / </summary>
		private Double price;

		// / <summary>
		// / Sku商品列表
		// / </summary>
		public List<SkuShopCartDto> skuscd;

		// / <summary>
		// / 是否选中
		// / </summary>
		private Boolean isselected;

		// / <summary>
		// / 库存
		// / </summary>
		private int stock;
		/**
		 * 购物车子表ID
		 */
		private int shopcartproid;
		

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public List<SkuShopCartDto> getSkuscd() {
			return skuscd;
		}

		public void setSkuscd(List<SkuShopCartDto> skuscd) {
			this.skuscd = skuscd;
		}

		public Boolean getIsselected() {
			return isselected;
		}

		public void setIsselected(Boolean isselected) {
			this.isselected = isselected;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public int getShopcartproid() {
			return shopcartproid;
		}

		public void setShopcartproid(int shopcartproid) {
			this.shopcartproid = shopcartproid;
		}


	}

	// / <summary>
	// / sku购物车dto
	// / </summary>
	public static class SkuShopCartDto {
		// / <summary>
		// / 库存商品Id
		// / </summary>
		private int id;

		// / <summary>
		// / 名称
		// / </summary>
		private String name;

		// / <summary>
		// / 价格
		// / </summary>
		private Double price;

		// / <summary>
		// / 图片路径
		// / </summary>
		private String img;
		
		/**
		 * app图片
		 */
		private String imgurlApp;
		
		// / <summary>
		// / 数量
		// / </summary>
		private int count;

		// / <summary>
		// / 库存数量
		// / </summary>
		private int stock;
		// / <summary>
		// / 规格名称值
		// / </summary>
		private String specsinfo;

		// / <summary>
		// / 是否选中
		// / </summary>
		private Boolean isselected;

		// / <summary>
		// / 商品类型
		// / </summary>
		private int protype;

		// / <summary>
		// / 闪购或秒杀id
		// / </summary>
		private int spikeid;
		
		private int spuid;
		/**
		 * 购物车子表ID
		 */
		private int shopcartproid;
		
		/**
		 * 是否有效
		 */
		private int isvalid;
		
		/**
		 * 使用时间
		 */
		private String usetime;
		
		private  String  validdesc;
		
		public String getValiddesc() {
			return validdesc;
		}

		public void setValiddesc(String validdesc) {
			this.validdesc = validdesc;
		}

		public String getUsetime() {
			return usetime;
		}

		public void setUsetime(String usetime) {
			this.usetime = usetime;
		}

		public int getIsvalid() {
			return isvalid;
		}

		public void setIsvalid(int isvalid) {
			this.isvalid = isvalid;
		}

		public int getId() {
			return id;
		}

		public String getImgurlApp() {
			return imgurlApp;
		}

		public void setImgurlApp(String imgurlApp) {
			this.imgurlApp = imgurlApp;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public String getSpecsinfo() {
			return specsinfo;
		}

		public void setSpecsinfo(String specsinfo) {
			this.specsinfo = specsinfo;
		}

		public Boolean getIsselected() {
			return isselected;
		}

		public void setIsselected(Boolean isselected) {
			this.isselected = isselected;
		}

		public int getProtype() {
			return protype;
		}

		public void setProtype(int protype) {
			this.protype = protype;
		}

		public int getSpikeid() {
			return spikeid;
		}

		public void setSpikeid(int spikeid) {
			this.spikeid = spikeid;
		}

		public int getShopcartproid() {
			return shopcartproid;
		}

		public void setShopcartproid(int shopcartproid) {
			this.shopcartproid = shopcartproid;
		}

		public int getSpuid() {
			return spuid;
		}

		public void setSpuid(int spuid) {
			this.spuid = spuid;
		}

	}

	// / <summary>
	// / 购物车活动信息dto
	// / </summary>
	public static class ActivityShopCartDto {
		public ActivityShopCartDto() {
			skuscd = new ArrayList<SkuShopCartDto>();
			gifscd = new ArrayList<GiffullShopCartDto>();
		}

		// / <summary>
		// / 活动Id
		// / </summary>
		private int id;

		// / <summary>
		// / 活动名称
		// / </summary>
		private String name;

		// / <summary>
		// / 活动类型
		// / </summary>
		private int type;

		// / <summary>
		// / 活动类型名称
		// / </summary>
		private String typename;

		// / <summary>
		// / 活动描述
		// / </summary>
		private String desc;

		// / <summary>
		// / 优惠金额
		// / </summary>
		private Double delmoney;

		// / <summary>
		// / 是否选择了
		// / </summary>
		private Boolean isselect;

		// / <summary>
		// / Sku赠品列表
		// / </summary>
		public List<SkuShopCartDto> skuscd;

		// / <summary>
		// / 非Sku商品 赠品列表
		// / </summary>
		public List<GiffullShopCartDto> gifscd;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getTypename() {
			return typename;
		}

		public void setTypename(String typename) {
			this.typename = typename;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public Double getDelmoney() {
			return delmoney;
		}

		public void setDelmoney(Double delmoney) {
			this.delmoney = delmoney;
		}

		public Boolean getIsselect() {
			return isselect;
		}

		public void setIsselect(Boolean isselect) {
			this.isselect = isselect;
		}

		public List<SkuShopCartDto> getSkuscd() {
			return skuscd;
		}

		public void setSkuscd(List<SkuShopCartDto> skuscd) {
			this.skuscd = skuscd;
		}

		public List<GiffullShopCartDto> getGifscd() {
			return gifscd;
		}

		public void setGifscd(List<GiffullShopCartDto> gifscd) {
			this.gifscd = gifscd;
		}

	}

	// / <summary>
	// / 满赠非Sku商品 dto
	// / </summary>
	public static class GiffullShopCartDto {
		// / <summary>
		// / 赠品Id(优惠卷Id)
		// / </summary>
		private Integer id;

		// / <summary>
		// / （如果是优惠卷是面值，如果是积分 是积分值 ）
		// / </summary>
		private Double facevalue;

		// / <summary>
		// / 赠品类型（不含Sku商品）
		// / </summary>
		private int type;
		// / <summary>
		// / 数量
		// / </summary>
		private int count;

		// / <summary>
		// / 选中符合条件 为真
		// / </summary>
		private Boolean isselected;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Double getFacevalue() {
			return facevalue;
		}

		public void setFacevalue(Double facevalue) {
			this.facevalue = facevalue;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Boolean getIsselected() {
			return isselected;
		}

		public void setIsselected(Boolean isselected) {
			this.isselected = isselected;
		}

	}

	public static class CouponShopCartDto {
		// / <summary>
		// / 优惠卷Id
		// / </summary>
		private int id;
		// / <summary>
		// / 面值
		// / </summary>
		private Double facevalue;
		// / <summary>
		// / 名称
		// / </summary>
		private String name;
		// /// <summary>
		// /// 开始时间
		// /// </summary>
		// public string StartTime { get; set; }
		// /// <summary>
		// /// 结束时间
		// /// </summary>
		// public string EndTime { get; set; }
		// /// <summary>
		// / 是否已获取
		// / </summary>
		private Boolean isget;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Double getFacevalue() {
			return facevalue;
		}

		public void setFacevalue(Double facevalue) {
			this.facevalue = facevalue;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Boolean getIsget() {
			return isget;
		}

		public void setIsget(Boolean isget) {
			this.isget = isget;
		}

	}

}
