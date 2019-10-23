package cn.itsource.anju.vo;


import cn.itsource.anju.domain.Brand;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yza
 * @version 1.0
 * @description TODO
 * @date 2019/10/23 11:26
 */
@Data
public class BrandVo {

    private Set<String> letters = new TreeSet<>();

    private List<Brand> brands = new ArrayList<>();

}
