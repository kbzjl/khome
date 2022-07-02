package cn.kj1001.khome.base.util;
import java.util.Map;
import java.util.Set;

/**
 * kj1001
 *
 * @Description : 统一参数工具类
 * @Author : MrZhang
 * @Date: 2022/3/24 14:56
 */

public class ParmUtil {
    private ParmUtil(){};
    /**
    * 参数介绍:
    * @description: 功能描述 如果遇见大量的重复代码，一般都可以抽出工具类处理逻辑
    * @author: MrZhang
    * @date: 2022/3/24 15:06 
    * @param: [checkMap(验证的map), parMap(参数集合)]
    * @return: cn.kj1001.khome.base.util.JsonResult  
    **/
    public static JsonResult check(Map<String,String> checkMap, Map<String,Object>parMap){
        //取出checkMap的key
        Set<String> keySet = checkMap.keySet();
        //遍历set，和parMap的内容逐个比对
        for (String key: keySet) {
            if(parMap.get(key)==null||parMap.get(key).equals("")){
                return JsonResult.err(201,parMap.get(key)+"参数未传递");
            }
            
        }
        return null;
    }

}
 
