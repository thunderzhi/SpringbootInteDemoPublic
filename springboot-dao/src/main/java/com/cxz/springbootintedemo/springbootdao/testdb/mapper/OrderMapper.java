package com.cxz.springbootintedemo.springbootdao.testdb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxz.springbootintedemo.springbootdomain.entity.testdb.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/8/26 15:45
 */
@Mapper
//@Qualifier("sqlSessionFactory")
public interface OrderMapper extends BaseMapper<Order> {


    @SelectProvider(type = OrderQueryManager.class,method = "selectAll")
//    @ResultMap("BaseResultMap")
    @Results({@Result(column = "Id",property = "Id"),
            @Result(column = "CreateTime",property = "createtime"),
            @Result(column = "DataFlag",property = "dataflag"),
            @Result(column = "OrderNo",property = "orderno")
    })
    List<Order> selectAll();
}