package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ShopSortEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ProductDto;
import com.yinlian.api.app.dto.ShopCategoryDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.CircleService;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.ShopcategoryService;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.MapDistance;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import data.StringUtil;

@Controller
@RequestMapping("/wap/zhuye")
public class WapCenterPageController {
	
    @Autowired
    private ShopService         shopService;
    @Autowired
    private ShopcategoryService shopcategoryService;
	@Autowired
	private CircleService     circleService;
	@Autowired
	private ProductService productService;
    
    
	@RequestMapping("/fjsm.html")
	public String fjsm(String isjck,String isfree,String issupport,String classid,String shopcircle,String issort,Model model){
		if (StringUtil.isNotNull(issort)) {
			model.addAttribute("sort", issort);
		}else{
			model.addAttribute("sort", "");
		}
		if (StringUtil.isNotNull(classid)) {
			model.addAttribute("classid", classid);
		}else{
			model.addAttribute("classid", "");
		}
		if (StringUtil.isNotNull(shopcircle)) {
			model.addAttribute("shopcircle", shopcircle);
		}else{
			model.addAttribute("shopcircle", "");
		}
		if (StringUtil.isNotNull(isjck)) {
			model.addAttribute("isjck", isjck);
		}else{
			model.addAttribute("isjck", "");
		}
		if (StringUtil.isNotNull(isfree)) {
			model.addAttribute("isfree", isfree);
		}else{
			model.addAttribute("isfree", "");
		}
		if (StringUtil.isNotNull(issupport)) {
			model.addAttribute("issupport", issupport);
		}else{
			model.addAttribute("issupport", "");
		}
		model.addAttribute("fl",querycategory("3").getData());
		model.addAttribute("qy", addcard("3","1","100").getData());
		
		return "/template/wap/zhuye/fjsm";
	}
	
	 /**
     *  附近商盟
     *@param ch         必填
     *@param longitude  必填
     *@param latitude   必填
     *@param isjck      非
     *@param isfree     非
     *@param issupport  非
     *@param classid    非
     *@param shopcircle 非
     *@param page        必填
     *@param size        必填
     *@return
     */
    @RequestMapping(value = "/queryscopshop")
    public @ResponseBody ReusltItem queryScope(String ch, String longitude, String latitude, String isjck,
                          String isfree, String issupport, String classid, String circleid,
                          String issort, String page, String size) {
     ReusltItem item = new ReusltItem();
     try {
         if (!StringUtilsEX.isChannelTypeExist(ch)) {
             item.setCode(-101);
             item.setDesc("登录通道参数错误");
             return item;
         }
         if (StringUtils.isBlanck(longitude) || StringUtils.isBlanck(latitude)) {
             item.setCode(-102);
             item.setDesc("请选择你所在的经纬度");
             return item;
         }
         if (StringUtilsEX.ToInt(page) < 0) {
             item.setCode(-103);
             item.setDesc("分页参数page错误:" + page);
             return item;
         }
         if (StringUtilsEX.ToInt(size) < 0) {
             item.setCode(-104);
             item.setDesc("分页参数size错误:" + size);
             return item;
         }
         Map<String, String> map = MapDistance.getSquarePoint(longitude, latitude,
             ConstanValue.DISTANCE);
         CriteriaShop criteria = new CriteriaShop();
         criteria.setLongmax(map.get("longmax"));
         criteria.setLongmin(map.get("longmin"));
         criteria.setLatmax(map.get("latmax"));
         criteria.setLatmin(map.get("latmin"));
         if (StringUtils.isNotNull(isjck)) {
             criteria.setIsjck(StringUtilsEX.toBooleanForOS(isjck));
         }
         if (StringUtils.isNotNull(isfree)) {
             criteria.setIsfree(StringUtilsEX.toBooleanForOS(isfree));
         }
         if (StringUtils.isNotNull(issupport)) {
             criteria.setIssupport(StringUtilsEX.toBooleanForOS(issupport));

         }
         if (StringUtils.isNotNull(classid)) {
             criteria.setClassid(StringUtilsEX.ToInt(classid));
         }
         if (StringUtils.isNotNull(circleid)) {
             criteria.setCircleid(StringUtilsEX.ToInt(circleid));
         }
         criteria.setLongitude(longitude);
         criteria.setLatitude(latitude);
         if (StringUtils.isNotNull(issort) && issort.equals(ShopSortEnum.距离.getValue())) {
             criteria.setOrderByClause("distances");
         }
         if (StringUtils.isNotNull(issort) && issort.equals(ShopSortEnum.评价.getValue())) {
             criteria.setOrderByClause("gooddescription");
             criteria.setSort("DESC");
         }
         if (StringUtils.isNotNull(issort) && issort.equals(ShopSortEnum.人气.getValue())) {
             criteria.setOrderByClause("popularity");
             criteria.setSort("DESC");
         }
         if (StringUtils.isNotNull(issort) && issort.equals(ShopSortEnum.人均消费.getValue())) {
             criteria.setOrderByClause("consumption");
         }
         PageBean pageBean = shopService.queryBylatAndlogitCriteria(criteria,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
         item.setCode(0);
         item.setData(pageBean.getBeanList());
         item.setMaxRow(pageBean.getTr());
         item.setPageIndex(StringUtilsEX.ToInt(page));
     } catch (Exception e) {
    	 if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
         LogHandle.error(LogType.wap,"查询附近商盟异常! 异常信息:{0}", e, "zhuye/queryscopshop");
     }
     return item;
 }


    /**
     * 查询商盟分类
     *@param ch
     *@return
     */
    @RequestMapping(value = "/querycategory", produces = "text/html;charset=UTF-8")
    public BaseResult querycategory(String ch) {
        BaseResult item = new BaseResult();
        try {
            List<ShopCategoryDto> list = shopcategoryService.getAllList();
            item.setData(list);
            item.setCode(0);
        } catch (Exception e) {
        	if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
            LogHandle.error(LogType.wap,"查询商盟分类异常! 异常信息:{0}", e, "zhuye/queryscopshop");
        }
        return item;
    }
    
    /**
	 * 查询所有的商圈
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/allCircle", produces = "text/html;charset=UTF-8")
	public @ResponseBody ReusltItem addcard(String ch,String page,String size){
			
		ReusltItem item = new ReusltItem();
		try {
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item;
			}
			Integer ipage = StringUtilsEX.ToIntNull(page);
			Integer isize = StringUtilsEX.ToIntNull(size);
			if (ipage == null || ipage <= 0) {
				ipage = 1;
			}
			if (isize == null || isize <= 0) {
				isize = 10;
			}
			Criteria criteria=new Criteria();
			PageBean listBean=circleService.queryAll(criteria,ipage, isize);
			item.setData(listBean.getBeanList());
			item.setPageIndex(ipage);
			item.setPageSize(isize);
			item.setPage(listBean.getTp());
			item.setMaxRow(listBean.getTr());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, MessageFormat.format("查询商圈异常! 异常信息:{0}",
					e.toString()), "zhuye/allCircle");
		}
		return item;
	}
    
	/**
	 * 获取子专题下的商品详细信息
	 * @param type
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/relateInfo", produces = "text/html;charset=UTF-8")
	public @ResponseBody  ReusltItem relateInfo(String type,String ids,String ch) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(type)) {
                item.setCode(-101);
                item.setDesc("关联类型不能为空");
                return item;
            }
			if(StringUtilsEX.ToInt(type)==TopicTypeEnum.商品.getValue()){
				List<ProductDto>  list=new ArrayList<ProductDto>();
				List<Integer> goodids=new ArrayList<Integer>();
				if(ids.contains(",")){
					String[] a=ids.split(",");
					for (int i = 0; i < a.length; i++) {
						goodids.add(StringUtilsEX.ToInt(a[i]));
					}
				}else{
					goodids.add(StringUtilsEX.ToInt(ids));
				}
				if(goodids!=null&&goodids.size()>0){
					for (Integer id : goodids) {
						ProductDto dto=productService.selectInfoById(id,WebSetEnum.wap.getValue());
						if(dto!=null){
							list.add(dto);
						}
					}
				}
				item.setData(list);
			}
		} catch (Exception e) {
			item.setCode(-900);
			e.printStackTrace();
			item.setDesc("新品上市异常：" + e.getMessage());
			LogHandle.error(LogType.wap, "新品上市异常异常! 异常信息:{0}", e,"/wap/centerPage/relateInfo");
		}
		return item;
	}
}
