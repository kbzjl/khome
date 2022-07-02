package cn.kj1001.khome.admin.service.impl;

import cn.kj1001.khome.base.entity.Access;
import cn.kj1001.khome.base.dao.AccessDao;
import cn.kj1001.khome.admin.service.AccessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户信息表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
public class AccessServiceImpl extends ServiceImpl<AccessDao, Access> implements AccessService {

}
