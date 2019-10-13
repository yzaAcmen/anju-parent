package cn.itsource.anju.service.impl;

import cn.itsource.anju.domain.Brand;
import cn.itsource.anju.mapper.BrandMapper;
import cn.itsource.anju.query.BrandQuery;
import cn.itsource.anju.service.IBrandService;
import cn.itsource.anju.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author yza
 * @since 2019-10-14
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
    /**
     * 分页高级查询
     * @param query
     * @return
     */
    @Override
    public PageList<Brand> queryPage(BrandQuery query) {
        IPage<Brand> brandIPage =
                baseMapper.queryPage(new Page(query.getPage(), query.getRows()), query);
        PageList<Brand> pageList = new PageList<>(brandIPage.getTotal(),brandIPage.getRecords());
        return pageList;
    }
}
