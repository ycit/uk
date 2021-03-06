package com.ycit.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ycit.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * json util
 *
 * @author uk
 * 2019/4/21 11:04
 */
public class JsonUtil {

    private JsonUtil() {

    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();


    static {
        // 序列化时 是否包含 null 字段：不包含
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //反序列化时是否忽略 不存在的属性值：忽略
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public  static <T> T json2Object(String source, Class<T> classType) {
        try {
            return mapper.readValue(source, classType);
        } catch (IOException e) {
            LOGGER.debug("exception is ", e);
            throw new BizException(500, "json 读取出错");
        }
    }

    public static <T> List<T> json2ObjectList(String source, Class<T> classType) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, classType);
        try {
            return mapper.readValue(source, javaType);
        } catch (IOException e) {
            LOGGER.debug("exception is ", e);
            throw new BizException(500, "json list 读取出错");
        }
    }

    public static <T> String objects2Json(T source) {
        try {
            return mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            LOGGER.debug("exception is ", e);
            throw new BizException(500, "json 写入出错");
        }
    }


    public static void main(String[]args) {


    }

}
