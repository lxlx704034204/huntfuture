package com.hante.domain.query;

import com.hante.common.component.jpa.AutoPageable;
import com.hante.common.component.jpa.AutoQuery;
import com.hante.common.domain.dto.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

@Setter
@Getter
@ToString(callSuper = true)
public class SysAccountQuery extends BaseQuery {
    @AutoPageable(fieldName = "createAt",direction = Sort.Direction.DESC)
    @AutoQuery
    private String roleId;
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE)
    private String loginName;
}
