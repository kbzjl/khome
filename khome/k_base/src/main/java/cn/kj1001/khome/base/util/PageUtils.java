package cn.kj1001.khome.base.util;

import com.github.pagehelper.PageHelper;

import java.util.Map;

/**
 * kj1001
 *
 * @Description : 分页
 * @Author : MrZhang
 * @Date: 2022/3/24 15:09
 */
public class PageUtils {
    private PageUtils(){};
    public static  void  pageNums(Map<String,Object> parMap){

        Integer pageNum = 1;
        Integer pageSize = 10;
        if (parMap.get("pageNum")!=null){
            pageNum = Integer.valueOf(parMap.get("pageNum").toString());
        }
        if(parMap.get("pageSize")!=null){
            pageSize = Integer.valueOf(parMap.get("pageSize").toString());

        }

        //开启分页
        PageHelper.startPage(pageNum,pageSize);
    }
}
 
