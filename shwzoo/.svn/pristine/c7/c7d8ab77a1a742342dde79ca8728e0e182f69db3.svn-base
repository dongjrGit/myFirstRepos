package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.wssc.platform.controller.DepartmentController;
import com.yinlian.wssc.web.dto.DepartmentDto;
import com.yinlian.wssc.web.mapper.DepartmentMapper;
import com.yinlian.wssc.web.po.Department;
import com.yinlian.wssc.web.po.DepartmentExample;
import com.yinlian.wssc.web.service.DepartmentService;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;

public class DepartmentServiceImpl implements DepartmentService {

    /**
     * 日志输出的类
     */

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentMapper    departmentMapper;

    /** 
     * 
     * @see com.yinlian.wssc.web.service.DepartmentService#queryDepartByName(java.lang.String)
     */
    @Override
    public List<Department> queryDepartByName(String keyWords) throws Exception {
        Map<String, Object> map = new HashedMap();
        map.put("keyword", keyWords);
        return departmentMapper.selectDepartByName(map);
    }

    /**
     * 
     * @see com.yinlian.wssc.web.service.DepartmentService#queryAll()
     */
    @Override
    public List<DepartmentDto> queryAll(Integer fatherid) throws Exception {
        //获取一级部门
        return querydepart(fatherid);
    }

    private List<DepartmentDto> querydepart(Integer fatherid) {
        List<DepartmentDto> list = new ArrayList<DepartmentDto>();
        list = departmentMapper.queryAll(fatherid);
        if (list != null && list.size() > 0) {
            for (DepartmentDto departmentDto : list) {
                if (departmentDto != null) {
                    List<DepartmentDto> list2 = querydepart(departmentDto.getId());
                    departmentDto.setChildrens(list2);
                }
            }
        }

        return list;
    }

    /**
     * 根据fatherid获取子部门信息
     */
    public List<Department> getByFatherID(Integer fatherid) throws Exception {
        return departmentMapper.getByFatherID(fatherid);
    }

    /**
     * 修改部门状态
     */
    public int updateStatus(Integer status, Integer id) throws Exception {
        Department department = new Department();
        department.setId(id);
        department.setStatus(status);
        return departmentMapper.updateStatus(department);
    }

    /**
     * 
     * @see com.yinlian.wssc.web.service.DepartmentService#addDepart(com.yinlian.wssc.web.po.Department)
     */
    @Override
    public int addDepart(Department department) throws Exception {
        Integer fatherid = department.getFatherid();
        String fullpath = "";
        if (fatherid != null && fatherid != 0) {
            Department _department = departmentMapper.selectByPrimaryKey(fatherid);
            fullpath = _department.getFullpath();
        }
        departmentMapper.insertGetId(department);
        Integer id = department.getId();
        department.setFullpath(fullpath + id + ",");
        fullpath = department.getFullpath();
        int count = StringUtils.calidator(fullpath);
        department.setLevel(count);
        return departmentMapper.updateByPrimaryKey(department);
    }

    /**
     * 
     * @see com.yinlian.wssc.web.service.DepartmentService#deleteById(java.lang.String)
     */
    @Override
    public int deleteById(String id) throws Exception {

        return departmentMapper.deleteByPrimaryKey(StringUtilsEX.ToInt(id));
    }

    /**
     * 
     * @see com.yinlian.wssc.web.service.DepartmentService#updateStatusById(java.lang.String, java.lang.String)
     */
    @Override
    public int updateStatusById(String id, String status) throws Exception {
        Department record = departmentMapper.selectByPrimaryKey(StringUtilsEX.ToInt(id));
        Department department = new Department();
        department.setStatus(StringUtilsEX.ToInt(status));
        BeanUtils.copyProperties(department, record);
        return departmentMapper.updateByPrimaryKey(record);
    }

    /**
     * 
     * @see com.yinlian.wssc.web.service.DepartmentService#updateDepartById(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int updateDepartById(String id, String num, String name, String fatherID,
                                String fullPath, String status) throws Exception {
        Department record = departmentMapper.selectByPrimaryKey(StringUtilsEX.ToInt(id));
        Department department = new Department();
        department.setNum(num);
        department.setName(name);
        department.setFatherid(StringUtilsEX.ToInt(fatherID));
        department.setFullpath(fullPath);
        department.setStatus(StringUtilsEX.ToInt(status));
        BeanUtils.copyProperties(department, record);

        return departmentMapper.updateByPrimaryKey(record);
    }

    /**
     * 
     * @see com.yinlian.wssc.web.service.DepartmentService#queryDepartById(java.lang.Integer)
     */
    @Override
    public DepartmentDto queryDepartById(Integer id) throws Exception {

        return departmentMapper.selectDepartById(id);
    }

    @Override
    public List<Department> queryAll() throws Exception {
        DepartmentExample example = new DepartmentExample();

        return departmentMapper.selectByExample(example);
    }

    /**
     * 获取部门全路径名称
     * @param fullpath
     * @return
     */
    public String getFullname(String fullpath) {
        String[] s = fullpath.split(",");
        String rsl = "";
        for (String id : s) {
            Department depart = departmentMapper.selectByPrimaryKey(Integer.parseInt(id));
            if (depart != null) {
                if (rsl == "")
                    rsl = rsl.concat(depart.getName());
                else
                    rsl = rsl.concat("->" + depart.getName());
            }
        }
        return rsl;
    }

    public Department getByID(Integer id) throws Exception {
        return departmentMapper.selectByPrimaryKey(id);
    }
}
