package cn.kj1001.khome.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 认证表
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("u_attest")
public class Attest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户类型（0用户，1商家）
     */
    private Integer type;

    /**
     * 身份证号
     */
    private String cardNum;

    /**
     * 身份证路径
     */
    private String cardUrl;

    /**
     * 营业执照
     */
    private String businessNum;

    /**
     * 营业执照路径
     */
    private String businessUrl;

    /**
     * 审核状态(0,未审核，1.已通过，2.已驳回)
     */
    private Integer state;

    /**
     * 删除状态
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


}
