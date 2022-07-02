package cn.kj1001.khome.admin.controller;


import cn.kj1001.khome.admin.service.OrderService;
import cn.kj1001.khome.base.util.JsonResult;
import cn.kj1001.khome.base.util.PageUtils;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    public JsonResult listOrders(@RequestParam Map<String,Object> parMap){
        List<Map<String,Object>> list = orderService.getList(parMap);
        if(list.isEmpty()||list==null){
            return JsonResult.err(201,"参数异常");
        }
        //分页看不懂
        return JsonResult.ok();

    }
}

