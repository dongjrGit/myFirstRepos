package com.yinlian.wssc.seller.controller;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import com.yinlian.wssc.web.dto.InsertSkuParamDto;
import com.yinlian.wssc.web.dto.InsertSkuShowtimeDto;
import com.yinlian.wssc.web.dto.MemberCommentDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.Commentreply;
import com.yinlian.wssc.web.po.Commentshowimg;
import com.yinlian.wssc.web.po.ProductImgs;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.SpuUpdateStatus;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.CommentreplyService;
import com.yinlian.wssc.web.service.CommentshowimgService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.service.ProductUpdateRecordsService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.BasicConvert;
import com.yinlian.wssc.web.util.CriteriaMemberComment;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller/shopproduct")
public class ShopProductController {

    @Autowired
    private SpuService          spuService;
    
    @Autowired
    private SkuService          skuService;


    @Autowired
    private CommentService	commentService;
    
    @Autowired
    private ProductImgsService  productImgsService;

    @Autowired
    private ProductService      productService;
    
    @Autowired
    private ShopService         shopservice;

    @Autowired
    private CommentshowimgService   commentshowimgService;
    
    @Autowired
   	private OperaterecordsService operaterecordsService ;
    
    @Autowired
   	private CommentreplyService commentreplyService ;
    
    @Autowired
    private ProductUpdateRecordsService productUpdateRecordsService;

    SessionUser                 user   = null;

    /**
     * 获取店铺商品列表
     * 
     * @param criteria
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/s_getshopList")
    public @ResponseBody ReusltItem s_getshopList(ProductCriteria criteria, String page, String size) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            int sid = user.getShopid();
            criteria.setSid(String.valueOf(sid));
            criteria.setIsowend("false");
            getDateList(item, criteria, page, size, "");
        } catch (Exception e) {
     
            item.setCode(-900);
            item.setDesc("异常：" + e.getLocalizedMessage());
        }
        return item;
    }

    private void getDateList(ReusltItem item, ProductCriteria criteria, String page, String size,
                             String _temp) throws Exception {
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
            && _temp.indexOf(status) == -1 && !("2,4").equals(status)) {
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
        item.setPageIndex(pageBean.getPc());

    }
    /**
	 * 根据分页查询会员评论列表
	 * @param pageindex
	 * @param pagesize
	 * @param ordernum
	 * @param spuname
	 * @return
	 */
	@RequestMapping("/queryEvalutionList")
	public ReusltItem queryMemberComment(String pageindex,String pagesize,String spuname,
			String status,String ordernum){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(pageindex) <= 0) {
				item.setCode(-101);
				item.setDesc("当前页参数错误pageindex"+pageindex);
			}
			if (StringUtilsEX.ToInt(pagesize) <= 0) {
				item.setCode(-102);
				item.setDesc("每页记录数参数错误pagesize"+pagesize);
			} 
			CriteriaMemberComment criteria = new CriteriaMemberComment();
            user = SessionState.GetCurrentUser();
            int sid = user.getShopid();
            criteria.setShopid(sid);
			criteria.setProname(spuname);
			criteria.setStatus(StringUtilsEX.ToInt(status));
			criteria.setOrdernum(ordernum);
			criteria.setOrderByClause("date");
			criteria.setSort("desc");
			PageBean pageBean = commentService.queryMemberCommentByCriteria(criteria,
					StringUtilsEX.ToInt(pageindex),StringUtilsEX.ToInt(pagesize));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			LogHandle.error(LogType.SellerShopManagement,"获取商品评价列表异常", e,"/shopproduct/queryEvalutionList");
            item.setCode(-900);
            item.setDesc("系统异常");
		}
		return item;
	}
	/**
	 * 改变顾客对商品评论的状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/changeShopStatus")
	public ReusltItem changeShopStatus(String id, String status) {
		ReusltItem item = new ReusltItem();
		try {
			Integer _status = StringUtilsEX.ToInt(status);
			Integer _id = StringUtilsEX.ToInt(id);
			if (_id < 0) {
				item.setCode(-101);
				item.setDesc("店铺id错误");
				return item;
			}
			if (_status < 0) {
				item.setCode(-102);
				item.setDesc("店铺要改变状态错误");
				return item;
			}
			
			Comment comment = commentService.queryCommentById(_id);
			comment.setStatus(_status);
			int result = commentService.updateCommentById(comment);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("操作成功！");

			} else {
				item.setCode(-200);
				item.setDesc("操作失败！");
			}
		} catch (Exception e) {
        	LogHandle.error(LogType.SellerShopManagement,"更新评价状态操作出错! 异常信息:",
					e, "/shopproduct/changeShopStatus");
            item.setCode(-900);
            item.setDesc("系统异常");
        }
		return item;
	}
	/**
	 * 添加一条回复
	 * @param commentid
	 * @param replytype
	 * @param parentid
	 * @param byreplyuserid
	 * @param content
	 * @return
	 */
	@RequestMapping("/addReply")
	public ReusltItem addReply(String commentid,String replytype,String parentid,
			String byreplyuserid,String content,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(parentid) <= 0) {
				item.setCode(-102);
				item.setDesc("参数错误:parentid"+parentid);
			}
			if (StringUtilsEX.ToInt(byreplyuserid) <= 0) {
				item.setCode(-103);
				item.setDesc("参数错误:byreplyuserid"+byreplyuserid);
			}
			if (content == null) {
				item.setCode(-104);
				item.setDesc("内容不能为空");
			}
			SessionUser user = SessionUtil.getSessionUser(request);
				
			Integer userid=user.getUserId();
			Commentreply commentreply = new Commentreply();
			commentreply.setType(StringUtilsEX.ToInt(replytype));
			commentreply.setParentid(StringUtilsEX.ToInt(parentid));
			commentreply.setByreplyuserid(StringUtilsEX.ToInt(byreplyuserid));
			commentreply.setContent(content);
			commentreply.setCreatetime(new Date());
			commentreply.setCreateuserid(userid);
			commentreply.setShowname(0);
			commentreply.setVaildflag(0);
			int result = commentreplyService.addReply(commentreply);
			if (result>0) {
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
                            		user.getUserId(), user.getLoginName(), "memberCommentDetail.jsp", "/platform/membercommen/addReply", "添加一条回复");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加一条回复操作记录出错! 异常信息:",
    								e, "/platform/membercommen/addReply");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加一条回复的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "添加回复的信息出错! 异常信息:",
					e, "/platform/membercommen/addReply");
		}
		return item;
	}

	/**
	 * 根据id删除评论照片
	 * @param imgid
	 * @return
	 */
	@RequestMapping("/deleteImgById")
	public @ResponseBody ReusltItem deleteImgById(String imgid){
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(imgid) <=0) {
				item.setCode(-101);
				item.setDesc("参数错误:imgid"+imgid);
			}
			Commentshowimg commentshowimg = commentshowimgService.queryById(StringUtilsEX.ToInt(imgid));
			commentshowimg.setVaildflag(1);
			commentshowimg.setDeldate(new Date());
			int result = commentshowimgService.updateById(commentshowimg);
			if (result>0) {
				item.setCode(0);
				item.setDesc("删除成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除评论照片页面", "/platform/membercommen/deleteImgById", "根据id删除评论照片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除评论照片操作记录出错! 异常信息:",
    								e, "/platform/membercommen/deleteImgById");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id删除评论照片的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "根据id删除评论照片的信息出错! 异常信息:",
					e, "/platform/membercommen/deleteImgById");
		}
		return item;
	}
	/**
	 * 根据id查询评论明细
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryCommentDetail")
	public ReusltItem queryCommentDetail(String id){
		ReusltItem item = new ReusltItem();
		try {
			MemberCommentDto memberCommentDto = commentService.queryById(StringUtilsEX.ToInt(id));
			
			if (memberCommentDto != null) {
				item.setCode(0);
				item.setData(memberCommentDto);
				item.setDesc("查询成功");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id查询订单明细的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "根据id查询订单明细的信息出错! 异常信息:",
					e, "/seller/shopproduct/queryCommentDetail");
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
    @RequestMapping("/s_getshopshelveslist")
    public @ResponseBody ReusltItem s_getShopShelvesList(ProductCriteria criteria, String page,
                                                         String size) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            String _temp = String.valueOf(ProStatusEnum.上架.getValue()) + ","
                           + String.valueOf(ProStatusEnum.下架.getValue()) + ","
                           + String.valueOf(ProStatusEnum.已审核.getValue()) + ","
                           + String.valueOf(ProStatusEnum.解冻申请中.getValue()) + ","
                           + String.valueOf(ProStatusEnum.冻结.getValue());
            criteria.setIsowend("false");
            //所属店铺默认为1 
            int sid = user.getShopid();
            criteria.setSid(String.valueOf(sid));
            getDateList(item, criteria, page, size, _temp);
        } catch (Exception e) {
        	LogHandle.error(LogType.SellerShopManagement,"获取商品信息出现的异常信息:",
					e, "/s_getshopshelveslist");
            item.setCode(-900);
            item.setDesc("系统异常");
        }
        return item;
    }

    /**
     * 插入spu
     * 
     * @param spuWithBLOBs
     * @param result
     * @return
     */
    @RequestMapping("/insertShopSpu")
    public @ResponseBody ReusltItem insertShopSpu(String name, String customcid, String classid,
            String title, String titleShort, String tag, String imgurlApp,String isnk,
            String weight, String producer, String pack,String shelflife, String description,
            String spuinfo, String packinglist,String afterservice,String price, String isoffer, 
            String skuparam,String showy,String showm,String showdays,String tnum,String istoday,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
   
            SpuWithBLOBs record = new SpuWithBLOBs();
            if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
                item.setCode(-101);
                item.setDesc("商品名称不能为空");
                return item;
            }
            if(name.length()>100){
            	item.setCode(-112);
            	item.setDesc("商品名称过长");
            	return item;
            }
            Spu spu=spuService.getByName(name, null);
            if(spu!=null){
            	item.setCode(-112);
            	item.setDesc("商品名称重复，请更新商品名称重新提交");
            	return item;
            }
            if(StringUtilsEX.ToDouble(price)<=0){
            	item.setCode(-112);
            	item.setDesc("商品原价参数错误");
            	return item;
            }
            if (StringUtilsEX.IsNullOrWhiteSpace(tnum)) {
                item.setCode(-102);
                item.setDesc("商品ID不能为空");
                return item;
            }
            Shop shop = shopservice.queryById(user.getShopid());
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
            record.setPrice(new BigDecimal(price));

            record.setCreatetime(new Date());
            record.setIsdel(false);
            //用户所属店铺ID
            record.setShopid(user.getShopid());
            record.setUserid(user.getUserId());

            record.setStatus(ProStatusEnum.未审核.getValue());
            record.setIsowned(false);
            record.setPronum(ProductNumUtil.getSkuNum());
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
                SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
             ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
          		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
          		user.getUserId(), user.getLoginName(), "添加spu页面", "/seller/shopproduct/insertShopSpu", "添加spu");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加添加spu操作记录出错! 异常信息:",
								e, "/seller/shopproduct/insertShopSpu");
					}
					
				}
			});
            } else {
                item.setCode(-200);
                item.setDesc("添加失败");
            }
        } catch (Exception e) {
        	LogHandle.error(LogType.SellerShopManagement,"添加店铺商品出现的异常信息:",
					e, "/shopproduct/insertShopSpu");
            item.setCode(-900);
            item.setDesc("系统异常");
        }
        return item;
    }

    /**
     * 更新店铺spu
     * 
     * @param spuWithBLOBs
     * @return
     */
    @RequestMapping("/updateShopSpu")
    public @ResponseBody ReusltItem updateShopSpu(String id, String name, String customcid, 
    		String classid, String title,String isnk,
    		String titleShort, String tag, String imgurlApp,
            String weight, String producer, String pack,String shelflife, String description,
            String spuinfo, String packinglist,String afterservice,String price, String isoffer,String tnum,
            String skuparam,String showy,String showm,String showdays,
            String skuid,String year,String month,String istoday) {
        ReusltItem item = new ReusltItem();

        try {
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
            
            Shop shop = shopservice.queryById(user.getShopid());
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
            record.setPrice(new BigDecimal(price));
            record.setProtype(StringUtilsEX.ToInt(isnk));
            record.setIsoffer(StringUtilsEX.ToInt(isoffer));
            record.setIstoday(StringUtilsEX.ToInt(istoday));
//            record.setImgurlApp(imgurlApp);
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
            record.setStatus(ProStatusEnum.未审核.getValue());
            
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
                SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
           ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
        		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
        		user.getUserId(), user.getLoginName(), "更新店铺spu页面", "/seller/shopproduct/updateShopSpu", "更新店铺spu");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加更新店铺spu操作记录出错! 异常信息:",
								e, "/seller/shopproduct/updateShopSpu");
					}
				}
			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	LogHandle.error(LogType.SellerShopManagement,"更新店铺spu操作记录出错! 异常信息:",
					e, "/shopproduct/updateShopSpu");
            item.setCode(-900);
            item.setDesc("系统异常");
        }
        return item;
    }

    /**
     * 删除店铺spu
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deletShopSpu")
    public @ResponseBody ReusltItem deletShopSpu(String id) {
        ReusltItem item = new ReusltItem();
        try {
            if (BasicConvert.string2Integer(id) < 0) {
                item.setCode(-101);
                item.setDesc("商品的id错误,id" + id);
                return item;
            }
            int result = spuService.delete(BasicConvert.string2Integer(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
                SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
               ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
      		           OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
      		          user.getUserId(), user.getLoginName(), "删除店铺spu页面", "/seller/shopproduct/deletShopSpu", "删除店铺spu");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加删除店铺spu操作记录出错! 异常信息:",
								e, "/seller/shopproduct/deletShopSpu");
					}
				}
			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
            }
        } catch (Exception e) {
        	LogHandle.error(LogType.SellerShopManagement,"删除店铺spu操作记录出错! 异常信息:",
					e, "/shopproduct/deletShopSpu");
            item.setCode(-900);
            item.setDesc("系统异常");
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
            SessionUser user = SessionState.GetCurrentUser();
			  //异步操作 不影响正常流程
           ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		   cachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try{
					operaterecordsService.insertOperaterecords(
		           OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
		          user.getUserId(), user.getLoginName(), "店铺批量上下架页面", "/seller/shopproduct/shopbatchshelves", "店铺批量上下架");
				}
				catch(Exception e){
					LogHandle.error(LogType.OperateRecords,"添加店铺批量上下架操作记录出错! 异常信息:",
							e, "/seller/shopproduct/shopbatchshelves");
				}
			}
		});
        } catch (Exception e) {
        	LogHandle.error(LogType.SellerShopManagement,"店铺批量上下架出错! 异常信息:",
					e, "/shopproduct/shopbatchshelves");
            item.setCode(-900);
            item.setDesc("系统异常");
        }
        return item;
    }

//    /**
//     * 店铺批量申请解冻
//     * 
//     * @param spuid
//     * @return
//     */
//    @RequestMapping("/applyForThawList")
//    public @ResponseBody ReusltItem applyForThawList(String spuids) {
//        ReusltItem item = new ReusltItem();
//        try {
//            UpdateBatchStatus(spuids, item, ProStatusEnum.解冻申请中.getValue(), ShopProType.店铺);
//            SessionUser user = SessionState.GetCurrentUser();
//			  //异步操作 不影响正常流程
//           ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//		   cachedThreadPool.execute(new Runnable() {
//			@Override
//			public void run() {
//				try{
//					operaterecordsService.insertOperaterecords(
//		           OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
//		          user.getUserId(), user.getLoginName(), "店铺批量申请解冻页面", "/seller/shopproduct/applyForThawList", "店铺批量申请解冻");
//				}
//				catch(Exception e){
//					LogHandle.error(LogType.OperateRecords,"添加店铺批量申请解冻操作记录出错! 异常信息:",
//							e, "/seller/shopproduct/applyForThawList");
//				}
//			}
//		});
//        } catch (Exception e) {
//            // TODO: handle exception
//            logger.error(SkuController.class + "店铺商品批量申请解冻", e);
//            item.setCode(-900);
//            item.setDesc("异常：" + e.getMessage());
//        }
//        return item;
//    }

    
    private void UpdateBatchStatus(String spuids, BaseResult item, int status, ShopProType sptype)
                                                                                                  throws Exception {
    	SessionUser user=SessionState.GetCurrentUser();
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
            	if (status == ProStatusEnum.上架.getValue()) {
            		Shop shop=shopservice.queryById(user.getShopid());
                	if(shop==null){
                 		item.setCode(-103);
                        item.setDesc("未找到对应店铺信息，商品不能上架。spuid：" + s);
                        return;
                 	}
                 	if(shop.getStatus()!=ShopStatusEnum.Open.getValue()){
                 		item.setCode(-104);
                        item.setDesc("店铺不是营业中商品不能上架，spuid：" + s);
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
        //		spuService.updateBatchStatus(sus);		
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

//    /**
//     * 店铺商品解冻申请
//     * 
//     * @param id
//     * @param status
//     * @return
//     */
//    @RequestMapping("/applyForThaw")
//    public @ResponseBody ReusltItem applyForThaw(String id) {
//        ReusltItem item = new ReusltItem();
//        try {
//            if (StringUtilsEX.ToInt(id) < 0) {
//                item.setCode(-102);
//                item.setDesc("商品ID参数错误，id：" + id);
//                return item;
//            }
//            if (updateStatus(ProStatusEnum.解冻申请中.getValue(), StringUtilsEX.ToInt(id)) > 0) {
//                item.setCode(0);
//                item.setDesc("更新成功");
//                SessionUser user = SessionState.GetCurrentUser();
//		  		  //异步操作 不影响正常流程
//                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//			   cachedThreadPool.execute(new Runnable() {
//				@Override
//				public void run() {
//					try{
//						operaterecordsService.insertOperaterecords(
//        		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
//        		user.getUserId(), user.getLoginName(), "店铺商品解冻申请页面", "/seller/shopproduct/applyForThaw", "店铺商品解冻申请");
//					}
//					catch(Exception e){
//						LogHandle.error(LogType.OperateRecords,"添加店铺商品解冻申请操作记录出错! 异常信息:",
//								e, "/seller/shopproduct/applyForThaw");
//					}
//					
//				}
//			});
//            } else {
//                item.setCode(-200);
//                item.setDesc("更新失败");
//            }
//        } catch (Exception e) {
//            logger.error("店铺商品解冻申请出现的异常信息:" + e.getMessage(), e);
//            item.setCode(-900);
//            item.setDesc("异常：" + e.getMessage());
//        }
//        return item;
//    }

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
            if (StringUtilsEX.ToInt(status) == ProStatusEnum.上架.getValue()) {
        		Shop shop=shopservice.queryById(user.getShopid());
            	if(shop==null){
             		item.setCode(-103);
                    item.setDesc("未找到对应店铺信息，商品不能上架。spuid：" + id);
                    return item;
             	}
             	if(shop.getStatus()!=ShopStatusEnum.Open.getValue()){
             		item.setCode(-104);
                    item.setDesc("店铺不是营业中商品不能上架，spuid：" + id);
                    return item;
             	}
        	}
//            SpuWithBLOBs record = spuService.queryById(StringUtilsEX.ToInt(id));
//            if(record!=null){
//            	 Integer shopid=record.getShopid();
//            	 Shop shop=shopservice.queryById(shopid);
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
                SessionUser user = SessionState.GetCurrentUser();
		  		  //异步操作 不影响正常流程
              ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
      		           OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
      		         user.getUserId(), user.getLoginName(), " 更新上下架页面", "/seller/shopproduct/shelve", " 更新上下架");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加 更新上下架操作记录出错! 异常信息:",
								e, "/seller/shopproduct/shelve");
					}
					
				}
			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	LogHandle.error(LogType.SellerShopManagement,"店铺商品更新上下架操作记录出错! 异常信息:",
					e, "/shopproduct/shelve");
            item.setCode(-900);
            item.setDesc("系统异常");
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
            	LogHandle.info(LogType.SellerShopManagement,MessageFormat.format("添加标准商品图片成功,图片路径:{0}", img)
            			, "/shopproduct/addSpuImage");
                item.setCode(0);
                item.setDesc("添加标准商品图片成功");
                SessionUser user = SessionState.GetCurrentUser();
		  		  //异步操作 不影响正常流程
               ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
    		           OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
    		         user.getUserId(), user.getLoginName(), "添加标准商品图片页面", "/seller/shopproduct/addSpuImage", "添加标准商品图片");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加添加标准商品图片操作记录出错! 异常信息:",
								e, "/seller/shopproduct/addSpuImage");
					}
					
				}
			});
            } else {
            	LogHandle.info(LogType.SellerShopManagement,MessageFormat.format("添加标准商品图片失败,图片路径:{0}", img)
            			, "/shopproduct/addSpuImage");
                item.setCode(-200);
                item.setDesc("添加标准商品图片失败");
            }
        } catch (Exception e) {
            // TODO: handle exception
        	LogHandle.error(LogType.SellerShopManagement,"添加商品图片异常", e
        			, "/shopproduct/addSpuImage");
            item.setCode(-900);
            item.setDesc("系统异常");
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
            	LogHandle.info(LogType.SellerShopManagement,MessageFormat.format("编辑标准商品图片成功,图片路径:{0},id:{1}", img, id),
            			"/shopproduct/editSpuImage");
                item.setCode(0);
                item.setDesc("编辑标准商品图片成功");
                SessionUser user = SessionState.GetCurrentUser();
		  		  //异步操作 不影响正常流程
             ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
  		           OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
  		         user.getUserId(), user.getLoginName(), "编辑标准商品图片页面", "/seller/shopproduct/editSpuImage", "编辑标准商品图片");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加编辑标准商品图片操作记录出错! 异常信息:",
								e, "/seller/shopproduct/editSpuImage");
					}
					
				}
			});
            } else {
            	LogHandle.info(LogType.SellerShopManagement,MessageFormat.format("编辑标准商品图片失败,图片路径:{0},id:{1}", img, id),
            			"/shopproduct/editSpuImage");
                item.setCode(-200);
                item.setDesc("编辑库存商品图片失败");
            }
        } catch (Exception e) {
            // TODO: handle exception
        	LogHandle.error(LogType.SellerShopManagement,"编辑标准商品图片异常", e,"/shopproduct/editSpuImage");
            item.setCode(-900);
            item.setDesc("系统异常");
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
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("ID参数错误，id：" + id);
                return item;
            }
            if (productImgsService.deleteByPrimaryKey(StringUtilsEX.ToInt(id)) > 0) {
            	LogHandle.info(LogType.SellerShopManagement,MessageFormat.format("删除标准商品图片成功,id:{0}", id), "/shopproduct/delSpuImage");
                item.setCode(0);
                item.setDesc("删除标准商品图片成功");
                SessionUser user = SessionState.GetCurrentUser();
		  		  //异步操作 不影响正常流程
               ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
		           OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
		         user.getUserId(), user.getLoginName(), "删除标准商品图片页面", "/seller/shopproduct/delSpuImage", "删除标准商品图片");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加删除标准商品图片操作记录出错! 异常信息:",
								e, "/seller/shopproduct/delSpuImage");
					}
					
				}
			});
            } else {
            	LogHandle.info(LogType.SellerShopManagement,MessageFormat.format("删除标准商品图片失败,id:{0}", id), "/shopproduct/delSpuImage");
                item.setCode(-200);
                item.setDesc("删除标准商品图片失败");
            }

        } catch (Exception e) {
            // TODO: handle exception
        	LogHandle.error(LogType.SellerShopManagement,"删除标准商品图片异常", e,"/shopproduct/delSpuImage");
            item.setCode(-900);
            item.setDesc("系统异常");
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
            // TODO: handle exception
        	LogHandle.error(LogType.SellerShopManagement,"根据spuid获取商品图片异常", e,"/shopproduct/getImageBySpu");
            item.setCode(-900);
            item.setDesc("系统异常");
        }
        return item;
    }

    /**
     * 模糊检索商品列表
     * @param name
     * @return
     */
    @RequestMapping("/getSpuStartWithName")
    public @ResponseBody ReusltItem getSpuStartWithName(String name, String classid) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            //			Integer shopid=1;  //店铺ID 默认为1
            List<Spu> list = new ArrayList<Spu>();
            if (StringUtilsEX.ToInt(classid) > 0) {
                list = spuService.getSpuStartWithName(user.getShopid(), name,
                    StringUtilsEX.ToInt(classid));
            } else {
                list = spuService.getSpuStartWithName(user.getShopid(), name, 0);
            }
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	LogHandle.error(LogType.SellerShopManagement,"模糊检索商品列表异常", e,"/shopproduct/getSpuStartWithName");
            item.setCode(-900);
            item.setDesc("系统异常");
        }
        return item;
    }

    /**
     * 根据名称模糊检索商品列表 已上架商品
     * @param name
     * @return
     */
    @RequestMapping("/getSpuWithName")
    public @ResponseBody ReusltItem getSpuWithName(String name) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();

            item.setData(productService.getSpuStartWithName(user.getShopid(), name));
            item.setCode(0);

        } catch (Exception e) {
        	LogHandle.error(LogType.SellerShopManagement,"模糊检索商品列表异常", e,"/shopproduct/getSpuWithName");
            item.setCode(-900);
            item.setDesc("系统异常");
        }
        return item;
    }
    
    @RequestMapping("/getWarnSKUList")
    @ResponseBody
    public ReusltItem getWarnSKUList(String page,String size){
    	ReusltItem item=new ReusltItem();
    	try {
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(page)<=0 || StringUtilsEX.ToInt(size)<=0){
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			ProductCriteria criteria=new ProductCriteria();
			criteria.setSid(String.valueOf(user.getShopid()));
			criteria.setIsowend("false");
			PageBean pagebean=skuService.selectWanrSkuPage(
					criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pagebean.getBeanList());
			item.setMaxRow(pagebean.getTr());
			item.setPageIndex(pagebean.getPc());
		} catch (Exception e) {
			LogHandle.error(LogType.SellerShopManagement,"获取库存预警商品列表异常", e,"/shopproduct/getWarnSKUList");
            item.setCode(-900);
            item.setDesc("系统异常");
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
			user=SessionState.GetCurrentUser();
			List<Spu> spulist=spuService.platgetSspuStartWithName(name,user.getShopid());
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
					"/shopproduct/getSspuStartWithName");
		}
		return item;
    }
    
    /**
     * 获取当月的商品库存数量
     * @param skuid
     * @param spuid
     * @param showy
     * @param showm
     * @return
     */
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
   			LogHandle.error(LogType.Product, "查询马戏团票表演时间异常:" , e, "/shopproduct/getBySkuShowtime");
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
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "spgl_stockadd.jsp", "/seller/shopproduct/addTimeStock", "批量添加商品库存售价");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量添加商品库存售价操作记录出错! 异常信息:",
    								e, "/seller/shopproduct/addTimeStock");
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
   			LogHandle.error(LogType.Product, "批量添加商品库存售价异常:" , e, "/shopproduct/addTimeStock");
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
			LogHandle.error(LogType.Product, "查询商品库存售价信息异常:" , e, "/shopproduct/getSpuStocklist");
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
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_stocklist.jsp", "/seller/shopproduct/editSpuTimeStock", "编辑商品库存售价信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"编辑商品库存售价信息操作记录出错! 异常信息:",
    								e, "/seller/shopproduct/editSpuTimeStock");
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
			LogHandle.error(LogType.Product, "编辑商品库存售价信息异常:" , e, "/shopproduct/editSpuTimeStock");
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
                             		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                             		user.getUserId(), user.getLoginName(), "goods_stocklist.jsp", "/seller/shopproduct/delSpuTimeStock", "删除商品库存售价信息");
     					}
     					catch(Exception e){
     						LogHandle.error(LogType.OperateRecords,"删除商品库存售价信息操作记录出错! 异常信息:",
     								e, "/seller/shopproduct/delSpuTimeStock");
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
			LogHandle.error(LogType.Product, "删除商品库存售价信息异常:" , e, "/shopproduct/delSpuTimeStock");
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
                             		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                             		user.getUserId(), user.getLoginName(), "goods_stocklist.jsp", "/seller/shopproduct/delTimeStockList", "删除商品库存售价信息");
     					}
     					catch(Exception e){
     						LogHandle.error(LogType.OperateRecords,"批量删除商品库存售价信息操作记录出错! 异常信息:",
     								e, "/seller/shopproduct/delTimeStockList");
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
			LogHandle.error(LogType.Product, "批量删除商品库存售价信息异常:" , e, "/shopproduct/delTimeStockList");
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
                             		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                             		user.getUserId(), user.getLoginName(), "spgl_stockadd.jsp", "/seller/shopproduct/addTimeStock", "批量添加商品库存售价");
     					}
     					catch(Exception e){
     						LogHandle.error(LogType.OperateRecords,"批量添加商品库存售价操作记录出错! 异常信息:",
     								e, "/seller/shopproduct/addTimeStock");
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
    			LogHandle.error(LogType.Product, "批量添加商品库存售价异常:" , e, "/shopproduct/addTimeStock");
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
			LogHandle.error(LogType.SellerShopManagement, "查询马戏团票表演时间异常:" , e, "/spu/getBySkuShowtime");
		}
		return item;

	}*/
}





