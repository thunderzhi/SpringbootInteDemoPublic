package com.cxz.springbootintedemo.springbootdomain.entity.testdb;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/8/26 15:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_Order")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "Id",type = IdType.AUTO)
    private Long Id;


    @TableField("OrderNo")
    private String orderno;

    @TableField("CreateTime")
    private LocalDateTime createtime;

    @TableField("DataFlag")
    private Integer dataflag;


    @TableField("UserName")
    private String username;

    @TableField("Amount")
    private BigDecimal amount;
}