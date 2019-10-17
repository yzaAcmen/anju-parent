package cn.itsource.anju.service;

import cn.itsource.anju.domain.Product;
import cn.itsource.anju.query.ProductQuery;
import cn.itsource.anju.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

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
}
