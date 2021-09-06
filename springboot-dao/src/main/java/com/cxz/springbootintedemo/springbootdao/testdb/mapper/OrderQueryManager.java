package com.cxz.springbootintedemo.springbootdao.testdb.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/8/26 15:46
 */
@Repository
public class OrderQueryManager {

    public String selectAll(){
        String sql = new SQL() {{
            SELECT("Id, OrderNo, CreateTime ,DataFlag, UserName");
            FROM("T_Order");
        }}.toString();
        return sql;
    }
}
