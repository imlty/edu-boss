package com.lagou.educourseboot.service;



import com.lagou.educourseboot.entity.Course;

import java.util.List;

/**
 * @BelongsProject: edu-lagou
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-10-16 16:16
 * @Description:
 */
public interface CourseService {
    /**
     * 查询全部课程信息
     * @return
     */
    List<Course> getAllCourse();

    /**
     * 查询已登录用户购买的全部课程信息
     * @param idList 课程编号集合
     * @return
     */
    List<Course> getMyCoursesById(List<String> idList);

    /**
     * 查询某门课程的详细信息
     * @param courseid 课程编号
     * @return
     */
    Course getCourseById(Integer courseid);

}
