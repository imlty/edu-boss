package com.lagou.eduorderboot.controller;

import com.lagou.eduorderboot.entity.UserCourseOrder;
import com.lagou.eduorderboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: lagou-edu-web
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-09-09 16:11
 * @Description:
 */
@RestController
@RequestMapping("order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("saveOrder")
    public String saveOrder(String orderNo,String user_id , String course_id,String activity_course_id,String source_type) {
        orderService.saveOrder(orderNo, user_id, course_id, activity_course_id, source_type);
        return orderNo;
    }

    @GetMapping("updateOrder")
    public Integer updateOrder(String orderNo , Integer status) {
        System.out.println("订单编号 = " + orderNo);
        System.out.println("状态编码 = " + status);
        Integer integer = orderService.updateOrder(orderNo, status);
        System.out.println("订单更新 = " + integer);
        return integer;
    }

    @GetMapping("deleteOrder/{orderno}")
    public Integer deleteOrder(@PathVariable("orderno")String orderno ) {
        Integer integer = orderService.deleteOrder(orderno);
        return integer;
    }

    @GetMapping("getOKOrderCourseIds/{userid}")
    public List<Object> getOKOrderCourseIds(@PathVariable("userid")String userid ) {
        System.out.println("获取"+userid +"的购买的课程编号！");
        List<UserCourseOrder> list = orderService.getOKOrderCourseIds(userid);
        List<Object> list2 = new ArrayList<>();
        for(UserCourseOrder order : list){
            list2.add(order.getCourseId());
        }
        List<Object> collect = list.parallelStream().map(UserCourseOrder::getCourseId).collect(Collectors.toList());
        System.out.println("已购成功的课程id：" + list2);
        return list2;
    }

}
