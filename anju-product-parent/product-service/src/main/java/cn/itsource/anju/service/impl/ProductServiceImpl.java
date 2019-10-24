package cn.itsource.anju.service.impl;

import cn.itsource.anju.client.ProductESClient;
import cn.itsource.anju.client.StaticPageClient;
import cn.itsource.anju.domain.*;
import cn.itsource.anju.mapper.*;
import cn.itsource.anju.query.ProductQuery;
import cn.itsource.anju.service.IProductService;
import cn.itsource.anju.service.IProductTypeService;
import cn.itsource.anju.util.PageList;
import cn.itsource.anju.vo.ProductTypeCrumbVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author yza
 * @since 2019-10-17
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductExtMapper productExtMapper;

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    ProductESClient client;

    @Autowired
    private IProductTypeService typeService;

    @Autowired
    private StaticPageClient pageClient;

    /**
     * 在线商城搜索商品
     * @param param
     * @return
     */
    @Override
    public PageList<Product> queryOnSale(ProductParam param) {
        //查询es中的数据
        PageList<ProductDoc> pageList = client.search(param);

        //封装成PageList
        List<Product> list = new ArrayList<>();
        Product p = null;
        for (ProductDoc doc : pageList.getRows()) {
            p = new Product();
            p.setMedias(doc.getMedias());
            p.setId(doc.getId());
            p.setName(doc.getName());
            p.setSubName(doc.getSubName());
            p.setSaleCount(doc.getSaleCount());
            p.setMaxPrice(doc.getMaxPrice());
            p.setMinPrice(doc.getMinPrice());
            list.add(p);
        }
        return new PageList<>(pageList.getTotal(),list);
    }


    /**
     * 批量下架
     * @param idList
     */
    @Override
    public void offSale(List<Long> idList) {
        //修改数据库状态
        baseMapper.offSale(idList,System.currentTimeMillis());
        //删除es中的数据
        client.deleteBatch(idList);
    }

    /**
     * 批量上架
     * @param idList
     */
    @Override
    public void onSale(List<Long> idList) {
        //修改数据库的商品的state
        baseMapper.onSale(idList,System.currentTimeMillis());
        //查询商品信息
        List<Product> products = baseMapper.selectBatchIds(idList);
        //保存到es中
        List<ProductDoc> productDocList = products2Docs(products);
        //页面静态化商品详情页
        staticDetailPage(products);
        client.saveBatch(productDocList);
    }
    /**
     * 批量商品详情页静态化
     * @param products
     */
    private void staticDetailPage(List<Product> products) {
        for (Product product : products) {
            String templatePath = "D:\\SoftwareInstallation\\Java\\anju-parent\\anju-product-parent\\product-service\\src\\main\\resources\\template\\product.detail.vm";
            String targetPath = "D:\\SoftwareInstallation\\Java\\auju-web-parent\\common-service\\detail\\"+product.getId()+".html";
            //数据
            Map<String,Object> model = new HashMap<>();
            //面包屑数据
            List<ProductTypeCrumbVo> crumbs = typeService.loadTypeCrumb(product.getProductTypeId());
            model.put("crumbs",crumbs);
            model.put("product",product);
            //sku
            String skuProperties = product.getSkuProperties();
            List<Specification> skus = JSONArray.parseArray(skuProperties,Specification.class);
            model.put("skus",skus);
            //viewProperties
            String viewProperties = product.getViewProperties();
            List<Specification> views = JSONArray.parseArray(viewProperties, Specification.class);
            model.put("views",views);
            //商品详情
            ProductExt productExt = productExtMapper.selectOne(new QueryWrapper<ProductExt>().eq("productId", product.getId()));
            String richContent = productExt.getRichContent();
            model.put("richContent",richContent);
            //商品的媒体属性
            String mediasStr = product.getMedias();//aaa,bbb,ccc
            //[[aaa,aaa,aaa],[bbb,bbb,bbb],[ccc,ccc,ccc]]
            String[] mediasArr = mediasStr.split(",");//[aaa,bbb,ccc]
            List<List<String>> medias = new ArrayList<>();
            for (String media : mediasArr) {
                List<String> oneMedia = new ArrayList<>();
                oneMedia.add("http://172.16.4.46"+media);
                oneMedia.add("http://172.16.4.46"+media);
                oneMedia.add("http://172.16.4.46"+media);
                medias.add(oneMedia);
            }
            String images = JSON.toJSONString(medias);
            model.put("medias",images);

            //skuJSONStr
            model.put("skuJSON",product.getSkuProperties());

            pageClient.generateStaticPage(templatePath,targetPath,model);

        }
    }
    /**
     * 集合的转换
     * @param products
     * @return
     */
    private List<ProductDoc> products2Docs(List<Product> products) {
        List<ProductDoc> productDocList = new ArrayList<>();
        ProductDoc doc = null;
        for (Product product : products) {
            doc = product2Doc(product);
            productDocList.add(doc);
        }
        return productDocList;
    }

    /**
     * 对象的转换
     * @param product
     * @return
     */
    private ProductDoc product2Doc(Product product) {
        ProductDoc doc = new ProductDoc();
        ProductType productType = productTypeMapper.selectById(product.getProductTypeId());
        Brand brand = brandMapper.selectById(product.getBrandId());


        doc.setId(product.getId());
        //all 标题 副标题 类型名称 品牌名称
        StringBuilder all = new StringBuilder();
        all.append(product.getName())
                .append(" ")
                .append(product.getSubName())
                .append(" ")
                .append(productType.getName())
                .append(" ")
                .append(brand.getName());
        doc.setAll(all.toString());
        doc.setProductTypeId(product.getProductTypeId());
        doc.setBrandId(product.getBrandId());

        //最高价格  最低价格
        List<Sku> skus = skuMapper.selectList(new QueryWrapper<Sku>().eq("product_id", product.getId()));
        Integer maxPrice = 0;
        Integer minPrice = 0;
        //默认第一个价格为最低价格
        if(skus!=null&&skus.size()>0){
            minPrice = skus.get(0).getPrice();
        }
        for (Sku sku : skus) {
            if(sku.getPrice()>maxPrice){
                maxPrice = sku.getPrice();
            }
            if(sku.getPrice()<minPrice){
                minPrice = sku.getPrice();
            }
        }
        doc.setMaxPrice(maxPrice);
        doc.setMinPrice(minPrice);

        doc.setSaleCount(product.getSaleCount());
        doc.setOnSaleTime(product.getOnSaleTime());
        doc.setCommentCount(product.getCommentCount());
        doc.setViewCount(product.getViewCount());
        doc.setName(product.getName());
        doc.setSubName(product.getSubName());
        doc.setMedias(product.getMedias());
        doc.setViewProperties(product.getViewProperties());
        doc.setSkuProperties(product.getSkuProperties());


        return doc;
    }

    @Override
    @Transactional
    public boolean save(Product product) {
        //创建时间
        product.setCreateTime(System.currentTimeMillis());
        //t_product
        baseMapper.insert(product);//mybatis-plus自动返回生成的主键，主键生成到了product对象中
        //t_product_ext
        ProductExt ext = product.getExt();
        ext.setProductId(product.getId());
        productExtMapper.insert(ext);
        return true;
    }

    /**
     * 根据商品ID查询商品的显示属性
     * @param productId
     * @return
     */
    @Override
    public List<Specification> getViewProperties(Long productId) {

        List<Specification> specifications = null;

        //查询商品表中的viewProperties
        Product product = baseMapper.selectById(productId);
        String viewProperties = product.getViewProperties();
        //判断是否为null
        if(StringUtils.isEmpty(viewProperties)){
            //根据商品类型查询属性表
            Long productTypeId = product.getProductTypeId();
            specifications = specificationMapper.selectList(new QueryWrapper<Specification>()
                    .eq("product_type_id", productTypeId).eq("isSku", 0));
        }else{
            //转成List<Specification>
            specifications = JSONArray.parseArray(viewProperties, Specification.class);
        }
        return specifications;
    }


    /**
     * 保存sku属性
     * @param productId
     * @param skuProperties
     * @param skus
     */
    @Override
    @Transactional
    public void saveSkuProperties(Long productId, List<Specification> skuProperties, List<Map<String, String>> skus) {
        //修改t_product表中skuProperties
        String skuPropertiesJSON = JSON.toJSONString(skuProperties);
        System.out.println("skuProperties"+skuPropertiesJSON);
        baseMapper.updateSkuProperties(productId,skuPropertiesJSON);
        //维护t_sku表

        //先删除之前的
        skuMapper.delete(new QueryWrapper<Sku>().eq("product_id",productId));
        //再添加新的
        Sku sku = null;
        for (Map<String, String> skuMap : skus) {//{"年龄":"xx","肤色":"xxx","price":0,"store":0,"indexs":"xxx_0_1"}
            sku = new Sku();
            //从参数中获取数据封装到sku对象中
            sku.setCreateTime(System.currentTimeMillis());
            sku.setProductId(productId);
            //skuName
            StringBuilder sb = new StringBuilder();
            //map的遍历
            for (Map.Entry<String, String> skuEntry : skuMap.entrySet()) {
                if(!"price".equals(skuEntry.getKey())&&!"store".equals(skuEntry.getKey())&&!"indexs".equals(skuEntry.getKey())){
                    sb.append(skuEntry.getValue());
                }
            }
            sku.setSkuName(sb.toString());
            sku.setPrice(Integer.parseInt(skuMap.get("price")));
            sku.setAvailableStock(Integer.parseInt(skuMap.get("store")));

            sku.setIndexs(skuMap.get("indexs"));

            skuMapper.insert(sku);
        }

    }

    @Override
    public List<Specification> getSkuProperties(Long productId) {
        List<Specification> specifications = null;
        //查询商品表中的skuProperties
        Product product = baseMapper.selectById(productId);
        String skuProperties = product.getSkuProperties();
        //判断是否为null
        if(StringUtils.isEmpty(skuProperties)){
            //根据商品类型查询属性表
            Long productTypeId = product.getProductTypeId();
            specifications = specificationMapper.selectList(new QueryWrapper<Specification>()
                    .eq("product_type_id", productTypeId).eq("isSku", 1));
        }else{
            //转成List<Specification>
            specifications = JSONArray.parseArray(skuProperties, Specification.class);
        }
        return specifications;
    }

    /**
     * 保存显示属性
     * @param productId 商品编号
     * @param specifications 显示属性
     * @return
     */
    @Override
    public void saveViewProperties(Long productId, List<Specification> specifications) {
        String viewProperties = JSON.toJSONString(specifications);
        baseMapper.updateViewProperties(productId,viewProperties);
    }

    @Override
    public PageList<Product> queryPage(ProductQuery query) {
        IPage iPage = baseMapper.queryPage(new Page(query.getPage(), query.getRows()), query);
        return new PageList<>(iPage.getTotal(),iPage.getRecords());
    }

}
