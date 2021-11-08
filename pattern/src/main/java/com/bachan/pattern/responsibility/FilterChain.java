package com.bachan.pattern.responsibility;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter{

    private List<Filter> filters = new ArrayList<>();
    //用于标记规则的引用顺序
    int index = 0;

    public Filter addFilter(Filter filter){
        filters.add(filter);
        //代码的设计技巧:Chain链添加过滤规则结束后返回添加后的Chain，方便我们下面doFilter函数的操
        return  this;
    }

    @Override
    public void doFilter(Request request, FilterChain filterChain) {

        //index初始化为0,filters.size()为3，不会执行return操作
        if (index == filters.size()){
            return;
        }

        Filter filter = filters.get(index);
        index ++;
        filter.doFilter(request, filterChain);
    }
}
