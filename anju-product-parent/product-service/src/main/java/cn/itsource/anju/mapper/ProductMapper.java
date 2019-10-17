package cn.itsource.anju.mapper;

import cn.itsource.anju.domain.Product;
import cn.itsource.anju.query.ProductQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author yza
 * @since 2019-10-17
 */
public interface ProductMapper extends BaseMapper<Product> {

    IPage queryPage(Page page, @Param("query") ProductQuery query);
}
