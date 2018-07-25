package com.hante;


import com.hante.common.domain.model.BaseModel;
import org.junit.Test;

import java.lang.reflect.Field;

public class LbsTest {
    @Test
    public void test1() {
        Field[] fields = BaseModel.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType());
        }
    }
}
