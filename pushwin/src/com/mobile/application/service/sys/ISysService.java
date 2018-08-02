package com.mobile.application.service.sys;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.entity.TBasisSystem;

public interface ISysService {

	Map<String, String> qrySysSetting();

	void saveSysSetting(MultipartFile file, TBasisSystem tBasisSystem) throws BusinessException, IOException;

}
