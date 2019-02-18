package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.DepartmentDto;
import com.yinlian.wssc.web.po.Department;

/**
 * 部门管理的业务类
 * @author Administrator
 *
 */

public interface DepartmentService {

	/**
	 * 查询所有部门
	 * @return
	 * @throws Exception
	 */
	List<DepartmentDto> queryAll(Integer fatherid) throws Exception;

	/**
	 * 根据name查询部门
	 * @param keyWords
	 * @return
	 */
	List<Department> queryDepartByName(String keyWords) throws Exception;

	/**
	 * 根据id删除部门
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteById(String id) throws Exception;

	/**
	 * 根据id修改状态 0 启用 1禁用
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	int updateStatusById(String id, String status) throws Exception;

	/**
	 * 添加部门
	 * @param department
	 * @return
	 * @throws Exception
	 */
	int addDepart(Department department) throws Exception;

	/**
	 * 根据id修改部门
	 * @param id
	 * @param num
	 * @param name
	 * @param fatherID
	 * @param fullPath
	 * @param status
	 * @return
	 * @throws Exception
	 */
	int updateDepartById(String id, String num, String name,
			String fatherID, String fullPath, String status) throws Exception;

	/**
	 * 根据id查询部门
	 * @param id
	 * @return
	 * @throws Exception
	 */
	DepartmentDto queryDepartById(Integer id) throws Exception;

	/**
	 * 查询所有的部门
	 * @return
	 */
	List<Department> queryAll() throws Exception;
	
	List<Department> getByFatherID(Integer fatherid)throws Exception;
	    
	int updateStatus(Integer status,Integer id)throws Exception;

	public Department getByID(Integer id)throws Exception;

}
