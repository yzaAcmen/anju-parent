package cn.itsource.anju.service.impl;

import cn.itsource.anju.client.StaticPageClient;
import cn.itsource.anju.domain.Product;
import cn.itsource.anju.domain.ProductType;
import cn.itsource.anju.mapper.ProductMapper;
import cn.itsource.anju.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author yza
 * @since 2019-10-14
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
}
