package com.techown.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techown.wssc.web.mapper.ScenicImageMapper;
import com.techown.wssc.web.po.ScenicImage;
import com.techown.wssc.web.service.ScenicImageService;
@Component("scenicImageService")
public class ScenicImageServiceImpl implements ScenicImageService{

	@Autowired
	private ScenicImageMapper scenicImageMapper;
	
	@Override
	public List<ScenicImage> selectByImageId(String imageId) {
		return scenicImageMapper.selectByImageId(imageId);
	}

}
