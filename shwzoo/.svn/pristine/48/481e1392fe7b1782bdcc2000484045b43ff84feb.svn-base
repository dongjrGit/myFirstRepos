package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.search.IdCardCriteria;
import com.yinlian.wssc.web.mapper.IdcardinfoMapper;
import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.po.IdcardinfoExample;
import com.yinlian.wssc.web.service.IdCardinfoService;

@Component("idCardinfoService")
public class IdCardinfoServiceImpl implements IdCardinfoService {
	@Autowired
	private IdcardinfoMapper idcardinfoMapper;

	@Override
	public int insert(Idcardinfo idcardinfo) throws Exception {
		return idcardinfoMapper.insert(idcardinfo);
	}

	@Override
	public List<Idcardinfo> quertByUserId(int userid) throws Exception {
		return idcardinfoMapper.quertByUserId(userid);
	}

	@Override
	public Integer checkcardinfo(IdCardCriteria criteria) throws Exception {
		Integer count = idcardinfoMapper.checkcardinfo(criteria);
		if (count == null) {
			count = 0;
		}
		return count;
	}

	@Override
	public Idcardinfo getByGroupCode(String groupcode) throws Exception {
		return idcardinfoMapper.getByGroupCode(groupcode);
	}

	@Override
	public int delCardInfo(Idcardinfo idcardinfo) throws Exception {
		return idcardinfoMapper.delCardInfo(idcardinfo);
	}

}
