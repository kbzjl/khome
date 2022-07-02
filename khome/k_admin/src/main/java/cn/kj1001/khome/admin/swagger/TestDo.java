package cn.kj1001.khome.admin.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/4/7 17:26
 */
@Data
@ApiModel("swg测试实体类")
public class TestDo {
    // @ApiModelProperty(value="id")
    @ApiModelProperty(value="id",example = "1")
    private String id;
    @ApiModelProperty(value="姓名",example = "张三")
    private String name;
    @ApiModelProperty(value="年龄",example = "18")
    private Integer age;
}
 
