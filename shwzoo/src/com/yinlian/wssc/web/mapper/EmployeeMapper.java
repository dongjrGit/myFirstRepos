package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Employee;
import com.yinlian.wssc.web.po.EmployeeExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer id);

    Employee selectByPrimaryStatus(Integer id);

    int updateByExampleSelective(@Param("record") Employee record,
                                 @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    /**
     * 查询出店铺下所有员工
     * @param pageBeanUtil
     * @return
     */
    List<Employee> selectEmployeeByShopidPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 返回自只能主键
     * @param employee
     */
    int insertForId(Employee employee) throws Exception;
}