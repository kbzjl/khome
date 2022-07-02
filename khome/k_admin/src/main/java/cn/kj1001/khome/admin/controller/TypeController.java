package cn.kj1001.khome.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.kj1001.khome.admin.service.TypeService;
import cn.kj1001.khome.base.entity.Type;
import cn.kj1001.khome.base.util.JsonResult;
import cn.kj1001.khome.base.util.PageUtils;
import cn.kj1001.khome.base.util.ParmUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题库类型表 前端控制器
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeService typeService;


    //获取题库列表
    @GetMapping("list")
    public JsonResult getList(Map<String,Object> parMap){

        //使用封装的分页工具类
        PageUtils.pageNums(parMap);

        //完成查询并判断
        List<Type> list = typeService.selectList(parMap);
        if(list==null||list.isEmpty()){
            return JsonResult.err(205,"题库列表不存在");
        }

        //整合分页并返回
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        return JsonResult.ok(pageInfo);

    }

    //添加题库类型
    @PostMapping("addType")
    public JsonResult addType(@RequestParam Map<String,Object> parMap){
        //判断参数
        Map<String,String> checkMap = new HashMap<>(){
            {
                put("typeName","题库类型名称");
            }
        };

        //调用工具类验证参数
        JsonResult jr = ParmUtil.check(checkMap, parMap);
        if(jr!=null){
            return jr;
        }

        //封装对象 将map转为对象
        Type type = BeanUtil.toBean(parMap, Type.class);
        return typeService.addType(type);
    }
    //修改题库类型
    @PostMapping("updateType")
    public JsonResult updateType(@RequestParam Map<String,Object> parMap){
        //判断参数
        Map<String,String> checkMap = new HashMap<>(){
            {
                put("id","题库类型ID");
                //put("typeName","题目类型名");

            }
        };
        JsonResult jr = ParmUtil.check(checkMap, parMap);
        if(jr!=null) return jr;
        Type type = BeanUtil.toBean(parMap, Type.class);
        return typeService.updateType(type);
    }
}

