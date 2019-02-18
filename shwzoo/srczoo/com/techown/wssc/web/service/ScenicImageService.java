package com.techown.wssc.web.service;

import java.util.List;

import com.techown.wssc.web.po.ScenicImage;

public interface ScenicImageService {
	public List<ScenicImage> selectByImageId(String imageId);
}
