package com.hante.common.component.jpa;

import com.hante.common.domain.dto.BaseQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * JPA自动分页
 */
public class PageableUtil {

    /**
     * 获取JPA分页的注入参数
     *
     * @param query 前台传入的对象，BaseQuery的子类
     * @return PageRequest
     */
    public static PageRequest getPageRequest(BaseQuery query) {
        List<AutoPageable> pageableList = Arrays.asList(query.getClass().getDeclaredFields()).parallelStream()
                .filter(field -> field.isAnnotationPresent(AutoPageable.class))
                .map(field -> field.getAnnotation(AutoPageable.class))
                .sorted(Comparator.comparing(AutoPageable::order)).collect(toList());
        //如果没有@AutoPageable注解，就不设置排序条件
        if (pageableList.isEmpty()) {
            return new PageRequest(query.getPageNumber(), query.getPageSize());
        }
        //按照排序完的注解生成排序条件
        List<Sort.Order> orders = pageableList.parallelStream()
                .map(autoPageable -> new Sort.Order(autoPageable.direction(), autoPageable.fieldName())).collect(toList());
        return new PageRequest(query.getPageNumber(), query.getPageSize(), new Sort(orders));
    }
}
