package com.lagou.educourseboot.service.impl;

import com.lagou.educourseboot.entity.Course;
import com.lagou.educourseboot.mapper.CourseMapper;
import com.lagou.educourseboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: edu-lagou
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-10-16 16:16
 * @Description:
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public List<Course> getAllCourse() {
        //将redis内存中的序列化的集合名称用String重新命名（增加可读性）
        RedisSerializer<String> rs = new StringRedisSerializer();
        redisTemplate.setKeySerializer(rs);

        //1.去redis查询数据集合
        System.out.println("---------------------》》》查询redis");
        List<Course> list = (List<Course>)redisTemplate.opsForValue().get("allCourses");

        //2.如果redis中没有的话，去mysql数据集合
        if(list == null){
            synchronized (this){
                list = (List<Course>)redisTemplate.opsForValue().get("allCourses");
                if(list == null){
                    System.out.println("===查询mysql===");
                    list = courseMapper.getAllCourse();
                    //3.将数据集合放入redis，以便下次查询使用
                    redisTemplate.opsForValue().set("allCourses",list,10, TimeUnit.SECONDS);
                }
            }
        }
        return list;
    }

    @Override
    public List<Course> getMyCoursesById(List<String> idList) {
        return courseMapper.getMyCoursesById(idList);
    }

    @Override
    public Course getCourseById(Integer courseid) {
        return courseMapper.getCourseById(courseid);
    }

}
