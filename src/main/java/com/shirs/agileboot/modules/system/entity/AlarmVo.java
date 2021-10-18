package com.shirs.agileboot.modules.system.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlarmVo {

    private Integer alarmId;

    private String alarmName;

    private String alarmTime;

    private String alarmType;
}
