package cn.itsource.anju.service;


import cn.itsource.anju.domain.Brand;
import cn.itsource.anju.query.BrandQuery;
import cn.itsource.anju.util.PageList;
import cn.itsource.anju.vo.BrandVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author yza
 * @since 2019-10-14
 */
public interface IBrandService extends IService<Brand> {

    /**
     * 分页高级查询
     * @param query
     * @return
     */
    PageList<Brand> queryPage(BrandQuery query);
   BrandVo getByProductTypeId(Long productTypeId);
}
