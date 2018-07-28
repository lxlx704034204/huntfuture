package com.hante.common.component.jpa;

import com.hante.common.domain.dto.BaseQuery;
import com.hante.common.domain.enums.EnumSysStatus;
import com.hante.common.exception.HanteException;
import com.hante.common.utils.date.DateUtils;
import com.hante.common.utils.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * JPA自动查询条件组长
 *
 * @param <T> 要返回PO对象的类型
 */
@Slf4j
public class QueryExample<T> implements Specification<T> {

    private BaseQuery query;

    public QueryExample(BaseQuery query) {
        this.query = query;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return getPredicates(root, criteriaBuilder, query);
    }

    private Predicate getPredicates(Root<T> root, CriteriaBuilder criteriaBuilder, BaseQuery query) {
        List<Predicate> predicates = Arrays.asList(query.getClass().getDeclaredFields()).parallelStream()
                .filter(field -> field.isAnnotationPresent(AutoQuery.class))
                .map(field -> this.getOnePredicate(root, criteriaBuilder, field, query))
                .filter(Objects::nonNull)
                .collect(toList());
        //在底层封装标记删除的实现
        predicates.add(criteriaBuilder.notEqual(root.get("sysStatus").as(Integer.class), EnumSysStatus.DELETED.getCode()));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    @SuppressWarnings("all")
    private Predicate getOnePredicate(Root<T> root, CriteriaBuilder criteriaBuilder, Field field, BaseQuery query) {
        AutoQuery autoQuery = field.getAnnotation(AutoQuery.class);
        String fieldName = field.getName();
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Object reflectValue;
        try {
            reflectValue = query.getClass().getDeclaredMethod("get" + fieldName).invoke(query);
        } catch (Exception e) {
            throw new HanteException("查询类缺少get方法，类名：" + query.getClass().getSimpleName(), HanteException.BAD_CODE);
        }
        //封装动态查询，为null或者为0的值不查询
        if (reflectValue == null || reflectValue.toString().equals("0")) {
            return null;
        }
        String columnName = autoQuery.fieldName().equals("") ? field.getName() : autoQuery.fieldName();
        switch (autoQuery.condition()) {
            case LIKE:
                return criteriaBuilder.like(root.get(columnName).as(String.class), "%" + reflectValue.toString() + "%");
            case MATCH:
                return criteriaBuilder.equal(root.get(columnName).as(field.getType()), reflectValue);
            case DIFFER:
                return criteriaBuilder.notEqual(root.get(columnName).as(field.getType()), reflectValue);
            case MIN_INT:
                return criteriaBuilder.greaterThanOrEqualTo(root.get(columnName).as(Integer.class), (Integer) reflectValue);
            case MAX_INT:
                return criteriaBuilder.lessThanOrEqualTo(root.get(columnName).as(Integer.class), (Integer) reflectValue);
            case MIN_LONG:
                return criteriaBuilder.greaterThanOrEqualTo(root.get(columnName).as(Long.class), (Long) reflectValue);
            case MAX_LONG:
                return criteriaBuilder.lessThanOrEqualTo(root.get(columnName).as(Long.class), (Long) reflectValue);
            case MIN_DATE:
                return criteriaBuilder.greaterThanOrEqualTo(root.get(columnName).as(Date.class), (Date) reflectValue);
            case MAX_DATE:
                return criteriaBuilder.lessThan(root.get(columnName).as(Date.class), DateUtils.addDate((Date) reflectValue, 1));
            default:
                throw new HanteException("无法识别的查询条件枚举类：" + autoQuery.condition(), HanteException.BAD_CODE);
        }
    }

}
