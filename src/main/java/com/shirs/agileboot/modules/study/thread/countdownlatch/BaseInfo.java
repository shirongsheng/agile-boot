package com.shirs.agileboot.modules.study.thread.countdownlatch;

import com.alibaba.fastjson.JSONObject;

public class BaseInfo {
    public JSONObject getBaseInfo() {
        JSONObject baseInfo = new JSONObject();
        baseInfo.put("名称", "AOC显示屏");
        baseInfo.put("价格", "1299");
        baseInfo.put("库存", "286");
        return baseInfo;
    }
}
