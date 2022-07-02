package cn.kj1001.khome.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.kj1001.khome.admin.service.InfoService;
import cn.kj1001.khome.base.dao.InfoDao;
import cn.kj1001.khome.base.entity.Info;
import cn.kj1001.khome.base.util.JsonResult;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家信息表 前端控制器
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;
    @GetMapping("list")
    public JsonResult getList(@RequestParam Map<String,Object> parMap){
        Integer pageNum=1;
        Integer pageSize=10;
        
        //判断参数
        if (parMap.get("pageNum")!=null){
            pageNum = Integer.valueOf(parMap.get("pageNum").toString());
        }
        if(parMap.get("pageSize")!=null){
            pageSize = Integer.valueOf(parMap.get("pageSize").toString());
        }        
        
        //开启分页插件 并传入当前页数和每页显示数据
        PageHelper.startPage(pageNum,pageSize);

        //完成查询并判断
        List<Info> list = infoService.getList(parMap);
        if(list==null||list.isEmpty()){
            return JsonResult.err(205,"数据不存在");
        }

        //整合分页并返回
        PageInfo<Info> page = new PageInfo<Info>(list);
        return JsonResult.ok(page);
    }

    @GetMapping("stoInfo")
    public JsonResult getSto(@RequestParam Map<String,Object> parMap){
        if(parMap.get("phone")==null||parMap.get("phone")==""){
            return JsonResult.err(201,"手机号不为空");
        }
        if(parMap.get("stoName")==null||parMap.get("stoName")==""){
            return JsonResult.err(201,"用户名不为空");
        }

        //判断前端传入的
        List<Info> list = infoService.getSto(parMap);
        if (list==null|| list.isEmpty()){
            return JsonResult.err(201,"用户不存在");
        }
        return JsonResult.ok(infoService.getSto(parMap));

    }

    @PostMapping("update")
    public JsonResult updateSto(@RequestParam Map<String,Object> parMap){

        if (parMap.get("phone")==null||parMap.get("phone").equals("")){
           return JsonResult.err(201,"手机号不能为空");
        }
        if (parMap.get("qq")==null||parMap.get("qq").equals("")){
            return JsonResult.err(201,"qq号不能为空");
        }
        if (parMap.get("wx")==null||parMap.get("wx").equals("")){
            return JsonResult.err(201,"wx号不能为空");
        }
        Info info = BeanUtil.toBean(parMap, Info.class);
        return infoService.updateSto(info);

    }
    @PostMapping("add")
    public JsonResult addSto(@RequestParam Map<String,Object> parMap){
        if (parMap.get("phone")==null||parMap.get("phone").equals("")){
            return JsonResult.err(201,"手机号不能为空");
        }
        if (parMap.get("qq")==null||parMap.get("qq").equals("")){
            return JsonResult.err(201,"qq号不能为空");
        }
        if (parMap.get("wx")==null||parMap.get("wx").equals("")){
            return JsonResult.err(201,"wx号不能为空");
        }
        Info info = BeanUtil.toBean(parMap, Info.class);
        return infoService.addSto(info);
    }


}

