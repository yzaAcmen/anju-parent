package cn.itsource.anju.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 品牌信息
 * </p>
 *
 * @author yza
 * @since 2019-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_brand")
@ToString
public class Brand implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("createTime")
    private Long createTime;

    @TableField("updateTime")
    private Long updateTime;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 英文名
     */
    @TableField("englishName")
    private String englishName;

    /**
     * 首字母
     */
    @TableField("firstLetter")
    private String firstLetter;

    private String description;

    /**
     * 商品分类ID
     */
    private Long productTypeId;

    @TableField("sortIndex")
    private Integer sortIndex;

    /**
     * 品牌LOGO
     */
    private String logo;


    @TableField(exist = false) // 操作表做增删改的时候忽略这个字段
    private ProductType productType;

}
