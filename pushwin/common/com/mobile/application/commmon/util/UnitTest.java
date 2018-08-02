package com.mobile.application.commmon.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.SerializationUtils;

import com.mobile.application.vo.CommonVO;

public class UnitTest {
	@Test
	public void objSerialize() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException{
		
		
		List<CommonVO> vos = new ArrayList<CommonVO>();
		CommonVO vo = new CommonVO(false, "成功了");
		CommonVO vo2 = new CommonVO(true, "成功了");
		vos.add(vo2);
		vos.add(vo);
		JSONObject.fromObject(vo);
		
		Method method = ReflectionUtils.findMethod(vo.getClass(), "isSuccess");
		System.out.println(method.invoke(vo, null));
		Field field = vo.getClass().getDeclaredField("msg");
		if(!Modifier.isPublic(field.getModifiers())){  
            field.setAccessible(true);  
        }
		field.set(vo, "失败了");
//		byte[] objByte = SerializationUtils.serialize(vo);
//		CommonVO vo2 = (CommonVO) SerializationUtils.deserialize(objByte);
//		System.out.println(vo2.getMsg());
		System.out.println(ToStringBuilder.reflectionToString(JSONObject.fromObject(vo), ToStringStyle.MULTI_LINE_STYLE));
//		FileSystemUtils.deleteRecursively(new File("D:/xx"));
	}
	
}
