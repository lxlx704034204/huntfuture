package com.hante;


import com.hante.common.domain.model.BaseModel;
import org.junit.Test;

import java.lang.reflect.Field;

public class Mytest {
    @Test
    public void test1() {
        Field[] fields = BaseModel.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType());
        }
    }
}
