package com.mobile.application.service.shootmater.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.dao.IShootMaterDao;
import com.mobile.application.dao.impl.BaseDAOImpl;
import com.mobile.application.entity.TBasisShootMater;
import com.mobile.application.service.shootmater.IShootMaterService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;
import com.mysql.jdbc.Util;

@Service
public class ShootMaterServiceImpl extends BaseDAOImpl<TBasisShootMater> implements IShootMaterService {

	@Autowired
	private IShootMaterDao ShootMaterDao;

	@Override
	@Transactional
	public List<?> qryShootMater(HttpSession session,String type) {
		
		return this.ShootMaterDao.qryShootMater(type);
	}

	@Override
	@Transactional
	public CommonVO delShootMater(HttpSession session, String materId,
			String materIdPid) {
		TBasisShootMater mater = (TBasisShootMater) this.getByID(TBasisShootMater.class, materId);
		this.delete(mater);
		return new CommonVO(true,"删除成功");
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public CommonVO saveShootMater(HttpSession session, String materId,
			String materPid, String materLevel, String materName,
			String shootRequire,String materType) {
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		List<?> listId= this.ShootMaterDao.qryMaterbypid(materType,materName);
		//List <TBasisShootMater> list = (List<TBasisShootMater>) this.get(TBasisShootMater.class, "materName", materName);
		if(StringUtils.isBlank(materId)){
			//是否重复
			if(listId.size()>0){
				return new CommonVO(false,"名称重复");
			}
			TBasisShootMater mater = new TBasisShootMater();
			mater.setMaterLevel(materLevel);
			mater.setMaterName(materName);
			mater.setMaterPid(materPid);
			mater.setShootRequire(shootRequire);
			mater.setCreatUser(sessionVO.getUserId());
			mater.setMaterType(materType);
			mater.setCreatTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			this.save(mater);
		}else{
			//是否重复
			if(listId.size()>0){
				if(!listId.get(0).equals(materId))
				return new CommonVO(false,"名称重复");
			}
			TBasisShootMater mater1 = (TBasisShootMater) this.getByID(TBasisShootMater.class, materId);
			mater1.setMaterName(materName);
			mater1.setShootRequire(shootRequire);
			this.update(mater1);
		}
		return new CommonVO(true,"保存成功");
	}
	
	@Override
	@Transactional
	public List<?> qryShootingDict(String lastUpdateTime){
		return this.ShootMaterDao.qryShootingDict(lastUpdateTime);
	}
	
}
