package cn.kj1001.khome.admin.controller;

import cn.kj1001.khome.admin.swagger.TestDo;
import cn.kj1001.khome.base.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/4/7 17:30
 */
@Slf4j
@Api(tags = "标准的rest接口")
@RestController
@RequestMapping("swg/test")
public class SwagTestController {


    @ApiOperation("新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "编号",defaultValue = "123"),
            @ApiImplicitParam(name="age",value = "年龄",defaultValue = "18")
    })
    @PostMapping
    public JsonResult add(@RequestParam String id,@RequestParam Integer age){
        log.info(id+" "+age);
        return JsonResult.ok();
    }

    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public JsonResult del(@PathVariable String id){
        log.info(id);
        return JsonResult.ok();

    }

    @ApiOperation("修改")
    @PutMapping
    public JsonResult update(TestDo testDo){
        log.info(testDo.toString());
        return JsonResult.ok();
    }

    @ApiOperation("查询")
    @GetMapping
    public JsonResult get(TestDo testDo){
        log.info(testDo.toString());
        return JsonResult.ok(testDo);
    }
}
 
