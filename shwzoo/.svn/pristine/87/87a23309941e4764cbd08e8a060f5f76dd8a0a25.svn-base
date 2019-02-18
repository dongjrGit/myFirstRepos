package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.pc.dto.ReceiveaddrDto;
import com.yinlian.wssc.web.mapper.ReceiveaddressMapper;
import com.yinlian.wssc.web.po.Receiveaddress;
import com.yinlian.wssc.web.service.ReceiveAddressService;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 收货人地址实现层
 * @author admin
 *
 */
public class ReceiveAddressServiceImpl implements ReceiveAddressService{
	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private ReceiveaddressMapper receiveAddressMapper;

	@Override
	public int insert(String userId,String name, String mobile, String provinceCode,String provinceName,
			String CityCode,String cityName,String AreaCode,String areaName, String address, Integer isDefault)
			throws Exception {
		Receiveaddress receiveaddress=new Receiveaddress();
		receiveaddress.setUserid(StringUtilsEX.ToInt(userId));
		receiveaddress.setMobile(mobile);
		receiveaddress.setAddress(address);
		receiveaddress.setName(name);
		
		receiveaddress.setAreacode(AreaCode);
		receiveaddress.setAreaname(areaName);
		
		receiveaddress.setCitycode(CityCode);
		receiveaddress.setCityname(cityName);
		
		receiveaddress.setProvincecode(provinceCode);
		receiveaddress.setProvincename(provinceName);
		
		receiveaddress.setIsdefault(isDefault);//1 表示默认地址     0表示不是默认地址
		
		if(isDefault==1){
			List<Receiveaddress> list=receiveAddressMapper.selectByUserId(StringUtilsEX.ToInt(userId));
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setIsdefault(0);
					receiveAddressMapper.updateByPrimaryKey(list.get(i));
				}
			}
		}
		
		return receiveAddressMapper.insert(receiveaddress);
	}

	@Override
	public List<Receiveaddress> queryAllByUserId(String userId) throws Exception {
		 if (userId == null) {
	            if (logger.isDebugEnabled()) {
	                logger.debug(" The parameter userId is null");
	                throw new IllegalArgumentException(" The parameter userId is null");
	            }
		 }
		return receiveAddressMapper.selectByUserId(StringUtilsEX.ToInt(userId));
	}

	@Override
	public int delByID(String id) throws Exception {
		 if (id == null) {
	            if (logger.isDebugEnabled()) {
	                logger.debug(" The parameter id is null");
	                throw new IllegalArgumentException(" The parameter id is null");
	            }
		 }
		 Receiveaddress  address=receiveAddressMapper.selectByPrimaryKey(StringUtilsEX.ToInt(id));
		 Integer userid=address.getUserid();
		 Integer isDefault=address.getIsdefault();
		 if(isDefault==1){
			 List<Receiveaddress> list=receiveAddressMapper.selectByUserId(userid);
			 for (Receiveaddress receiveaddress : list) {
				Integer  _id=receiveaddress.getId();
				if(StringUtilsEX.ToInt(id)!=_id){
					receiveaddress.setIsdefault(1);
					receiveAddressMapper.updateByPrimaryKey(receiveaddress);
					break;
				}
			 }
		 }
		return receiveAddressMapper.deleteByPrimaryKey(StringUtilsEX.ToInt(id));
	}

	@Override
	public Receiveaddress selectByPrimaryKey(int id) throws Exception {
		return receiveAddressMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateReceiver(Receiveaddress receiveaddress) throws Exception {
		
		if(receiveaddress.getIsdefault()==1){
//			List<Receiveaddress> list=receiveAddressMapper.selectByUserId(receiveaddress.getUserid());
//			if(list!=null&&list.size()>0){
//				for (int i = 0; i < list.size(); i++) {
//					if(list.get(i).getId()!=receiveaddress.getId()){
//						list.get(i).setIsdefault(0);
//						receiveAddressMapper.updateByPrimaryKey(list.get(i));
//					}
//				}
//			}
			receiveAddressMapper.updateDefault(receiveaddress.getUserid(), receiveaddress.getId());
		}
		receiveAddressMapper.updateByPrimaryKey(receiveaddress);
	}
	
	@Override
	public Receiveaddress getDefaultAddress(int userId){
		List<Receiveaddress> addressList =receiveAddressMapper.selectByUserId(userId);
		for(Receiveaddress address : addressList){
			if(address.getIsdefault() == 1)
				return address;
		}
		return addressList.isEmpty() ? new Receiveaddress() : addressList.get(0);
		
	}

	@Override
	public int inserAddress(Receiveaddress receiveaddress) throws Exception {
		
		return receiveAddressMapper.insertSelective(receiveaddress);
	}
	
	public List<ReceiveaddrDto> getByUser(int userid)throws Exception{
		List<Receiveaddress> list= receiveAddressMapper.selectByUserId(userid);
		List<ReceiveaddrDto> dtolist=new ArrayList<ReceiveaddrDto>();
		ReceiveaddrDto dto=null;
		if(list!=null && list.size()>0){
			for (Receiveaddress rd : list) {
				dto=new ReceiveaddrDto();
				dto.setAddress(rd.getAddress());
				dto.setArea(rd.getAreaname());
				dto.setCity(rd.getCityname());
				dto.setId(rd.getId());
				dto.setIsdefault(rd.getIsdefault());
				dto.setMobile(rd.getMobile());
				dto.setProvince(rd.getProvincename());
				dto.setUsername(rd.getName());
				dtolist.add(dto);
			}
		}
		return dtolist;
	}
	
}
