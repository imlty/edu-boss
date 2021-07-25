package com.lagou.eduorderboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.eduorderboot.entity.UserCourseOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-07 17:26:30
 */
@Service
public interface OrderDao extends BaseMapper<UserCourseOrder> {
}