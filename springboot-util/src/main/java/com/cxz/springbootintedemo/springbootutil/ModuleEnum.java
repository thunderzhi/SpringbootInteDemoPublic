package com.cxz.springbootintedemo.springbootutil;
/**
 *
 *
 * @author cxz
 * @date 2020/10/15
 */
public enum ModuleEnum {
    /**
     *util
     */
    UTIL("com.cxz.utils"),

    /**
     *  domain
     */
    DOMAIN("com.cxz.domain"),

    /**
     *  dao
     */
    DAO("com.cxz.dao"),

    /**
     *  service
     */
    SERVICE("com.cxz.service"),

    /**
     *
     */
    API("com.cxz.api");


    private String code;
    ModuleEnum(String code) {
        this.code = code;
    }
}
