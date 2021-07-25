package com.lagou.educommentboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lagou.eduapi.entity.CourseComment;
import com.lagou.eduapi.entity.CourseCommentFavoriteRecord;
import com.lagou.educommentboot.mapper.CourseCommentDao;
import com.lagou.educommentboot.mapper.CourseCommentFavoriteRecordDao;
import com.lagou.educommentboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CourseCommentDao courseCommentDao;

    @Autowired
    private CourseCommentFavoriteRecordDao courseCommentFavoriteRecordDao;

    //保存留言
    @Override
    public Integer saveComment(CourseComment comment) {
        return courseCommentDao.insert(comment);
    }

    @Override
    public List<CourseComment> getCommentsByCourseId(Integer courseid, Integer offset, Integer pageSize) {
        return courseCommentDao.getCommentsByCourseId(courseid, offset, pageSize);
    }

    /**
     *点赞：
     * 先查看当前用户对这条留言是否点过赞，
     * 如果点过：修改is_del状态即可，取消赞
     * 如果没点过：保存一条点赞的信息
     *
     * 最终，更新赞的数量
     */
    @Override
    public Integer saveFavorite(Integer comment_id, Integer userid) {
        QueryWrapper<CourseCommentFavoriteRecord> qw = new QueryWrapper<>();
        qw.eq("comment_id", comment_id); // where comment_id = #{cid}
        qw.eq("user_id", userid); // and user_id = #{uid}
        Integer i = courseCommentFavoriteRecordDao.selectCount(qw);

        int i1 = 0;
        int i2 = 0;
        if(i == 0){ //没点过赞
            // 保存赞信息
            CourseCommentFavoriteRecord favoriteRecord = new CourseCommentFavoriteRecord();
            favoriteRecord.setCommentId(comment_id);
            favoriteRecord.setUserId(userid);
            favoriteRecord.setIsDel(0);
            favoriteRecord.setCreateTime(new Date());
            favoriteRecord.setUpdateTime(new Date());
            i1 = courseCommentFavoriteRecordDao.insert(favoriteRecord);
        }else{
            // 更新赞的状态
            CourseCommentFavoriteRecord favoriteRecord = new CourseCommentFavoriteRecord();
            favoriteRecord.setIsDel(0);

            QueryWrapper<CourseCommentFavoriteRecord> qw2 = new QueryWrapper<>();
            qw2.eq("comment_id", comment_id); // where comment_id = #{cid}
            qw2.eq("user_id", userid); // and user_id = #{uid}
            i1 = courseCommentFavoriteRecordDao.update(favoriteRecord,qw2);
        }
        // 更新本条留言赞的数量
        i2 = courseCommentDao.updateLikeCount(1,comment_id);

        if(i1 == 0 || i2 == 0){
            // 在项目中，使用抛异常的方式来回滚事务！
            throw  new RuntimeException("点赞失败！");
        }
        return comment_id;
    }

    /**
     * 删除点赞的信息（is_del = 1）
     * 更新留言赞的数量-1
     * @param comment_id 留言编号
     * @param userid 用户编号
     * @return 0:失败，1：成功
     */
    @Override
    public Integer cancelFavorite(Integer comment_id, Integer userid) {
        //Integer i1 = courseCommentDao.updateFavoriteStatus(1, comment_id, userid);
        CourseCommentFavoriteRecord favoriteRecord = new CourseCommentFavoriteRecord();
        favoriteRecord.setIsDel(1);

        QueryWrapper<CourseCommentFavoriteRecord> qw = new QueryWrapper<>();
        qw.eq("comment_id", comment_id); // where comment_id = #{cid}
        qw.eq("user_id", userid); // and user_id = #{uid}

        Integer i1 = courseCommentFavoriteRecordDao.update(favoriteRecord,qw);

        Integer i2 = courseCommentDao.updateLikeCount(-1,comment_id);

        if(i1 == 0 || i2 == 0){
            throw  new RuntimeException("取消赞失败！");
        }
        return i2;
    }
}
