package cn.itsource.anju.service;

import cn.itsource.anju.domain.Product;
import cn.itsource.anju.domain.Specification;
import cn.itsource.anju.query.ProductQuery;
import cn.itsource.anju.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author yza
 * @since 2019-10-17
 */
public interface IProductService extends IService<Product> {
    PageList<Product> queryPage(ProductQuery query);

    List<Specification> getViewProperties(Long productId);

    void saveViewProperties(Long productId, List<Specification> viewProperties);

    List<Specification> getSkuProperties(Long productId);

    /**
     * 保存sku
     */
    void saveSkuProperties(Long productId, List<Specification> skuProperties, List<Map<String, String>> skus);

    void offSale(List<Long> idList);

    void onSale(List<Long> idList);
}
