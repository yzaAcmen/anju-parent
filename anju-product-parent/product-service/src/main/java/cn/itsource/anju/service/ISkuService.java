package cn.itsource.anju.service;

import cn.itsource.anju.domain.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * SKU 服务类
 * </p>
 *
 * @author yza
 * @since 2019-10-17
 */
public interface ISkuService extends IService<Sku> {
    Map<String, Object> skuChange(Long productId, String indexs);
}
