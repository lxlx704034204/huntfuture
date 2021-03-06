package com.hante.common.component.jpa;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//@Inherited
public @interface AutoQuery {

    //查询类型枚举类，可以满足大部分的查询需求，如果有特殊需要可以自己编写dao或者sql语句
    enum ConditionType {
        //匹配查询
        MATCH,
        //模糊查询（% + value + %）
        LIKE,
        //不能等于
        DIFFER,
        //最小值(可以等于)
        MIN_INT,
        //最大值（可以等于）
        MAX_INT,
        //最小值(可以等于)
        MIN_LONG,
        //最大值（可以等于）
        MAX_LONG,
        //该天00:00:00开始
        MIN_DATE,
        //截止到该天23:59:59
        MAX_DATE;
    }

    //查询类型
    ConditionType condition() default ConditionType.MATCH;

    //查询对应实体类里面的字段
    String fieldName() default "";
}
