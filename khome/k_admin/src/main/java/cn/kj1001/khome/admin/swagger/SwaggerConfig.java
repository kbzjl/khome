package cn.kj1001.khome.admin.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * kj1001
 *
 * @Description : swagger配置类
 * @Author : MrZhang
 * @Date: 2022/4/7 20:06
 */
@Configuration
public class SwaggerConfig {

    /**
     * 获取文档的摘要信息
     * @return
     */
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())//文档说明
                .select()
                //需要进行在页面上显示的内容 ,选择注解为ApiOperation方法，所有的访问路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 文档变量信息
     * @return
     */
    public ApiInfo apiInfo(){
        System.out.println("api文档创建成功");
        return new ApiInfoBuilder()
                .title("khome项目文档")
                .description("这是一个接口文档")
                .contact(new Contact("khome","http://www.baidu.com","hr@khome.cn"))
                .version("1.0.0")
                .build();
    }
}
 
