package cn.itsource.anju.controller;

import cn.itsource.anju.util.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yza
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 10:54
 */
@RestController
public class RedisController {

    /**
     * 获取缓存数据
     * @param key
     * @return
     */
    @GetMapping("/redis")
    public String get(@RequestParam("key") String key){
        return RedisUtils.INSTANCE.get(key);
    }

    /**
     * 设置缓存数据
     * @param key
     * @param value
     */
    @PostMapping("/redis")
    public void set(@RequestParam("key") String key,@RequestParam("value") String value){
        RedisUtils.INSTANCE.set(key,value);
    }


}