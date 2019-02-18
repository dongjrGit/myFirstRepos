package com.yinlian.wssc.platform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.FloorVo;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Floor;
import com.yinlian.wssc.web.po.Floorproduct;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.FloorService;
import com.yinlian.wssc.web.service.FloorproductService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaFloor;
import com.yinlian.wssc.web.util.CriteriaFloorproduct;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/platform/floor")
public class FloorController {

    
    @Autowired
    private      FloorService          floorService;
    
    @Autowired
    private      SpuService            spuService;
    
    @Autowired
    private      FloorproductService    floorproductService;
    
    @Autowired
    private      BrandService           brandService;
    
    @Autowired
    private      CategoryService        categoryService;
    
    @Autowired
    private      TopicService            topicService;
    
    SessionUser                 user   = null;
    @Autowired
    private OperaterecordsService operaterecordsService ;
   
    /**
     * 得到所有的专题
     * @return
     */
    @RequestMapping("/GetAllSpecials")
    public ReusltItem GetAllSpecials() {
    	 ReusltItem item = new ReusltItem();
    	 try {
    		 List<Topic>  topiclist=topicService.queryAll();
        	 item.setCode(0);
        	 item.setData(topiclist);
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("获取专题信息异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "获取专题信息出错! 异常信息:",
					e, "/platform/floor/GetAllSpecials");
		}
    	 return item;
    }
    
    /**
     * 得到所有的spu
     * @return
     */
    @RequestMapping("/GetListStartwithName")
    public ReusltItem GetListStartwithName(String spuName) {
    	 ReusltItem item = new ReusltItem();
    	 try {
    		 List<Spu>  spulist=spuService.getSpuStartWithNames(spuName);
        	 item.setCode(0);
        	 item.setData(spulist);
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("获取商品信息异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "获取商品信息出错! 异常信息:{0}",
					e, "/platform/floor/GetListStartwithName");
		}
    	 return item;
    }
    
    /**
     * 得到顶级分类
     * @return
     */
    @RequestMapping("/P_GetFirstClass")
    public ReusltItem P_GetFirstClass() {
    	 ReusltItem item = new ReusltItem();
    	 try {
    		 List<Category> categorylist=categoryService.queryFirstClass();
        	 item.setCode(0);
        	 item.setData(categorylist);
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("查询一级分类信息异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "获取一级分类信息出错! 异常信息:",
					e, "/platform/floor/P_GetFirstClass");
		}
    	 
    	 
    	 return item;
    }
    
    
    /**
     * 得到分类的子级
     * @return
     */
    @RequestMapping("/P_GetChildrenByFatherID")
    public ReusltItem P_GetChildrenByFatherID(String fatherID) {
    	 ReusltItem item = new ReusltItem();
    	 try {
    		 List<Category> categorylist=categoryService.queryChildrenClass(StringUtilsEX.ToInt(fatherID));
        	 item.setCode(0);
        	 item.setData(categorylist);
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询子集分类信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor,"获取子级分类信息出错! 异常信息:",
					e, "/platform/floor/P_GetChildrenByFatherID");
		}
    	 return item;
    }
    
    
    /**
     * 得到分类的子级
     * @return
     */
    @RequestMapping("/P_GetFatherByChildrenID")
    public ReusltItem P_GetFatherByChildrenID(String id) {
    	 ReusltItem item = new ReusltItem();
    	 try {
    		 Category category=categoryService.queryFatherClass(StringUtilsEX.ToInt(id));
        	 if (category!=null) {
        		 item.setCode(0);
            	 item.setData(category);
			}else{
				item.setCode(-1);
			}
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询子集分类信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor,"获取父类分类信息出错! 异常信息:",
					e, "/platform/floor/P_GetFatherByChildrenID");
		}
    	 return item;
    }
    
    
    /**
     * 查询所有的品牌
     * @return
     */
    @RequestMapping("/queryAllBand")
    public ReusltItem queryAllBand() {
        ReusltItem item = new ReusltItem();
        try {
        	List<Brand> brandlist=brandService.selectAll();
        	item.setCode(0);
        	item.setData(brandlist);
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询所有的品牌信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "获取所有的品牌信息出错! 异常信息:",
					e, "/platform/floor/queryAllBand");
		}
        
        return item;
    }
    
    /**
     * 查询所有楼层
     * @return
     */
    @RequestMapping("/queryall")
    public ReusltItem queryAll() {
        ReusltItem item = new ReusltItem();
        try {
        	List<FloorVo> list=new ArrayList<FloorVo>();
        	List<Floor>  floorlist=floorService.queryAll();
        	for (int i = 0; i < floorlist.size(); i++) {
				FloorVo floorVo=new FloorVo();
				floorVo.setCode(floorlist.get(i).getId());
				floorVo.setName(floorlist.get(i).getName());
				list.add(floorVo);
			}
        	item.setCode(0);
        	item.setData(list);
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询楼层的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "获取楼层信息出错! 异常信息:",
					e, "/platform/floor/queryall");
        }
        return item;
    }
    
    /**
     * 楼层-添加/编辑
     * @return
     */
    @RequestMapping("/editfloor")
    public ReusltItem editfloor(String id,String name,String type,String pagetag,String orderby,String desc,String webset,
    							HttpServletRequest request,String imgurl) {
    	 ReusltItem item = new ReusltItem();
    	 try {
    		if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
      			item.setCode(-101);
      			item.setDesc("楼层名称(name)不能为空！");
      			return item;
      		}
      		if (StringUtilsEX.isBlank(type)) {
      			item.setCode(-102);
      			item.setDesc("请选择楼层类型");
      			return item;
      		}
      		if (StringUtilsEX.IsNullOrWhiteSpace(pagetag)) {
      			item.setCode(-103);
      			item.setDesc("页面标识(pagetag)不能为空！");
      			return item;
      		}
      		
      		if (StringUtilsEX.IsNullOrWhiteSpace(orderby)) {
      			item.setCode(-105);
      			item.setDesc("排序(orderby)不能为空！");
      			return item;
      		}
      		if (StringUtilsEX.IsNullOrWhiteSpace(webset)) {
      			item.setCode(-106);
      			item.setDesc("使用平台不能为空！");
      			return item;
      		}
      		
      		if(id!=""){
      			Floor floor1=floorService.querybyId(StringUtilsEX.ToInt(id));
      			floor1.setName(name);
          		floor1.setType(StringUtilsEX.ToInt(type));
          		floor1.setPagetag(StringUtilsEX.ToInt(pagetag));
          		/*floor1.setDisplay(StringUtilsEX.ToInt(display));*/
          		floor1.setOrderby(StringUtilsEX.ToInt(orderby));
          		floor1.setDescription(desc);
          		floor1.setWebSet(webset);
          		floor1.setImgurl(imgurl);
          		int code=floorService.update(floor1);
          		if(code>0){
          			item.setCode(0);
          			item.setDesc("楼层信息修改成功");
          			user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "FloorsEdit.jsp", "/platform/floor/editfloor", "修改楼层信息");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改楼层信息操作记录出错! 异常信息:",
        								e, "/platform/floor/editfloor");
        					}
        					
        				}
        			});
          		}
      		}else{
      			Floor floor=new Floor();
      			floor.setName(name);
          		floor.setType(StringUtilsEX.ToInt(type));
          		floor.setPagetag(StringUtilsEX.ToInt(pagetag));
          		/*floor.setDisplay(StringUtilsEX.ToInt(display));*/
          		floor.setOrderby(StringUtilsEX.ToInt(orderby));
          		floor.setDescription(desc);
          		floor.setWebSet(webset);
          		floor.setImgurl(imgurl);
          		int code=floorService.insert(floor);
          		if(code>0){
          			item.setCode(0);
          			item.setDesc("插入楼层信息成功");
          			user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "FloorsEdit.jsp", "/platform/floor/editfloor", "添加楼层信息");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加楼层信息操作记录出错! 异常信息:",
        								e, "/platform/floor/editfloor");
        					}
        					
        				}
        			});
          		}
      		}
      		
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("编辑楼层出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "编辑楼层出错! 异常信息:",
					e, "/platform/floor/editfloor");
		}
    	 return item;
    }
    
    /**
     * 查询楼层信息
     * @param fid
     * @return
     */
    @RequestMapping("/queryById")
    public ReusltItem queryById(String fid) {
    	 ReusltItem item = new ReusltItem();
    	 try {
    		/*if (StringUtilsEX.IsNullOrWhiteSpace(fid)) {
       			item.setCode(-101);
       			item.setDesc("楼层id(fid)不能为空！");
       			return item;
       		}*/
    		item.setData(floorService.querybyId(StringUtilsEX.ToInt(fid)));
            item.setCode(0); 
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询楼层信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "查询楼层信息出错! 异常信息:",
					e, "/platform/floor/queryById");
		}
    	 return item;
    }
    
    /**
     * 查询楼层
     * 
     * @param page
     * @param size
     * @param type
     * @param title
     * @return
     */
    @RequestMapping("/queryFloorListByCriteria")

    public ReusltItem queryFloorListByCriteria(String page, String size, String name, String type,String pagetag,String webset) {

        ReusltItem item = new ReusltItem();
        try {
			CriteriaFloor criteria = new CriteriaFloor();
			criteria.setName(name);
			criteria.setType(StringUtilsEX.ToInt(type));
			criteria.setWebSet(webset);
			criteria.setPagetag(StringUtilsEX.ToInt(pagetag));
			criteria.setOrderByClause(" orderby ");
			PageBean pageBean = floorService.queryFloorListByCriteria(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc()); 
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("获取楼层信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "获取楼层信息出错! 异常信息:",
					e, "/platform/floor/queryFloorListByCriteria");
		}
        return item;
    }
    
    /**
     * 删除楼层
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public ReusltItem deleteById(String fID,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	if (StringUtilsEX.ToInt(fID) < 0) {
				item.setCode(-101);
				item.setDesc("获取参数错误：id"+fID);
				return item;
			}
        	
        	int result=floorService.delete(StringUtilsEX.ToInt(fID));
			
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				user=SessionUtil.getSessionUser(request);
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Floors.jsp", "/platform/floor/deleteById", "删除楼层信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除楼层信息操作记录出错! 异常信息:",
    								e, "/platform/floor/deleteById");
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
        		item.setDesc("删除楼层的信息出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "删除楼层的信息出错! 异常信息:{0}",
					e, "/platform/floor/deleteById");
		}
        return item;
    }
    
    
    /**
     * 楼层的编辑
     * @return
     */
    @RequestMapping("/editprofloor")
    public ReusltItem editprofloor(String id,String flID,String proID,String img,String orderby,String proType,String url,String proName,String display,String desc,
    								HttpServletRequest request) {    	
    	 ReusltItem item = new ReusltItem();
    	try {
    		if (StringUtilsEX.IsNullOrWhiteSpace(flID)) {
      			item.setCode(-101);
      			item.setDesc("楼层ID(flID)不能为空！");
      			return item;
      		}
      		/*if (StringUtilsEX.IsNullOrWhiteSpace(proID)) {
      			item.setCode(-102);
      			item.setDesc("");
      			return item;
      		}*/
      		if(StringUtilsEX.ToInt(proType)!=2){
      			if(StringUtilsEX.isBlank(img)){
          			item.setCode(-103);
          			item.setDesc("请上传图片");
          			return item;
          		}
      		}
      		if (StringUtilsEX.IsNullOrWhiteSpace(orderby)) {
      			item.setCode(-106);
      			item.setDesc("排序不能为空！");
      			return item;
      		}
      		if (StringUtilsEX.IsNullOrWhiteSpace(proType)) {
      			item.setCode(-104);
      			item.setDesc("商品类型不能为空！");
      			return item;
      		}
      		if (StringUtilsEX.IsNullOrWhiteSpace(desc)) {
      			item.setCode(-105);
      			item.setDesc("描述不能为空！");
      			return item;
      		}
      		if (StringUtilsEX.IsNullOrWhiteSpace(display)) {
      			item.setCode(-105);
      			item.setDesc("商品显示位置不能为空！");
      			return item;
      		}
      		if(StringUtilsEX.ToInt(id)>0){
      			//修改楼层关联信息
      			Floorproduct floorproduct =floorproductService.queryById(StringUtilsEX.ToInt(id));
      			floorproduct.setFloorid(StringUtilsEX.ToInt(flID));
      			floorproduct.setProid(StringUtilsEX.ToInt(proID));
      			floorproduct.setImg(img);
      			floorproduct.setOrderby(StringUtilsEX.ToInt(orderby));
      			floorproduct.setProtype(StringUtilsEX.ToInt(proType));
      			floorproduct.setUrl(url);
      			floorproduct.setDisplay(StringUtilsEX.ToInt(display));
      			floorproduct.setDescription(desc);
      			floorproduct.setProname(proName);

      			int temp=floorproductService.update(floorproduct);
      			if (temp>0) {
      				item.setCode(0);
          			item.setDesc("修改楼层关联信息成功");
          			user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "ProFloorsEdit.jsp", "/platform/floor/editfloor", "修改楼层关联信息");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改楼层关联信息操作记录出错! 异常信息:",
        								e, "/platform/floor/editfloor");
        					}
        					
        				}
        			});
				}
      			
      			
      		}else{
      			//插入楼层关联信息
      			Floorproduct floorproduct=new Floorproduct();
      			
      			floorproduct.setFloorid(StringUtilsEX.ToInt(flID));
      			floorproduct.setProid(StringUtilsEX.ToInt(proID));
      			floorproduct.setImg(img);
      			floorproduct.setOrderby(StringUtilsEX.ToInt(orderby));
      			floorproduct.setProtype(StringUtilsEX.ToInt(proType));
      			floorproduct.setUrl(url);
      			floorproduct.setDisplay(StringUtilsEX.ToInt(display));
      			floorproduct.setDescription(desc);
      			floorproduct.setProname(proName);
          		
      			int code=floorproductService.insert(floorproduct);
          		if(code>0){
          			item.setCode(0);
          			item.setDesc("插入楼层关联信息成功");
          			user=SessionUtil.getSessionUser(request);
          		//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "ProFloorsEdit.jsp", "/platform/floor/editfloor", "添加楼层关联信息");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加楼层关联信息操作记录出错! 异常信息:",
        								e, "/platform/floor/editfloor");
        					}
        					
        				}
        			});
          		}
      		}
      		
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("编辑楼层出错：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "编辑楼层出错! 异常信息:",
					e, "/platform/floor/editfloor");
		}
    	 return item;
    }  
    	
    /**
     * 删除楼层关联信息
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteProById")
    public ReusltItem deleteProById(String fID,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	if (StringUtilsEX.ToInt(fID) < 0) {
				item.setCode(-101);
				item.setDesc("获取参数错误：id"+fID);
				return item;
			}
        	
        	int relust=floorproductService.delect(StringUtilsEX.ToInt(fID));
			
			if (relust > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				user=SessionUtil.getSessionUser(request);
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "ProFloors.jsp", "/platform/floor/deleteProById", "删除楼层关联信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除楼层关联信息操作记录出错! 异常信息:",
    								e, "/platform/floor/deleteProById");
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
        		item.setDesc("删除楼层关联信息：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "删除楼层关联的信息出错! 异常信息:",
					e, "/platform/floor/deleteProById");
		}
        return item;
    }
    
   
    /**
     * 通过id获取头层关联的信息
     * @param fID
     * @return
     */
    
    @RequestMapping("/GetProFloorByID")
    public ReusltItem  GetProFloorByID(String fID) {
    	ReusltItem item = new ReusltItem();
    	try {
    		if (StringUtilsEX.ToInt(fID) < 0) {
				item.setCode(-101);
				item.setDesc("获取参数错误：id"+fID);
				return item;
			}
			Floorproduct  floorproduct=floorproductService.queryById(StringUtilsEX.ToInt(fID));
			item.setCode(0);
			item.setData(floorproduct);
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("获取楼层关联信息：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "获取楼层关联的信息出错! 异常信息:",
					e, "/platform/floor/deleteProById");
		}
    	return  item;
    }
        
        
    /**
     * 查询楼层关联信息
     * 
     * @param page
     * @param size
     * @param type
     * @param title
     * @return
     */
    @RequestMapping("/GetPageList")

    public ReusltItem GetPageList(String page, String size,String fid,String type) {

        ReusltItem item = new ReusltItem();
        try {
        	CriteriaFloorproduct  criteria=new CriteriaFloorproduct();
        	criteria.setFid(StringUtilsEX.ToInt(fid));
        	criteria.setType(StringUtilsEX.ToInt(type));
        	/*criteria.setDisplay(StringUtilsEX.ToInt(display));*/
        	PageBean pageBean=floorproductService.queryFloorListByCriteria(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
           
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc()); 
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("获取楼层信息错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "获取楼层信息出错! 异常信息:",
					e, "/platform/floor/queryFloorListByCriteria");
		}
        return item;
    }	
    
    /**
     * 修改楼层关联信息的单个属性
     * @param id
     * @param setValue
     * @param type
     * @return
     */
    @RequestMapping("/SetProFloorValue")
    public ReusltItem SetProFloorValue(String id,String setValue,String type,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	if(StringUtilsEX.ToInt(type)==1){
        		Floorproduct floorproduct =floorproductService.queryById(StringUtilsEX.ToInt(id));
        		floorproduct.setOrderby(StringUtilsEX.ToInt(setValue));
        		int temp=floorproductService.update(floorproduct);
      			if (temp>0) {
      				item.setCode(0);
          			item.setDesc("保存成功");
          			user=SessionUtil.getSessionUser(request);
    				//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "ProFloors.jsp", "/platform/floor/SetProFloorValue", "修改楼层关联信息的单个属性");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改楼层关联信息的单个属性操作记录出错! 异常信息:",
        								e, "/platform/floor/SetProFloorValue");
        					}
        					
        				}
        			});
				}
            }else if(StringUtilsEX.ToInt(type)==0){
            	Floorproduct floorproduct =floorproductService.queryById(StringUtilsEX.ToInt(id));
        		floorproduct.setDisplay(StringUtilsEX.ToInt(setValue));
        		int temp=floorproductService.update(floorproduct);
      			if (temp>0) {
      				item.setCode(0);
          			item.setDesc("保存成功");
				}
            }
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("修改楼层关联信息错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor,"修改楼层关联信息出错! 异常信息:",e
					, "/platform/floor/SetProFloorValue");
		}
        
        return item;
    }
    
    /**
     * 修改楼层关联信息的单个属性
     * @param id
     * @param setValue
     * @param type
     * @return
     */
    @RequestMapping("/SetFloorValue")
    public ReusltItem SetFloorValue(String id,String setValue,String type,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	if(StringUtilsEX.ToInt(type)==1){
        		Floor floor1=floorService.querybyId(StringUtilsEX.ToInt(id));
          		floor1.setOrderby(StringUtilsEX.ToInt(setValue));
          		int code=floorService.update(floor1);
          		if(code>0){
          			item.setCode(0);
          			item.setDesc("保存成功");
          			user=SessionUtil.getSessionUser(request);
    				//异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "Floors.jsp", "/platform/floor/SetFloorValue", "修改楼层关联信息的单个属性");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改楼层关联信息的单个属性操作记录出错! 异常信息:",
        								e, "/platform/floor/SetFloorValue");
        					}
        					
        				}
        			});
          		}
            }else if(StringUtilsEX.ToInt(type)==0){
            	Floor floor1=floorService.querybyId(StringUtilsEX.ToInt(id));
          		floor1.setDisplay(StringUtilsEX.ToInt(setValue));
          		int code=floorService.update(floor1);
          		if(code>0){
          			item.setCode(0);
          			item.setDesc("保存成功");
          		}
            }
		} catch (Exception e) {
			item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("修改楼层关联信息错误：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.floor, "修改楼层关联信息出错! 异常信息:",
					e, "/platform/floor/SetFloorValue");
		}
        return item;
    }
}
