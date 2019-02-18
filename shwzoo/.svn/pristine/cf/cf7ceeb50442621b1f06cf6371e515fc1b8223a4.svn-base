package com.yinlian.wssc.seller.view.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yinlian.Enums.ShopTypeEnum;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.ProductImgs;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductSpecsService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpecsvaluesService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 卖家商品管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/seller/productshop")
public class ProductShopController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductSpecsService productSpecsService;
	@Autowired
	private SpecsvaluesService specsvaluesService;
	@Autowired
	private BrandService brandService;

	@Autowired
	private SpuService spuService;

	@Autowired
	private ShopService   shopService;
	
	@Autowired
	private SkuService skuService;

	@Autowired
	private ProductImgsService productImgsService;

	/**
	 * 显示商品管理的页面
	 * 
	 * @return
	 */
	@RequestMapping("/spgl_spulist")
	public String spgl_spulist() {
		
		return "seller/spgl/spgl_spulist";
	}
	/**
	 * 显示商品评价列表的页面
	 * 
	 * @return
	 */
	@RequestMapping("/evaluation_list")
	public String evaluation_list() {
		
		return "seller/spgl/evaluation_list";
	}
	/**
	 * 显示评论明细的页面
	 * @return
	 */
	@RequestMapping("/showMemberCommentDetail")
	public String showMemberCommentDetail(String type,HttpServletRequest request){
		request.setAttribute("type", type);
		return "seller/spgl/comment_detail";	
	}
	/**
	 * 显示添加spu的页面
	 * 
	 * @return
	 */
/*	@RequestMapping("/spgl_spuAdd")
	public String spgl_spuAdd(HttpServletRequest request) {
		try {
			SessionUser user=SessionState.GetCurrentUser();
			Shop shop=shopService.queryById(user.getShopid());
			request.setAttribute("stype", shop.getShoptype());
			Date nowdate = new Date();
			request.setAttribute("year", DateUtil.getYear(nowdate));
	    	request.setAttribute("month", DateUtil.getMonth(nowdate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "seller/spgl/spgl_spuAdd";
	}*/

	@RequestMapping("/spgl_spuAdd")
	public String spgl_spuAdd(HttpServletRequest request) {
		try {
			SessionUser user=SessionState.GetCurrentUser();
			Shop shop=shopService.queryById(user.getShopid());
			request.setAttribute("stype", shop.getShoptype());
			Date nowdate = new Date();
			request.setAttribute("year", DateUtil.getYear(nowdate));
	    	request.setAttribute("month", DateUtil.getMonth(nowdate));
	    	
	    	List<Integer>  yearlist=new ArrayList<Integer>();
	    	Integer  year=DateUtil.getYear(nowdate);
	    	for (int i = 0; i < 10; i++) {
				yearlist.add(year+i);
			}
	    	request.setAttribute("yearlist", yearlist);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "seller/spgl/spgl_spuAddNew";
	}
	
	/**
	 * 显示更新spu的页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/spgl_spuEdit")
	public String spgl_spuEdit(String id, HttpServletRequest request) {
		SpuWithBLOBs spu=new SpuWithBLOBs();
    	Sku sku=new Sku();
    	List<SkuShowtime> showtimelist=new ArrayList<SkuShowtime>();
    	try {
    		if(StringUtilsEX.ToInt(id)>0){
    			spu=spuService.queryById(StringUtilsEX.ToInt(id));
                if(spu!=null){
                	Shop shop=shopService.queryById(spu.getShopid());
                    request.setAttribute("stype", shop.getShoptype());
                    
                    request.setAttribute("shopname", shop.getName());
                	request.setAttribute("stype", shop.getShoptype());
                	
                	sku=skuService.getBySpuID(spu.getId());
                	
                	Date nowdate = new Date();
                	request.setAttribute("year", DateUtil.getYear(nowdate));
			    	request.setAttribute("month", DateUtil.getMonth(nowdate));
			    	
                	showtimelist=spuService.getListBySkuidAndMonth(sku.getId(),spu.getId(),
                			DateUtil.getYear(nowdate),DateUtil.getMonth(nowdate));
                	
                	List<Integer>  yearlist=new ArrayList<Integer>();
                	Integer  year=DateUtil.getYear(nowdate);
                	for (int i = 0; i < 10; i++) {
            			yearlist.add(year+i);
            		}
                	request.setAttribute("yearlist", yearlist);
    		    }
                
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.setAttribute("sku", sku);
    	request.setAttribute("spu", spu);
    	request.setAttribute("showtimelist", showtimelist);
		return "seller/spgl/spgl_spuEditNew";
	}

	/**
	 * 库存商品列表
	 * @param spuId
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_skulist")
	public String spgl_skulist(@RequestParam("spuid") String spuId,
			HttpServletRequest request) {
		try {
			SpuWithBLOBs spu = new SpuWithBLOBs();
			List<Sku> skulist = new ArrayList<Sku>();
			try {
				if (StringUtilsEX.ToInt(spuId) > 0) {
					spu = spuService.queryById(StringUtilsEX.ToInt(spuId));
					skulist = skuService.getListBySpuID(spu.getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("spu", spu);
			request.setAttribute("skulist", skulist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "seller/spgl/spgl_skulist";
	}

	/**
	 * 添加库存商品
	 * @param spuId
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_skuAdd")
	public String spgl_skuAdd(@RequestParam("spuid") String spuId,
			HttpServletRequest request) {
		String sid = "", cid = "";
		try {
			if (StringUtilsEX.ToInt(spuId) > 0) {
				SpuWithBLOBs spu = spuService.queryById(StringUtilsEX
						.ToInt(spuId));
				cid = spu.getClassid().toString();
				sid = spu.getId().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("spuid", sid);
		request.setAttribute("classid", cid);

		return "seller/spgl/spgl_skuAdd";
	}
	
	/**
	 * 编辑库存商品
	 * @param skuId
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_skuEdit")
	public String spgl_skuEdit(@RequestParam("id") String skuId,
			HttpServletRequest request) {
		 Sku sku = new Sku();
	        SpuWithBLOBs spu = new SpuWithBLOBs();
	        try {
	            if (StringUtilsEX.ToInt(skuId) > 0) {

	                sku = skuService.selectByID(StringUtilsEX.ToInt(skuId));
	                spu = spuService.queryById(sku.getSpuId());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        request.setAttribute("spu", spu);
	        request.setAttribute("data", sku);
	        return "seller/spgl/spgl_skuEdit";
	}
	
	/**
	 * 自定义分类列表
	 * @return
	 */
	@RequestMapping("/spgl_customlist")
	public String spgl_customlist(){
		 return "seller/spgl/spgl_customlist";
	}
	
	/**
	 * 自定义分类编辑
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_customEdit")
	public String spgl_customEdit(String id,Model model){
		 Category cate= new Category();
		 String fid = "", sid = "",tid="";
		  try {
	            if (StringUtilsEX.ToInt(id) > 0) {

	            	cate = categoryService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
	            	 if (cate != null) {
	                     if (cate.getFullpath().split(",").length > 0) {
	                         switch (cate.getFullpath().split(",").length) {
	                             case 1:                            	 
	                                 break;
	                             case 2:
	                                 fid = cate.getFullpath().split(",")[0];
	                                 break;
	                             case 3:
	                                 fid = cate.getFullpath().split(",")[0];
	                                 sid = cate.getFullpath().split(",")[1];
	                                 break;
	                             case 4:
	                                 fid = cate.getFullpath().split(",")[0];
	                                 sid = cate.getFullpath().split(",")[1];
	                                 tid = cate.getFullpath().split(",")[2];
	                                 break;
	                             default:
	                                 break;
	                         }
	                     }

	                 }
	               
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  model.addAttribute("fid", fid);
		  model.addAttribute("sid", sid);
		  model.addAttribute("tid", tid);
		  model.addAttribute("data", cate);
		 return "seller/spgl/spgl_customEdit";
	}
	
	/**
	 * 标准商品图片列表
	 * @param spuid
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_spuImglist")
	public String spgl_spuImglist(String spuid,HttpServletRequest request){		
		SpuWithBLOBs spu = new SpuWithBLOBs();
		List<ProductImgs> Imgs = new ArrayList<ProductImgs>();
		try {
			if (StringUtilsEX.ToInt(spuid) > 0) {
				spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
				// 获取图片列表
				Imgs = productImgsService.selectBySpu(spu.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("spu", spu);
		request.setAttribute("imglist", Imgs);
//		request.setAttribute("imgsrc", getImgurl());
		 return "seller/spgl/spgl_spuImglist";
	}
	
	/**
	 * 添加标准商品图片
	 * @param spuid
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_spuImgAdd")
	public String spgl_spuImgAdd(String spuid,HttpServletRequest request){
		
		ProductImgs Imgs = new ProductImgs();
		try {			
				if (StringUtilsEX.ToInt(spuid) > 0) {
					Imgs.setSpuId(StringUtilsEX.ToInt(spuid));
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("img", Imgs);
//		request.setAttribute("imgsrc", getImgurl());
		 return "seller/spgl/spgl_spuImgAdd";
	}
	
	/**
	 * 编辑标准商品图片
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_spuImgEdit")
	public String spgl_spuImgEdit(String id,HttpServletRequest request){
		ProductImgs Imgs = new ProductImgs();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				// 获取图片
				Imgs = productImgsService.selectByPrimaryKey(StringUtilsEX
						.ToInt(id));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("img", Imgs);
//		request.setAttribute("imgsrc", getImgurl());
		
		 return "seller/spgl/spgl_spuImgEdit";
	}
	/**
	 * 库存商品图片列表
	 * @param skuid
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_skuImglist")
	public String spgl_skuImglist(String skuid,HttpServletRequest request){
		Sku sku = new Sku();
		List<ProductImgs> Imgs = new ArrayList<ProductImgs>();
		try {
			if (StringUtilsEX.ToInt(skuid) > 0) {
				sku = skuService.selectByID(StringUtilsEX.ToInt(skuid));
				// 获取图片列表
				Imgs = productImgsService.selectBySku(sku.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sku", sku);
		request.setAttribute("imglist", Imgs);
//		request.setAttribute("imgsrc", getImgurl());
		
		 return "seller/spgl/spgl_skuImglist";
	}
	/**
	 * 添加库存图片
	 * @param skuid
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_skuImgAdd")
	public String spgl_skuImgAdd(String skuid,HttpServletRequest request){
		ProductImgs Imgs = new ProductImgs();
		try {			
				if (StringUtilsEX.ToInt(skuid) > 0) {
					Imgs.setSkuId(StringUtilsEX.ToInt(skuid));
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("img", Imgs);
//		request.setAttribute("imgsrc", getImgurl());
		
		 return "seller/spgl/spgl_skuImgAdd";
	}
	/**
	 * 编辑库存图片
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_skuImgEdit")
	public String spgl_skuImgEdit(String id,HttpServletRequest request){
		ProductImgs Imgs = new ProductImgs();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				// 获取图片
				Imgs = productImgsService.selectByPrimaryKey(StringUtilsEX
						.ToInt(id));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("img", Imgs);
//		request.setAttribute("imgsrc", getImgurl());
		
		 return "seller/spgl/spgl_skuImgEdit";
	}
	
	/**
	 * 店铺商品上下架 申请解冻
	 * @return
	 */
	@RequestMapping("/spgl_spsxj")
	public String spgl_spsxj(){
		 return "seller/spgl/spgl_spsxj";
	}
	
	/**
	 * 库存商品调价
	 * @param skuid
	 * @param request
	 * @return
	 */
	@RequestMapping("/spgl_skutj")
	public String spgl_skutj(String skuid,HttpServletRequest request){
		Sku sku = new Sku();
		try {
			if (StringUtilsEX.ToInt(skuid) > 0) {
				sku = skuService.selectByID(StringUtilsEX.ToInt(skuid));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sku", sku);
		 return "seller/spgl/spgl_skutj";
	}
	
	
//	// 获取文件服务器路径
//	private String getImgurl() {
//		ReadFileUrlUtil ef = new ReadFileUrlUtil();
//		return ef.fileUrl();
//	}
	
	/**
	 * 库存预警列表
	 * @return
	 */
	@RequestMapping("/warnSku_list")
	public String warnSku_list(){
		return "seller/spgl/warnSku_list";
	}
	
	
	@RequestMapping("/showSpuStock")
    public String showSpuStock(String spuid, HttpServletRequest request) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        Date nowdate = new Date();
        List<Integer>  yearlist=new ArrayList<Integer>();
        try {
            if (StringUtilsEX.ToInt(spuid) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
            }
        	for (int i =2017 ; i < 2027; i++) {
    			yearlist.add(i);
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("yearlist", yearlist);
        request.setAttribute("year", DateUtil.getYear(nowdate));
    	request.setAttribute("month", DateUtil.getMonth(nowdate));
        request.setAttribute("spu", spu);
        return "seller/spgl/spgl_stocklist";
    }
	    
	@RequestMapping("/addSpuStock")
    public String addSpuStock(String spuid, HttpServletRequest request) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        Sku  sku=new Sku();
        Date nowdate = new Date();
        List<Integer>  yearlist=new ArrayList<Integer>();
        try {
            if (StringUtilsEX.ToInt(spuid) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
            	sku=skuService.getBySpuID(spu.getId());
            }
        	Integer  year=DateUtil.getYear(nowdate);
        	for (int i = 0; i < 10; i++) {
    			yearlist.add(year+i);
    		}

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("year", DateUtil.getYear(nowdate));
    	request.setAttribute("month", DateUtil.getMonth(nowdate));
    	request.setAttribute("yearlist", yearlist);
        request.setAttribute("spu", spu);
        request.setAttribute("sku", sku);
        return "seller/spgl/spgl_stockadd";
    }
	
	@RequestMapping("/addSpuStockMonth")
    public String addSpuStockMonth(String spuid, HttpServletRequest request) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        Sku  sku=new Sku();
        Date nowdate = new Date();
        List<Integer>  yearlist=new ArrayList<Integer>();
        try {
            if (StringUtilsEX.ToInt(spuid) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
            	sku=skuService.getBySpuID(spu.getId());
            }
        	Integer  year=DateUtil.getYear(nowdate);
        	for (int i = 0; i < 10; i++) {
    			yearlist.add(year+i);
    		}

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("year", DateUtil.getYear(nowdate));
    	request.setAttribute("month", DateUtil.getMonth(nowdate));
    	request.setAttribute("yearlist", yearlist);
        request.setAttribute("spu", spu);
        request.setAttribute("sku", sku);
        return "seller/spgl/spgl_stockaddByMonth";
    }
	
	

}





