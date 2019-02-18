package com.yinlian.api.app.dto;

import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.util.DateUtil;
/**
 * 团购详细信息
 * @author Administrator
 *
 */
public class GroupBuyDetailDto {

	private Integer id;          // 团购ID
	private String title;        // 标题
	private Double cprice;        // 现价
	private Double oprice;        // 原价
	private Integer shopid;      // 店铺ID
	private Date validitystart;      //开始时间
	private Date validityend;        //过期时间
	private Integer type;            //团购类型
	private Boolean isanytime;       //是否支持随时退
	private Boolean isbook;          //是否支持预约
	private Boolean isexpired;       //是否支持过期退
	private String detaildesc;       //详情页简介
	private Integer stock;           //数量
	private Integer salescount;      //销量
    private String content;          //内容详情
	private String buynotes;	     //购买须知
	private ShopBaseDto shop;           //店铺基本信息
	private List<Comment> commentlist;  //评价列表
	private List<AdvertImg> imglist;    // 图片列表
	
	public String getValiditystart() {
		return DateUtil.dateConvert(this.validitystart) ;
	}

	public void setValiditystart(Date validitystart) {
		this.validitystart = validitystart;
	}

	public String getValidityend() {
		return DateUtil.dateConvert(this.validityend) ;
	}

	public void setValidityend(Date validityend) {
		this.validityend = validityend;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getIsanytime() {
		return isanytime;
	}

	public void setIsanytime(Boolean isanytime) {
		this.isanytime = isanytime;
	}

	public Boolean getIsbook() {
		return isbook;
	}

	public void setIsbook(Boolean isbook) {
		this.isbook = isbook;
	}

	public Boolean getIsexpired() {
		return isexpired;
	}

	public void setIsexpired(Boolean isexpired) {
		this.isexpired = isexpired;
	}

	public String getDetaildesc() {
		return detaildesc;
	}

	public void setDetaildesc(String detaildesc) {
		this.detaildesc = detaildesc;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSalescount() {
		return salescount;
	}

	public void setSalescount(Integer salescount) {
		this.salescount = salescount;
	}

	public List<Comment> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(List<Comment> commentlist) {
		this.commentlist = commentlist;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getCprice() {
		return cprice;
	}

	public void setCprice(Double cprice) {
		this.cprice = cprice;
	}

	public Double getOprice() {
		return oprice;
	}

	public void setOprice(Double oprice) {
		this.oprice = oprice;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public List<AdvertImg> getImglist() {
		return imglist;
	}

	public void setImglist(List<AdvertImg> imglist) {
		this.imglist = imglist;
	}
	   public String getContent() {
	    	  if (content != null && content.indexOf("\"") > 0) {
	              this.content = content.replace("\"", "'");
	          }
	    	  this.content = content.replace("<","&lt;");
	    	  this.content = content.replace(">","&gt;");
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content == null ? null : content.trim();
	    }

	    public String getBuynotes() {
	    	if (buynotes != null && buynotes.indexOf("\"") > 0) {
	            this.buynotes = buynotes.replace("\"", "'");
	        }
	    	this.buynotes = buynotes.replace("<","&lt;");
	        this.buynotes = buynotes.replace(">","&gt;");
	        return buynotes;
	    }

	    public void setBuynotes(String buynotes) {
	        this.buynotes = buynotes == null ? null : buynotes.trim();
	    }

		public ShopBaseDto getShop() {
			return shop;
		}

		public void setShop(ShopBaseDto shop) {
			this.shop = shop;
		}
}
