package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.wssc.web.dto.UserfinanceDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.OrdersMapper;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.UserFinanceService;
import com.yinlian.wssc.web.util.CriteriaFinance;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("userFinanceService")
public class UserFinanceServiceImpl implements UserFinanceService {
    @Autowired
    private UserfinanceMapper userfinanceMapper;
    
    @Autowired
    private OrdersMapper  ordersMapper;

	@Autowired
	private UsersMapper  usersMapper;

    @Override
    public List<Userfinance> getListByNumber(String code) throws Exception {
        return userfinanceMapper.getListByNumber(code, UserFinance_Type.已支付.getValue());
    }

    @Override
    public Userfinance getUserFinance(Integer status, int id) throws Exception {
        return userfinanceMapper.getUserFinance(id, status);
    }

    /**
     * @see com.yinlian.wssc.web.service.UserFinanceService#selectPage(com.yinlian.wssc.web.util.CriteriaFinance, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectPage(CriteriaFinance criteria, Integer pc, Integer ps) throws Exception {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<UserfinanceDto> beanList = userfinanceMapper.selectPlatformByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

	@Override
	public int insert(Userfinance userfinance) throws Exception {
		return userfinanceMapper.insert(userfinance);
	}
	
	@Override
    public List<Userfinance> getUseridFinance(int userID) throws Exception {
        return userfinanceMapper.getUseridFinance(userID);
    }

	@Override
    public List<Userfinance> getUseridandTimeFinance(int userID,String start,String end) throws Exception {
        return userfinanceMapper.getUseridandTimeFinance(userID,start,end);
    }

	@Override
    public List<Userfinance> getUseridandMonthFinance(int userID,String time) throws Exception {
        return userfinanceMapper.getUseridandMonthFinance(userID,time);
    }
	
	@Override
	public PageBean selectShopFinancePage(CriteriaFinance criteria, int pc, int ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<UserfinanceDto> beanList = userfinanceMapper.selectShopFinanceByPage(pageBeanUtil);
        if(beanList!=null){
        	for (UserfinanceDto dto : beanList) {
        		Users u=usersMapper.getByOrderCode(dto.getNumber());
				if(u!=null){
					dto.setBuyername(u.getNickname());
					dto.setBuyernum(u.getUsername());
				}
        		Orders o=ordersMapper.getByCode(dto.getNumber());
        		if(o!=null){
        			dto.setGroupnum(o.getGroupcode());
        		}
			}
        }
        pageBean.setBeanList(beanList);
        return pageBean;
	}
	
	@Override
	public List<UserfinanceDto> selectShopFinanceList(CriteriaFinance criteria)
			throws Exception {		
		List<UserfinanceDto> list=userfinanceMapper.selectShopFinanceList(criteria);
		if(list!=null){
			for (UserfinanceDto dto : list) {
				Users u=usersMapper.getByOrderCode(dto.getNumber());
				if(u!=null){
					dto.setBuyername(u.getNickname());
					dto.setBuyernum(u.getUsername());
				}
				Orders o=ordersMapper.getByCode(dto.getNumber());
        		if(o!=null){
        			dto.setGroupnum(o.getGroupcode());
        		}
			}
		}
		return list;
	}
	
}
