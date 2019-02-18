package com.yinlian.wssc.seller.view.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ActivityUseTypeEnum;
import com.yinlian.Enums.CouponUseTypeEnum;
import com.yinlian.Enums.ManZengTypeEnum;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.ActivityMarket;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.Fullgift;
import com.yinlian.wssc.web.po.GroupbuyWithBLOBs;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.SpikeSpu;
import com.yinlian.wssc.web.po.Spikeactivity;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.ActivityService;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.GroupBuyService;
import com.yinlian.wssc.web.service.PackageService;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/seller/YxglShop")
public class YxglShopController {
	 /**
     * 日志类
     */
    private static final Logger logger = LoggerFactory.getLogger(YxglShopController.class);
    
    @Autowired
	private CouponService couponService;
	@Autowired
	private SpuService spuService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SpikeActivityService spikeActivityService;
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private PackageService packageService;
	@Autowired
	private GroupBuyService groupBuyService;
	@Autowired
	private AdvertImgService advertImgService;
	/**
	 * 营销-优惠卷列表
	 * 
	 * @return
	 */
	@RequestMapping("/yxgl_CouponList")
	public String yxgl_CouponList(HttpServletRequest request) {
		Integer userid = 0;
		try {
			SessionUser user = SessionState.GetCurrentUser();
			userid = user.getShopid();
		} catch (Exception e) {
           e.printStackTrace();
		}
		request.setAttribute("shopid",userid);
		return "seller/yxgl/yxgl_CouponList";
	}

	/**
	 * 营销-优惠卷添加
	 * 
	 * @return
	 */
	@RequestMapping("/yxgl_CouponAdd")
	public String yxgl_CouponAdd() {
		return "seller/yxgl/yxgl_CouponAdd";
	}

	/**
	 * 营销-优惠卷编辑
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_CouponEdit")
	public String yxgl_CouponEdit(String id, HttpServletRequest request) {
		Coupon coupon = new Coupon();
		String spuname = "", spuid = "", fid = "", sid = "", tid = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				coupon = couponService.getByID(StringUtilsEX.ToInt(id));
				if (coupon.getCouponusetype() == CouponUseTypeEnum.商品
						.getValue()) {
					spuname = spuService.queryById(coupon.getUsetypeid())
							.getName();
					spuid = spuService.queryById(coupon.getUsetypeid()).getId()
							.toString();
					String fullpath = categoryService.queryById(
							spuService.queryById(coupon.getUsetypeid())
									.getClassid()).getFullpath();
					switch (fullpath.split(",").length) {
					case 1:
                        fid = fullpath.split(",")[0];
                        break;
                    case 2:
                        fid = fullpath.split(",")[0];
                        sid = fullpath.split(",")[1];
                        break;
                    case 3:
                        fid = fullpath.split(",")[0];
                        sid = fullpath.split(",")[1];
                        tid = fullpath.split(",")[2];
                        break;
					default:
						break;
					}
				}
				if(coupon.getCouponusetype() == CouponUseTypeEnum.分类
						.getValue()){
					String fullpath = categoryService.queryById(
							coupon.getUsetypeid()).getFullpath();
					switch (fullpath.split(",").length) {
					case 1:
                        fid = fullpath.split(",")[0];
                        break;
                    case 2:
                        fid = fullpath.split(",")[0];
                        sid = fullpath.split(",")[1];
                        break;
                    case 3:
                        fid = fullpath.split(",")[0];
                        sid = fullpath.split(",")[1];
                        tid = fullpath.split(",")[2];
                        break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("data", coupon);
		request.setAttribute("spuname", spuname);
		request.setAttribute("spuid", spuid);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("tid", tid);
		return "seller/yxgl/yxgl_CouponEdit";
	}

	/**
	 * 添加领取用户
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_CouponUserAdd")
	public String yxgl_CouponUserAdd(String id, HttpServletRequest request) {
		Coupon coupon = new Coupon();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				coupon = couponService.getByID(StringUtilsEX.ToInt(id));			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("data", coupon);
		return "seller/yxgl/yxgl_CouponUserAdd";
	}

	/**
	 * 优惠卷领取用户列表
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_CouponUserList")
	public String yxgl_CouponUserList(String id, HttpServletRequest request) {
		Coupon coupon = new Coupon();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				coupon = couponService.getByID(StringUtilsEX.ToInt(id));			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("data", coupon);
		return "seller/yxgl/yxgl_CouponUserList";
	}
	
	/**
	 * 秒杀活动列表
	 * @return
	 */
	@RequestMapping("/yxgl_SpikeList")
	public String yxgl_SpikeList(){
		return "seller/yxgl/yxgl_SpikeList";
	}
	/**
	 * 添加秒杀活动参与商品
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_SpikeSpuAdd")
	public String yxgl_SpikeSpuAdd(String id,HttpServletRequest request){
		String spikeid="";
		try{
			if(StringUtilsEX.ToInt(id)>0){
				spikeid=id;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("spikeid", spikeid);
		return "seller/yxgl/yxgl_SpikeSpuAdd";
	}
	
	/**
	 * 编辑秒杀活动参与商品
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_SpikeSpuEdit")
	public String yxgl_SpikeSpuEdit(String id,HttpServletRequest request){
		SpikeSpu ssSpikeSpu=new SpikeSpu();
		String spuname="";
		try{
			if(StringUtilsEX.ToInt(id)>0){
				ssSpikeSpu=spikeActivityService.selectSpikeSpuByID(StringUtilsEX.ToInt(id));
				spuname=spuService.queryById(ssSpikeSpu.getSpuid()).getName();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("data", ssSpikeSpu);
		request.setAttribute("spuname", spuname);
		return "seller/yxgl/yxgl_SpikeSpuEdit";
	}
	
	/**
	 * 秒杀活动-参与商品列表
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_SpikeSpuList")
	public String yxgl_SpikeSpuList(String id,HttpServletRequest request){
		Spikeactivity spike=new Spikeactivity();
		int isout=0;
		try{
			if(StringUtilsEX.ToInt(id)>0){
				spike=spikeActivityService.selectByID(StringUtilsEX.ToInt(id));
				Date tDate=spikeActivityService.getServiceDate();
				if(spike.getStarttime().getTime()>tDate.getTime()){
					isout=1;
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("data", spike);
		request.setAttribute("isout", isout);
		return "seller/yxgl/yxgl_SpikeSpuList";
	}
	
	/**
	 * 满减活动添加
	 * @return
	 */
	@RequestMapping("/yxgl_FullcutAdd")
	public String yxgl_FullcutAdd(){
		
		return "seller/yxgl/yxgl_FullcutAdd";
	}
	
	/**
	 * 满减活动编辑
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_FullcutEdit")
	public String yxgl_FullcutEdit(String id,HttpServletRequest request){
		ActivityMarket aMarket = new ActivityMarket();
		String spuname = "", spuid = "", fid = "", sid = "", tid = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				aMarket = activityService.getById(StringUtilsEX.ToInt(id));
				if (aMarket.getUsetype() == ActivityUseTypeEnum.针对商品.getValue()) {
					SpuWithBLOBs spu = new SpuWithBLOBs();
					spu = spuService.queryById(aMarket.getSpuid());
					spuname = spu.getName();
					spuid = spu.getId().toString();
					String fullpath = categoryService.queryById(
							spu.getClassid()).getFullpath();
					switch (fullpath.split(",").length) {
					case 1:
						fid = fullpath.split(",")[0];
						break;
					case 2:
						fid = fullpath.split(",")[0];
						sid = fullpath.split(",")[1];
						break;
					case 3:
						fid = fullpath.split(",")[0];
						sid = fullpath.split(",")[1];
						tid = fullpath.split(",")[2];
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("data", aMarket);
		request.setAttribute("spuname", spuname);
		request.setAttribute("spuid", spuid);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("tid", tid);
		return "seller/yxgl/yxgl_FullcutEdit";
	}
	
	/**
	 * 满减活动列表
	 * @return
	 */
	@RequestMapping("/yxgl_FullcutList")
	public String yxgl_FullcutList(){
		
		return "seller/yxgl/yxgl_FullcutList";
	}
	/**
	 * 满赠活动添加
	 * @return
	 */
	@RequestMapping("/yxgl_FullgiftAdd")
	public String yxgl_FullgiftAdd(){
		return "seller/yxgl/yxgl_FullgiftAdd";
	}
	
	/**
	 * 满赠活动编辑
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_FullgiftEdit")
	public String yxgl_FullgiftEdit(String id, HttpServletRequest request){
		ActivityMarket aMarket = new ActivityMarket();
		String spuname = "", spuid = "", fid = "", sid = "", tid = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				aMarket = activityService.getById(StringUtilsEX.ToInt(id));
				if (aMarket.getUsetype() == ActivityUseTypeEnum.针对商品.getValue()) {
					SpuWithBLOBs spu = new SpuWithBLOBs();
					spu = spuService.queryById(aMarket.getSpuid());
					spuname = spu.getName();
					spuid = spu.getId().toString();
					String fullpath = categoryService.queryById(
							spu.getClassid()).getFullpath();
					switch (fullpath.split(",").length) {
					case 1:
						fid = fullpath.split(",")[0];
						break;
					case 2:
						fid = fullpath.split(",")[0];
						sid = fullpath.split(",")[1];
						break;
					case 3:
						fid = fullpath.split(",")[0];
						sid = fullpath.split(",")[1];
						tid = fullpath.split(",")[2];
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("data", aMarket);
		request.setAttribute("spuname", spuname);
		request.setAttribute("spuid", spuid);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("tid", tid);
		return "seller/yxgl/yxgl_FullgiftEdit";
	}
	
	/**
	 * 满赠活动列表
	 * @return
	 */
	@RequestMapping("/yxgl_FullgiftList")
	public String yxgl_FullgiftList(){
		return "seller/yxgl/yxgl_FullgiftList";
	}
	
	/**
	 * 满赠活动赠品列表
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_GiftList")
	public String yxgl_GiftList(String id, HttpServletRequest request){
		ActivityMarket aMarket = new ActivityMarket();
//		String gifttype = "-1";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				aMarket = activityService.getById(StringUtilsEX.ToInt(id));
				List<Fullgift> list = new ArrayList<Fullgift>();
				list = activityService.getGiftListByID(aMarket.getId());
//				if (list.size() > 0) {
//					gifttype = list.get(0).getGifttype().toString();
//				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("data", aMarket);
		request.setAttribute("gifttype", ManZengTypeEnum.商品.getValue());
		return "seller/yxgl/yxgl_GiftList";
	}
	
	/**
	 * 组合商品添加
	 * @return
	 */
	@RequestMapping("/yxgl_PackageAdd")
	public String yxgl_PackageAdd(){		
		return "seller/yxgl/yxgl_PackageAdd";
	}
	
	/**
	 * 组合商品编辑
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_PackageEdit")
	public String yxgl_PackageEdit(String id, HttpServletRequest request){		
		Package pack = new Package();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				pack = packageService.getByID(StringUtilsEX.ToInt(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("data", pack);
		return "seller/yxgl/yxgl_PackageEdit";
	}
	
	/**
	 * 组合商品列表
	 * @return
	 */
	@RequestMapping("/yxgl_PackageList")
	public String yxgl_PackageList(){		
		return "seller/yxgl/yxgl_PackageList";
	}
	
	/**
	 * 组合商品赠品列表
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_PackageSku")
	public String yxgl_PackageSku(String id, HttpServletRequest request){	
		Package pack = new Package();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				pack = packageService.getByID(StringUtilsEX.ToInt(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("data", pack);
		return "seller/yxgl/yxgl_PackageSku";
	}
	
	/**
	 * 团购管理
	 * @return
	 */
	@RequestMapping("/yxgl_GroupBuyList")
	public String yxgl_GroupBuyList(){
		return "seller/yxgl/yxgl_GroupBuyList";
	}
	
	@RequestMapping("/yxgl_GroupBuyAdd")
	public String yxgl_GroupBuyAdd(){
		return "seller/yxgl/yxgl_GroupBuyAdd";
	}
	
	@RequestMapping("/yxgl_GroupBuyEdit")
	public String yxgl_GroupBuyEdit(String id,HttpServletRequest request){
		GroupbuyWithBLOBs gBloBs=new GroupbuyWithBLOBs();
		try{
			if(StringUtilsEX.ToInt(id)>0){
				gBloBs=groupBuyService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("data", gBloBs);
		return "seller/yxgl/yxgl_GroupBuyEdit";
	}
	
	/**
	 * 团购图片列表
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/yxgl_GroupBuyImgList")
	public String yxgl_GroupBuyImgList(String id,HttpServletRequest request){
		GroupbuyWithBLOBs gBloBs=new GroupbuyWithBLOBs();
		try{
			if(StringUtilsEX.ToInt(id)>0){
				gBloBs=groupBuyService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("group", gBloBs);
		return "seller/yxgl/yxgl_GroupBuyImgList";
	}
	
	@RequestMapping("/yxgl_GroupBuyImgEdit")
	public String yxgl_GroupBuyImgEdit(String id,String groupid,HttpServletRequest request){
		String actionString ="addimg";
		AdvertImg img=new AdvertImg();
		try{
			if(StringUtilsEX.ToInt(id)>0){
				img=advertImgService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
				actionString ="editImg";
			}else{
				if(StringUtilsEX.ToInt(groupid)>0){
					img.setGroupbyid(StringUtilsEX.ToInt(groupid));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("action", actionString);
		request.setAttribute("data", img);
		return "seller/yxgl/yxgl_GroupBuyImgEdit";
	}
}
