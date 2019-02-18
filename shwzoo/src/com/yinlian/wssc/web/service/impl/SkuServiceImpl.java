package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.dto.SkuValuesDto;
import com.yinlian.wssc.web.dto.Sku_SpecsvDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SkuMapper;
import com.yinlian.wssc.web.mapper.SkuSpecsvMapper;
import com.yinlian.wssc.web.mapper.SkuSpecsvMapperCustom;
import com.yinlian.wssc.web.mapper.SpecsvaluesMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuSpecsv;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaSku;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 库存商品业务类
 * 
s * @author Administrator
 *
 */
public class SkuServiceImpl implements SkuService {

	@Autowired
	private SkuMapper skuMapper;

	@Autowired
	private SpecsvaluesMapper valuesMapper;

	@Autowired
	private SkuSpecsvMapper specsvMapper;

	@Autowired
	private SkuSpecsvMapperCustom skuSpecsvMapperCustom;

	@Autowired
	private SpuMapper spuMapper;

	/**
	 * 添加库存商品
	 */
	public int insert(Sku sku) throws Exception {
		return skuMapper.insert(sku);
	}

	/**
	 * 编辑库存商品
	 */
	public int updateSku(Sku sku) throws Exception {
		return skuMapper.updateByPrimaryKeyWithBLOBs(sku);
	}

	/**
	 * 根据ID获取库存商品
	 */
	public Sku selectByID(Integer id) throws Exception {
		return skuMapper.selectByPrimaryKey(id);
	}

	/**
	 * 获取库存商品列表（分页）
	 */
	public PageBean getList(Criteria criteria, Integer pc, Integer ps)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Sku> beanList = skuMapper.getPageList(pageBeanUtil);

		pageBean.setBeanList(beanList);

		return pageBean;
	}

	/**
	 * 根据spuid获取库存商品列表
	 */
	public List<Sku> getListBySpuID(Integer spuid) throws Exception {
		return skuMapper.getList(spuid);
	}

	/**
	 * 库存商品调价
	 * 
	 * @param settype
	 * @param price
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updatePrice(Integer settype, Double price, Integer id)
			throws Exception {
		Sku sku = new Sku();
		Integer returns = -1;
		switch (settype) {
		case 0:
			sku = skuMapper.selectByPrimaryKey(id);
			sku.setPrice(BigDecimal.valueOf(price));
			skuMapper.updatePrice(sku);
			
			Spu spu3 = new Spu();
			spu3.setId(sku.getSpuId());
			Double priceFloat3 = skuMapper.getMinPrice(sku.getSpuId());
			spu3.setPrice(BigDecimal.valueOf(priceFloat3));
			returns = spuMapper.updatePrice(spu3);
			break;
		case 1:
			sku.setOldprice(BigDecimal.valueOf(price));
			sku.setId(id);
			returns = skuMapper.updateOldPrice(sku);
			break;
		case 2:
			sku = skuMapper.selectByPrimaryKey(id);
			sku.setAppprice(BigDecimal.valueOf(price));
			skuMapper.updateAppPrice(sku);
			// 修改spu表价格
			Spu spu = new Spu();
			spu.setId(sku.getSpuId());
			Double priceFloat = skuMapper.getMinPriceBySpu(sku.getSpuId());
			spu.setAppprice(BigDecimal.valueOf(priceFloat));
			returns = spuMapper.updateAppPrice(spu);
			break;
		case 3:
			sku = skuMapper.selectByPrimaryKey(id);
			sku.setWapprice(BigDecimal.valueOf(price));
			skuMapper.updatewapPrice(sku);
			// 修改spu表wap价格
			Spu spu1 = new Spu();
			spu1.setId(sku.getSpuId());
			Double priceFloat1 = skuMapper.getMinWapPrice(sku.getSpuId());
			spu1.setWapprice(BigDecimal.valueOf(priceFloat1));
			returns = spuMapper.updateWapPrice(spu1);
			break;
		case 4:
			sku = skuMapper.selectByPrimaryKey(id);
			sku.setWechatprice(BigDecimal.valueOf(price));
			skuMapper.updateCharPrice(sku);
			// 修改spu表价格
			Spu spu2 = new Spu();
			spu2.setId(sku.getSpuId());
			Double priceFloat2 = skuMapper.getMinWeChatPrice(sku.getSpuId());
			spu2.setWechatprice(BigDecimal.valueOf(priceFloat2));
			returns = spuMapper.updateChatPrice(spu2);
			break;
		default:
			break;
		}
		return returns;
	}

	/**
	 * 
	 * @param sku
	 *            库存商品信息
	 * @param sv_addlist
	 *            可输入规格值添加
	 * @param ssv_addlist
	 *            添加库存关联规格值中间表信息
	 */
	public int addSku(Sku sku, List<Specsvalues> sv_addlist,
			List<SkuSpecsv> ssv_addlist) throws Exception {
		int returns = 0;
		List<Integer> ids=new ArrayList<Integer>();
		for (SkuSpecsv skuSpecsv : ssv_addlist) {
			ids.add(skuSpecsv.getSpecsvId());
		}
		if(ids.size()>0){
			List<Sku_SpecsvDto> has=specsvMapper.isHaveSpecsv(sku.getSpuId(), ids);//查相同spuid下的sku_specsv表 按id分组 若同一skuid则重复
			if(has!=null&&has.stream().anyMatch(x->x.getSv_count() == ids.size())){
				return -2;		
			}
		}
		Spu spu = new Spu();
		spu.setId(sku.getSpuId());		
		
		Double a=skuMapper.getMinPriceBySpu(sku.getSpuId());
		Double priceFloat = a==null?0:a;
		if (priceFloat >(sku.getAppprice()==null?0:sku.getAppprice().doubleValue()))
			spu.setAppprice(sku.getAppprice());
		else {
			spu.setAppprice(BigDecimal.valueOf(priceFloat));
		}
		
		Double bFloat=skuMapper.getMinWapPrice(sku.getSpuId());
		Double wapPrice=bFloat==null?0:bFloat;
		if (wapPrice>(sku.getWapprice()==null?0:sku.getWapprice().doubleValue())) {
			spu.setWapprice(sku.getWapprice());
		}{
			spu.setWapprice(BigDecimal.valueOf(wapPrice));
		}
		
		Double cFloat=skuMapper.getMinWeChatPrice(sku.getSpuId());
		Double wxPrice=cFloat==null?0:cFloat;
		if (wxPrice>(sku.getWechatprice()==null?0:sku.getWechatprice().doubleValue())) {
			spu.setWechatprice(sku.getWechatprice());
		}{
			spu.setWechatprice(BigDecimal.valueOf(wxPrice));
		}
		
		Double dFloat=skuMapper.getMinPrice(sku.getSpuId());
		Double price=dFloat==null?0:dFloat;
		if(price>(sku.getPrice()==null?0:sku.getPrice().doubleValue())){
			spu.setPrice(sku.getPrice());
		}else{
			spu.setPrice(BigDecimal.valueOf(price));
		}
		
		returns = skuMapper.addSku(sku);
		returns = spuMapper.updatePrice(spu);
		if (sv_addlist.size() > 0) {
			// returns = valuesMapper.insertList(sv_addlist);
			SkuSpecsv ssv=null;
			for (Specsvalues sv : sv_addlist) {
				ssv = new SkuSpecsv();
				valuesMapper.insertSpecsvalues(sv);
				ssv.setSkuId(sku.getId());
				ssv.setSpecsvId(sv.getId());
				ssv_addlist.add(ssv);
			}
		}
		for (SkuSpecsv specsv : ssv_addlist) {
			specsv.setSkuId(sku.getId());
		}
		if (ssv_addlist.size() > 0) {
			returns = specsvMapper.insertList(ssv_addlist);
		}

		return returns;
	}

	/**
	 * 编辑库存商品
	 */
	public int editSku(Sku sku, List<Integer> sv_dellist,
			List<Specsvalues> sv_addlist, List<SkuSpecsv> ssv_addlist)
			throws Exception {
		int returns = 0;
		List<Integer> ids=new ArrayList<Integer>();
		for (SkuSpecsv skuSpecsv : ssv_addlist) {
			ids.add(skuSpecsv.getSpecsvId());
		}
		if (ids.size()>0) {
			List<Sku_SpecsvDto> has=specsvMapper.isHaveSpecsv(sku.getSpuId(), ids);//查相同spuid下的sku_specsv表 按id分组 若同一skuid则重复
			if(has!=null&& has.stream().anyMatch(x->x.getSv_count() == ids.size())){
				Object[] svlist = has.stream().filter(x->x.getSv_count() == ids.size()).toArray();
				for(Object sv : svlist)
				{
					Sku_SpecsvDto dto = (Sku_SpecsvDto)sv;
					if (dto.getSkuid() != sku.getId()) {
						return -2;
					}
				}		
			}
		}		
		Spu spu = new Spu();
		spu.setId(sku.getSpuId());

		Double priceFloat = skuMapper.getMinPriceBySpu(sku.getSpuId());
		if (priceFloat > sku.getAppprice().doubleValue())
			spu.setPrice(sku.getAppprice());
		else {
			spu.setPrice(BigDecimal.valueOf(priceFloat));
		}
		returns = skuMapper.updateByPrimaryKeyWithBLOBs(sku);


		// 删除原有的库存关联规格值
		 specsvMapper.deleteBySkuID(sku.getId());

		// 删除不用的可输入的规格值
		if (sv_dellist.size() > 0) {
			valuesMapper.deleteList(sv_dellist);
		}

		// 新增可输入的规格值
		if (sv_addlist.size() > 0) {
			// returns = valuesMapper.insertList(sv_addlist);
			SkuSpecsv ssv = null;
			for (Specsvalues sv : sv_addlist) {				
				returns = valuesMapper.insertSpecsvalues(sv);
				ssv = new SkuSpecsv();
				ssv.setSkuId(sku.getId());
				ssv.setSpecsvId(sv.getId());
				ssv_addlist.add(ssv);
			}
		}

		// 新增库存关联规格值
		if (ssv_addlist.size() > 0) {
			specsvMapper.insertList(ssv_addlist);
		}

		return returns;
	}

	/**
	 * 根据库存商品ID获取关联规格值信息
	 * 
	 * @param skuid
	 * @return
	 * @throws Exception
	 */
	public List<SkuValuesDto> getValueBySkuID(Integer skuid) throws Exception {
		return skuSpecsvMapperCustom.getSpecsVBySkuID(skuid);
	}

	@Override
	public List<Sku> getListByIds(List<Integer> ids) throws Exception {
		return skuMapper.getListByIds(ids);
	}

	@Override
	public int updateStockById(Integer stock, Integer skuId) throws Exception {
		return skuMapper.updateStockById(skuId, stock);
	}
   @Override
	public int updateSetStockById(Integer stock, Integer skuId) throws Exception {
		return skuMapper.updateSetStockById(skuId, stock);
	}
	/**
	 * 根据店铺ID和名称模糊检索sku列表
	 */
	public List<Sku> getSkuStartWithName(Integer shopid, String name)
			throws Exception {
		return skuMapper.getSkuStartWithName(shopid, name);
	}

	@Override
	public List<Sku> getActSkuStartWithName(Integer shopid, String name)
			throws Exception {
		return skuMapper.getActSkuStartWithName(shopid, name);
	}

	
	
	@Override
	public PageBean getUserListOrderSpu(Integer pc, Integer ps,
			CriteriaSku criteria) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																			// 设置其他的参数
																			// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Sku> beanList = skuMapper.getUserListOrderSpuPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public PageBean selectWanrSkuPage(ProductCriteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Sku> beanList = skuMapper.getWarnSKUPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public Sku getByTicketnum(String tnum, Integer id) throws Exception {
		
		return skuMapper.getByTicketnum(id, tnum);
	}

	@Override
	public Sku getByShopTicketnum(String tnum, Integer shopid,Integer id) throws Exception {
		
		return skuMapper.getByShopTicketnum(shopid, tnum,id);
	}

	@Override
	public Sku getBySpuID(Integer spuid) throws Exception {
	
		return skuMapper.getBySpuId(spuid);
	}

}
