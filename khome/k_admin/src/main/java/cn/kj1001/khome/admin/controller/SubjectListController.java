package cn.kj1001.khome.admin.controller;


import cn.kj1001.khome.admin.service.SubjectListService;
import cn.kj1001.khome.base.util.JsonResult;
import cn.kj1001.khome.base.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题库列表表 前端控制器
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/subjectList")
public class SubjectListController {
    @Autowired
    SubjectListService subjectListService;

    @GetMapping("/list")
    public JsonResult getSubList(@RequestParam Map<String,Object> parMap){
        List<Map<String,Object>> list = subjectListService.getSubjectList(parMap);
        if (list == null || list.isEmpty()) {
            return JsonResult.err(205, "数据异常");
        }

         return JsonResult.ok();

    }

}

