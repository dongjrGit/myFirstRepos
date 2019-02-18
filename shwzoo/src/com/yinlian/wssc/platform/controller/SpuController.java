/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.ShopProType;
import com.yinlian.Enums.ShopStatusEnum;
import com.yinlian.Enums.ShopTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.dto.InsertSkuShowtimeDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.ProductImgs;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.SpuUpdateStatus;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductSpecsService;
import com.yinlian.wssc.web.service.ProductUpdateRecordsService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.BasicConvert;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 * @version $Id: SpuController.java, v 0.1 2016年3月7日 下午3:36:56 Administrator Exp
 *          $
 */
@RestController
@RequestMapping("/platform/spu")
public class SpuController {

    @Autowired
    private SpuService          spuService;

    @Autowired
    private ProductSpecsService productSpecsService;

    @Autowired
    private ProductImgsService  productImgsService;
    
    @Autowired
    private ShopService         shopService;

    SessionUser                 user = null;
    @Autowired
    private OperaterecordsService operaterecordsService ;
    
    @Autowired
    private ProductUpdateRecordsService productUpdateRecordsService;
    
    @Autowired
    private SkuService          skuService;

    private void UpdateBatchStatus(String spuids, BaseResult item, int status, ShopProType sptype)
                                                                                                  throws Exception {
        if (StringUtilsEX.IsNullOrWhiteSpace(spuids)) {
            item.setCode(-102);
            item.setDesc("商品spuids参数错误，spuids：" + spuids);
            return;
        }
        String[] spuidarr = spuids.split(",");
        String _temp = "";
        String oldStatus;
        if(status==3){
        	oldStatus="4";
        }else{
        	oldStatus="3";
        }
        String ip = GetIpAddresss.getIpAddr();
        String SStatus=String.valueOf(status);
       
        for (String s : spuidarr) {
            if (StringUtilsEX.ToInt(s) > 0) {
            	 if(status == ProStatusEnum.上架.getValue()){
                 	Shop shop=shopService.getShopBySpuID(StringUtilsEX.ToInt(s));
                 	if(shop==null){
                 		item.setCode(-103);
                        item.setDesc("根据spuid检索不到对应店铺信息，商品不能上架。spuid：" + s);
                        return;
                 	}
                 	if(shop.getStatus()!=ShopStatusEnum.Open.getValue()){
                 		item.setCode(-104);
                        item.setDesc("店铺"+shop.getName()+"不是营业中商品不能上架，spuid：" + s);
                        return;
                 	}
                 }
                if (StringUtilsEX.IsNullOrWhiteSpace(_temp))
                    _temp = s;
                else
                    _temp += "," + s;
                continue;
            }
            item.setCode(-102);
            item.setDesc("商品spuids参数错误，spuid：" + spuids);
            return;
        }
        SpuUpdateStatus sus = new SpuUpdateStatus();
        sus.setIsowend(sptype.getValue());
        sus.setStatus(status);
        sus.setIds(_temp);
        if (status == ProStatusEnum.上架.getValue()) {
            spuService.updateShelves(sus);
            for (String s : spuidarr) {
            	//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						productUpdateRecordsService.insertProSXJ("status", oldStatus, SStatus, 
    								StringUtilsEX.ToInt(s), user.getUserId(), user.getName(),ip);
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"上架/下架店铺商品操作记录出错! 异常信息:",
    								e, "/platform/Spu/spuFreezeOrTraw");
    					}
    					
    				}
    			});
            }
        } else {
            spuService.updateBatchStatus(sus);
            for (String s : spuidarr) {
            	//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						productUpdateRecordsService.insertProSXJ("status", oldStatus, SStatus, 
    								StringUtilsEX.ToInt(s), user.getUserId(), user.getName(),ip);
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"上架/下架店铺商品操作记录出错! 异常信息:",
    								e, "/platform/Spu/spuFreezeOrTraw");
    					}
    					
    				}
    			});
            }
        }

        item.setCode(0);
    }

    private void UpdateBatchShelves(String spuids, BaseResult item, String status,
                                    ShopProType sptype) throws Exception {
        int _status = StringUtilsEX.ToInt(status);
        if (_status != ProStatusEnum.上架.getValue() && _status != ProStatusEnum.下架.getValue()) {
            item.setCode(-101);
            item.setDesc("状态参数错误，status：" + status);
            return;
        }
        UpdateBatchStatus(spuids, item, _status, sptype);
    }

    private void UpdateBatchAudit(String status, BaseResult item, String spuids, ShopProType sptype)
                                                                                                    throws Exception {
        int _status = StringUtilsEX.ToInt(status);
        if (_status != ProStatusEnum.下架.getValue() && _status != ProStatusEnum.冻结.getValue()) {
            item.setCode(-101);
            item.setDesc("状态参数错误，status：" + status);
            return;
        }
        UpdateBatchStatus(spuids, item, _status, sptype);
    }

    /**
     * 查询直营商品的分页信息
     * 
     * @param criteria
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryDirectGoods")
    public @ResponseBody ReusltItem queryDirectGoods(Criteria criteria, String page, String size) {
        ReusltItem item = new ReusltItem();
        try {
            if (BasicConvert.string2Integer(page) < 0) {
                item.setCode(-102);
                item.setDesc("分页参数错误，pageindex：" + page);
                return item;
            }
            if (BasicConvert.string2Integer(size) < 0) {
                item.setCode(-102);
                item.setDesc("分页参数错误，pageindex：" + size);
                return item;
            }
            PageBean pageBean = spuService.queryByCriteria(criteria,
                BasicConvert.string2Integer(page), BasicConvert.string2Integer(size));
            item.setCode(0);
            item.setDesc("查询成功");
            item.setData(pageBean.getBeanList());
            item.setSum(pageBean.getTr());
            item.setPage(pageBean.getTp());
            item.setPageSize(pageBean.getPs());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询直营商品异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "查询直营商品异常:", e,
                "platform/Spu/queryDirectGoods");
        }
        return item;
    }

    /**
     * 删除spu
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deletDirect")
    public @ResponseBody ReusltItem deletDirect(String id) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            if (BasicConvert.string2Integer(id) < 0) {
                item.setCode(-101);
                item.setDesc("直营商品的id错误,id" + id);
                return item;
            }
            int result = spuService.delete(BasicConvert.string2Integer(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
                LogHandle.info(LogType.Role,
                    MessageFormat.format("删除成功! ID:{0},操作人ID:{1}", id, user.getUserId()),
                    "platform/Spu/deletDirect");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_zyspgl_list.jsp", "/platform/Spu/deletDirect", "删除商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除商品操作记录出错! 异常信息:",
    								e, "/platform/Spu/deletDirect");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
                LogHandle.info(LogType.Role,
                    MessageFormat.format("删除失败! ID:{0},操作人ID:{1}", id, user.getUserId()),
                    "platform/Spu/deletDirect");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("删除商品异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle
                .error(LogType.Product, "删除商品异常:" + e.getMessage(), "platform/Spu/deletDirect");
        }
        return item;
    }

    /**
     * 添加商品
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
	@RequestMapping("/insertSpu")
    public ReusltItem insertDirect(String name, String customcid, String classid,String isnk,
             String title,String shopid, String titleShort, String tag, String imgurlApp,
             String weight, String producer, String pack,String shelflife, String description,
             String spuinfo, String packinglist,String afterservice,String price, String isoffer,String tnum, 
             String skuparam,String showy,String showm,String showdays,String istoday,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            SpuWithBLOBs record = new SpuWithBLOBs();
            if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
                item.setCode(-101);
                item.setDesc("商品名称不能为空");
                return item;
            }
            Shop shop = shopService.queryById(StringUtilsEX.ToInt(shopid));
            if(shop==null || shop.getId() <= 0){
            	 item.setCode(-201);
                 item.setDesc("店铺id参数错误");
                 return item;
            }
            if(name.length()>100){
            	item.setCode(-112);
            	item.setDesc("商品名称过长");
            	return item;
            }
            Spu spu=spuService.getByName(name, null);
            if(spu!=null){
            	item.setCode(-113);
            	item.setDesc("商品名称重复，请更新商品名称重新提交");
            	return item;
            }
            if(StringUtilsEX.ToDouble(price)<=0){
            	item.setCode(-114);
            	item.setDesc("商品原价参数错误");
            	return item;
            }
            if (StringUtilsEX.IsNullOrWhiteSpace(tnum)) {
                item.setCode(-102);
                item.setDesc("商品ID不能为空");
                return item;
            }
            
            if(shop.getShoptype().equals(ShopTypeEnum.马戏团票.getValue())){
            	//本店铺内的马戏团票可以重复
            	Sku skut=skuService.getByShopTicketnum(tnum, shop.getId(),null);
            	if(skut!=null){
            		item.setCode(-107);
         			item.setDesc("商品ID重复");
         			return item;
            	}
            }else{
            	Sku skut=skuService.getByTicketnum(tnum, null);
            	if(skut!=null){
            		item.setCode(-107);
         			item.setDesc("商品ID重复");
         			return item;
            	}
            }
            
        	
            record.setName(name.trim());
            record.setTitle(title);
            record.setTitleShort(titleShort);
            record.setTag(tag);
            record.setProducer(producer);
            record.setPack(StringUtilsEX.ToIntNull(pack));
            record.setWeight(StringUtilsEX.ToDecimalNull(weight));
            record.setShelflife(StringUtilsEX.ToIntNull(shelflife));
            record.setCommentcount(0);
            record.setIsoffer(StringUtilsEX.ToInt(isoffer));
            record.setProtype(StringUtilsEX.ToInt(isnk));
            record.setIstoday(StringUtilsEX.ToInt(istoday));
            if(!StringUtilsEX.IsNullOrWhiteSpace(imgurlApp)){
            	String [] imgstr=imgurlApp.split(",");
            	if(imgstr.length>0){
            		if(imgstr.length==1){
            			record.setImgurl(imgstr[0]);
            			record.setImgurlApp(imgstr[0]);
            			record.setImgurlWap(imgstr[0]);
            			record.setImgurlWechat(imgstr[0]);
            		}else{
            			record.setImgurl(imgstr[1]);
            			record.setImgurlApp(imgstr[0]);
            			record.setImgurlWap(imgstr[0]);
            			record.setImgurlWechat(imgstr[0]);
            		}
            		
            	}
            }
            record.setDescription(description);
            record.setSpuinfo(spuinfo);
            record.setPackinglist(packinglist);
            record.setAfterservice(afterservice);
            record.setCreatetime(new Date());
            record.setIsdel(false);
            record.setShopid(shop.getId());
            record.setUserid(user.getUserId());
            record.setStatus(ProStatusEnum.上架.getValue());
            record.setIsowned(false);
            record.setPronum(ProductNumUtil.getSkuNum());
            record.setPrice(new BigDecimal(price));
            record.setSalescount(0);
            record.setShelvetime(record.getCreatetime());
            
            
            //库存商品信息
            Sku sku = new Sku();
            sku.setName(record.getName());
            sku.setNum(ProductNumUtil.getSkuNum());
            sku.setPrice(new BigDecimal(price));
            sku.setTicketnum(tnum);
            sku.setAppprice(new BigDecimal(price));
            sku.setIstoday(0);
            sku.setSalescount(0);
            sku.setStock(1);
            sku.setWarnnum(0);
            //库存商品图片 开始
            sku.setImgurlApp(record.getImgurlApp());
            sku.setImgurl(record.getImgurl());
            sku.setImgurlCart(record.getImgurlApp());
            sku.setImgurlOrderdetail(record.getImgurlApp());
            sku.setImgurlOrderlist(record.getImgurlApp());
            sku.setImgurlWap(record.getImgurlApp());
            sku.setImgurlWechat(record.getImgurlApp());
            //库存商品图片 结束
            
            // 校验商品包装信息
         	List<InsertSkuShowtimeDto> skudtos = JSON.parseArray(skuparam, InsertSkuShowtimeDto.class);
         	
         	List<SkuShowtime>  showtimes=new ArrayList<SkuShowtime>();
         	if (skudtos != null && skudtos.size() >= 0) {
         		for (InsertSkuShowtimeDto skudto : skudtos) {
         			SkuShowtime showtime=new SkuShowtime();
         			showtime.setShowyear(skudto.getShowyear());
         			showtime.setShowmonth(skudto.getShowmonth());
         			showtime.setShowdays(skudto.getShowdays());
         			if(StringUtilsEX.ToInt(skudto.getStock())<0){
         				showtime.setStock(0);
         			}else{
         				showtime.setStock(StringUtilsEX.ToInt(skudto.getStock()));
         			}
         			if(StringUtilsEX.ToDouble(skudto.getPrice())<0){
         				showtime.setPrice(new BigDecimal(0));
         			}else{
         				showtime.setPrice(new BigDecimal(skudto.getPrice()));
         			}
         			showtimes.add(showtime);
				}
         	}
             
            int code = spuService.insertSpuNew(record, sku,showtimes);
            if (code > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_proedit.jsp", "/platform/Spu/insertDirect", "添加直营商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加直营商品操作记录出错! 异常信息:",
    								e, "/platform/Spu/insertDirect");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("添加失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("添加直营商品异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "添加直营商品异常:" ,e,
                "platform/Spu/insertDirect");
        }
        return item;
    }


    /**
     * 更新spu
     * 
     * @param spuWithBLOBs
     * @return
     */
    @RequestMapping("/updateSpu")
    public @ResponseBody ReusltItem updateDirect(String id, String name, String customcid, 
    		String classid, String title,String shopid, String isnk,
    		String titleShort, String tag, String imgurlApp,
            String weight, String producer, String pack,String shelflife, String description,
            String spuinfo, String packinglist,String afterservice,String price, String isoffer,String tnum,
            String skuparam,String showy,String showm,String showdays,
            String skuid,String year,String month,String istoday) {
        ReusltItem item = new ReusltItem();

        try {
            user = SessionState.GetCurrentUser();
            SpuWithBLOBs record = new SpuWithBLOBs();
            if (StringUtilsEX.ToInt(id) <= 0) {
                item.setCode(-101);
                item.setDesc("商品ID参数错误");
                return item;
            }
            record = spuService.queryById(StringUtilsEX.ToInt(id));
            if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
                item.setCode(-102);
                item.setDesc("商品名称不能为空");
                return item;
            }
            if(name.length()>100){
            	item.setCode(-112);
            	item.setDesc("商品名称过长");
            	return item;
            }
            Spu spu=spuService.getByName(name, record.getId());
            if(spu!=null){
            	item.setCode(-112);
            	item.setDesc("商品名称重复，请更新商品名称重新提交");
            	return item;
            }
            if(StringUtilsEX.ToDouble(price)<=0){
            	item.setCode(-114);
            	item.setDesc("商品原价参数错误");
            	return item;
            }
            if (StringUtilsEX.IsNullOrWhiteSpace(tnum)) {
                item.setCode(-102);
                item.setDesc("商品ID不能为空");
                return item;
            }
            Shop shop = shopService.queryById(StringUtilsEX.ToInt(shopid));
            if(shop==null || shop.getId() <= 0){
            	 item.setCode(-201);
                 item.setDesc("店铺id参数错误");
                 return item;
            }
            if(shop.getShoptype().equals(ShopTypeEnum.马戏团票.getValue())){
            	//本店铺内的马戏团票可以重复
            	Sku skut=skuService.getByShopTicketnum(tnum, shop.getId(),StringUtilsEX.ToInt(skuid));
            	if(skut!=null){
            		item.setCode(-107);
         			item.setDesc("商品ID重复");
         			return item;
            	}
            }else{
            	Sku skut=skuService.getByTicketnum(tnum, StringUtilsEX.ToInt(skuid));
            	if(skut!=null){
            		item.setCode(-107);
         			item.setDesc("商品ID重复");
         			return item;
            	}
            }
            
            record.setName(name.trim());

            record.setTitleShort(titleShort);
            if (StringUtilsEX.ToInt(customcid) > 0) {
                record.setCustomcid(StringUtilsEX.ToInt(customcid));
            }
            record.setTag(tag);
            record.setProducer(producer);
            record.setPack(StringUtilsEX.ToIntNull(pack));
            record.setWeight(StringUtilsEX.ToDecimalNull(weight));
            record.setShelflife(StringUtilsEX.ToIntNull(shelflife));            
            record.setIsoffer(StringUtilsEX.ToInt(isoffer));
            record.setPrice(new BigDecimal(price));
            record.setProtype(StringUtilsEX.ToInt(isnk));
            record.setIstoday(StringUtilsEX.ToInt(istoday));
            //标准商品表图片
            if(!StringUtilsEX.IsNullOrWhiteSpace(imgurlApp)){
            	String [] imgstr=imgurlApp.split(",");
            	if(imgstr.length>0){
            		if(imgstr.length==1){
            			record.setImgurl(imgstr[0]);
            			record.setImgurlApp(imgstr[0]);
            			record.setImgurlWap(imgstr[0]);
            			record.setImgurlWechat(imgstr[0]);
            		}else{
            			record.setImgurl(imgstr[1]);
            			record.setImgurlApp(imgstr[0]);
            			record.setImgurlWap(imgstr[0]);
            			record.setImgurlWechat(imgstr[0]);
            		}
            		
            	}
            }
            
            record.setDescription(description);
            record.setSpuinfo(spuinfo);
            record.setPackinglist(packinglist);
            record.setAfterservice(afterservice);
//            record.setStatus(ProStatusEnum.下架.getValue());
            
            // 校验商品包装信息
         	List<InsertSkuShowtimeDto> skudtos = JSON.parseArray(skuparam, InsertSkuShowtimeDto.class);
         	
         	List<SkuShowtime>  showtimes=new ArrayList<SkuShowtime>();
         	if (skudtos != null && skudtos.size() >= 0) {
         		for (InsertSkuShowtimeDto skudto : skudtos) {
         			SkuShowtime showtime=new SkuShowtime();
         			showtime.setShowyear(skudto.getShowyear());
         			showtime.setShowmonth(skudto.getShowmonth());
         			showtime.setShowdays(skudto.getShowdays());
         			if(StringUtilsEX.ToInt(skudto.getStock())<0){
         				showtime.setStock(0);
         			}else{
         				showtime.setStock(StringUtilsEX.ToInt(skudto.getStock()));
         			}
         			if(StringUtilsEX.ToDouble(skudto.getPrice())<0){
         				showtime.setPrice(new BigDecimal(0));
         			}else{
         				showtime.setPrice(new BigDecimal(skudto.getPrice()));
         			}
         			showtimes.add(showtime);
				}
         	}
         	
         	 Sku sku = skuService.selectByID(StringUtilsEX.ToInt(skuid));
             sku.setName(record.getName());
             sku.setPrice(new BigDecimal(price));
             sku.setTicketnum(tnum);
             sku.setAppprice(new BigDecimal(price));
             sku.setIstoday(0);
             //库存商品图片 开始
             sku.setImgurlApp(record.getImgurlApp());
             sku.setImgurl(record.getImgurl());
             sku.setImgurlCart(record.getImgurlApp());
             sku.setImgurlOrderdetail(record.getImgurlApp());
             sku.setImgurlOrderlist(record.getImgurlApp());
             sku.setImgurlWap(record.getImgurlApp());
             sku.setImgurlWechat(record.getImgurlApp());
         	
            int code = spuService.updateSpuNew(record, sku,showtimes,StringUtilsEX.ToInt(year),StringUtilsEX.ToInt(month));
            if (code > 0) {
                item.setCode(0);
                item.setDesc("更新成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_proedit.jsp", "/platform/Spu/updateDirect", "更新直营商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"更新直营商品操作记录出错! 异常信息:",
    								e, "/platform/Spu/updateDirect");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("更新直营商品信息异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "更新直营商品信息异常:" , e,
                "platform/Spu/updateDirect");
        }
        return item;
    }
    
    /**
     * 更新上下架
     * 
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/shelve")
    public @ResponseBody ReusltItem shelve(String id, String status) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(status) != ProStatusEnum.上架.getValue()
                && StringUtilsEX.ToInt(status) != ProStatusEnum.下架.getValue()) {
                item.setCode(-101);
                item.setDesc("商品上下架状态参数错误，status：" + status);
                return item;
            }
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-102);
                item.setDesc("商品ID参数错误，id：" + id);
                return item;
            }
            if(StringUtilsEX.ToInt(status) == ProStatusEnum.上架.getValue()){
            	Shop shop=shopService.getShopBySpuID(StringUtilsEX.ToInt(id));
            	if(shop==null){
             		item.setCode(-103);
                    item.setDesc("根据spuid检索不到对应店铺信息，商品不能上架。spuid：" + id);
                    return item;
             	}
             	if(shop.getStatus()!=ShopStatusEnum.Open.getValue()){
             		item.setCode(-104);
                    item.setDesc("店铺"+shop.getName()+"不是营业中商品不能上架，spuid：" + id);
                    return item;
             	}
            	
            }
//            SpuWithBLOBs record = spuService.queryById(StringUtilsEX.ToInt(id));
//            if(record!=null){
//            	 Integer shopid=record.getShopid();
//            	 Shop shop=shopService.queryById(shopid);
//            	 if(shop!=null){
//            		 Integer shopstatus=shop.getStatus();
//            		 if(shopstatus!=ShopStatusEnum.Open.getValue()){
//            			  item.setCode(-103);
//                          item.setDesc("店铺没开张不能上下架");
//                          return item;
//            		 }
//            	 }
//            }
            if (updateStatus(StringUtilsEX.ToInt(status), StringUtilsEX.ToInt(id)) > 0) {
                item.setCode(0);
                item.setDesc("更新成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_zyspgl_list.jsp", "/platform/Spu/shelve", "商品更新上下架");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"商品更新上下架操作记录出错! 异常信息:",
    								e, "/platform/Spu/shelve");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("商品更新上下架异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "商品更新上下架异常:", e, "platform/Spu/shelve");
        }
        return item;
    }

    /**
     * 直营商品更新上下架
     * 
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/zyshelve")
    public @ResponseBody ReusltItem zyshelve(String id, String status) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(status) != ProStatusEnum.上架.getValue()
                && StringUtilsEX.ToInt(status) != ProStatusEnum.下架.getValue()) {
                item.setCode(-101);
                item.setDesc("商品上下架状态参数错误，status：" + status);
                return item;
            }
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-102);
                item.setDesc("商品ID参数错误，id：" + id);
                return item;
            }
            if (updateStatus(StringUtilsEX.ToInt(status), StringUtilsEX.ToInt(id)) > 0) {
                item.setCode(0);
                item.setDesc("更新成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_zyspgl_list.jsp", "/platform/Spu/zyshelve", "直营商品更新上下架");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"直营商品更新上下架操作记录出错! 异常信息:",
    								e, "/platform/Spu/zyshelve");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("直营商品更新上下架异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "直营商品更新上下架异常:" + e.getMessage(),
                "platform/Spu/zyshelve");
        }
        return item;
    }

    /**
     * 添加标准商品图片
     * 
     * @param imgurl
     * @param orderby
     * @param spuid
     * @return
     */
    @RequestMapping("/addSpuImage")
    public @ResponseBody ReusltItem addSpuImage(String img, String orderby, String spuid) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            if (StringUtilsEX.isBlank(img)) {
                item.setCode(-101);
                item.setDesc("图片不能为空");
                return item;
            }
            if (StringUtilsEX.ToInt(orderby) < 0) {
                item.setCode(-102);
                item.setDesc("图片排序参数错误，orderby：" + orderby);
                return item;
            }
            if (StringUtilsEX.ToInt(spuid) < 0) {
                item.setCode(-103);
                item.setDesc("商品ID参数错误，skuid：" + spuid);
                return item;
            }
            ProductImgs imgs = new ProductImgs();
            imgs.setImgurl(img);
            imgs.setOrderby(StringUtilsEX.ToInt(orderby));
            imgs.setSpuId(StringUtilsEX.ToInt(spuid));
            imgs.setStatus(0);
            imgs.setAddtime(new Date());

            if (productImgsService.insert(imgs) > 0) {
                LogHandle.info(LogType.Product, MessageFormat.format("添加直营标准商品图片成功,图片路径:{0}", img),
                    "platform/Spu/addSpuImage");
                item.setCode(0);
                item.setDesc("添加直营标准商品图片成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_spuimg_edit.jsp", "/platform/Spu/addSpuImage", "添加直营标准商品图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加直营标准商品图片操作记录出错! 异常信息:",
    								e, "/platform/Spu/addSpuImage");
    					}
    					
    				}
    			});
            } else {
                LogHandle.info(LogType.Product, MessageFormat.format("添加直营标准商品图片失败,图片路径:{0}", img),
                    "platform/Spu/addSpuImage");
                item.setCode(-200);
                item.setDesc("添加直营标准商品图片失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("添加商品图片异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "添加商品图片异常:" , e,
                "platform/Spu/addSpuImage");
        }

        return item;
    }

    /**
     * 编辑标准商品图片
     * 
     * @param id
     * @param imgurl
     * @param orderby
     * @param skuid
     * @return
     */
    @RequestMapping("/editSpuImage")
    public @ResponseBody ReusltItem editSpuImage(String id, String img, String orderby, String spuid) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            if (StringUtilsEX.isBlank(img)) {
                item.setCode(-101);
                item.setDesc("图片不能为空");
                return item;
            }
            if (StringUtilsEX.ToInt(orderby) < 0) {
                item.setCode(-102);
                item.setDesc("图片排序参数错误，orderby：" + orderby);
                return item;
            }
            if (StringUtilsEX.ToInt(spuid) < 0) {
                item.setCode(-103);
                item.setDesc("库存ID参数错误，skuid：" + spuid);
                return item;
            }
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-104);
                item.setDesc("ID参数错误，id：" + id);
                return item;
            }
            ProductImgs imgs = productImgsService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
            imgs.setImgurl(img);
            imgs.setOrderby(StringUtilsEX.ToInt(orderby));

            if (productImgsService.updateByPrimaryKey(imgs) > 0) {
                LogHandle.info(LogType.Product,
                    MessageFormat.format("编辑直营标准商品图片成功,图片路径:{0},id:{1}", img, id),
                    "platform/Spu/editSpuImage");
                item.setCode(0);
                item.setDesc("编辑直营标准商品图片成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_spuimg_edit.jsp", "/platform/Spu/editSpuImage", "修改直营库存商品图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改直营库存商品图片操作记录出错! 异常信息:",
    								e, "/platform/Spu/editSpuImage");
    					}
    					
    				}
    			});
            } else {
                LogHandle.info(LogType.Product,
                    MessageFormat.format("编辑直营标准商品图片失败,图片路径:{0},id:{1}", img, id),
                    "platform/Spu/editSpuImage");
                item.setCode(-200);
                item.setDesc("编辑直营库存商品图片失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("编辑商品图片异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "编辑商品图片异常:" , e,
                "platform/Spu/editSpuImage");
        }

        return item;
    }

    /**
     * 删除标准商品图片
     * 
     * @param id
     * @return
     */
    @RequestMapping("/delSpuImage")
    public @ResponseBody ReusltItem delSpuImage(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("ID参数错误，id：" + id);
                return item;
            }
            if (productImgsService.deleteByPrimaryKey(StringUtilsEX.ToInt(id)) > 0) {
                LogHandle.info(LogType.Product, MessageFormat.format("删除直营标准商品图片成功,id:{0}", id),
                    "platform/Spu/delSpuImage");
                item.setCode(0);
                item.setDesc("删除直营标准商品图片成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_spgl_spuimgs_list.jsp", "/platform/Spu/delSpuImage", "删除直营标准商品图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除直营标准商品图片操作记录出错! 异常信息:",
    								e, "/platform/Spu/delSpuImage");
    					}
    					
    				}
    			});
            } else {
                LogHandle.info(LogType.Product, MessageFormat.format("删除直营标准商品图片失败,id:{0}", id),
                    "platform/Spu/delSpuImage");
                item.setCode(-200);
                item.setDesc("删除直营标准商品图片失败");
            }

        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("编辑商品图片异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "删除商品图片异常:" , e,
                "platform/Spu/delSpuImage");
        }

        return item;
    }

    /**
     * 根据spuid获取标准商品图片
     * 
     * @param spuid
     * @return
     */
    @RequestMapping("/getImageBySpu")
    public @ResponseBody ReusltItem getImageBySpu(String spuid) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(spuid) < 0) {
                item.setCode(-101);
                item.setDesc("商品ID参数错误，spuid：" + spuid);
                return item;
            }
            item.setData(productImgsService.selectBySpu(StringUtilsEX.ToInt(spuid)));
            item.setCode(0);

        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("根据spuid获取标准商品图片异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "根据spuid获取标准商品图片异常:", e,
                "platform/Spu/getImageBySpu");
        }
        return item;
    }

    /**
     * 店铺批量上下架
     * 
     * @param spuid
     * @return
     */
    @RequestMapping("/shopbatchshelves")
    public @ResponseBody ReusltItem ShopBatchShelves(String spuids, String status) {
        ReusltItem item = new ReusltItem();
        try {
            UpdateBatchShelves(spuids, item, status, ShopProType.店铺);
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("店铺商品批量上下架异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "店铺商品批量上下架异常:" , e,
                "platform/Spu/shopbatchshelves");
        }
        return item;
    }

    /**
     * 直营批量上下架
     * 
     * @param spuid
     * @return
     */
    @RequestMapping("/regularpbatchshelves")
    public @ResponseBody ReusltItem RegularBatchShelves(String spuids, String status) {
        ReusltItem item = new ReusltItem();
        try {
            UpdateBatchShelves(spuids, item, status, ShopProType.直营);
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("直营商品批量上下架异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "直营商品批量上下架异常:" ,e,
                "platform/Spu/regularpbatchshelves");
        }
        return item;
    }

    /**
     * 店铺商品批量审核
     * 
     * @param spuid
     * @return
     */
    @RequestMapping("/shopbatchaudit")
    public @ResponseBody ReusltItem ShopBatchAudit(String spuids) {
        ReusltItem item = new ReusltItem();
        try {
            int status = ProStatusEnum.上架.getValue();
            UpdateBatchStatus(spuids, item, status, ShopProType.店铺);
            //			UpdateBatchAudit(status,item,spuids, ShopProType.店铺);
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("店铺商品批量审核异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "店铺商品批量审核异常:" ,e,
                "platform/Spu/shopbatchaudit");
        }
        return item;
    }

    /**
     * 店铺商品审核
     * 
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/spuCheck")
    public @ResponseBody ReusltItem spuCheck(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            Integer status = ProStatusEnum.上架.getValue();
            // if(StringUtilsEX.ToInt(status)!=ProStatusEnum.已审核.getValue() ||
            // StringUtilsEX.ToInt(status)!=ProStatusEnum.未审核.getValue()){
            // item.setCode(-101);
            // item.setDesc("商品审核状态参数错误，status："+status);
            // return item;
            // }
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-102);
                item.setDesc("商品ID参数错误，id：" + id);
                return item;
            }
            if (updateStatus(status, StringUtilsEX.ToInt(id)) > 0) {
                item.setCode(0);
                item.setDesc("更新成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_dpspsh.jsp", "/platform/Spu/spuCheck", "店铺商品审核");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"店铺商品审核操作记录出错! 异常信息:",
    								e, "/platform/Spu/spuCheck");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("店铺商品审核异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "店铺商品审核异常:" , e, "platform/Spu/spuCheck");
        }
        return item;
    }

    /**
     * 店铺商品提交审核
     * 
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/spuReviewCheck")
    public @ResponseBody ReusltItem spuReviewCheck(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-102);
                item.setDesc("商品ID参数错误，id：" + id);
                return item;
            }
            if (updateStatus(1, StringUtilsEX.ToInt(id)) > 0) {
                item.setCode(0);
                item.setDesc("更新成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_dpspsh.jsp", "/platform/Spu/spuReviewCheck", "店铺商品提交审核");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"店铺商品提交审核操作记录出错! 异常信息:",
    								e, "/platform/Spu/spuReviewCheck");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("店铺商品提交审核异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "店铺商品提交审核异常:" , e,
                "platform/Spu/spuReviewCheck");
        }
        return item;
    }

    private Integer updateStatus(Integer status, Integer id) throws Exception {
        SpuWithBLOBs record = spuService.queryById(id);
        record.setStatus(status);
        String oldStatus;
        if(status==3){
        	oldStatus="4";
        }else{
        	oldStatus="3";
        }
        String ip = GetIpAddresss.getIpAddr();
        if (status == ProStatusEnum.上架.getValue()) {
        	//异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						productUpdateRecordsService.insertProSXJ("status", oldStatus, status.toString(), id, user.getUserId(), user.getName(),ip);
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"上架/下架店铺商品操作记录出错! 异常信息:",
								e, "/platform/Spu/spuFreezeOrTraw");
					}
					
				}
			});
            return spuService.updateShelve(status, id);
        } else {
        	//异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						productUpdateRecordsService.insertProSXJ("status", oldStatus, status.toString(), id, user.getUserId(), user.getName(),ip);
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"上架/下架店铺商品操作记录出错! 异常信息:",
								e, "/platform/Spu/spuFreezeOrTraw");
					}
					
				}
			});
            return spuService.update(record);
        }

    }

    /**
     * 冻结/解冻店铺商品
     * 
     * @param id
     * @return
     */
    @RequestMapping("/spuFreezeOrTraw")
    public @ResponseBody ReusltItem spuFreezeOrTraw(String id,String status) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(status) != ProStatusEnum.下架.getValue()
                && StringUtilsEX.ToInt(status) != ProStatusEnum.冻结.getValue()) {
                item.setCode(-101);
                item.setDesc("商品审核状态参数错误，status：" + status);
                return item;
            }
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-102);
                item.setDesc("商品ID参数错误，id：" + id);
                return item;
            }
            if (updateStatus(StringUtilsEX.ToInt(status), StringUtilsEX.ToInt(id)) > 0) {
                item.setCode(0);
                item.setDesc("更新成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_dpspgl.jsp", "/platform/Spu/spuFreezeOrTraw", "冻结/解冻店铺商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"冻结/解冻店铺商品操作记录出错! 异常信息:",
    								e, "/platform/Spu/spuFreezeOrTraw");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("冻结/解冻店铺商品异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "冻结/解冻店铺商品异常:" , e,
                "platform/Spu/spuFreezeOrTraw");
        }
        return item;
    }

    /**
     * 模糊检索商品列表
     * 
     * @param name
     * @return
     */
    @RequestMapping("/getSpuStartWithName")
    public @ResponseBody ReusltItem getSpuStartWithName(String name, String classid) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            // Integer shopid=1; //直营店铺ID 默认为1
            List<Spu> list = new ArrayList<Spu>();
            if (StringUtilsEX.ToInt(classid) > 0) {
                list = spuService.getSpuStartWithName(user.getShopid(), name,
                    StringUtilsEX.ToInt(classid));
            } else {
                list = spuService.getSpuStartWithName(user.getShopid(), name, 0);
            }
            item.setData(list);
            item.setCode(0);
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("模糊检索商品列表异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "模糊检索商品列表异常:" , e,
                "platform/Spu/getSpuStartWithName");
        }
        return item;
    }



    /**
     * 模糊检索商品列表
     * 
     * @param name
     * @return
     */
    @RequestMapping("/getSpuStartWithShopName")
    public @ResponseBody ReusltItem getSpuStartWithShopName(String name, String shopid) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            // Integer shopid=1; //直营店铺ID 默认为1
            List<Spu> list = new ArrayList<Spu>();
            if (StringUtilsEX.ToInt(shopid) > 0) {
                list = spuService.getSpuStartWithName(StringUtilsEX.ToInt(shopid), name,0);
            } else {
                list = spuService.getSpuStartWithName(0, name, 0);
            }
            item.setData(list);
            item.setCode(0);
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("模糊检索商品列表异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "模糊检索商品列表异常:" , e,
                "platform/Spu/getSpuStartWithName");
        }
        return item;
    }

	/**
	 * 模糊检索sku商品bySKU 名称
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/getBySkuName")
	public @ResponseBody BaseResult getBySkuName(String name) {
		BaseResult item = new BaseResult();
		try {
			item.setCode(0);
			item.setData(spuService.getBySkuName(name));
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("模糊检索sku商品bySKU 名称异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Product, "模糊检索sku商品bySKU 名称异常:" , e, "platform/Spu/getBySkuName");
		}
		return item;

	}
	
	/**
     * 模糊检索所有商品列表
     * @param name
     * @return
     */
    @RequestMapping("/getSspuStartWithName")
    public @ResponseBody ReusltItem getSspuStartWithName(String name){
    	ReusltItem item = new ReusltItem();
		try {
			List<Spu> spulist=spuService.platgetSspuStartWithName(name);
			item.setData(spulist);
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("模糊检索商品列表信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product,
					"模糊检索商品列表信息出错! 异常信息:", e,
					"/spu/getSspuStartWithName");
		}
		return item;
    }
    
    @RequestMapping("/getBySkuShowtime")
	public BaseResult getBySkuShowtime(String skuid,String spuid,String showy,String showm) {
		BaseResult item = new BaseResult();
		try {
			 if (StringUtilsEX.ToInt(skuid) <= 0) {
				item.setCode(-101);
	            item.setDesc("商品ID参数错误");
	            return item;
			 }
			 if (StringUtilsEX.ToInt(spuid) <= 0) {
					item.setCode(-101);
		            item.setDesc("商品ID参数错误");
		            return item;
				 }
             if (StringUtilsEX.ToInt(showy) <= 0) {
                item.setCode(-102);
             	item.setDesc("表演时间参数错误");
             	return item;
			 }
            if (StringUtilsEX.ToInt(showm) <= 0) {
            	item.setCode(-102);
            	item.setDesc("表演时间参数错误");
            	return item;
             }
            List<SkuShowtime> showtimes=new ArrayList<SkuShowtime>();
            showtimes=spuService.getListBySkuidAndMonth(StringUtilsEX.ToInt(skuid),StringUtilsEX.ToInt(spuid),
            		StringUtilsEX.ToInt(showy),StringUtilsEX.ToInt(showm));
			item.setCode(0);
			item.setData(showtimes);
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询马戏团票表演时间异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Product, "查询马戏团票表演时间异常:" , e, "/spu/getBySkuShowtime");
		}
		return item;

	}
    
    
    @RequestMapping("/getSpuStocklist")
    public ReusltItem getList(String spuid,String showy,String showm,String showd, String index, String size) {
         ReusltItem item = new ReusltItem();
        try {
        	if (StringUtilsEX.ToInt(spuid) <= 0) {
                item.setCode(-102);
                item.setDesc("商品id参数错误");
                return item;
            }
            if (StringUtilsEX.ToInt(index) <= 0) {
                item.setCode(-102);
                item.setDesc("分页参数错误，pageindex：" + index);
                return item;
            }
            if (StringUtilsEX.ToInt(size) <= 0) {
                item.setCode(-103);
                item.setDesc("分页参数错误，pagesize：" + size);
                return item;
            }
            ProductCriteria criteria=new ProductCriteria();
            criteria.setSpuid(StringUtilsEX.ToInt(spuid));
            if(StringUtilsEX.ToInt(showy)>0){
            	criteria.setShowy(StringUtilsEX.ToInt(showy));
            }
            if(StringUtilsEX.ToInt(showm)>0){
            	criteria.setShowm(StringUtilsEX.ToInt(showm));
            }
            if(StringUtilsEX.ToInt(showd)>0){
            	criteria.setShowd(StringUtilsEX.ToInt(showd));
            }
            PageBean pageBean = spuService.getSpuStockPage(criteria,StringUtilsEX.ToInt(index),StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setDesc("查询成功");
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询商品库存售价信息异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Product, "查询商品库存售价信息异常:" , e, "/spu/getSpuStocklist");
        }
        return item;
    }
    
    
    /**
     * 修改商品库存价格
     * @param id
     * @param stock
     * @param price
     * @return
     */
    @RequestMapping("/editSpuTimeStock")
    public ReusltItem editSpuTimeStock(String id,String stock,String price) {
    	ReusltItem item=new ReusltItem();
        try {
        	if(StringUtilsEX.ToInt(id)<=0){
       		 item.setCode(-101);
		         item.setDesc("id参数错误");
		         return item;
       	    }
        	
        	if(StringUtilsEX.ToInt(stock)<0){
        		item.setCode(-102);
		        item.setDesc("库存参数错误");
		        return item;
        	}
        	
        	if(StringUtilsEX.ToDouble(price)<0){
        		item.setCode(-103);
		        item.setDesc("售价参数错误");
		        return item;
        	}
        	
        	SkuShowtime   skuShowtime=spuService.getSkuShowTimeById(StringUtilsEX.ToInt(id));
        	if(skuShowtime==null){
        		item.setCode(-104);
		        item.setDesc("id参数错误");
		        return item;
        	}
        	skuShowtime.setStock(StringUtilsEX.ToInt(stock));
        	skuShowtime.setPrice(new BigDecimal(price));
        	
        	
            if (spuService.updateSkuShowTime(skuShowtime) > 0) {
                item.setCode(0);
                item.setDesc("编辑商品库存售价成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_stocklist.jsp", "/platform/Spu/editSpuTimeStock", "编辑商品库存售价信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"编辑商品库存售价信息操作记录出错! 异常信息:",
    								e, "/platform/Spu/editSpuTimeStock");
    					}
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("编辑商品库存售价失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("编辑商品库存售价信息异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Product, "编辑商品库存售价信息异常:" , e, "/spu/editSpuTimeStock");
        }
        return item;
    }
    
    
    /**
     * 删除商品库存售价
     * @param id
     * @return
     */
    @RequestMapping("/delSpuTimeStock")
    public ReusltItem delSpuTimeStock(String id) {
         ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
            Integer ID = StringUtilsEX.ToInt(id);
            if (ID < 0) {
                item.setCode(-101);
                item.setDesc("ID参数错误,id:" + id);
                return item;
            }
            
            if (spuService.delSpuTimeStock(StringUtilsEX.ToInt(id)) > 0) {
            	 item.setCode(0);
                 item.setDesc("删除商品库存售价成功");
               //异步操作 不影响正常流程
                 ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
     			cachedThreadPool.execute(new Runnable() {
     				@Override
     				public void run() {
     					try{
     						operaterecordsService.insertOperaterecords(
                             		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                             		user.getUserId(), user.getLoginName(), "goods_stocklist.jsp", "/platform/Spu/delSpuTimeStock", "删除商品库存售价信息");
     					}
     					catch(Exception e){
     						LogHandle.error(LogType.OperateRecords,"删除商品库存售价信息操作记录出错! 异常信息:",
     								e, "/platform/Spu/delSpuTimeStock");
     					}
     				}
     			});
            } else {
                item.setCode(-200);
                item.setDesc("删除商品库存售价信息失败");
            }

        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("删除商品库存售价信息异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Product, "删除商品库存售价信息异常:" , e, "/spu/delSpuTimeStock");
        }
        return item;
    }
    
    
    @RequestMapping("/delTimeStockList")
    public ReusltItem delTimeStockList(String ids) {
         ReusltItem item = new ReusltItem();
        try {
            List<Integer> idlist = new ArrayList<Integer>();
            String[] idStrings = ids.split(",");
            Integer ID = 0;
            for (int i = 0; i < idStrings.length; i++) {
                ID = StringUtilsEX.ToInt(idStrings[i]);
                if (ID > 0)
                    idlist.add(ID);
                else {
                    item.setCode(-102);
                    item.setDesc("ID参数错误,id:" + idStrings[i]);
                    return item;
                }
            }
            if (spuService.delTimeStockList(idlist) > 0) {
            	 item.setCode(0);
                 item.setDesc("批量删除商品库存售价成功");
               //异步操作 不影响正常流程
                 ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
     			cachedThreadPool.execute(new Runnable() {
     				@Override
     				public void run() {
     					try{
     						operaterecordsService.insertOperaterecords(
                             		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                             		user.getUserId(), user.getLoginName(), "goods_stocklist.jsp", "/platform/Spu/delTimeStockList", "删除商品库存售价信息");
     					}
     					catch(Exception e){
     						LogHandle.error(LogType.OperateRecords,"批量删除商品库存售价信息操作记录出错! 异常信息:",
     								e, "/platform/Spu/delTimeStockList");
     					}
     				}
     			});
            } else {
            	 item.setCode(-200);
                 item.setDesc("批量删除商品库存售价信息失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("批量删除商品库存售价信息异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Product, "批量删除商品库存售价信息异常:" , e, "/spu/delTimeStockList");
        }
        return item;
    }
    
    /**
     * 批量添加商品库存售价
     * @param skuparam
     * @return
     */
    @RequestMapping("/addTimeStock")
    public @ResponseBody ReusltItem addTimeStock(String skuparam) {
        ReusltItem item = new ReusltItem();
        try {
        	List<InsertSkuShowtimeDto> skudtos = JSON.parseArray(skuparam, InsertSkuShowtimeDto.class);
         	
         	List<SkuShowtime>  showtimes=new ArrayList<SkuShowtime>();
         	if (skudtos != null && skudtos.size() >= 0) {
         		for (InsertSkuShowtimeDto skudto : skudtos) {
         			SkuShowtime  skutime=spuService.getSkuTimeByDay(skudto.getSkuid(),skudto.getSpuid(),
         					skudto.getShowyear(),skudto.getShowmonth(),skudto.getShowdays());
         			if(skutime!=null){
         				item.setCode(-201);
         				item.setDesc(skudto.getShowyear()+"年"+skudto.getShowmonth()+"月"+skudto.getShowdays()+"日已经有库存售价信息，不能重复添加！");
         				return item;
         			}
         			SkuShowtime showtime=new SkuShowtime();
         			showtime.setSkuid(skudto.getSkuid());
         			showtime.setSpuid(skudto.getSpuid());
         			showtime.setShowyear(skudto.getShowyear());
         			showtime.setShowmonth(skudto.getShowmonth());
         			showtime.setShowdays(skudto.getShowdays());
         			if(StringUtilsEX.ToInt(skudto.getStock())<0){
         				item.setCode(-101);
         				item.setDesc("库存参数错误");
         				return item;
         			}
         			showtime.setStock(StringUtilsEX.ToInt(skudto.getStock()));
         			if(StringUtilsEX.ToDouble(skudto.getPrice())<0){
         				item.setCode(-101);
         				item.setDesc("售价参数错误");
         				return item;
         			}
         			showtime.setPrice(new BigDecimal(skudto.getPrice()));
         			showtimes.add(showtime);
				}
         	}

            if (spuService.insertTimeStockList(showtimes) > 0) {
                item.setCode(0);
                item.setDesc("批量添加商品库存售价成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_stockadd.jsp", "/platform/Spu/addTimeStock", "批量添加商品库存售价");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量添加商品库存售价操作记录出错! 异常信息:",
    								e, "/platform/Spu/addTimeStock");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("批量添加商品库存售价失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("批量添加商品库存售价异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "批量添加商品库存售价异常:" , e,
                "platform/Spu/addTimeStock");
        }

        return item;
    }
    
    /**
     * 批量添加商品库存售价
     * @param skuparam
     * @return
     */
    @RequestMapping("/addTimeStockByMonth")
    public @ResponseBody ReusltItem addTimeStockByMonth(String skuid,String spuid,
    		String showy,String showm,String stock,String money) {
        ReusltItem item = new ReusltItem();
        try {
        	if(StringUtilsEX.ToInt(skuid)<0){
        		item.setCode(-101);
 				item.setDesc("库存商品id参数错误");
 				return item;
        	}
        	if(StringUtilsEX.ToInt(spuid)<0){
        		item.setCode(-102);
 				item.setDesc("商品id参数错误");
 				return item;
        	}
        	if(StringUtilsEX.ToInt(showy)<0){
        		item.setCode(-103);
 				item.setDesc("日期参数错误");
 				return item;
        	}
        	if(StringUtilsEX.ToInt(showm)<0){
        		item.setCode(-104);
 				item.setDesc("日期参数错误");
 				return item;
        	}
        	if(StringUtilsEX.ToInt(stock)<0){
 				item.setCode(-105);
 				item.setDesc("库存参数错误");
 				return item;
 			}
 			if(StringUtilsEX.ToDouble(money)<0){
 				item.setCode(-106);
 				item.setDesc("售价参数错误");
 				return item;
 			}
        	
 			List<SkuShowtime> timelist=spuService.getListBySkuidAndMonth(StringUtilsEX.ToInt(skuid),StringUtilsEX.ToInt(spuid),
 					StringUtilsEX.ToInt(showy),StringUtilsEX.ToInt(showm));
 			if(timelist!=null&&timelist.size()>0){
 				item.setCode(-107);
 				item.setDesc("本月已存在部分库存售价，不能按月一次性添加");
 				return item;
 			}
 			
 			int days=30;
 			int month=StringUtilsEX.ToInt(showm);
 			int year=StringUtilsEX.ToInt(showy);
 			if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
 				days=31;
 				
 			}else if(year%4==0 && month==2){
 				
 				days=29;
 			}else if(year%4!=0 && month==2){
 				
 				days=28;
 			}
 			List<SkuShowtime>  showtimes=new ArrayList<SkuShowtime>();
 			for(int i=1;i<=days;i++){
 				SkuShowtime showtime=new SkuShowtime();
     			showtime.setSkuid(StringUtilsEX.ToInt(skuid));
     			showtime.setSpuid(StringUtilsEX.ToInt(spuid));
     			showtime.setShowyear(year);
     			showtime.setShowmonth(month);
     			showtime.setShowdays(i);
     			showtime.setStock(StringUtilsEX.ToInt(stock));
     			showtime.setPrice(new BigDecimal(money));
     			showtimes.add(showtime);
 			}
 			
            if (spuService.insertTimeStockList(showtimes) > 0) {
                item.setCode(0);
                item.setDesc("批量添加商品库存售价成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_stockadd.jsp", "/platform/Spu/addTimeStock", "批量添加商品库存售价");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量添加商品库存售价操作记录出错! 异常信息:",
    								e, "/platform/Spu/addTimeStock");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("批量添加商品库存售价失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("批量添加商品库存售价异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
            LogHandle.error(LogType.Product, "批量添加商品库存售价异常:" , e,
                "platform/Spu/addTimeStock");
        }

        return item;
    }
    
	/**
	 * 查询马戏团票表演时间
	 * 
	 * @param name
	 * @return
	 */
	/*@RequestMapping("/getBySkuShowtime")
	public BaseResult getBySkuShowtime(String skuid,String showy,String showm) {
		BaseResult item = new BaseResult();
		try {
			 if (StringUtilsEX.ToInt(skuid) <= 0) {
				item.setCode(-101);
	            item.setDesc("商品ID参数错误");
	            return item;
			 }
             if (StringUtilsEX.ToInt(showy) <= 0) {
                item.setCode(-102);
             	item.setDesc("表演时间参数错误");
             	return item;
			 }
            if (StringUtilsEX.ToInt(showm) <= 0) {
            	item.setCode(-102);
            	item.setDesc("表演时间参数错误");
            	return item;
             }
            String days="";
            List<SkuShowtime> showtimes=spuService.getBySkuid(StringUtilsEX.ToInt(skuid));
            if(showtimes!=null && showtimes.size()>0){
            	 Optional<SkuShowtime> sOptional=showtimes.stream().filter(x->
 				x.getShowyear().equals(StringUtilsEX.ToInt(showy)) 
 				&& x.getShowmonth().equals(StringUtilsEX.ToInt(showm))).findFirst();
            	 if(sOptional.isPresent()){
            		 days=sOptional.get().getShowdays();
            	 }
            }
			item.setCode(0);
			item.setData(days);
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询马戏团票表演时间异常：" + e.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Product, "查询马戏团票表演时间异常:" , e, "/spu/getBySkuShowtime");
		}
		return item;

	}*/
}