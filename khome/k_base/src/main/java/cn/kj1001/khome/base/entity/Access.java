package cn.kj1001.khome.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账户信息表
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("u_access")
public class Access implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户id
     */
    private String id;

    /**
     * 删除状态 0-未删除 1 删除
     */
    private Integer delState;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 余额
     */
    private Integer balance;

    /**
     * 消费金额
     */
    private Integer consum;

    /**
     * 充值金额
     */
    private Integer rech;

    /**
     * 用户id
     */
    private String userId;


}
