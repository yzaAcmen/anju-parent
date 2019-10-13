package cn.itsource.anju.mapper;

import cn.itsource.anju.domain.Brand;
import cn.itsource.anju.query.BrandQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author yza
 * @since 2019-10-14
 */
@Component
public interface BrandMapper extends BaseMapper<Brand> {

    //mapper高级查询+分页
    //List<Brand> queryPage(BrandQuery query);

    /**
     * mybatis-plus对自定义sql的分页查询
     * （1）返回类型为IPage
     * （2）第一个参数必须为Page
     *  (3)mybatis有多个参数
     *      @Param("query") BrandQuery query
     *      在sql中就可以使用#{query.keyword}获取到查询参数
     * @param page
     * @param query
     * @return
     */
    IPage<Brand> queryPage(Page page, @Param("query") BrandQuery query);
}
