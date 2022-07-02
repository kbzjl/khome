package cn.kj1001.khome.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 题库列表表
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("que_subject_list")
public class SubjectList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 商家id
     */
    private String stoId;

    /**
     * 题库列表名称
     */
    private String listName;

    /**
     * 题库类型0.全免费，1.会员免费，2.付费)
     */
    private Integer type;

    /**
     * 题库类型id
     */
    private String typeId;

    /**
     * 题库列表信息
     */
    private String info;

    /**
     * 金额
     */
    private Integer money;

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
