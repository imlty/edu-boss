package com.lagou.eduorderboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lagou.eduorderboot.entity.UserCourseOrder;
import com.lagou.eduorderboot.mapper.OrderDao;
import com.lagou.eduorderboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void saveOrder(String orderNo, String user_id, String course_id, String activity_course_id, String source_type) {
        UserCourseOrder order = new UserCourseOrder();

        order.setOrderNo(orderNo);
        order.setUserId(user_id);
        order.setCourseId(course_id);
        order.setActivityCourseId( Integer.parseInt(activity_course_id));
        order.setSourceType(source_type);
        order.setIsDel(0);
        order.setStatus(0);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        orderDao.insert(order);
    }

    @Override
    public Integer updateOrder(String orderNo, int status) {
        UserCourseOrder order = new UserCourseOrder();
        order.setStatus(status);

        QueryWrapper<UserCourseOrder> qw = new QueryWrapper();
        qw.eq("order_no", orderNo);
        return orderDao.update(order,qw);
    }

    @Override
    public Integer deleteOrder(String orderNo) {
        QueryWrapper<UserCourseOrder> qw = new QueryWrapper();
        qw.eq("order_no", orderNo);
        // delete from user_course_order where order_no = orderNo
        return orderDao.delete(qw);
    }

    @Override
    public List<UserCourseOrder> getOKOrderCourseIds(String userId) {
        QueryWrapper<UserCourseOrder> qw = new QueryWrapper();
        qw.select("course_id"); // SELECT course_id FROM edu_order.user_course_order
        qw.eq("STATUS", 20); //WHERE STATUS = 20
        qw.eq("is_del", 0); //AND is_del = 0
        qw.eq("user_id", userId); //AND  user_id = #{userId
        return orderDao.selectList(qw);
    }

}
