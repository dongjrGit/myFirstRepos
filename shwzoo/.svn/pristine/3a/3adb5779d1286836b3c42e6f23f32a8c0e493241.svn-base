package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.pc.dto.ConcernSpuDto;
import com.yinlian.pc.dto.PcShopCollectDto;
import com.yinlian.pc.dto.PcSpuCollectDto;
import com.yinlian.wssc.search.Pc_CollectCriteria;
import com.yinlian.wssc.web.dto.CollectInfo;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.UsercollectMapper;
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaCollect;
import com.yinlian.wssc.web.util.PageBeanUtil;
@Component("usercollectService")
public class UsercollectServiceImpl implements UsercollectService {
	/**
	 * 输出日志的控制类
	 */
	private static final Logger logger = LoggerFactory.getLogger(UsercollectServiceImpl.class);
	
	@Autowired
	private UsercollectMapper usercollectMapper;

	/**
	 * 分页查询会员商品收藏的信息
	 * @see com.yinlian.wssc.web.service.UsercollectService#queryMemberCollerByCriteria(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryMemberCollerByCriteria(Criteria criteria,Integer pc, Integer ps)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<CollectInfo> beanList = usercollectMapper.selectMemberCollectByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
		return pageBean;
	}
	/**
	 * 分页查询会员店铺收藏的信息
	 * @see com.yinlian.wssc.web.service.UsercollectService#queryMemberCollerShopByCriteria(com.yinlian.wssc.web.util.CriteriaCollect, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryMemberCollerShopByCriteria(CriteriaCollect criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<CollectInfo> beanList = usercollectMapper.selectMemberCollectShopByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * 根据id修改会员收藏
	 * @see com.yinlian.wssc.web.service.UsercollectService#delectById(java.lang.Integer)
	 */
	@Override
	public int updateMemberCollectById(Usercollect usercollect) throws Exception {
		
		return usercollectMapper.updateByPrimaryKey(usercollect);
	}

	/**
	 * 根据id查询会员收藏的信息
	 * @see com.yinlian.wssc.web.service.UsercollectService#queryById(java.lang.Integer)
	 */
	@Override
	public Usercollect queryById(Integer id) throws Exception {
		
		return usercollectMapper.selectByPrimaryKey(id);
	}

	@Override
	public int add(Usercollect usercollect) throws Exception {
		
		return usercollectMapper.insert(usercollect);
	}

	@Override
	public int delShop(Integer userId, Integer shopId,Date delTime) throws Exception {
		
		return usercollectMapper.delShop(userId, shopId, delTime);
	}
    @Override
    public int delCollectShops(Integer userid,List<Integer> shopids,Date delTime) throws Exception {		
		return usercollectMapper.delCollectShops(userid, shopids, delTime);
	}
	@Override
	public int delSpu(Integer userId, Integer spuId,Date delTime) throws Exception {
		
		return usercollectMapper.delSpu(userId, spuId, delTime);
	}
	@Override
	public int delCollectSpus(Integer userid,List<Integer> spuids,Date delTime) throws Exception{
		return usercollectMapper.delCollectSpus(userid, spuids, delTime);
	}
	@Override
	public List<Usercollect> selectShop(Integer userId, Integer shopId)
			throws Exception {
		
		return usercollectMapper.selectShop(userId, shopId);
	}

	@Override
	public List<Usercollect> selectSpu(Integer userId, Integer spuId)
			throws Exception {
		
		return usercollectMapper.selectSpu(userId, spuId);
	}

	@Override
	public PageBean selectPage(CriteriaCollect criteria,Integer pc, Integer ps)throws Exception {//Integer userId, Integer type
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Usercollect> beanList = usercollectMapper.selectPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
		return pageBean;
	}
	@Override
	public List<Usercollect> select(Integer userId, Integer type)
			throws Exception {
		
		return usercollectMapper.select(userId, type);
	}

	@Override
	public Integer getCountByShopId(Integer shopid) throws Exception {
		
		return usercollectMapper.getCountByShopId(shopid);
	}
	/**
	 * 是否收藏商品
	 */
	@Override
	public Boolean IsCollectSpu(Integer userId, Integer spuId) throws Exception {
		Boolean flag=false;
		List<Usercollect> list=usercollectMapper.selectSpu(userId, spuId);
		if(list!=null&&list.size()>0){
			flag=true;
		}
		return flag;
	}
	/**
	 * 是否收藏店铺
	 */
	@Override
	public Boolean IsCollectShop(Integer userId, Integer shopid)
			throws Exception {
		Boolean flag=false;
		List<Usercollect> list=usercollectMapper.selectShop(userId, shopid);
		if(list!=null&&list.size()>0){
			flag=true;
		}
		return flag;
	}
	@Override
	public List<ConcernSpuDto> getConcernSpuByUser(Integer userid)
			throws Exception {
		
		return usercollectMapper.getConcernSkuByUser(userid);
	}
	@Override
	public PageBean spuListByPage(Pc_CollectCriteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<PcSpuCollectDto> beanList = usercollectMapper.selectSpuCollectByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
		return pageBean;
	}
	@Override
	public PageBean ShopListByPage(Pc_CollectCriteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<PcShopCollectDto> beanList = usercollectMapper.selectShopCollectByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
		return pageBean;
	}

}
