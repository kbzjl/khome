package cn.kj1001.khome.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 题目表
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("que_about")
public class About implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题目id
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
     * 题库列表id
     */
    private String listId;

    /**
     * 题目内容
     */
    private String title;

    /**
     * 题目类型(0.单选，1.多选，2.判断，3.填空4.问答)
     */
    private Integer type;

    /**
     * 答案
     */
    private String answer;

    /**
     * 解析
     */
    private String analysis;


}
