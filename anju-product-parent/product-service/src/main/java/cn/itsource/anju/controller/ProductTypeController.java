package cn.itsource.anju.controller;


import cn.itsource.anju.domain.ProductType;
import cn.itsource.anju.query.ProductTypeQuery;
import cn.itsource.anju.service.IProductTypeService;
import cn.itsource.anju.util.AjaxResult;
import cn.itsource.anju.util.PageList;
import cn.itsource.anju.vo.ProductTypeCrumbVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {
    @Autowired
    public IProductTypeService productTypeService;

    /**
    * 保存和修改公用的
    * @param productType  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody ProductType productType){
        try {
            if(productType.getId()!=null){
                productTypeService.updateById(productType);
            }else{
                productTypeService.save(productType);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            productTypeService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ProductType get(@PathVariable("id") Long id)
    {
        return productTypeService.getById(id);
    }


    /**
    * 查看所有
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<ProductType> list(){

        return productTypeService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<ProductType> json(@RequestBody ProductTypeQuery query)
    {
        Page<ProductType> page = new Page<ProductType>(query.getPage(),query.getRows());
        IPage<ProductType> ipage = productTypeService.page(page);
        return new PageList<ProductType>(ipage.getTotal(),ipage.getRecords());
    }

    /**
     * 加载类型树
     * @return
     */
    @RequestMapping(value = "/loadTypeTree",method = RequestMethod.GET)
    public List<ProductType> loadTypeTree(){
        return productTypeService.loadTypeTree();
    }
    /**
     * 静态化首页
     * @return
     */
    @PostMapping("/genHomePage")
    public AjaxResult genHomePage(){
        try {
            productTypeService.genHomePage();
            return AjaxResult.me().setSuccess(true).setMessage("成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("失败!"+e.getMessage());
        }
    }
    /**
     * 加载类型面包屑
     * @param productTypeId
     * @return
     */
    @GetMapping("/crumb")
    public List<ProductTypeCrumbVo> loadTypeCrumb(@RequestParam("productTypeId") Long productTypeId){
        return productTypeService.loadTypeCrumb(productTypeId);
    }
}
