package cn.itsource.anju.vo;

import cn.itsource.anju.domain.Specification;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SkusVo {

    private List<Specification> skuProperties;

    private List<Map<String,String>> skus;
}
