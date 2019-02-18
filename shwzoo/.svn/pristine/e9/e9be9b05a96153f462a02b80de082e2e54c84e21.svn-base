package com.yinlian.wssc.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.PointRuleEnum;
import com.yinlian.Enums.PointsRecordsFromTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.pc.dto.PcPointsDto;
import com.yinlian.wssc.search.Pc_PointsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.PointsrecordsMapper;
import com.yinlian.wssc.web.mapper.PointsruleMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.mapper.UserslevelMapper;
import com.yinlian.wssc.web.po.Pointsrecords;
import com.yinlian.wssc.web.po.Pointsrule;
import com.yinlian.wssc.web.po.PointsruleExample;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.CriteriaMemberPoints;
import com.yinlian.wssc.web.util.PageBeanUtil;
@Component("pointsRecordService")
public class PointsRecordServiceImpl implements PointsRecordService {

	@Autowired
	private PointsrecordsMapper pointsrecordsMapper;
	
	@Autowired
	private UserslevelMapper userslevelMapper;
	
	@Autowired
	private PointsruleMapper pointsruleMapper;
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired  UserslevelService  userslevelService;
	 
	@Override
	public int addPoints(Integer userid)
			throws Exception {
		Users user=usersMapper.selectByPrimaryKey(userid);
		PointsruleExample example = new PointsruleExample();
		PointsruleExample.Criteria criteria = example.createCriteria();
		Integer levelid=user.getLevelid();
		if(levelid==null || levelid==-1){
			Userslevel userlevel = userslevelService.getlowerLevel();
		    levelid=userlevel.getId();
		}
		criteria.andUserlevelEqualTo(levelid);
		criteria.andNameEqualTo(PointRuleEnum.签到.getValue());
		List<Pointsrule> list=pointsruleMapper.selectByExample(example);
		Pointsrecords pointrecords=new Pointsrecords();
		if(list!=null&& list.size()>0){
			pointrecords.setPoints(list.get(0).getPoints());
			pointrecords.setType(PointsRecordsTypeEnum.增加.getValue());
			pointrecords.setFromtype (PointsRecordsFromTypeEnum.签到.getValue());
			pointrecords.setCreatetime (new Date());
			pointrecords.setUserid(userid);
			udpateusers(userid,list);
			
			return pointsrecordsMapper.insertSelective(pointrecords);
		}else{
			return 0;
		}
	
	}
	
	public List<Pointsrecords> selectByuserid(Integer userid) throws Exception{
		return pointsrecordsMapper.selectByuserid(userid);
	}
	
	
	public int udpateusers(Integer userid,List<Pointsrule> list) throws Exception{
		Users users=usersMapper.selectByPrimaryKey(userid);
		Integer total=users.getTotalpoints();
		Integer points=users.getPoints();
		if(points==null){
			points=0;
		}
		if(total==null){
			total=0;
		}
		Integer rulePoInt=list.get(0).getPoints();
		users.setTotalpoints(total+rulePoInt);
		users.setPoints(points+rulePoInt);
		return usersMapper.updateByPrimaryKey(users);
	}

	@Override
	public List<Pointsrecords> selectByuser(Integer userid)
			throws Exception {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		return pointsrecordsMapper.selectByuser(userid, sim.format(new Date()));
	}
	
	/**
	 * 修改用户积分
	 * @author fjh
	 * @param points:要修改的积分数量
	 * @param userid:用户id
	 * @param fromtype:积分来源
	 * @param recordtype:修改积分类型
	 * @return 0:根据id未能获取到用户信息
	 * @return -1:用户可用积分不足
	 * @return >0:修改用户积分正常
	 */
	public int updateUserPoint(int points,int userid,int fromtype,int recordtype)throws Exception{
		Users user=usersMapper.selectByPrimaryKey(userid);
	    if(user==null){
		    return 0;
	    }
		int totalpoints=user.getTotalpoints();
		int userpoints = user.getPoints();
		if(recordtype==PointsRecordsTypeEnum.增加.getValue()){
			userpoints += points;
			totalpoints += points;
		}else{
			if (userpoints < points) {
				return -1;
			}
			userpoints -= points;
		}
		user.setPoints(userpoints);
		user.setTotalpoints(totalpoints);
		usersMapper.updateByPrimaryKey(user);
		Pointsrecords pointrecords=new Pointsrecords();
		pointrecords.setPoints(points);
		pointrecords.setType(recordtype);
		pointrecords.setFromtype (fromtype);
		pointrecords.setCreatetime (new Date());
		pointrecords.setUserid(userid);
		return pointsrecordsMapper.insert(pointrecords);
	}

	@Override
	public Integer getPoints(Integer userId) throws Exception {
		Integer points=0;
		Users users=usersMapper.selectByPrimaryKey(userId);
		Integer levelId=users.getLevelid();
		if(levelId==null){
			Userslevel userlevel = userslevelMapper.levelOrder();
			levelId=userlevel.getId();
		}
		PointsruleExample example = new PointsruleExample();
		PointsruleExample.Criteria criteria = example.createCriteria();
		criteria.andUserlevelEqualTo(levelId);
		criteria.andNameEqualTo(PointRuleEnum.签到.getValue());
		List<Pointsrule> list=pointsruleMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0).getPoints();
		}else{
			return points;
		}
		
	}

	@Override
	public List<Pointsrecords> queryByUserId(Integer userId) throws Exception {
		return pointsrecordsMapper.selectByuserid(userId);
	}

	@Override
	public int getContinousCount(Integer userId) throws Exception {
		int count=0;
		List<Pointsrecords> list=pointsrecordsMapper.selectByuserid(userId);
		if(list!=null&&list.size()>0){
			List<Date> datalist = new ArrayList<Date>();
			for (int i = 0; i < list.size(); i++) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String data=list.get(i).getCreatetimestr();
				datalist.add(sdf.parse(data));
			}
			int size=0;
			int num=1;
			Date date=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date cd=datalist.get(0);
			String _date=sdf.format(date);
			if((!sdf.format(date).equals(sdf.format(cd)))&&((sdf.parse(_date).getTime())-cd.getTime()!=1000*60*60*24)){
				return 0;
			}
			long l1,l2;
			while(size+1<datalist.size()){
				l1=datalist.get(size).getTime();
				l2=datalist.get(size+1).getTime();
				if(l1-l2==1000*60*60*24){
					num++;
					size++;
				}else{
					break;
				}
			}
			return num;
		}
		return count;
	}

	@Override
	public PageBean pointListByPage(Pc_PointsCriteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<PcPointsDto> beanList = pointsrecordsMapper.selectPointByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public PageBean PlatformpointListByPage(CriteriaMemberPoints criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																			// 设置其他的参数
																			// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<PcPointsDto> beanList = pointsrecordsMapper.selectPlatformPointByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

}
