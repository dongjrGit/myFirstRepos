package com.yinlian.wssc.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.util.BasicConvert;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/procuctajax")
public class ProductAjaxController {

	@Autowired
	private ProductService productService;

	private void getDateList(ReusltItem item, ProductCriteria criteria, String page, String size, String _temp)
			throws Exception {
		if (("").equals(page) || page == null)
			page = "1";
		if (("").equals(size) || size == null)
			size = "10";
		if (BasicConvert.string2Integer(page) < 0) {
			item.setCode(-102);
			item.setDesc("分页参数错误，pageindex：" + page);
			return;
		}
		if (BasicConvert.string2Integer(size) < 0) {
			item.setCode(-102);
			item.setDesc("分页参数错误，pageindex：" + size);
			return;
		}
		String status = criteria.getStatus();

		if (!StringUtilsEX.IsNullOrWhiteSpace(_temp) && !StringUtilsEX.IsNullOrWhiteSpace(status)
				&& _temp.indexOf(status) == -1&&!("2,4").equals(status)) {
			item.setCode(-102);
			item.setDesc("状态参数错误status：" + status);
			return;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(status)) {
			status = _temp;
		}
		criteria.setStatus(status);
		criteria.setSort("Desc");
		criteria.setOrderByClause("CreateTime");
		PageBean pageBean = productService.getList(criteria, BasicConvert.string2Integer(page),
				BasicConvert.string2Integer(size));
		item.setCode(0);
		item.setData(pageBean.getBeanList());
		item.setMaxRow(pageBean.getTr());
		item.setPage(pageBean.getTp());
		item.setPageSize(pageBean.getPs());
		item.setPageIndex(pageBean.getPc());

	}

	/**
	 * 获取直营商品列表
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/p_getregularlist")
	public @ResponseBody ReusltItem p_getRegularList(ProductCriteria criteria, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			criteria.setIsowend("true");
			getDateList(item, criteria, page, size, "");
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取直营商品列表的异常信息：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			 LogHandle.error(LogType.Product, "获取直营商品列表的异常信息:" ,e,"/platform/procuctajax/p_getregularlist");
		}
		return item;
	}

	/**
	 * 获取店铺商品列表
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/p_getshopList")
	public @ResponseBody ReusltItem p_getshopList(ProductCriteria criteria, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			criteria.setIsowend("false");
			getDateList(item, criteria, page, size, "");
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取店铺商品列表的异常信息：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Product, "获取店铺商品列表的异常信息:" ,e,"/platform/procuctajax/p_getshopList");
		}
		return item;
	}

	/**
	 * 获取直营商品上下架列表
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/p_getregularshelveslist")
	public @ResponseBody ReusltItem p_getRegularShelvesList(ProductCriteria criteria, String page, String size) {
		ReusltItem item = new ReusltItem();

		try {
			criteria.setIsowend("true");
			String _temp = String.valueOf(ProStatusEnum.上架.getValue()) + ","
					+ String.valueOf(ProStatusEnum.下架.getValue());
			getDateList(item, criteria, page, size, _temp);
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取直营商品上下架列表的异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Product, "获取直营商品上下架列表的异常信息:",e,"/platform/procuctajax/p_getregularshelveslist");
//			logger.error("获取商品信息出现的异常信息:" + e.getMessage(), e);
		}
		return item;
	}

	/**
	 * 获取店铺商品上下架列表
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/p_getshopshelveslist")
	public @ResponseBody ReusltItem p_getShopShelvesList(ProductCriteria criteria, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			String _temp = String.valueOf(ProStatusEnum.上架.getValue()) + ","
					+ String.valueOf(ProStatusEnum.下架.getValue()) + "," + String.valueOf(ProStatusEnum.已审核.getValue());
			criteria.setIsowend("false");
			getDateList(item, criteria, page, size, _temp);
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取店铺商品上下架列表的异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Product, "获取店铺商品上下架列表的异常信息:" , e,"/platform/procuctajax/p_getshopshelveslist");
		}
		return item;
	}

	/**
	 * 获取店铺审核商品列表
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/p_getshopauditlist")
	public @ResponseBody ReusltItem p_getShopAuditList(ProductCriteria criteria, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			criteria.setStatus(String.valueOf(ProStatusEnum.未审核.getValue()));
			criteria.setIsowend("false");
			getDateList(item, criteria, page, size, "");
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取店铺审核商品列表的异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Product, "获取店铺审核商品列表的异常信息:" ,e,"/platform/procuctajax/p_getshopauditlist");
		}
		return item;
	}
	/**
	 * 根据名称模糊检索商品列表
	 * @param name
	 * @return
	 */
	@RequestMapping("/getSpuStartWithName")
	public @ResponseBody ReusltItem getSpuStartWithName(String name){
		ReusltItem item = new ReusltItem();
		try {
			Integer shopid=1; //所属店铺ID 默认为1
			item.setData(productService.getSpuStartWithName(shopid, name));
			item.setCode(0);
			
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("根据名称模糊检索商品列表的异常信息：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Product, "根据名称模糊检索商品列表的异常信息:",e,"/platform/procuctajax/getSpuStartWithName");
		}
		return item;
	}

}
