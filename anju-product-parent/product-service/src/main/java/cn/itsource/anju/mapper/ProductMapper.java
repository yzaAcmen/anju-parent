package cn.itsource.anju.mapper;

import cn.itsource.anju.domain.Product;
import cn.itsource.anju.query.ProductQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author yza
 * @since 2019-10-17
 */
@Component
public interface ProductMapper extends BaseMapper<Product> {

    IPage queryPage(Page page, @Param("query") ProductQuery query);

    void updateViewProperties(@Param("productId") Long productId,
                              @Param("viewProperties") String viewProperties);

    void updateSkuProperties(@Param("productId") Long productId,@Param("skuProperties") String skuProperties);

    void onSale(@Param("idList") List<Long> idList,@Param("currentTimeMillis") long currentTimeMillis);

    void offSale(@Param("idList")List<Long> idList,@Param("currentTimeMillis") long currentTimeMillis);
}
