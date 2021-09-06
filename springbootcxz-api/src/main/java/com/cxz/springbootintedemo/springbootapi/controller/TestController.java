package com.cxz.springbootintedemo.springbootapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.cxz.springbootintedemo.springbootdao.testdb.imp.LeeCodeService;
import com.cxz.springbootintedemo.springbootdao.testdb.imp.OrderService;
import com.cxz.springbootintedemo.springbootdao.testdb.imp.TestService;
import com.cxz.springbootintedemo.springbootdomain.entity.testdb.Order;
import com.cxz.springbootintedemo.springbootdomain.entity.testdb.TLeecode;
import com.cxz.springbootintedemo.springbootdomain.entity.testdb2.TOrderRefund;
import com.cxz.springbootintedemo.springbootdomain.vo.request.OrderRequest;

import com.cxz.springbootintedemo.springbootutil.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.ReactorNettyHttpClientMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/8/26 15:51
 */
@Api(tags = "TEST")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    public OrderService orderService;

    @RequestMapping(value = "/getOrderList", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "getOrderList")//swagger 当前接口注解
    public Map<String, List<Order>> getOrderList(OrderRequest req){
        List<Order> orderList = null;
        HashMap<String, List<Order>> map = new HashMap<>();
        try {
           // LogUtil.WriteLog(MessageFormat.format("request: {0}",  JsonUtil.toJson(req)));
            QueryWrapper<Order> qw = new QueryWrapper<>();
            //qw.eq("OrderNo",req.getOrderno());
            orderList = orderService.getOrderList(qw);
            map = new HashMap<>(6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("order",orderList);
        return map;
    }

    @Autowired
    public LeeCodeService leeCodeService;

    @RequestMapping(value = "/getleecodeList", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "getleecodeList")//swagger 当前接口注解
    public Map<String, List<TLeecode>> getleecodeList(OrderRequest req){
        List<TLeecode> orderList = null;
        HashMap<String, List<TLeecode>> map = new HashMap<>();
        try {
            //LogUtil.WriteLog(MessageFormat.format("request: {0}",  JsonUtil.toJson(req)));
            QueryWrapper<TLeecode> qw = new QueryWrapper<>();
            //qw.eq("OrderNo",req.getOrderno());
            orderList = leeCodeService.getAll();
            map = new HashMap<>(6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("order",orderList);
        return map;
    }

    @RequestMapping(value = "/testread", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "testread")
    public Map<String,String> testread(OrderRequest req) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        leeCodeService.read();
        map.put("testread","ok");
        return map;
    }


    @RequestMapping(value = "/addlee", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "addlee")
    public Map<String,String> addlee(OrderRequest req) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        int res = leeCodeService.AddLC();
        map.put("testread", String.valueOf(res));
        return map;
    }

    @RequestMapping(value = "/getRandomLee", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "getRandomLee")
    public Map<String,String> getRandomLee(){
        HashMap<String, String> map = new HashMap<>();
        TLeecode randomLee = leeCodeService.getRandomLee();

        map.put("getRandomLee", JsonUtil.toJson(randomLee));
        return map;
    }


    @RequestMapping(value = "/getOrder", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "getOrder")//swagger 当前接口注解
    public Map<String, List<Order>> getOrder(OrderRequest req){
        List<Order> orderList = null;
        HashMap<String, List<Order>> map =new HashMap<>();
        try {
            //LogUtil.WriteLog(MessageFormat.format("request: {0}",  JsonUtil.toJson(req)));
            QueryWrapper<Order> qw = new QueryWrapper<>();
            qw.eq("Id",req.getId());
            //qw.eq("OrderNo",req.getOrderno());
            orderList = orderService.getOrder(qw);
            map = new HashMap<>(6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("order",orderList);
        return map;
    }
    @Autowired
    public TestService testService;

    @RequestMapping(value = "/tranMultiDB", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "tranMultiDB")
    public Map<String, String> TranMultiDB(){

        HashMap<String, String> map = new HashMap<>();
        ArrayList<TOrderRefund> rorders = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        int i = 0;
        while (i<6){
            TOrderRefund rorder = new TOrderRefund();
            rorder.setAmount(new BigDecimal(999));
            //TOrderRefund.setCreatetime(LocalDateTime.now());
            String rorderno = UUID.randomUUID().toString().substring(0,10);
            rorder.setOrderno(rorderno);
            rorders.add(rorder);
            Order order = new Order();
            order.setUsername("cxz");
            order.setCreatetime(LocalDateTime.now());
            String orderno = UUID.randomUUID().toString().substring(0,10);
            order.setOrderno(orderno);
            orders.add(order);
            i++;
        }
        int res  = testService.multidbinsert(orders,rorders);
        map.put("user", String.valueOf(res));
        return map;
    }

    @RequestMapping(value = "/tranMultiDBNoErr", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "tranMultiDBNoErr")
    public Map<String, String> TranMultiDBNoErr(){

        HashMap<String, String> map = new HashMap<>();
        ArrayList<TOrderRefund> rorders = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        int i = 0;
        while (i<6){
            TOrderRefund rorder = new TOrderRefund();
            rorder.setAmount(new BigDecimal(999));
            //TOrderRefund.setCreatetime(LocalDateTime.now());
            String rorderno = UUID.randomUUID().toString().substring(0,10);
            rorder.setOrderno(rorderno);
            rorders.add(rorder);
            Order order = new Order();
            order.setUsername("cxz");
            order.setCreatetime(LocalDateTime.now());
            String orderno = UUID.randomUUID().toString().substring(0,10);
            order.setOrderno(orderno);
            orders.add(order);
            i++;
        }
        int res  = testService.multidbinsertNoErr(orders,rorders);
        map.put("user", String.valueOf(res));
        return map;
    }
}
