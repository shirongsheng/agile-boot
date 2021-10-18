package com.shirs.agileboot.modules.study.thread.countdownlatch;

import com.alibaba.fastjson.JSONObject;

public class Comment {
    public JSONObject getComments() {
        JSONObject comments = new JSONObject();
        comments.put("coment1", "不错不错");
        comments.put("coment2", "666,下次还买");
        comments.put("coment3", "这就推荐给朋友");
        return comments;
    }
}
