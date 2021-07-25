package com.lagou.educommentboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.eduapi.entity.CourseCommentFavoriteRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: edu-lagou
 * @Author: GuoAn.Sun
 * @CreateTime: 2020-10-21 10:17
 * @Description:
 */
@Service
public interface CourseCommentFavoriteRecordDao extends BaseMapper<CourseCommentFavoriteRecord> {

    @Select({"select * from course_comment_favorite_record where comment_id = #{comment_id}"})
    List<CourseCommentFavoriteRecord> findByCommentid(Integer comment_id);
}
