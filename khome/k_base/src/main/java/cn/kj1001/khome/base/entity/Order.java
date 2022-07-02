package cn.kj1001.khome.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("or_order")
public class Order implements Serializable {

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
     * 订单类型(0.题库,1课程)
     */
    private Integer type;

    /**
     * 商家id
     */
    private String infoId;

    /**
     * 支付金额
     */
    private Integer money;

    /**
     * 支付状态0未支付1支付中2已支付
     */
    private Integer state;

    /**
     * 支付方式(0.余额，1,支付宝，2.微信，3.云闪付，4.银联)
     */
    private Integer payType;

    /**
     * 支付数量
     */
    private Integer payNum;

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
