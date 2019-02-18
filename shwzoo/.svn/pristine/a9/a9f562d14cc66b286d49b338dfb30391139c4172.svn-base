package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.ImagesMapper;
import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.service.ImagesService;

@Component("imagesService")
public class ImagesServiceImpl implements ImagesService {

	@Autowired 
	private ImagesMapper imagesMapper ;
	@Override
	public List<Images> getByFKid(int fkid) throws Exception {

		return imagesMapper.getByFKid(fkid);
	}
	@Override
	public List<Images> queryImages(Integer id, int value) throws Exception {

		return imagesMapper.getImages(id,value);
	}

}
