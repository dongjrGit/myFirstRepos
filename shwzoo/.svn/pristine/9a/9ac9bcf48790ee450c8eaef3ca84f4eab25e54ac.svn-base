package com.yinlian.wssc.platform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/WarnSku")
public class WarnSkuController {
	
	@Autowired
	private  SkuService    skuService;
	 
	 
    @RequestMapping("/getZYWarnSkuList")
    public ReusltItem  getZYWarnSkuList(String page,String size,String shopid,String gname,
    		String warnbnum,String warnenum,String stockbnum,String stockenum,HttpServletRequest request){
    	
    	ReusltItem item = new ReusltItem();
    	try{
    		
    		Integer page1 = StringUtilsEX.ToIntNull(page);
			Integer size11 = StringUtilsEX.ToIntNull(size);
			if (page1 == null || page1 <= 0) {
				page1 = 1;
			}
			if (size11 == null || size11 <= 0) {
				size11 = 10;
			}
    
			ProductCriteria criteria=new ProductCriteria();
			SessionUser user=SessionUtil.getSessionUser(request);
			if(user!=null){
				criteria.setSid(user.getShopid()+"");
			}
			criteria.setName(gname);
			criteria.setIsowend("true");
			if(StringUtilsEX.ToInt(warnbnum)>=0){
				criteria.setWarnmin(StringUtilsEX.ToInt(warnbnum));
			}
			if(StringUtilsEX.ToInt(warnenum)>=0){
				criteria.setWarnmax(StringUtilsEX.ToInt(warnenum));
			}
			
			if(StringUtilsEX.ToInt(stockbnum)>=0){
				criteria.setStockmin(StringUtilsEX.ToInt(stockbnum));
			}
			if(StringUtilsEX.ToInt(stockenum)>=0){
				criteria.setStockmax(StringUtilsEX.ToInt(stockenum));
			}
			
			PageBean list = skuService.selectWanrSkuPage(criteria,page1, size11);
			item.setData(list.getBeanList());
			if (list.getTr() == null) {
				item.setMaxRow(0);
			} else {
				item.setMaxRow(list.getTr());
			}
			item.setPageIndex(list.getPc());
			item.setPageSize(list.getPs());
    		
			
    	}catch(Exception e){
    		if (DebugConfig.BLUETOOTH_DEBUG) {
    			item.setDesc("查询消费列表异常：" + e.getMessage());
           } else {
               item.setDesc("系统错误！");
           }
           item.setCode(-900);
			LogHandle.error(LogType.Product, "查询消费列表异常! 异常信息:{0}", e,
					"/platform/WarnSku/getFinanceList");
    	}
    	return item;
    }
    
    
    @RequestMapping("/getWarnSkuList")
    public ReusltItem  getWarnSkuList(String page,String size,String shopid,String gname,
    		String warnbnum,String warnenum,String stockbnum,String stockenum){
    	
    	ReusltItem item = new ReusltItem();
    	try{
    		
    		Integer page1 = StringUtilsEX.ToIntNull(page);
			Integer size11 = StringUtilsEX.ToIntNull(size);
			if (page1 == null || page1 <= 0) {
				page1 = 1;
			}
			if (size11 == null || size11 <= 0) {
				size11 = 10;
			}
    
			ProductCriteria criteria=new ProductCriteria();
			criteria.setName(gname);
			criteria.setSid(shopid);
			if(StringUtilsEX.ToInt(warnbnum)>=0){
				criteria.setWarnmin(StringUtilsEX.ToInt(warnbnum));
			}
			if(StringUtilsEX.ToInt(warnenum)>=0){
				criteria.setWarnmax(StringUtilsEX.ToInt(warnenum));
			}
			
			if(StringUtilsEX.ToInt(stockbnum)>=0){
				criteria.setStockmin(StringUtilsEX.ToInt(stockbnum));
			}
			if(StringUtilsEX.ToInt(stockenum)>=0){
				criteria.setStockmax(StringUtilsEX.ToInt(stockenum));
			}
			criteria.setIsowend("false");
    		
			PageBean list = skuService.selectWanrSkuPage(criteria,page1, size11);
			item.setData(list.getBeanList());
			if (list.getTr() == null) {
				item.setMaxRow(0);
			} else {
				item.setMaxRow(list.getTr());
			}
			item.setPageIndex(list.getPc());
			item.setPageSize(list.getPs());
    		
			
    	}catch(Exception e){
    		if (DebugConfig.BLUETOOTH_DEBUG) {
    			item.setDesc("调整库存异常：" + e.getMessage());
           } else {
               item.setDesc("系统错误！");
           }
           item.setCode(-900);
			item.setDesc("查询消费列表异常：" + e.getMessage());
			LogHandle.error(LogType.Product, "查询消费列表异常! 异常信息:{0}", e,
					"/platform/WarnSku/getWarnSkuList");
    	}
    	return item;
    }
    /**
     * 更新库存
     * @param skuid 
     * @param stock 库存
     * */
    @RequestMapping("/updateStock")
    public ReusltItem  updateStock(String skuid,String stock){
    	
    	ReusltItem item = new ReusltItem();
    	try{    		
    		if(StringUtilsEX.ToInt(skuid)<=0){
    			item.setCode(-101);
    			item.setDesc("商品id错误");
    			return item;
			}
    		if(StringUtilsEX.ToInt(stock)<=0){
    			item.setCode(-102);
    			item.setDesc("库存数量错误");
    			return item;
			}			
		 int result=skuService.updateSetStockById(StringUtilsEX.ToInt(stock), StringUtilsEX.ToInt(skuid));
    		if(result>0){
    			item.setCode(0);
    			item.setDesc("调整库存成功");
    		}else{
    			item.setCode(-200);
    			item.setDesc("调整库存失败");
    		}
    	}catch(Exception e){
    		if (DebugConfig.BLUETOOTH_DEBUG) {
    			item.setDesc("调整库存异常：" + e.getMessage());
           } else {
               item.setDesc("系统错误！");
           }
           item.setCode(-900);
			LogHandle.error(LogType.Product, "调整库存异常! 异常信息:{0}", e,
					"/platform/WarnSku/updateStock");
    	}
    	return item;
    }
}
