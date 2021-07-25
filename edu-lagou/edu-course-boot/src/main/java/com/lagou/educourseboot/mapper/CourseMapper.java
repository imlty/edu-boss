package com.lagou.educourseboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.educourseboot.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: edu-lagou
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-10-16 16:15
 * @Description:
 */
@Service
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 查询全部课程信息
     * @return
     */
    List<Course> getAllCourse();

    /**
     * 查询已登录用户购买的全部课程信息
     * @return
     */
    List<Course> getMyCoursesById(@Param("idList")List<String> idList);

    /**
     * 查询某门课程的详细信息
     * @param courseid 课程编号
     * @return
     */
    Course getCourseById(@Param("courseid") Integer courseid);
}
