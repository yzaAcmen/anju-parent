package cn.itsource.anju.service.impl;

import cn.itsource.anju.domain.ProductComment;
import cn.itsource.anju.mapper.ProductCommentMapper;
import cn.itsource.anju.service.IProductCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价 服务实现类
 * </p>
 *
 * @author yza
 * @since 2019-10-17
 */
@Service
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment> implements IProductCommentService {

}
