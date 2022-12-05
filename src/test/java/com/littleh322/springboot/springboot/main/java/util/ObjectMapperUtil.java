package com.littleh322.springboot.springboot.main.java.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
