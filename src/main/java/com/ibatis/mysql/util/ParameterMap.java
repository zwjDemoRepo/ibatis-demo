package com.ibatis.mysql.util;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/12/9 0009.
 */
public class ParameterMap extends HashMap<Object,Object> {
    public ParameterMap(Object... parameters) {
        for (int i = 0; i < parameters.length - 1; i += 2) {
            super.put(parameters[i], parameters[i + 1]);
        }
    }
}
