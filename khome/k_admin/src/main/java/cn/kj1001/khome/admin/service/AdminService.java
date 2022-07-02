package cn.kj1001.khome.admin.service;

import cn.kj1001.khome.base.entity.Admin;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface AdminService extends IService<Admin> {
    JsonResult login(Map<String,Object> parMap);

}
