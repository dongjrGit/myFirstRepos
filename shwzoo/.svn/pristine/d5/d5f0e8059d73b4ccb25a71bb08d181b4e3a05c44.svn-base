package com.yinlian.wssc.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.DepartmentDto;
import com.yinlian.wssc.web.po.Department;
import com.yinlian.wssc.web.po.DepartmentExample;

public interface DepartmentMapper {
    int countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Department record,
                                 @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record,
                        @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectDepartByName(Map<String, Object> map);

    List<DepartmentDto> queryAll(Integer fatherid);

    DepartmentDto selectDepartById(Integer id);

    /**
     * 添加部门并返回主键
     * @param department
     */
    void insertGetId(Department department);
    
    List<Department> getByFatherID(Integer fatherid)throws Exception;
    
    int updateStatus(Department record)throws Exception;
}