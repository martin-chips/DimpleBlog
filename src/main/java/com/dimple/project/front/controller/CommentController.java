package com.dimple.project.front.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.http.HttpUtils;
import com.dimple.framework.aspectj.lang.annotation.VLog;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.blog.domain.Comment;
import com.dimple.project.blog.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: CommentController
 * @description:
 * @author: Dimple
 * @date: 2019/12/15
 */
@RequestMapping("/f")
@RestController("frontCommentController")
public class CommentController extends BaseController {

    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * QQ 头像和昵称查询地址
     */
    private static final String QQ_QUERY_URL = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg";

    /**
     * 获取pageId的comment
     */
    @GetMapping("/comment/{id}")
    public AjaxResult commentBlog(@PathVariable Long id) {
        List<Comment> commentList = commentService.selectCommentListByPageId(id);
        return AjaxResult.success(commentList);
    }

    @PutMapping("/comment/good/{id}")
    @VLog(title = "点赞评论")
    public AjaxResult goodComment(@PathVariable Long id) {
        return toAjax(commentService.incrementCommentGood(id));
    }

    @PutMapping("/comment/bad/{id}")
    @VLog(title = "踩评论")
    public AjaxResult badComment(@PathVariable Long id) {
        return toAjax(commentService.incrementCommentBad(id));
    }

    /**
     * 新增评论
     */
    @PostMapping("comment")
    @VLog(title = "发表评论")
    public AjaxResult comment(@RequestBody Comment comment) {
        return toAjax(commentService.insertComment(comment));
    }

    /**
     * 根据QQ号获取信息
     */
    @GetMapping("comment/qqNum/{qqNum}")
    public AjaxResult getByQQNum(@PathVariable Long qqNum) {
        String json = HttpUtils.sendGet(QQ_QUERY_URL, "uins=" + qqNum, "GBK");
        Map<String, String> qqInfo = new HashMap<>();
        if (!StringUtils.isEmpty(json)) {
            json = json.replaceAll("portraitCallBack|\\\\s*|\\t|\\r|\\n", "");
            json = json.substring(1, json.length() - 1);
            JSONObject object = JSON.parseObject(json);
            JSONArray array = object.getJSONArray(String.valueOf(qqNum));
            qqInfo.put("avatar", "https://q1.qlogo.cn/g?b=qq&nk=" + qqNum + "&s=40");
            qqInfo.put("email", qqNum + "@qq.com");
            qqInfo.put("nickName", array.getString(6));
        }
        return AjaxResult.success(qqInfo);
    }

}
