package com.lagou.educommentboot.controller;

import com.lagou.eduapi.entity.CourseComment;
import com.lagou.educommentboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 96217
 */
@RestController
@RequestMapping("course")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("comment/saveCourseComment")
    public Object saveCourseComment(Integer courseid,Integer userid,String username,String comment) throws UnsupportedEncodingException {
        username = new String( username.getBytes("ISO-8859-1"),"UTF-8" );
        comment = new String( comment.getBytes("ISO-8859-1"),"UTF-8" );
        System.out.println("昵称：" + username);
        CourseComment courseComment = new CourseComment();
        courseComment.setCourseId(courseid); // 课程编号
        courseComment.setSectionId(0); // 章节编号 （预留字段，为项目的2.0版本保留）
        courseComment.setLessonId(0);// 小节编号（预留字段，为项目的2.0版本保留）
        courseComment.setUserId(userid); // 用户编号
        courseComment.setUserName(username); // 用户昵称
        courseComment.setParentId(0); //没有父id（预留字段，为项目的2.0版本保留）
        courseComment.setComment(comment);// 留言内容
        courseComment.setType(0); // 0用户留言（预留字段，为项目的2.0版本保留）
        courseComment.setLastOperator(userid); //最后操作的用户编号
        Integer i = commentService.saveComment(courseComment);
        return i;
    }


    @GetMapping("comment/getCourseCommentList/{courseid}/{pageIndex}/{pageSize}")
    public List<CourseComment> getCommentsByCourseId(@PathVariable("courseid") Integer courseid,@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize){
        int offset = (pageIndex-1)*pageSize;
        List<CourseComment> list = commentService.getCommentsByCourseId(courseid, offset, pageSize);
        System.out.println("获取第"+courseid+"门课程的留言：共计"+list.size()+"条");
        return list;
    }

    @GetMapping("comment/saveFavorite/{commentid}/{userid}")
    public Integer saveFavorite(@PathVariable("commentid") Integer commentid,@PathVariable("userid") Integer userid){
        Integer integer = commentService.saveFavorite(commentid, userid);
        return integer;
    }

    @GetMapping("comment/cancelFavorite/{commentid}/{userid}")
    public Integer cancelFavorite(@PathVariable("commentid") Integer commentid,@PathVariable("userid") Integer userid){
        Integer integer = commentService.cancelFavorite(commentid, userid);
        return integer;
    }
}
