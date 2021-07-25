package com.lagou.educommentboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.eduapi.entity.CourseComment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-07 17:26:30
 */
@Service
public interface CourseCommentDao extends BaseMapper<CourseComment> {

    /**
     * 某个课程的全部留言（分页）
     *
     * @param courseid 课程编号
     * @param offset   数据偏移
     * @param pageSize 每页条目数
     * @return 留言集合
     */
    @Select({"select\n" +
            "        cc.id cc_id,`course_id`,`section_id`,`lesson_id`,cc.user_id cc_user_id,`user_name`,`parent_id`,`is_top`,`comment`,`like_count`,`is_reply`,`type`,`status`,cc.create_time cc_create_time ,cc.update_time cc_update_time ,cc.is_del cc_is_del,`last_operator`,`is_notify`,`mark_belong`,`replied` ,\n" +
            "        ccfr.id ccfr_id,ccfr.user_id ccfr_user_id,comment_id,ccfr.is_del ccfr_is_del,ccfr.create_time ccfr_create_time,ccfr.update_time ccfr_update_time\n" +
            "        from course_comment cc left join (select * from course_comment_favorite_record where is_del = 0) ccfr\n" +
            "        on cc.id = ccfr.comment_id\n" +
            "        where cc.is_del = 0\n" +
            "        and course_id = #{courseid}\n" +
            "        order by is_top desc , like_count desc , cc.create_time desc\n" +
            "        limit #{offset}, #{pageSize}"})
    @Results({
            @Result(column = "cc_id", property = "id"),
            @Result(column = "cc_user_id", property = "userId"),
            @Result(column = "cc_create_time", property = "createTime"),
            @Result(column = "cc_update_time", property = "updateTime"),
            @Result(column = "cc_is_del", property = "isDel"),
            // 一对多
            @Result(column = "comment_id",
                    property = "favoriteRecords",
                    many = @Many(select = "com.lagou.educommentboot.mapper.CourseCommentFavoriteRecordDao.findByCommentid"))
    })
    List<CourseComment> getCommentsByCourseId(@Param("courseid") Integer courseid, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);


    @Update({"update course_comment set like_count = like_count + #{x} where id = #{comment_id}"})
    Integer updateLikeCount(@Param("x") Integer x, @Param("comment_id") Integer comment_id);
}
