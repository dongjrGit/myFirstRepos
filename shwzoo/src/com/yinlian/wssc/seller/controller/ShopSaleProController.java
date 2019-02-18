package com.yinlian.wssc.seller.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.util.CriteriaSaleProduct;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/seller/sales")
public class ShopSaleProController {

	@Autowired
	private OrderdetailService orderdetailService ;
	
	@RequestMapping("/getsalelist")
	public ReusltItem getsalelist(String page, String size, String type,String shopid,
			String site, String datef, String datet,String proname,String pronum,
			String countfrom,String countto){
		ReusltItem item=new ReusltItem();
		try{
			SessionUser user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(page) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex：" + page);
				return item;
			}
			if (StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex：" + size);
				return item;
			}
			if (StringUtilsEX.ToInt(type) <= 0) {
				item.setCode(-103);
				item.setDesc("统计类型参数错误，type：" + type);
				return item;
			}

			CriteriaSaleProduct criteria=new CriteriaSaleProduct();
			if(!StringUtilsEX.IsNullOrWhiteSpace(pronum)){
				criteria.setPronum(pronum.trim());
			}
			if(!StringUtilsEX.IsNullOrWhiteSpace(proname)){
				criteria.setProname(proname.trim());
			}
			if(StringUtilsEX.ToInt(countfrom)>=0){
				criteria.setCountfrom(StringUtilsEX.ToInt(countfrom));
			}
			if(StringUtilsEX.ToInt(countto)>0){
				criteria.setCountto(StringUtilsEX.ToInt(countto));
			}
			if(StringUtilsEX.ToInt(site)>0){
			criteria.setSites(StringUtilsEX.ToInt(site));
			}
			criteria.setTjtype(StringUtilsEX.ToInt(type));
			
			criteria.setShopid(user.getShopid());

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			switch (StringUtilsEX.ToInt(type)) {
			case 1:
				if (StringUtilsEX.ToShortDate(datef) == null
						|| StringUtilsEX.ToShortDate(datet) == null) {
					item.setCode(-105);
					item.setDesc("日期参数错误");
					return item;
				}
				criteria.setDatef(StringUtilsEX.ToShortDate(datef));
				criteria.setDatet(StringUtilsEX.ToShortDate(datet));
				break;
			case 2:
				Calendar calendar = Calendar.getInstance();
				String end = formatter.format(calendar.getTime());
				calendar.add(Calendar.DATE, -7);
				String start = formatter.format(calendar.getTime());
				criteria.setDatef(StringUtilsEX.ToShortDate(start));
				criteria.setDatet(StringUtilsEX.ToShortDate(end));
				break;
			case 3:
				if (StringUtilsEX.IsNullOrWhiteSpace(datef)) {
					item.setCode(-105);
					item.setDesc("日期参数错误");
					return item;
				}
				String[] ym = datef.split("-");
				if (ym.length > 1) {
					criteria.setDatey(ym[0]);
					criteria.setDatem(ym[1]);
				}
				break;
			case 4:
				if (StringUtilsEX.IsNullOrWhiteSpace(datef)) {
					item.setCode(-105);
					item.setDesc("日期参数错误");
					return item;
				}
				String[] yq = datef.split("-");
				if (yq.length > 1) {
					criteria.setDatey(yq[0]);
					criteria.setDatem(yq[1]);
				}
				break;
			case 5:
				if (StringUtilsEX.IsNullOrWhiteSpace(datef)) {
					item.setCode(-105);
					item.setDesc("日期参数错误");
					return item;
				}
				criteria.setDatey(datef);
				break;
			default:
				break;
			}
			PageBean pBean=orderdetailService.getSaleProsPage(criteria, 
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			
			item.setData(pBean.getBeanList());
			item.setPageIndex(pBean.getPc());
			item.setMaxRow(pBean.getTr());
			
		}catch(Exception e){
			item.setCode(-900);
			item.setDesc("商品销量统计异常：" + e.getMessage());
			LogHandle.error(LogType.Product, "商品销量统计异常!", e, "/seller/sales/getsalelist");
		}
		return item;
	}
}
