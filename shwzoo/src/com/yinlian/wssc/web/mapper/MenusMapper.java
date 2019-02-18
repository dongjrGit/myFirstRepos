package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Menus;
import com.yinlian.wssc.web.po.MenusExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface MenusMapper {
    int countByExample(MenusExample example);

    int deleteByExample(MenusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menus record);

    int insertSelective(Menus record);

    List<Menus> selectByExample(MenusExample example);

    Menus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menus record,
                                 @Param("example") MenusExample example);

    int updateByExample(@Param("record") Menus record, @Param("example") MenusExample example);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);

    /**
     * 查询所有后台的的一级菜单
     * @param fatherid
     * @return
     */
    List<Menus> selectAllPlatformMenus(Integer fatherid) throws Exception;

    int updateFullPath(Menus record) throws Exception;

    int updateStatus(Menus record) throws Exception;

    int insertMenu(Menus record) throws Exception;

    List<Menus> selectMenuByPage(PageBeanUtil pBeanUtil) throws Exception;

    List<Menus> selectByFullpath(String _parameter) throws Exception;

    int updateMenuList(List<Menus> list) throws Exception;

    List<Menus> getListByFatherid(Integer fid) throws Exception;

    /**
     * 查询所有的卖家的一级菜单
     * @param fatherid
     * @return
     */
    List<Menus> selectAllSellerMenus(Integer fatherid) throws Exception;

    /**
     * 
     * @param rigths
     * @return
     */
    List<Menus> selectByRigth(List<Integer> rigths) throws Exception;
    
    int deletemenu(Menus record) throws Exception;

}