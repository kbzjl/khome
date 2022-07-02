package cn.kj1001.khome.common.controller;

import cn.kj1001.khome.base.entity.User;
import cn.kj1001.khome.base.util.AliSmsUtil;
import cn.kj1001.khome.base.util.JsonResult;
import cn.kj1001.khome.base.util.ParmUtil;
import cn.kj1001.khome.base.util.RedisUtil;
import cn.kj1001.khome.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * kj1001
 *
 * @Description : 注册
 * @Author : MrZhang
 * @Date: 2022/3/29 16:12
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    @PostMapping("msg")
    public JsonResult msg(String phone) throws Exception {
        if (phone == null || "".equals(phone)) {
            return JsonResult.err(201, "手机号未传递");
        }
        String code = AliSmsUtil.getRandomCode(4);
        JsonResult jsr = AliSmsUtil.sendSms(phone,code);
        if(jsr!=null){
            return jsr;
        }
        redisUtil.setCode(phone,code);
        return JsonResult.ok();
    }

    @PostMapping("reg")
    public JsonResult reg(@RequestParam Map<String,Object>parMap){
        Map<String,String> checkMap = new HashMap<>(){
            {
                put("phone", "手机号");
                put("code", "验证码");
                put("password", "密码");
                put("userName", "用户名");
            }
        };
        JsonResult jsr = ParmUtil.check(checkMap,parMap);
        if(jsr != null){
            return jsr;
        }
        return userService.addUser(parMap);
    }

    @PostMapping("login")
    public JsonResult upload(@RequestBody User user){
        //参数验证

        if(user.getPhone().equals("")||user.getPhone()==null){
            return JsonResult.err(203,"用户名不能为空");

        }
        if (user.getPassword().equals("")||user.getPassword()==null){
            return JsonResult.err(203,"密码不能为空");
        }
        return userService.login(user);
    }

}
 
