package cn.itsource.anju.service;

import cn.itsource.anju.domain.ProductType;
import cn.itsource.anju.vo.ProductTypeCrumbVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author yza
 * @since 2019-10-14
 */
public interface IProductTypeService extends IService<ProductType> {
    /**
     * 加载类型树
     * @return
     */
    List<ProductType> loadTypeTree();

   public void genHomePage();

    List<ProductTypeCrumbVo> loadTypeCrumb(Long productTypeId);
}
