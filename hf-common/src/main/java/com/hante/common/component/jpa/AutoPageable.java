package com.hante.common.component.jpa;

import org.springframework.data.domain.Sort;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//@Inherited
public @interface AutoPageable {

    //排序方式(默认从小到大)
    Sort.Direction direction() default Sort.Direction.ASC;

    //排序对应数据库里面的字段
    String fieldName() default "";

    //该数值越低，排序优先级越高
    int order() default 0;

}
