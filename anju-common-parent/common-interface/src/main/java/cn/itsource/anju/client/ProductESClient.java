package cn.itsource.anju.client;

import cn.itsource.anju.domain.ProductDoc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("ANJU-COMMON")
public interface ProductESClient {

    /**
     * 批量保存
     * @param productDocList
     */
    @PostMapping("/es/saveBath")
    void saveBatch(@RequestBody List<ProductDoc> productDocList);

    /**
     * 批量删除
     * @param ids
     */
    @PostMapping("/es/deleteBath")
    void deleteBatch(@RequestBody List<Long> ids);
}