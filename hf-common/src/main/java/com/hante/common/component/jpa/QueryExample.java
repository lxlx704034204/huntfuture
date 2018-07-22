package com.hante.common.component.jpa;

import com.hante.common.domain.dto.BaseQuery;
import com.hante.common.domain.enums.EnumSysStatus;
import com.hante.common.exception.HanteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        return getPredicate(root, criteriaBuilder, query);
    }


    private Predicate getPredicate(Root<T> root, CriteriaBuilder criteriaBuilder, BaseQuery query) {
        List<Predicate> list = new LinkedList<>();
        Class<?> voClass = query.getClass();
        //通过反射获取所有的成员变量（继承自父类的不算）
        Field[] fields = voClass.getDeclaredFields();
        String fieldName, voFieldName, voFieldType, voFieldString;
        Method m;
        Long voFieldLong;
        Integer voFieldInteger;
        Date voFieldDate;
        AutoQuery autoQuery;
        try {
            for (Field field : fields) {
                if (!field.isAnnotationPresent(AutoQuery.class)) {
                    continue;
                }
                autoQuery = field.getAnnotation(AutoQuery.class);
                //数据库默认查询字段名为注释字段名
                fieldName = autoQuery.fieldName().equals("") ? field.getName() : autoQuery.fieldName();
                voFieldName = field.getName();
                voFieldName = voFieldName.substring(0, 1).toUpperCase() + voFieldName.substring(1);
                m = voClass.getDeclaredMethod("get" + voFieldName);
                voFieldType = field.getGenericType().getTypeName();
                if (voFieldType.equals("java.lang.String")) {
                    voFieldString = (String) m.invoke(query);
                    if (voFieldString == null || voFieldString.equals("")) {
                        continue;
                    }
                    switch (autoQuery.condition()) {
                        case LIKE:
                            list.add(criteriaBuilder.like(root.get(fieldName).as(String.class), "%" + voFieldString + "%"));
                            break;
                        case MATCH:
                            list.add(criteriaBuilder.equal(root.get(fieldName).as(String.class), voFieldString));
                            break;
                        case DIFFER:
                            list.add(criteriaBuilder.notEqual(root.get(fieldName).as(String.class), voFieldString));
                            break;
                        default:
                            throw new HanteException("自动查询标签使用错误，非法的String类型", HanteException.BAD_CODE);
                    }
                    continue;
                }

                if (voFieldType.equals("java.lang.Long")) {
                    voFieldLong = (Long) m.invoke(query);
                    if (voFieldLong == null || voFieldLong == 0L) {
                        continue;
                    }
                    switch (autoQuery.condition()) {
                        case MIN:
                            list.add(criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName).as(Long.class), voFieldLong));
                            break;
                        case MAX:
                            list.add(criteriaBuilder.lessThanOrEqualTo(root.get(fieldName).as(Long.class), voFieldLong));
                            break;
                        case MATCH:
                            list.add(criteriaBuilder.equal(root.get(fieldName).as(Long.class), voFieldLong));
                            break;
                        case DIFFER:
                            list.add(criteriaBuilder.notEqual(root.get(fieldName).as(Long.class), voFieldLong));
                            break;
                        default:
                            throw new HanteException("自动查询标签使用错误，非法的long类型", HanteException.BAD_CODE);
                    }
                    continue;
                }

                if (voFieldType.equals("java.lang.Integer")) {
                    voFieldInteger = (Integer) m.invoke(query);
                    if (voFieldInteger == null || voFieldInteger == 0) {
                        continue;
                    }
                    switch (autoQuery.condition()) {
                        case MIN:
                            list.add(criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName).as(Integer.class), voFieldInteger));
                            break;
                        case MAX:
                            list.add(criteriaBuilder.lessThanOrEqualTo(root.get(fieldName).as(Integer.class), voFieldInteger));
                            break;
                        case MATCH:
                            list.add(criteriaBuilder.equal(root.get(fieldName).as(Integer.class), voFieldInteger));
                            break;
                        case DIFFER:
                            list.add(criteriaBuilder.notEqual(root.get(fieldName).as(Integer.class), voFieldInteger));
                            break;
                        default:
                            throw new HanteException("自动查询标签使用错误，非法的int类型", HanteException.BAD_CODE);
                    }
                    continue;
                }

                if (voFieldType.equals("java.util.Date")) {
                    voFieldDate = (Date) m.invoke(query);
                    if (voFieldDate == null) {
                        continue;
                    }
                    switch (autoQuery.condition()) {
                        case MIN:
                            list.add(criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName).as(Date.class), voFieldDate));
                            break;
                        case MAX:
                            list.add(criteriaBuilder.lessThanOrEqualTo(root.get(fieldName).as(Date.class), voFieldDate));
                            break;
                        case MATCH:
                            list.add(criteriaBuilder.equal(root.get(fieldName).as(Date.class), voFieldDate));
                            break;
                        case DIFFER:
                            list.add(criteriaBuilder.notEqual(root.get(fieldName).as(Date.class), voFieldDate));
                            break;
                        default:
                            throw new HanteException("自动查询标签使用错误，非法的Date类型", HanteException.BAD_CODE);
                    }
                    continue;
                }

                throw new HanteException("自动查询标签使用错误，不支持的字段属性", HanteException.BAD_CODE);
            }
            //不获取标记为删除的记录
            list.add(criteriaBuilder.equal(root.get("sysStatus").as(Integer.class), EnumSysStatus.NORMAL.getCode()));
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        } catch (Exception e) {
            log.error("自动组装查询条件出错", e);
            throw new HanteException("自动查询标签使用错误，请查看日志排除", HanteException.BAD_CODE);
        }
    }
}
