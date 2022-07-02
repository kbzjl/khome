package cn.kj1001.khome.admin.service.impl;

import cn.kj1001.khome.base.entity.About;
import cn.kj1001.khome.base.dao.AboutDao;
import cn.kj1001.khome.admin.service.AboutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
public class AboutServiceImpl extends ServiceImpl<AboutDao, About> implements AboutService {

}
