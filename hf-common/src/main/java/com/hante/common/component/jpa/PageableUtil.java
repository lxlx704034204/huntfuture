package com.hante.common.component.jpa;

import com.hante.common.domain.dto.BaseQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * JPA自动分页
 */
public class PageableUtil {

    public static PageRequest getPageRequest(BaseQuery query) {
        Field[] fields = query.getClass().getDeclaredFields();
        String fieldName;
        List<SortCondition> conditions = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoPageable.class)) {
                AutoPageable autoPageable = field.getAnnotation(AutoPageable.class);
                fieldName = autoPageable.fieldName().equals("") ? field.getName() : autoPageable.fieldName();
                SortCondition condition = new SortCondition(fieldName, autoPageable.direction(),autoPageable.order());
                conditions.add(condition);
            }
        }
        if(conditions.isEmpty()){
            return new PageRequest(query.getPageNumber(), query.getPageSize());
        }
        //按照排列优先级对条件集合先进行排序
        Collections.sort(conditions);
        List<Sort.Order> orders = new ArrayList<>();
        for(SortCondition condition : conditions){
            Sort.Order order = new Sort.Order(condition.direction,condition.fieldName);
            orders.add(order);
        }
        return new PageRequest(query.getPageNumber(), query.getPageSize(), new Sort(orders));
    }

    public static class SortCondition implements Comparable<SortCondition> {
        /**
         * 排序字段名
         */
        private String fieldName;
        /**
         * 排序顺序
         */
        private Sort.Direction direction;
        /**
         * 条件优先级
         *
         * @see AutoPageable
         */
        private int order;

        public SortCondition() {
        }

        public SortCondition(String fieldName, Sort.Direction direction, int order) {
            this.fieldName = fieldName;
            this.direction = direction;
            this.order = order;
        }

        /**
         * 由order从低到高排序
         */
        @Override
        public int compareTo(SortCondition o) {
            return this.order - o.order;
        }
    }
}
