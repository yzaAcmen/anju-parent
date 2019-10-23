package cn.itsource.anju.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author yza
 * @version 1.0
 * @description 实体类负责和ES做数据交互
 * 需要查询的字段
 * 关键字 all ："商品标题 商品副标题 类型名称 品牌名称"  做分词
 * 商品类型ID
 * 商品品牌ID
 * 价格区间（最大价格，最小价格）
 *
 * 排序：销量、上架时间、评论数量、最大价格、最小价格、浏览数量
 *
 * 需要展示的字段
 * 商品的标题   商品的副标题 最大价格、最小价格  销量  图片  skuProperties  显示属性
 * @date 2019/10/21 10:30
 */
@Document(indexName = "anju")
@Data
public class ProductDoc {

    @Id
    private Long id;

    /**
     * 关键字
     * 分词+索引
     */
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String all;

    private Long productTypeId;

    private Long brandId;

    /**
     * 最高价格
     */
    private Integer maxPrice;

    /**
     * 最低价格
     */
    private Integer minPrice;

    /**
     * 销售数量
     */
    private Integer saleCount;

    /**
     * 上架时间
     */
    private Long onSaleTime;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 浏览数量
     */
    private Integer viewCount;

    /**
     * 商品名称
     */
    @Field(store = true,type = FieldType.Text)
    private String name;

    /**
     * 商品副标题
     */
    @Field(store = true,type = FieldType.Text)
    private String subName;

    /**
     * 商品媒体属性
     */
    @Field(store=true,type = FieldType.Text)
    private String medias;

    /**
     * 显示属性
     */
    @Field(store=true,type = FieldType.Text)
    private String viewProperties;

    /**
     * sku属性
     */
    @Field(store=true,type = FieldType.Text)
    private String skuProperties;

}