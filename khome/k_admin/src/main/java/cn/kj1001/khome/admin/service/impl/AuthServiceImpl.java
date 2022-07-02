package cn.kj1001.khome.admin.service.impl;

import cn.kj1001.khome.base.entity.Auth;
import cn.kj1001.khome.base.dao.AuthDao;
import cn.kj1001.khome.admin.service.AuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthDao, Auth> implements AuthService {

}
