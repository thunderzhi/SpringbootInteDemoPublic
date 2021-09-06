package com.cxz.springbootintedemo.springbootdomain.vo.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/8/26 16:47
 */
@Data
public class OrderRequest {

    private String orderno;

    private String name;

    private LocalDateTime createtime;

    private BigDecimal amount;

    private Long id;
}
