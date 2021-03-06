package com.cxz.springbootintedemo.springbootdao.testdb.imp;


import com.cxz.springbootintedemo.springbootdao.testdb.mapper.OrderMapper;
import com.cxz.springbootintedemo.springbootdao.testdb.mapper.T_LEECODEMapper;
import com.cxz.springbootintedemo.springbootdao.testdb2.mapper.TOrderRefundMapper;
import com.cxz.springbootintedemo.springbootdomain.entity.testdb.Order;
import com.cxz.springbootintedemo.springbootdomain.entity.testdb.TLeecode;
import com.cxz.springbootintedemo.springbootdomain.entity.testdb2.TOrderRefund;
import com.cxz.springbootintedemo.springbootutil.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/2 11:04
 */
@Service
public class TestService {


    @Autowired
    private TOrderRefundMapper tOrderRefundMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private T_LEECODEMapper tLeecodeMapper;


    public String test(){
        return "----------------------";
    }



    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class,RuntimeException.class},
            transactionManager = "transactionManager")
    public int multidbinsert(List<Order> orders,List<TOrderRefund> refunds){
        int res =0;
        for (Order order : orders) {
            int i = orderMapper.insert(order);
            res +=i;
            break;

        }
        for (TOrderRefund refund : refunds) {
            int i = tOrderRefundMapper.insert(refund);
            res +=i;
            break;

        }
        int j = 1/0;
        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class,RuntimeException.class},
            transactionManager = "transactionManager")
    public int multidbinsertNoErr(List<Order> orders, List<TOrderRefund> refunds){
        int res =0;
        for (Order order : orders) {
            int i = orderMapper.insert(order);
            res +=i;
            break;

        }
        for (TOrderRefund refund : refunds) {
            int i = tOrderRefundMapper.insert(refund);
            res +=i;
            break;

        }

        return res;
    }

    public int batchAddLC(){
        String path = "D:\\xuezhi\\Documents\\20210825LeeCodeEasy";
        String txt = readfile(path);
        int i  =0;
        List<TLeecode> tLeecodes = JsonUtil.toList(TLeecode.class, txt);
        for (TLeecode item : tLeecodes) {
            i+=tLeecodeMapper.insert(item);
        }

        return i;
    }

    public static String readfile(String fileName ){
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {

            reader = new BufferedReader(new FileReader(file));


            String tempString = null;
            int line = 1;
            // ?????????????????????????????????null???????????????
            while ((tempString = reader.readLine()) != null) {
                // ????????????

                line++;
                sb.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }
}