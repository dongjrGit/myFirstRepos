package com.yinlian.pc.dto;

import java.util.ArrayList;
import java.util.List;

import com.yinlian.api.app.dto.ShoppingNewCartDto.GiffullShopCartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.SkuShopCartDto;

public class OrdercartDto {

	/// <summary>
    /// 购物车信息
    /// </summary>
    public static class CartDto
    {
        public CartDto()
        {
            shops = new ArrayList<ShopCartDto>();
        }
        /// <summary>
        /// 优惠金额
        /// </summary>
        private Double delmoney;
        /// <summary>
        /// 商品件数
        /// </summary>
        private int totalcount;

        /// <summary>
        /// 总金额
        /// </summary>
        private Double totalmoney;

        /// <summary>
        /// 应付金额
        /// </summary>
        private Double paymoney ;
        
        public Double getDelmoney() {
			return delmoney;
		}

		public void setDelmoney(Double delmoney) {
			this.delmoney = delmoney;
		}

		public int getTotalcount() {
			return totalcount;
		}

		public void setTotalcount(int totalcount) {
			this.totalcount = totalcount;
		}

		public Double getTotalmoney() {
			return totalmoney;
		}

		public void setTotalmoney(Double totalmoney) {
			this.totalmoney = totalmoney;
		}

		public Double getPaymoney() {
			return paymoney;
		}

		public void setPaymoney(Double paymoney) {
			this.paymoney = paymoney;
		}
		/// <summary>
        /// 店铺商品信息
        /// </summary>
        public List<ShopCartDto> shops;

		
		public List<ShopCartDto> getShops() {
			return shops;
		}

		public void setShops(List<ShopCartDto> shops) {
			this.shops = shops;
		}
        
    }

    /// <summary>
    /// 店铺购物车信息
    /// </summary>
    public static class ShopCartDto
    {
        public ShopCartDto()
        {
            spuscd = new ArrayList<SpuShopCartDto>();
            packscd = new ArrayList<PackShopCartDto>();
        }

        /// <summary>
        /// 店铺Id
        /// </summary>
    	private int shopid;
        /// <summary>
        /// 店铺名称
        /// </summary>
    	private String shopname;

        /// <summary>
        /// 优惠金额
        /// </summary>
    	private Double delmoney;


        /// <summary>
        /// 总金额
        /// </summary>
    	private Double totalmoney;
    	
    	/**
    	 * 店铺活动信息
    	 */
    	public ActivityCartDto shopasd;

        /// <summary>
        /// Spu商品列表
        /// </summary>
    	public List<SpuShopCartDto> spuscd;

        /// <summary>
        /// 组合商品
        /// </summary>
    	public List<PackShopCartDto> packscd;
    	
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


		public Double getDelmoney() {
			return delmoney;
		}

		public void setDelmoney(Double delmoney) {
			this.delmoney = delmoney;
		}

		public Double getTotalmoney() {
			return totalmoney;
		}

		public void setTotalmoney(Double totalmoney) {
			this.totalmoney = totalmoney;
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

		public ActivityCartDto getShopasd() {
			return shopasd;
		}

		public void setShopasd(ActivityCartDto shopasd) {
			this.shopasd = shopasd;
		}
		
    }

    /// <summary>
    /// spu购物车信息
    /// </summary>
    public static class SpuShopCartDto
    {
        public SpuShopCartDto()
        {
            skuscd = new ArrayList<SkuShopCartDto>();
        }

        /// <summary>
        /// spu Id
        /// </summary>
    	private int id ;

        /// <summary>
        /// 总金额
        /// </summary>
    	private Double totalmoney;

        /// <summary>
        /// 优惠金额
        /// </summary>
    	private Double delmoney;

        /// <summary>
        /// Sku商品列表
        /// </summary>
    	public List<SkuShopCartDto> skuscd;
    	
    	/**
    	 * 商品活动信息
    	 */
    	public ActivityCartDto spuasd;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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

		public ActivityCartDto getSpuasd() {
			return spuasd;
		}

		public void setSpuasd(ActivityCartDto spuasd) {
			this.spuasd = spuasd;
		}

    }

    /// <summary>
    /// 组合商品购物车信息
    /// </summary>
    public static class PackShopCartDto
    {
        public PackShopCartDto()
        {
            skuscd = new ArrayList<SkuShopCartDto>();
        }
        /// <summary>
        /// 套餐Id
        /// </summary>
    	private int id;

        /// <summary>
        /// 套餐名称
        /// </summary>
    	private String name ;

        /// <summary>
        /// 数量
        /// </summary>
    	private int count;

        /// <summary>
        /// 价格
        /// </summary>
    	private Double price ;

        /// <summary>
        /// Sku商品列表
        /// </summary>
    	public List<SkuShopCartDto> skuscd;
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

		public int getShopcartproid() {
			return shopcartproid;
		}

		public void setShopcartproid(int shopcartproid) {
			this.shopcartproid = shopcartproid;
		}			
    	
    }

    /// <summary>
    /// sku购物车dto
    /// </summary>
    public static class SkuShopCartDto
    {
        /// <summary>
        /// 库存商品Id
        /// </summary>
    	private int id;

        /// <summary>
        /// 名称
        /// </summary>
    	private String name;

        /// <summary>
        /// 价格
        /// </summary>
    	private Double price;

        /// <summary>
        /// 图片路径
        /// </summary>
    	private String img;

        /// <summary>
        /// 数量
        /// </summary>
        private int count;

        /// <summary>
        /// 库存数量
        /// </summary>
        private int stock;
        /// <summary>
        /// 规格名称值
        /// </summary>
        private String specsinfo;

        /// <summary>
        /// 商品类型
        /// </summary>
        private int protype;

        /// <summary>
        /// 闪购或秒杀id
        /// </summary>
        private int spikeid;
        /**
    	 * 购物车子表ID
    	 */
    	 private int shopcartproid;
    	/**
    	 * SPUID
    	 */
    	private int spuid;
    	 
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

    /// <summary>
    /// 购物车活动信息dto
    /// </summary>
    public static class ActivityCartDto
    {
        public ActivityCartDto()
        {
            skuscd = new ArrayList<SkuShopCartDto>();
            gifscd = new ArrayList<GiffullShopCartDto>();
        }

        /// <summary>
        /// 活动Id
        /// </summary>
    	private int id ;

        /// <summary>
        /// 活动名称
        /// </summary>
    	private String name ;

        /// <summary>
        /// 活动类型
        /// </summary>
    	private int type;

        /// <summary>
        /// 活动类型名称
        /// </summary>
    	private String typename ;

        /// <summary>
        /// 活动描述
        /// </summary>
    	private String desc;

        /// <summary>
        /// 优惠金额
        /// </summary>
    	private Double delmoney;

        /// <summary>
        /// Sku赠品列表
        /// </summary>
    	public List<SkuShopCartDto> skuscd;

        /// <summary>
        /// 非Sku商品 赠品列表
        /// </summary>
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

    /// <summary>
    /// 满赠非Sku商品 dto
    /// </summary>
    public static class GiffullShopCartDto
    {
        /// <summary>
        /// 赠品Id(优惠卷Id)
        /// </summary>
    	private int id ;

        /// <summary>
        /// （如果是优惠卷是面值，如果是积分 是积分值 ）
        /// </summary>
    	private Double facevalue ;

        /// <summary>
        /// 赠品类型（不含Sku商品）
        /// </summary>
    	private int type ;
        /// <summary>
        /// 数量
        /// </summary>
    	private int count;

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
    	
    }
}
