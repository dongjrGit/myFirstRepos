/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.view.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yinlian.Enums.SearchAttrType_Enum;
import com.yinlian.Enums.ShopTypeEnum;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.dto.SkuValuesDto;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.ProductImgs;
import com.yinlian.wssc.web.po.ProductSpecs;
import com.yinlian.wssc.web.po.Searchattr;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.Specstype;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.ProductImgsService;
import com.yinlian.wssc.web.service.ProductSpecsService;
import com.yinlian.wssc.web.service.SearchattrService;
import com.yinlian.wssc.web.service.SearchvalueService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpecstypeService;
import com.yinlian.wssc.web.service.SpecsvaluesService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/platform/product")
public class ProductController {

    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private SpecstypeService    specstypeService;
    @Autowired
    private CategoryService     categoryService;
    @Autowired
    private ProductSpecsService productSpecsService;
    @Autowired
    private SpecsvaluesService  specsvaluesService;
    @Autowired
    private BrandService        brandService;

    @Autowired
    private SearchattrService   searchattrService;
    @Autowired
    private SearchvalueService  searchvalueService;

    @Autowired
    private SpuService          spuService;
    @Autowired
    private FreightService      freightService;

    @Autowired
    private SkuService          skuService;

    @Autowired
    private ProductImgsService  productImgsService;
    
    @Autowired
    private ShopService         shopService;
    /**
     * 添加规格类型
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/specstype_add")
    public String specstype_add() {

        return "platform/product/specstype_add";
    }

    /**
     * 规格类型列表
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/specstype_list")
    public String specstype_list() {

        return "platform/product/specstype_list";
    }

    /**
     * 编辑规格类型
     * 
     * @param Id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/specstype_edit")
    public String specstype_edit(@RequestParam("id") String Id, ModelMap model) {
        Specstype stype = new Specstype();
        String fid = "", sid = "", tid = "", fullpath = "";
        try {
            if (StringUtilsEX.ToInt(Id) > 0) {
                stype = new Specstype();
                stype = specstypeService.getByID(Integer.parseInt(Id));
                // 获取分类ID
                CategoryDTO dto = new CategoryDTO();
                dto = categoryService.queryById(stype.getClassid());
                if (dto != null) {
                    fullpath = dto.getFullpath();

                    if (fullpath.split(",").length > 0) {

                        switch (fullpath.split(",").length) {
                            case 1:
                                fid = fullpath.split(",")[0];
                                break;
                            case 2:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                break;
                            case 3:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                tid = fullpath.split(",")[2];
                                break;
                            default:
                                break;
                        }
                    } else {
                        fid = dto.getId().toString();
                    }
                }

            }
        } catch (Exception e) {
        }
        model.addAttribute("data", stype);
        model.addAttribute("fid", fid);
        model.addAttribute("sid", sid);
        model.addAttribute("tid", tid);
        return "platform/product/specstype_edit";
    }

    /**
     * 添加规格类型（从商品分类页面进入）
     * 
     * @param classId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/class_stypeadd")
    public String class_stypeadd(@RequestParam("cid") String classId, ModelMap model) {
        String fid = "", sid = "", tid = "", fullpath = "";
        try {
            if (StringUtilsEX.ToInt(classId) > 0) {
                // 获取分类ID
                CategoryDTO dto = new CategoryDTO();
                dto = categoryService.queryById(StringUtilsEX.ToInt(classId));
                if (dto != null) {
                    fullpath = dto.getFullpath();
                    if (fullpath.split(",").length > 0) {
                        switch (fullpath.split(",").length) {
                            case 1:
                                fid = fullpath.split(",")[0];
                                break;
                            case 2:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                break;
                            case 3:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                tid = fullpath.split(",")[2];
                                break;
                            default:
                                break;
                        }

                    } else {
                        fid = dto.getId().toString();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("classid", classId);
        model.addAttribute("fid", fid);
        model.addAttribute("sid", sid);
        model.addAttribute("tid", tid);
        return "platform/product/class_stypeadd";
    }

    /**
     * 编辑规格类型（从商品分类页面进入）
     * 
     * @param Id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/class_stypeedit")
    public String class_stypeedit(@RequestParam("id") String Id, ModelMap model) {
        String fid = "", sid = "", tid = "", fullpath = "";
        Specstype stype = null;
        try {
            if (StringUtilsEX.ToInt(Id) > 0) {
                stype = new Specstype();
                stype = specstypeService.getByID(Integer.parseInt(Id));
                // 获取分类ID
                CategoryDTO dto = new CategoryDTO();
                dto = categoryService.queryById(stype.getClassid());
                if (dto != null) {
                    fullpath = dto.getFullpath();
                    if (fullpath.split(",").length > 0) {
                        switch (fullpath.split(",").length) {
                            case 1:
                                fid = fullpath.split(",")[0];
                                break;
                            case 2:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                break;
                            case 3:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                tid = fullpath.split(",")[2];
                                break;
                            default:
                                break;
                        }

                    } else {
                        fid = dto.getId().toString();
                    }
                }

            }
        } catch (Exception e) {
            stype = new Specstype();
            e.printStackTrace();
        }
        model.addAttribute("data", stype);
        model.addAttribute("fid", fid);
        model.addAttribute("sid", sid);
        model.addAttribute("tid", tid);
        return "platform/product/class_stypeedit";
    }

    /**
     * 商品规格列表（从商品分类页面进入）
     * 
     * @param classId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/class_stypelist")
    public String class_stypelist(@RequestParam("cid") String classId, ModelMap model) {
        String fname = "", sname = "", tname = "", fullpath = "";
        try {
            if (StringUtilsEX.ToInt(classId) > 0) {
                // 获取分类ID
                CategoryDTO dto = new CategoryDTO();
                dto = categoryService.queryById(StringUtilsEX.ToInt(classId));
                if (dto != null) {
                    fullpath = dto.getFullpath();
                    if (fullpath.split(",").length > 0) {
                        switch (fullpath.split(",").length) {
                            case 1:
                                fname = dto.getName();
                                break;
                            case 2:
                                sname = dto.getName();
                                dto = categoryService.queryById(dto.getFatherid());
                                fname = dto.getName();
                                break;
                            case 3:
                                tname = dto.getName();
                                dto = categoryService.queryById(dto.getFatherid());
                                sname = dto.getName();
                                dto = categoryService.queryById(dto.getFatherid());
                                fname = dto.getName();
                                break;
                            default:
                                break;
                        }
                    } else {
                        fname = dto.getName();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("classid", classId);
        model.addAttribute("Fname", fname);
        model.addAttribute("Sname", sname);
        model.addAttribute("Tname", tname);
        return "platform/product/class_stypelist";
    }

    /**
     * 商品规格列表
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/prospecs_list")
    public String prospecs_list() {

        return "platform/product/prospecs_list";

    }

    /**
     * 添加商品规格
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/prospecs_add")
    public String prospecs_add() {

        return "platform/product/prospecs_add";

    }

    /**
     * 编辑商品规格
     * 
     * @param Id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/prospecs_edit")
    public String prospecs_edit(@RequestParam("id") String Id, ModelMap model) {
        ProductSpecs specs = new ProductSpecs();
        String fid = "", sid = "", tid = "", fullpath = "";
        try {
            if (StringUtilsEX.ToInt(Id) > 0) {
                specs = new ProductSpecs();
                specs = productSpecsService.selectById(Integer.parseInt(Id));
                // 获取分类ID
                CategoryDTO dto = new CategoryDTO();
                dto = categoryService.queryById(specs.getClassid());
                if (dto != null) {
                    fullpath = dto.getFullpath();
                    if (fullpath.split(",").length > 0) {
                        switch (fullpath.split(",").length) {
                            case 1:
                                fid = fullpath.split(",")[0];
                                break;
                            case 2:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                break;
                            case 3:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                tid = fullpath.split(",")[2];
                                break;
                            default:
                                break;
                        }

                    } else {
                        fid = dto.getId().toString();
                    }
                }

            }
        } catch (Exception e) {

        }
        model.addAttribute("data", specs);
        model.addAttribute("fid", fid);
        model.addAttribute("sid", sid);
        model.addAttribute("tid", tid);

        return "platform/product/prospecs_edit";

    }

    /**
     * 商品规格列表(从商品分类页面进入)
     * 
     * @param classId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/class_specslist")
    public String class_specslist(@RequestParam("cid") String classId, ModelMap model) {

        String fname = "", sname = "", tname = "", fullpath = "";
        try {
            if (StringUtilsEX.ToInt(classId) > 0) {
                // 获取分类ID
                CategoryDTO dto = new CategoryDTO();
                dto = categoryService.queryById(StringUtilsEX.ToInt(classId));
                if (dto != null) {
                    fullpath = dto.getFullpath();
                    if (fullpath.split(",").length > 0) {
                        switch (fullpath.split(",").length) {
                            case 1:
                                fname = dto.getName();
                                break;
                            case 2:
                                sname = dto.getName();
                                dto = categoryService.queryById(dto.getFatherid());
                                fname = dto.getName();
                                break;
                            case 3:
                                tname = dto.getName();
                                dto = categoryService.queryById(dto.getFatherid());
                                sname = dto.getName();
                                dto = categoryService.queryById(dto.getFatherid());
                                fname = dto.getName();
                                break;
                            default:
                                break;
                        }
                    } else {
                        fname = dto.getName();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("classid", classId);
        model.addAttribute("Fname", fname);
        model.addAttribute("Sname", sname);
        model.addAttribute("Tname", tname);
        return "platform/product/class_specslist";

    }

    /**
     * 添加商品规格(从商品分类页面进入)
     * 
     * @param classId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/class_specsadd")
    public String class_specsadd(@RequestParam("cid") String classId, ModelMap model) {
        String fid = "", sid = "", tid = "", fullpath = "";
        try {
            if (StringUtilsEX.ToInt(classId) > 0) {
                // 获取分类ID
                CategoryDTO dto = new CategoryDTO();
                dto = categoryService.queryById(StringUtilsEX.ToInt(classId));
                if (dto != null) {
                    fullpath = dto.getFullpath();
                    if (fullpath.split(",").length > 0) {
                        switch (fullpath.split(",").length) {
                            case 1:
                                fid = fullpath.split(",")[0];
                                break;
                            case 2:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                break;
                            case 3:
                                fid = fullpath.split(",")[0];
                                sid = fullpath.split(",")[1];
                                tid = fullpath.split(",")[2];
                                break;
                            default:
                                break;
                        }
                    } else {
                        fid = dto.getId().toString();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("classid", classId);
        model.addAttribute("fid", fid);
        model.addAttribute("sid", sid);
        model.addAttribute("tid", tid);
        return "platform/product/class_specsadd";

    }

    /**
     * 编辑商品规格(从商品分类页面进入)
     * 
     * @param Id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/class_specsedit")
    public String class_specsedit(@RequestParam("id") String Id, ModelMap model) {
        String fid = "", sid = "", tid = "", fullpath = "";
        ProductSpecs specs = new ProductSpecs();
        try {
            if (StringUtilsEX.ToInt(Id) > 0) {
                specs = productSpecsService.selectById(StringUtilsEX.ToInt(Id));
                if (specs != null) {

                    // 获取分类ID
                    CategoryDTO dto = new CategoryDTO();
                    dto = categoryService.queryById(specs.getClassid());
                    if (dto != null) {
                        fullpath = dto.getFullpath();
                        if (fullpath.split(",").length > 0) {
                            switch (fullpath.split(",").length) {
                                case 1:
                                    fid = fullpath.split(",")[0];
                                    break;
                                case 2:
                                    fid = fullpath.split(",")[0];
                                    sid = fullpath.split(",")[1];
                                    break;
                                case 3:
                                    fid = fullpath.split(",")[0];
                                    sid = fullpath.split(",")[1];
                                    tid = fullpath.split(",")[2];
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            fid = dto.getId().toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("fid", fid);
        model.addAttribute("sid", sid);
        model.addAttribute("tid", tid);
        model.addAttribute("data", specs);
        return "platform/product/class_specsedit";
    }

    /**
     * 显示 商品品牌的页面
     * 
     * @return
     */
    @RequestMapping("/showBrand")
    public String showBrandList() {

        return "platform/product/goods_brand_list";
    }

    /**
     * 显示商品品牌编辑页面
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showBrandUpdate")
    public String showBrandUpdate(Integer id, Model model) {
        try {
            Brand brand = brandService.selectById(id);
            if (brand != null) {
                model.addAttribute("brand", brand);
            }
        } catch (Exception e) {

        }
        return "platform/product/goods_brand_update";
    }

    /**
     * 显示商品品牌添加页面
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showBrandAdd")
    public String showBrandAdd() {

        return "platform/product/goods_brand_add";
    }

    /**
     * 商品规格值列表
     * 
     * @return
     */
    @RequestMapping("/specs_values")
    public String specs_values(@RequestParam("specsid") String Id, ModelMap model) {

        if (StringUtilsEX.ToInt(Id) > 0) {
            model.addAttribute("specsid", Id);
        } else {
            model.addAttribute("specsid", -1);
        }
        return "platform/product/specs_values";
    }

    /**
     * 显示 商品分类
     * 
     * @return
     */
    @RequestMapping("/goods_class")
    public String goods_class() {

        return "platform/product/goods_class";
    }

    /**
     * 商品分类添加
     * 
     * @return
     */
    @RequestMapping("/goods_classadd")
    public String goods_classadd() {

        return "platform/product/goods_classadd";
    }

    /**
     * 商品分类编辑
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/goods_classedit")
    public String goods_classedit(@RequestParam("id") String classId, ModelMap model) {
        Category cate = new Category();
        String fid = "", sid = "";
        try {
            if (StringUtilsEX.ToInt(classId) > 0) {
                // 获取分类ID
                cate = categoryService.queryById(StringUtilsEX.ToInt(classId));
                if (cate != null) {
                    if (cate.getFullpath().split(",").length > 0) {
                        switch (cate.getFullpath().split(",").length) {
                            case 1:
                                break;
                            case 2:
                                fid = cate.getFullpath().split(",")[0];
                                break;
                            case 3:
                                fid = cate.getFullpath().split(",")[0];
                                sid = cate.getFullpath().split(",")[1];
                                break;
                            default:
                                break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("data", cate);
        model.addAttribute("fid", fid);
        model.addAttribute("sid", sid);
        return "platform/product/goods_classedit";
    }

    /**
     * 显示添加spu的页面
     * 
     * @return
     */
    @RequestMapping("/showAddDirect")
    public String showAddDirect(HttpServletRequest request) {
        return "platform/product/goods_spgl_proedit";
    }

    /**
     * 显示直营商品管理的页面
     * 
     * @return
     */
    @RequestMapping("/showDirect")
    public String showDirect(String fc,String sc,String tc,String name,String num,Model model) {
    	model.addAttribute("sc", sc);
    	model.addAttribute("fc", fc);
    	model.addAttribute("tc", tc);
    	model.addAttribute("name", name);
    	model.addAttribute("num", num);
        return "platform/product/goods_spgl_zyspgl_list";
    }

    /**
     * 显示更新spu的页面
     * 
     * @param id
     * @return
     */
    @RequestMapping("/showUpdateDirect")
    public String showUpdateDirect(@RequestParam("id") String spuId, HttpServletRequest request,String fc,String sc,String tc,String name,String num) {
        if (spuId != null) {
            try {
            	request.setAttribute("name", name);
            	request.setAttribute("num", num);
            	request.setAttribute("tc", tc);
            	request.setAttribute("sc", sc);
            	request.setAttribute("fc",fc);
                SpuWithBLOBs spu = spuService.queryById(StringUtilsEX.ToInt(spuId));
                Brand brand = new Brand();
                CategoryDTO category = categoryService.queryById(spu.getClassid());
                if (spu.getBrandid() != null) {
                    brand = brandService.selectById(spu.getBrandid());
                }
                if(brand==null){
                	brand= new Brand();
                }
//                SessionUser user=SessionState.GetCurrentUser();
//                System.out.println("=============="+user.getShopid());
//                List<Freight> list = freightService.selectByShop(user.getShopid());
                request.setAttribute("spu", spu);
                request.setAttribute("category", category);
                request.setAttribute("brand", brand);
//                request.setAttribute("list", list);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return "platform/product/goods_spgl_zysp_update";
    }

    /**
     * 添加直营库存商品
     * 
     * @return
     */
    @RequestMapping("/goods_zySkuAdd")
    public String goods_zySkuAdd(@RequestParam("spuid") String spuId, ModelMap model) {
        String sid = "", cid = "";
        try {
            if (StringUtilsEX.ToInt(spuId) > 0) {
                SpuWithBLOBs spu = spuService.queryById(StringUtilsEX.ToInt(spuId));
                cid = spu.getClassid().toString();
                sid = spu.getId().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("spuid", sid);
        model.addAttribute("classid", cid);
        return "platform/product/goods_zySkuAdd";
    }

    /**
     * 直营库存商品列表
     * 
     * @return
     */
    @RequestMapping("/goods_zySkuList")
    public String goods_zySkuList(@RequestParam("spuid") String spuId, ModelMap model) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        List<Sku> skulist = new ArrayList<Sku>();
        try {
            if (StringUtilsEX.ToInt(spuId) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuId));
                skulist = skuService.getListBySpuID(spu.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("spu", spu);
        model.addAttribute("skulist", skulist);
        return "platform/product/goods_zySkuList";
    }

    /**
     * 编辑直营库存商品
     * 
     * @return
     */
    @RequestMapping("/goods_zySkuEdit")
    public String goods_zySkuEdit(@RequestParam("id") String skuId, ModelMap model) {
        Sku sku = new Sku();
        SpuWithBLOBs spu = new SpuWithBLOBs();
        try {
            if (StringUtilsEX.ToInt(skuId) > 0) {
                sku = skuService.selectByID(StringUtilsEX.ToInt(skuId));
                spu = spuService.queryById(sku.getSpuId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("spu", spu);
        model.addAttribute("data", sku);
        return "platform/product/goods_zySkuEdit";
    }

    /**
     * 显示商品属性列表页面
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/showSearchAttr")
    public String showSearchAttr(Integer id, Model model,String fc,String sc,String tc) {

        try {
            Searchattr searchattr = searchattrService.selectSearchAttrById(id);
            if (searchattr != null) {
                model.addAttribute("serachattr", searchattr);
            }
        } catch (Exception e) {

        }
        model.addAttribute("fc", fc);
        model.addAttribute("sc", sc);
        model.addAttribute("tc", tc);
        return "platform/product/goods_spgl_search_list";
    }

    @RequestMapping("/showSearValue")
    public String showSearValue(Integer attrid, Model model) {
    	Integer specsid =null;
        try {
            Searchattr searchattr = searchattrService.selectSearchAttrById(attrid);
            if(searchattr!=null){
            	if(searchattr.getAttrtype()==SearchAttrType_Enum.商品属性.getValue()){
            		ProductSpecs productSpecs = productSpecsService.queryIdBySearchAttr(searchattr.getClassid(),searchattr.getName());
                    specsid = productSpecs.getId();
            	}
            }
            
            model.addAttribute("specsid", specsid);
            model.addAttribute("searchattr", searchattr);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return "platform/product/goods_spgl_searchvalue_list";
    }

    /**
     * 库存商品调价
     * 
     * @param skuId
     * @param model
     * @return
     */
    @RequestMapping("/goods_setPrice")
    public String goods_setPrice(@RequestParam("skuid") String skuId, ModelMap model) {

        Sku sku = new Sku();
        try {
            if (StringUtilsEX.ToInt(skuId) > 0) {
                sku = skuService.selectByID(StringUtilsEX.ToInt(skuId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("sku", sku);
        return "platform/product/goods_setPrice";
    }

    /**
     * 显示spu图片列表的页面
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showSpuImg")
    public String showSpuImg(String spuid, Model model) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        List<ProductImgs> Imgs = new ArrayList<ProductImgs>();
        try {
            if (StringUtilsEX.ToInt(spuid) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
                //获取图片列表
                Imgs = productImgsService.selectBySpu(spu.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("spu", spu);
        model.addAttribute("imglist", Imgs);
//        model.addAttribute("imgsrc", getImgurl());
        return "platform/product/goods_spgl_spuimgs_list";
    }

    /**
     * 显示图片的编辑页面
     * 
     * @param sid
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showSpuImgEditor")
    public String showSpuImgEditor(String spuid, String id, Model model) {
        ProductImgs Imgs = new ProductImgs();
        String actionString = "addSpuImage";
        try {
            if (StringUtilsEX.ToInt(id) > 0) {
                //获取图片
                Imgs = productImgsService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
                actionString = "editSpuImage";
            } else {
                if (StringUtilsEX.ToInt(spuid) > 0) {
                    Imgs.setSpuId(StringUtilsEX.ToInt(spuid));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("imgaction", actionString);
        model.addAttribute("img", Imgs);
        return "platform/product/goods_spgl_spuimg_edit";
    }

    @RequestMapping("/goods_skuImgList")
    public String goods_skuImgList(String skuid, Model model) {
        Sku sku = new Sku();
        List<ProductImgs> Imgs = new ArrayList<ProductImgs>();
        try {
            if (StringUtilsEX.ToInt(skuid) > 0) {
                sku = skuService.selectByID(StringUtilsEX.ToInt(skuid));
                //获取图片列表
                Imgs = productImgsService.selectBySku(sku.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("sku", sku);
        model.addAttribute("imglist", Imgs);
        return "platform/product/goods_skuImgList";
    }

    @RequestMapping("/goods_skuImgAdd")
    public String goods_skuImgAdd(String skuid, String id, Model model) {
        ProductImgs Imgs = new ProductImgs();
        String actionString = "addSkuImage";
        try {
            if (StringUtilsEX.ToInt(id) > 0) {
                //获取图片
                Imgs = productImgsService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
                actionString = "editSkuImage";
            } else {
                if (StringUtilsEX.ToInt(skuid) > 0) {
                    Imgs.setSkuId(StringUtilsEX.ToInt(skuid));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("imgaction", actionString);
        model.addAttribute("img", Imgs);
        return "platform/product/goods_skuImgAdd";
    }

//    //获取文件服务器路径
//    private String getImgurl() {
//        ReadFileUrlUtil ef = new ReadFileUrlUtil();
//        return ef.fileUrl();
//    }

    /**
     * 直营商品上下架列表
     * @return
     */
    @RequestMapping("/goods_zySpusxj")
    public String goods_zySpusxj() {

        return "platform/product/goods_zySpusxj";
    }

    /**
     * 店铺商品列表
     * @return
     */
    @RequestMapping("/goods_dpspgl")
    public String goods_dpspgl(String isoffer,HttpServletRequest request) {
    	request.setAttribute("isoffer", isoffer);
        return "platform/product/goods_dpspgl";
    }
    
    /**
     * 添加店铺商品
     * @return
     */
 /*   @RequestMapping("/goods_dpaddspu")
    public String goods_dpaddspu(HttpServletRequest request) {
    	List<Shop> shops=new ArrayList<Shop>();
    	try {
    		shops=shopService.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.setAttribute("shoplist", shops);
    	Date nowdate = new Date();
    	request.setAttribute("year", DateUtil.getYear(nowdate));
    	request.setAttribute("month", DateUtil.getMonth(nowdate));
        return "platform/product/goods_addSpu";
    }*/
    
    
    
    @RequestMapping("/goods_dpaddspu")
    public String goods_dpaddspu(HttpServletRequest request) {
    	List<Shop> shops=new ArrayList<Shop>();
    	try {
    		shops=shopService.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.setAttribute("shoplist", shops);
    	Date nowdate = new Date();
    	request.setAttribute("year", DateUtil.getYear(nowdate));
    	request.setAttribute("month", DateUtil.getMonth(nowdate));
    	
    	List<Integer>  yearlist=new ArrayList<Integer>();
    	Integer  year=DateUtil.getYear(nowdate);
    	for (int i = 0; i < 10; i++) {
			yearlist.add(year+i);
		}
    	request.setAttribute("yearlist", yearlist);
        return "platform/product/goods_addSpunew";
    }
    
   /* *//**
     * 编辑店铺商品
     * @param request
     * @return
     *//*
    @RequestMapping("/goods_editspu")
    public String goods_editspu(String spuid,HttpServletRequest request) {
    	SpuWithBLOBs spu=new SpuWithBLOBs();
    	Sku sku=new Sku();
    	try {
    		if(StringUtilsEX.ToInt(spuid)>0){
    			spu=spuService.queryById(StringUtilsEX.ToInt(spuid));
                if(spu!=null){
                	Shop shop=shopService.queryById(spu.getShopid());
                	request.setAttribute("shopname", shop.getName());
                	request.setAttribute("stype", shop.getShoptype());
                	
                	List<Sku> skus=skuService.getListBySpuID(spu.getId());
        			if(skus!=null && skus.size()>1){
        				Optional<Sku> opday=skus.stream().filter(x->x.getIstoday()==1).findFirst();
        				if(opday.isPresent()){
        					request.setAttribute("daysku", opday.get());
        				}
        				Optional<Sku> opsku=skus.stream().filter(x->x.getIstoday()==0).findFirst();
        				if(opsku.isPresent()){
        					sku=opsku.get();
        				}
        			}else{
        				sku=skus.get(0);
        				if(shop.getShoptype().equals(ShopTypeEnum.马戏团票.getValue())){
        					Date nowdate = new Date();
        					SkuShowtime showtime=spuService.getBySkuidAndMonth(sku.getId(), DateUtil.getYear(nowdate), DateUtil.getMonth(nowdate));
        					if(showtime!=null){
        						request.setAttribute("days",showtime.getShowdays());
        					}else{
        						request.setAttribute("days", "");
        					}
        					
        					request.setAttribute("year", DateUtil.getYear(nowdate));
        			    	request.setAttribute("month", DateUtil.getMonth(nowdate));
        				}
        				request.setAttribute("showsku", spuService.getBySkuidLast(sku.getId()));
        			}
    		    }
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.setAttribute("sku", sku);
    	request.setAttribute("spu", spu);
        return "platform/product/goods_editSpu";
    }*/
    
    
    @RequestMapping("/goods_editspu")
    public String goods_editspu(String spuid,HttpServletRequest request) {
    	SpuWithBLOBs spu=new SpuWithBLOBs();
    	Sku sku=new Sku();
    	List<SkuShowtime> showtimelist=new ArrayList<SkuShowtime>();
    	try {
    		if(StringUtilsEX.ToInt(spuid)>0){
    			spu=spuService.queryById(StringUtilsEX.ToInt(spuid));
                if(spu!=null){
                	Shop shop=shopService.queryById(spu.getShopid());
                	request.setAttribute("shopname", shop.getName());
                	request.setAttribute("stype", shop.getShoptype());
                	
                	sku=skuService.getBySpuID(spu.getId());
                	
                	Date nowdate = new Date();
                	request.setAttribute("year", DateUtil.getYear(nowdate));
			    	request.setAttribute("month", DateUtil.getMonth(nowdate));
			    	
                	showtimelist=spuService.getListBySkuidAndMonth(sku.getId(),spu.getId(),
                			DateUtil.getYear(nowdate),DateUtil.getMonth(nowdate));
                	
                	List<Integer>  yearlist=new ArrayList<Integer>();
                	Integer  year=DateUtil.getYear(nowdate);
                	for (int i = 0; i < 10; i++) {
            			yearlist.add(year+i);
            		}
                	request.setAttribute("yearlist", yearlist);
    		    }
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.setAttribute("sku", sku);
    	request.setAttribute("spu", spu);
    	request.setAttribute("showtimelist", showtimelist);
        return "platform/product/goods_editSpunew";
    }
    /**
     * 店铺商品上下架列表
     * @return
     */
    @RequestMapping("/goods_dpspsxj")
    public String goods_dpspsxj() {
        return "platform/product/goods_dpspsxj";
    }

    /**
     * 店铺商品审核列表
     * @return
     */
    @RequestMapping("/goods_dpspsh")
    public String goods_dpspsh() {
        return "platform/product/goods_dpspsh";
    }

    /**
     * 店铺商品图片列表
     * @return
     */
    @RequestMapping("/goods_dpspimg_list")
    public String goods_dpspimg_list(String spuid, Model model) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        List<ProductImgs> Imgs = new ArrayList<ProductImgs>();
        try {
            if (StringUtilsEX.ToInt(spuid) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
                //获取图片列表
                Imgs = productImgsService.selectBySpu(spu.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("spu", spu);
        model.addAttribute("imglist", Imgs);
        return "platform/product/goods_dpspimg_list";
    }

    /**
     * 店铺商品图片列表
     * @return
     */
    @RequestMapping("/goods_dpSkulist")
    public String goods_dpSkulist(String spuid, Model model) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        List<Sku> skulist = new ArrayList<Sku>();
        try {
            if (StringUtilsEX.ToInt(spuid) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
                skulist = skuService.getListBySpuID(spu.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("spu", spu);
        model.addAttribute("skulist", skulist);
        return "platform/product/goods_dpSkulist";
    }

    /**
     * 店铺库存商品图片列表
     * @return
     */
    @RequestMapping("/goods_dpSkuImg_list")
    public String goods_dpSkuImg_list(String skuid, Model model) {
        Sku sku = new Sku();
        List<ProductImgs> Imgs = new ArrayList<ProductImgs>();
        try {
            if (StringUtilsEX.ToInt(skuid) > 0) {
                sku = skuService.selectByID(StringUtilsEX.ToInt(skuid));
                //获取图片列表
                Imgs = productImgsService.selectBySku(sku.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("sku", sku);
        model.addAttribute("imglist", Imgs);
        return "platform/product/goods_dpSkuImg_list";
    }

    /**
     * 店铺库存商品规格列表
     * @return
     */
    @RequestMapping("/goods_dpsku_specsvalueslist")
    public String goods_dpsku_specsvalueslist(String skuid, Model model) {
        List<SkuValuesDto> svlist = new ArrayList<SkuValuesDto>();
        Sku sku = new Sku();
        try {
            if (StringUtilsEX.ToInt(skuid) > 0) {
                sku = skuService.selectByID(StringUtilsEX.ToInt(skuid));
                svlist = skuService.getValueBySkuID(StringUtilsEX.ToInt(skuid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("sku", sku);
        model.addAttribute("svlist", svlist);
        return "platform/product/goods_dpsku_specsvalueslist";
    }

    /**
     * 自定义分类审核列表
     * 
     * @return
     */
    @RequestMapping("/goods_customlist")
    public String goods_customlist() {
        return "platform/product/goods_customlist";
    }
    
    /**
     * 直营店铺预警库存列表
     * 
     * @return
     */
    @RequestMapping("/goods_zyWarnSkuList")
    public String goods_zyWarnSkuList() {
        return "platform/product/goods_zyWarnSkuList";
    }
    
    /**
     * 店铺预警库存列表
     * 
     * @return
     */
    @RequestMapping("/goods_dpWarnSkulist")
    public String goods_dpWarnSkulist() {
        return "platform/product/goods_dpWarnSkulist";
    }
    
    
    @RequestMapping("/showSpuStock")
    public String showSpuStock(String spuid, HttpServletRequest request) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        Date nowdate = new Date();
        List<Integer>  yearlist=new ArrayList<Integer>();
        try {
            if (StringUtilsEX.ToInt(spuid) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
            }
        	for (int i =2017 ; i < 2027; i++) {
    			yearlist.add(i);
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("yearlist", yearlist);
        request.setAttribute("year", DateUtil.getYear(nowdate));
    	request.setAttribute("month", DateUtil.getMonth(nowdate));
        request.setAttribute("spu", spu);
        return "platform/product/goods_stocklist";
    }
    
    
    
    @RequestMapping("/addSpuStock")
    public String addSpuStock(String spuid, HttpServletRequest request) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        Sku  sku=new Sku();
        Date nowdate = new Date();
        List<Integer>  yearlist=new ArrayList<Integer>();
        try {
            if (StringUtilsEX.ToInt(spuid) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
            	sku=skuService.getBySpuID(spu.getId());
            }
        	Integer  year=DateUtil.getYear(nowdate);
        	for (int i = 0; i < 10; i++) {
    			yearlist.add(year+i);
    		}

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("year", DateUtil.getYear(nowdate));
    	request.setAttribute("month", DateUtil.getMonth(nowdate));
    	request.setAttribute("yearlist", yearlist);
        request.setAttribute("spu", spu);
        request.setAttribute("sku", sku);
        return "platform/product/goods_stockadd";
    }
    
    @RequestMapping("/addSpuStockMonth")
    public String addSpuStockMonth(String spuid, HttpServletRequest request) {
        SpuWithBLOBs spu = new SpuWithBLOBs();
        Sku  sku=new Sku();
        Date nowdate = new Date();
        List<Integer>  yearlist=new ArrayList<Integer>();
        try {
            if (StringUtilsEX.ToInt(spuid) > 0) {
                spu = spuService.queryById(StringUtilsEX.ToInt(spuid));
            	sku=skuService.getBySpuID(spu.getId());
            }
        	Integer  year=DateUtil.getYear(nowdate);
        	for (int i = 0; i < 10; i++) {
    			yearlist.add(year+i);
    		}

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("year", DateUtil.getYear(nowdate));
    	request.setAttribute("month", DateUtil.getMonth(nowdate));
    	request.setAttribute("yearlist", yearlist);
        request.setAttribute("spu", spu);
        request.setAttribute("sku", sku);
        return "platform/product/goods_stockaddBymonth";
    }
    
    
}
