package cn.itsource.anju.vo;


import cn.itsource.anju.domain.ProductType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yza
 * @version 1.0
 * @description TODO
 * @date 2019/10/23 10:21
 */
@Data
public class ProductTypeCrumbVo {

    /**
     * 当前类型
     */
    private ProductType currentType;
    /**
     * 同级别的其他类型
     */
    private List<ProductType> otherTypes = new ArrayList<>();

}
